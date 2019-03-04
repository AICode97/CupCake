<%-- 
    Document   : customer
    Created on : Mar 4, 2019, 10:47:13 AM
    Author     : Andreas Vikke
--%>

<%@page import="logic.model.User"%>
<%@include file = "../header.jsp" %>

<h1>You are logged in as: <% out.println(u.getUsername()); %></h1>

<%@include file = "../footer.jsp" %>

