<%-- 
    Document   : admin
    Created on : Mar 4, 2019, 11:00:11 AM
    Author     : Andreas Vikke
--%>

<%@page import="java.util.List"%>
<%@page import="logic.UserController"%>
<%@page import="logic.model.User"%>
<%@include file = "../header.jsp" %>
<%
    UserController uc = new UserController();
    List<User> users = uc.getUsers();
%>

<div class="contentBox">
    <div class="contentBoxContent">
        <h1>Admin Page</h1>
        <p>Logged in as: <% out.println(u.getUsername());%></p>
        <%@include file = "changepassword.jsp" %>
    </div>
</div>
<div class="contentBox">
    <div class="contentBoxContent">
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
                    <% for(User user : users) { %>
                        <tr>
                                <th scope="row">1</th>
                                <td><%= user.getUsername() %></td>
                                <td><%= user.getEmail()%></td>
                                <td class="tableButton"><button class="btn btn-info">Show Order</button></td>
                        </tr>
                    <% } %>
            </tbody>
        </table>
    </div>
</div>

<%@include file = "../footer.jsp" %>