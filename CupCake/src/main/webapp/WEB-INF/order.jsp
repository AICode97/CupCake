<%-- 
    Document   : order
    Created on : Mar 5, 2019, 2:38:37 PM
    Author     : Andreas Vikke
--%>

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
            <tr>
                <th scope="row">Test</th>
                <td>Test</td>
                <td>3</td>
                <td>60,-</td>
            </tr>
        </tbody>
    </table>
</div>

<%@include file = "../footer.jsp" %>