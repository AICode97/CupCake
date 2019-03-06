<%-- 
    Document   : shop.jsp
    Created on : Mar 5, 2019, 1:17:13 PM
    Author     : Andreas Vikke
--%>


<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %>
<%@page import="logic.CupcakeController"%>
<%@page import="data.mappers.CupcakeMapper"%>
<%@page import="logic.model.enums.CupcakePartEnum"%>
<%@page import="logic.model.CupcakePart"%>
<%@include file = "../header.jsp" %>


<%CupcakeController ccc = new CupcakeController();%>
<%List<CupcakePart> asd = ccc.getCupcakeParts();%>

<h1>Shop</h1>

<h3>Select a top and a bottom for your amazing CupCake</h3>
<form id="shopForm" method="POST">
    <fieldset>
        <legend>Cupcake Tops</legend>
        <p>
            <label>You have selected: </label>
            <select class="ccListSelection" name="top">
                <% for (CupcakePart a : asd) { %>
                <%if (a.getPart() == CupcakePartEnum.TOP){%>
                <option value=<%= a.getId() %>><%=a.getName()%></option>
                <% }} %>
            </select>
        </p>
    </fieldset>
    <fieldset>
        <legend>Cupcake Bottoms</legend>
        <p>
            <label>You have selected: </label>
            <select class="ccListSelection" name="bottom">
                <% for (CupcakePart a : asd) { %>
                <%if (a.getPart() == CupcakePartEnum.BOTTOM) {%>
                <option value=<%= a.getId() %>><%=a.getName()%></option>
                <% }}%>
            </select>
        </p>
    </fieldset>
    <input type="number" name="qty" placeholder="Quantity">
    <button type="submit" class="btn btn-primary" 
            formaction="${pageContext.request.contextPath}/CommandController?command=products">submit order</button>
</form>


<%@include file = "../footer.jsp" %>
