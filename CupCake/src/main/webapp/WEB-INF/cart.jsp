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
    if (session.getAttribute("ShoppingCart") != null) {
        sc = (ShoppingCart) session.getAttribute("ShoppingCart");
    }
%>

<div class="orderBox">
    <table class="table">
        <thead class="thead-light">
            <tr>
                <th scope="col">Cupcake Bottom</th>
                <th scope="col">Cupcake Topping</th>
                <th scope="col">Quantity</th>
                <th scope="col">Price</th>
                <th scope="col">Select</th>
            </tr>
        </thead>
        <tbody>
            <% for (LineItem li : sc.getLineItems()) {%>
            <tr>
                <td scope="row"><%= li.getBottom().getName()%></td>
                <td><%= li.getTop().getName()%></td>
                <td><%= li.getQuantity()%></td>
                <td><%= String.valueOf(li.getPrice())%>,-</td>
                <td id="deleteCol">
                    <form id="removeItem" method="POST"> 
                        <div class="form-group">
                            <input type="number" class="form-control" id="delete" name="qty" placeholder="Quantity" value="1">
                        </div>
                        <button type="submit" class="btn btn-primary" id="deleteButton"
                                formaction="CommandController?command=deleteItem&top=<%=li.getTop().getId()%>&bottom=<%=li.getBottom().getId()%>">Delete
                        </button>
                    </form>
                </td>
            </tr>
            <% }%>
            <tr class="table-active">
                <th>Total</th>
                <td></td>
                <td></td>
                <th><span id="totalPrice"><%= String.valueOf(sc.calculate())%></span><span>,-</span</th>
                <td></td>
            </tr>
        </tbody>
    </table>
</div>

<div class="balanceCheckoutBox">
    <div class="balanceBox">
        <form id="balanceForm" method="POST">
            <div class="form-group">
                <label>Balance</label>
                <input type="text" class="form-control" name="balance" placeholder="Add to balance" maxlength="25">
            </div>
            <button type="submit" class="btn btn-primary" formaction="CommandController?command=addBalance">Add Balance</button>
        </form>
    </div>
    <div class="checkoutBox">
        <form id="CheckoutForm" method="POST">
            <div class="alert alert-warning" role="alert" id="balanceError">Error: Total price higher than balance</div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary" formaction="CommandController?command=checkout">Checkout</button>
        </form>
    </div>
</div>
<%@include file = "../footer.jsp" %>
