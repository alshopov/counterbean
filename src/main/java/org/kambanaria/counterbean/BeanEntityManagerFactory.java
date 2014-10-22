package org.kambanaria.counterbean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BeanEntityManagerFactory implements ServletContextListener {

    private static final String PERSISTENCE_UNIT_NAME = "counterbean";
    private static EntityManagerFactory emf;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        emf.close();
    }

    public static EntityManager createEntityManager() {
        return emf.createEntityManager();
    }
}
