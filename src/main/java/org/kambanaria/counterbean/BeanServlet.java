package org.kambanaria.counterbean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.kambanaria.counterbean.BeanEntityManagerFactory.createEntityManager;

@WebServlet(name = "BeanServlet", urlPatterns = {"/"})
public class BeanServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse rsp) throws ServletException, IOException {
        EntityManager em = createEntityManager();
        List beans = em.createNamedQuery(Bean.Queries.ALL, Bean.class).getResultList();
        em.close();
        req.setAttribute("beanList", beans);
        req.getRequestDispatcher("beans.jsp").forward(req, rsp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse rsp) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        String action = req.getParameter("action");
        // dispatcher
        if (null == action) {
            errors.add("Missing action");
        } else if ("create".equals(action)) {
            // create logic
            String name = req.getParameter("name-new");
            if (null == name) {
                errors.add("Missing name");
            }
            String cnt = req.getParameter("count-new");
            if (null == cnt) {
                errors.add("Missing count");
            }
            Long count = null;
            try {
                count = new Long(cnt);
            } catch (NumberFormatException e) {
                errors.add("Wrong count");
            }
            if (null != name && null != count) {
                EntityManager em = createEntityManager();
                try {
                    em.getTransaction().begin();
                } catch (IllegalStateException e) {
                    errors.add(e.getMessage());
                }
                //persist the bean
                Bean bean = new Bean();
                bean.setName(name);
                bean.setCount(count);
                em.persist(bean);
                //commit transaction which will trigger the em to 
                //commit newly created entity into database
                try {
                    em.getTransaction().commit();
                } catch (IllegalStateException | RollbackException e) {
                    errors.add(e.getMessage());
                }
                em.close();
            }

        } else if (action.startsWith("update")) {
            // update logic
            String idSfx = action.substring("update".length());
            Long id = null;
            try {
                id = Long.parseLong(idSfx.substring(1));
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                errors.add("Wrong id");
            }
            String name = req.getParameter("name" + idSfx);
            if (null == name) {
                errors.add("Missing name");
            }
            String cnt = req.getParameter("count" + idSfx);
            if (null == cnt) {
                errors.add("Missing count");
            }
            Long count = null;
            try {
                count = new Long(cnt);
            } catch (NumberFormatException e) {
                errors.add("Wrong count");
            }
            if (null != id && null != name && null != count) {
                EntityManager em = createEntityManager();
                try {
                    em.getTransaction().begin();
                } catch (IllegalStateException e) {
                    errors.add(e.getMessage());
                }
                //persist the bean
                Bean bean = em.find(Bean.class, id);
                bean.setName(name);
                bean.setCount(count);
                em.persist(bean);
                //commit transaction which will trigger the em to 
                //commit newly created entity into database
                try {
                    em.getTransaction().commit();
                } catch (IllegalStateException | RollbackException e) {
                    errors.add(e.getMessage());
                }
                em.close();
            }
        } else if (action.startsWith(
                "delete")) {
            // delete logic
            String idSfx = action.substring("delete".length());
            Long id = null;
            try {
                id = Long.parseLong(idSfx.substring(1));
            } catch (NumberFormatException e) {
                errors.add("Wrong id");
            }
            if (null != id) {
                EntityManager em = createEntityManager();
                try {
                    em.getTransaction().begin();
                } catch (IllegalStateException e) {
                    errors.add(e.getMessage());
                }
                //persist the bean
                Bean bean = em.find(Bean.class, id);

                em.remove(bean);
                //commit transaction which will trigger the em to 
                //commit newly created entity into database
                try {
                    em.getTransaction().commit();
                } catch (IllegalStateException | RollbackException e) {
                    errors.add(e.getMessage());
                }
                em.close();
            }
        } else {
            // fall back case
            errors.add("Unknown action");
        }

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
        }
        EntityManager em = createEntityManager();
        List beans = em.createNamedQuery(Bean.Queries.ALL, Bean.class).getResultList();
        em.close();
        req.setAttribute(
                "beanList", beans);
        req.getRequestDispatcher(
                "beans.jsp").forward(req, rsp);
    }

}
