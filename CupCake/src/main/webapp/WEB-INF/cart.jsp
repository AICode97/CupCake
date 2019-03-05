<%-- 
    Document   : cart
    Created on : 05-03-2019, 16:34:06
    Author     : Martin Frederiksen
--%>

<%@page import="logic.model.CupcakePart"%>
<%@page import="logic.CupcakeController"%>
<%@page import="logic.model.ShoppingCart"%>
<%@page import="logic.model.Order"%>
<%@page import="logic.model.LineItem"%>
<%@page import="logic.model.LineItem"%>
<%@include file = "../header.jsp" %>

<%
    ShoppingCart sc = new ShoppingCart();
    User user = null;
    if (session.getAttribute("user") != null) {
        user = (User) session.getAttribute("user");
    }
    if (session.getAttribute("ShoppingCart") != null) {
        sc = (ShoppingCart) session.getAttribute("ShoppingCart");
    }
%>
<table class="table">
    <thead class="thead-light">
        <tr>
            <th scope="col">Cupcake Bottom</th>
            <th scope="col">Cupcake Topping</th>
            <th scope="col">Quantity</th>
            <th scope="col">Price</th>
        </tr>
    </thead>
    <tbody>
        <% for (LineItem li : sc.getLineItems()) {%>
        <tr>
            <td scope="row"><%= li.getBottom().getName()%></td>
            <td><%= li.getTop().getName()%></td>
            <td><%= li.getQuantity()%></td>
            <td><%= String.valueOf(li.getPrice())%>,-</td>
        </tr>
        <% }%>
        <tr class="table-active">
            <th>Total</th>
            <td></td>
            <td></td>
            <th><%= String.valueOf(sc.calculate())%>,-</th>
        </tr>
    </tbody>
</table>


<form id="balanceForm" method="POST">
    <div class="form-group">
        <label>Balance</label>
        <input type="text" class="form-control" name="balance" placeholder="Add to balance" maxlength="25">
    </div>
    <button type="submit" class="btn btn-primary" formaction="${pageContext.request.contextPath}/CommandController?command=addBalance">add balance</button>
</form>
<form id="CheckoutForm" method="POST">
    <div class="form-group">
        <button type="submit" class="btn btn-primary" formaction="${pageContext.request.contextPath}/CommandController?command=checkout">Checkout</button>
</form>
<%@include file = "../footer.jsp" %>