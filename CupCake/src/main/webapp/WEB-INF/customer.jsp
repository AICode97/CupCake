<%-- 
    Document   : customer
    Created on : Mar 4, 2019, 10:47:13 AM
    Author     : Andreas Vikke
--%>

<%@page import="logic.model.User"%>
<%@include file = "../header.jsp" %>
<h2>You are logged in as: <% out.println(u.getUsername());%></h2>
<div class="customerBox">
    <div class="customerBoxContent">
        <%@include file = "changepassword.jsp" %>
    </div>
</div>
<div class="customerBox">
    <div class="customerBoxContent">
    </div>
</div>


<%@include file = "../footer.jsp" %>

