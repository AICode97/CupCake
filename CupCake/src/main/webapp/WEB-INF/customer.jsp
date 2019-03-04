<%-- 
    Document   : customer
    Created on : Mar 4, 2019, 10:47:13 AM
    Author     : Andreas Vikke
--%>

<%@page import="logic.model.User"%>
<%@include file = "../header.jsp" %>

<div class="contentBox">
    <div class="contentBoxContent">
        <h1>Customer Page</h1>
        <p>Logged in as: <% out.println(u.getUsername());%></p>
        <%@include file = "changepassword.jsp" %>
    </div>
</div>
<div class="contentBox">
    <div class="contentBoxContent">
    </div>
</div>


<%@include file = "../footer.jsp" %>

