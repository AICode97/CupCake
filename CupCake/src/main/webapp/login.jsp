<%-- 
    Document   : login
    Created on : Mar 4, 2019, 9:58:26 AM
    Author     : Andreas Vikke
--%>

<%@include file = "header.jsp" %>

<div class="greyBox">
    <form method="POST">
        <div class="form-group">
            <label>Username</label>
            <input type="text" class="form-control" name="username" placeholder="Username..." maxlength="25">
        </div>
        <div class="form-group">
            <label>Password</label>
            <input type="password" class="form-control" name="password" placeholder="Password..."maxlength="25">
        </div>
        <button type="submit" class="btn btn-primary" formaction="${pageContext.request.contextPath}/CommandController?command=login">Login</button>
    </form>
</div>
<image id="ccimg" src="Images/CupCakeIMG.jpg" width="30%" height="30%"/>

<%@include file = "footer.jsp" %>