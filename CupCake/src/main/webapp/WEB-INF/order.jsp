<%-- 
    Document   : order
    Created on : Mar 5, 2019, 2:38:37 PM
    Author     : Andreas Vikke
--%>

<%@page import="logic.model.LineItem"%>
<%@page import="logic.model.Order"%>
<%@page import="logic.OrderController"%>
<%@include file = "../header.jsp" %>

<%
    Order order = (Order) session.getAttribute("order");
%>

<div class="orderBox">
    <table class="table">
        <thead class="thead-dark">
            <tr>
                <th scope="col"># Order Id</th>
                <th scope="col">Username</th>
                <th scope="col">Date</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th scope="row"><%= order.getOrderId()%></th>
                <td><%= order.getUsername()%></td>
                <td><%= order.getOrderDate()%></td>
            </tr>
        </tbody>
    </table>

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
</div>

<%@include file = "../footer.jsp" %>