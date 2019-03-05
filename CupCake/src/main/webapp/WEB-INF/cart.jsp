<%-- 
    Document   : cart
    Created on : 05-03-2019, 16:34:06
    Author     : Martin Frederiksen
--%>

<%@page import="logic.model.ShoppingCart"%>
<%@page import="logic.model.Order"%>
<%@page import="logic.model.LineItem"%>
<%@page import="logic.model.LineItem"%>
<%@include file = "../header.jsp" %>

<%
    ShoppingCart sc = (ShoppingCart) session.getAttribute("ShoppingCart");
%>
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
            <% for(LineItem li : sc.getLineItems()) { %>
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
                <th><%= String.valueOf(sc.calculate()) %>,-</th>
            </tr>
        </tbody>
    </table>
<%@include file = "../footer.jsp" %>