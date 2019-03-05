<%-- 
    Document   : customerorderlist
    Created on : 05-03-2019, 16:43:46
    Author     : William Sehested Huusfeldt
--%>

<%@page import="logic.model.User"%>
<%@page import="logic.model.Order"%>
<%@page import="logic.OrderController"%>
<%@page import="java.util.List"%>

<%
    OrderController oc = new OrderController();
    User us = (User) session.getAttribute("user");
    List<Order> orders = oc.getOrderByUser(us.getUsername());
%>

<table class="table">
    <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Username</th>
            <th scope="col">Date</th>
            <th scope="col"></th>
        </tr>
    </thead>
    <tbody>
        <%
            for (Order o : orders) {%>
        <tr>
            <th scope="row"><%= o.getOrderId() %></th>
            <td><%= o.getUsername() %></td>
            <td><%= o.getOrderDate() %></td>
            <td class="tableButton"><a href="${pageContext.request.contextPath}/order?orderId=<%= o.getOrderId() %>"><button class="btn btn-info">Show Order</button></a></td>
        </tr>
        <% }%>
    </tbody>
</table>
