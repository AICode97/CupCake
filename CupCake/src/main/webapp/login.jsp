<%-- 
    Document   : login
    Created on : Mar 4, 2019, 9:58:26 AM
    Author     : Andreas Vikke
--%>

<%@include file = "header.jsp" %>

<form action="${pageContext.request.contextPath}/CommandController">
    <input type="hidden" name="command" value="login">
    <div class="form-group">
        <label>Username</label>
        <input type="text" class="form-control" name="username" placeholder="Username...">
      </div>
    <div class="form-group">
        <label>Password</label>
        <input type="password" class="form-control" name="password" placeholder="Password...">
      </div>
    <button type="submit" class="btn btn-primary">Login</button>
</form>

<%@include file = "footer.jsp" %>
