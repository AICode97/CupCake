<%-- 
    Document   : admin
    Created on : Mar 4, 2019, 11:00:11 AM
    Author     : Andreas Vikke
--%>

<%@include file = "../header.jsp" %>

<div class="contentBox">
    <div class="contentBoxContent">
        <h1>Admin Page</h1>
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/admin"><i class="far fa-user"></i> Users</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/admin?origin=orders"><i class="far fa-list-alt"></i> Orders</a>
            </li>
        </ul>
        <%@include file = "changepassword.jsp" %>
    </div>
</div>
<div class="contentBox">
    <div class="contentBoxContent">
        <%
            String origin = request.getParameter("origin");
            if(origin != null && origin.equals("orders")) { %>
                <%@include file = "lists/orderlist.jsp" %>
            <% } else { %>
                <%@include file = "lists/userlist.jsp" %>
            <% } %>
    </div>
</div>

<%@include file = "../footer.jsp" %>