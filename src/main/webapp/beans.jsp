<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <title>CounterBean - count beans, kogs, minions</title>
    </head>
    <body>
        <h1>CounterBean - count beans, kogs, minions</h1>
        <c:forEach var="e" items="${errors}">
            <div class="alert alert-danger" role="alert">${e}</div>
        </c:forEach>
        <form role="form" action="." method="post">
            <table class="table table-striped table-bordered table-hover">
                <thead>
                    <tr>
                        <th class="col-md-1">№</th>
                        <th class="col-md-8">Name</th>
                        <th class="col-md-1">Count</th>
                        <th class="col-md-1">Update</th>
                        <th class="col-md-1">Delete</th>     
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="b" items="${beanList}">
                        <tr>
                            <td class="form-group">
                                <label class="sr-only" for="id-${b.id}">Id of bean/kog/minion</label>
                                <input type="text" class="form-control" id="id-${b.id}" name="id-${b.id}" placeholder="Id of bean/kog/minion" value="${b.id}" readonly>
                            </td>
                            <td class="form-group">
                                <label class="sr-only" for="name-${b.id}">Name of bean/kog/minion</label>
                                <input type="text" class="form-control" id="name-${b.id}" name ="name-${b.id}" placeholder="Name of bean/kog/minion" value="${b.name}">
                            </td>
                            <td class="form-group">
                                <label class="sr-only" for="count-${b.id}">Count</label>
                                <input type="number" class="form-control" id="count-${b.id}" name="count-${b.id}" placeholder="Count" value="${b.count}">
                            </td>
                            <td><button type="submit" class="btn btn-success" name="action" value="update-${b.id}">Update</button></td>
                            <td><button type="submit" class="btn btn-danger" name="action" value="delete-${b.id}">Delete</button></td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td class="form-group"> 
                            <label class="sr-only" for="id-new">Id of bean/kog/minion</label>
                            <input type="text" class="form-control" id="id-new" placeholder="#" readonly></td>
                        <td class="form-group">
                            <label class="sr-only" for="name-new">Name of bean/kog/minion</label>
                            <input type="text" class="form-control" id="name-new" name="name-new" placeholder="Name of new bean/kog/minion"></td>
                        <td class="form-group">
                            <label class="sr-only" for="count-new">Count</label>
                            <input type="number" class="form-control" id="count-new" name="count-new" placeholder="Count"></td>
                        <td class="form-group"><button type="submit" class="btn btn-primary" name="action" value="create">Create</button></td>
                        <td>&nbsp;</td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
