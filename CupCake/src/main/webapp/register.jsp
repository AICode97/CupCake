<%-- 
    Document   : login
    Created on : Mar 4, 2019, 9:58:26 AM
    Author     : Andreas Vikke
--%>

<%@include file = "header.jsp" %>
<link rel="stylesheet" type="text/css" href="style.css">
<div class="RegisterBox">
<form action="${pageContext.request.contextPath}/CommandController">
    <input type="hidden" name="command" value="register">
    <div class="form-group">
        <label>Email</label>
        <input type="email" class="form-control" name="email" placeholder="Email...">
    </div>
    <div class="form-group">
        <label>Username</label>
        <input type="text" class="form-control" name="username" placeholder="Username...">
    </div>
    <div class="form-group">
        <label>Password</label>
        <input type="password" class="form-control" name="password" placeholder="Password...">
    </div>
    <button type="submit" class="btn btn-primary">Register</button>
</form>
</div>

<%@include file = "footer.jsp" %>
