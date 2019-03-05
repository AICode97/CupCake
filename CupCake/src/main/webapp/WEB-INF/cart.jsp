<%-- 
    Document   : cart
    Created on : 05-03-2019, 16:34:06
    Author     : Martin Frederiksen
--%>

<%@page import="logic.model.Order"%>
<%@page import="logic.model.LineItem"%>
<%@page import="logic.model.LineItem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    Order order = (Order) session.getAttribute("order");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <table class="table">
        <thead class="thead-light">
                <tr>
                    <th scope="col">Cupcake Bottom</th>
                    <th scope="col">Cupcake Topping</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Price</th>
                </tr>
        </thead>
        <tbody>
            <% for(LineItem li : order.getLineItems()) { %>
                <tr>
                    <td scope="row"><%= li.getBottom().getName() %></td>
                    <td><%= li.getTop().getName() %></td>
                    <td><%= li.getQuantity() %></td>
                    <td><%= String.valueOf(li.getPrice()) %>,-</td>
                </tr>
            <% } %>
            <tr class="table-active">
                <th>Total</th>
                <td></td>
                <td></td>
                <th><%= String.valueOf(order.getTotalPrice()) %>,-</th>
            </tr>
        </tbody>
    </table>
    </body>
</html>
