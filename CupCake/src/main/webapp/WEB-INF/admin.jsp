<%-- 
    Document   : admin
    Created on : Mar 4, 2019, 11:00:11 AM
    Author     : Andreas Vikke
--%>

<%@page import="logic.model.User"%>
<%@include file = "../header.jsp" %>

<%
        HttpSession customerSession = request.getSession();
        User u = (User)customerSession.getAttribute("user");
    %>

<h1>You are logged in as admin: <% out.println(u.getUsername()); %></h1>

<%@include file = "../footer.jsp" %>