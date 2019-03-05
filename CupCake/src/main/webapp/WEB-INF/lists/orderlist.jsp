<%-- 
    Document   : orderlist
    Created on : Mar 5, 2019, 12:15:41 PM
    Author     : Andreas Vikke
--%>

<%@page import="java.util.List"%>
<%@page import="logic.UserController"%>
<%@page import="logic.model.User"%>

<%
    UserController uc = new UserController();
    List<User> users = uc.getUsers();
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
            int count = 1;
            for (User user : users) {%>
        <tr>
            <th scope="row"><%= count++ %></th>
            <td><%= user.getUsername()%></td>
            <td><%= user.getEmail()%></td>
            <td class="tableButton"><button class="btn btn-info">Show Order</button></td>
        </tr>
        <% }%>
    </tbody>
</table>

