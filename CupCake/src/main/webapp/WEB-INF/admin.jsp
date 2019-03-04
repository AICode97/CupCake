<%-- 
    Document   : admin
    Created on : Mar 4, 2019, 11:00:11 AM
    Author     : Andreas Vikke
--%>

<%@page import="logic.model.User"%>
<%@include file = "../header.jsp" %>



<div class="adminBox">
    <div class="adminBoxContent">
        <h1>You are logged in as admin: <% out.println(u.getUsername()); %></h1>
    </div>
</div>
<div class="adminBox">
    <div class="adminBoxContent">
        <h1>You are logged in as admin: <% out.println(u.getUsername());%></h1>
    </div>
</div>

<%@include file = "../footer.jsp" %>