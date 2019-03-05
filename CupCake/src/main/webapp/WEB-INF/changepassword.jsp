<%-- 
    Document   : ChangePassword
    Created on : 04-03-2019, 13:04:12
    Author     : William Sehested Huusfeldt
--%>

<h2>Change your password:</h2>

<form id="changePasswordForm" method="POST">
    <div class="form-group">
        <label>Current password</label>
        <input type="password" class="form-control" name="currentPassword" placeholder="Current password...">
    </div>
    <div class="form-group">
        <label>New password</label>
        <input type="password" class="form-control" name="newPassword" placeholder="New password...">
    </div>
    <div class="form-group">
        <label>New password</label>
        <input type="password" class="form-control" name="newPassword2" placeholder="Type your new password again...">
    </div>
    <button type="submit" class="btn btn-primary" formaction="${pageContext.request.contextPath}/CommandController?command=changePassword">Change password</button>
</form>

