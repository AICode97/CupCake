<%-- 
    Document   : admin
    Created on : Mar 4, 2019, 11:00:11 AM
    Author     : Andreas Vikke
--%>

<%@page import="logic.model.User"%>
<%@include file = "../header.jsp" %>



<div class="adminBox">
    <div class="adminBoxContent">
        <h1>Admin Page</h1>
        <p>Logged in as: <% out.println(u.getUsername()); %></p>
    </div>
</div>
<div class="adminBox">
    <div class="adminBoxContent">
    </div>
</div>

<%@include file = "../footer.jsp" %>