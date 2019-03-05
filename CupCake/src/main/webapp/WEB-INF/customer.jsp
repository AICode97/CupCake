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
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/customer"><i class="far fa-user"></i> Account Information</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/customer?origin=orders"><i class="far fa-list-alt"></i> Orders</a>
            </li>
        </ul>
    </div>
</div>
<div class="contentBox">
    <div class="contentBoxContent">
        <%
            String origin = request.getParameter("origin");
            if(origin == null) { %>
                <h1>Username: <% out.println(u.getUsername()); %></h1><br />
                <%@include file = "changepassword.jsp" %>
            <% } else if(origin.equals("orders")) { %>
                <%@include file = "lists/customerorderlist.jsp" %>
           <% } %>
    </div>
</div>


<%@include file = "../footer.jsp" %>

