<%-- 
    Document   : shop.jsp
    Created on : Mar 5, 2019, 1:17:13 PM
    Author     : Andreas Vikke
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="data.mappers.CupcakeMapper"%>
<%@page import="logic.model.enums.CupcakePartEnum"%>
<%@include file = "../header.jsp" %>

<%CupcakeMapper ccm = new CupcakeMapper();%>
<%--<%CupcakePartEnum top = new CupcakePartEnum();%>--%>

<h1>Shop</h1>

<h3>Select a top and a bottom for your amazing CupCake</h3>
<form id="shopForm" method="POST">
    <fieldset>
        <legend>Cupcake Tops</legend>
        <p>
            <label>You have selected: </label>
            <select class="ccTopList" name="topOption">
                <%-- <c:forEach items="${cupcakeTops}"  var="topid">
                    <tr>
                        <td>${topOption.} </td>
                    </tr>
                </c:forEach>
                --%>
                <%--
                <option value=<%= ccm.getCupcakePartById(CupcakePartEnum.TOP, 1)%>>første</option>
                <option value=<%= ccm.getCupcakePartById(CupcakePartEnum.TOP, 2)%>>anden</option>
                <option value=<%= ccm.getCupcakePartById(CupcakePartEnum.TOP, 3)%>>tredje</option>
                <option value=<%= ccm.getCupcakePartById(CupcakePartEnum.TOP, 4)%>>Fjerde</option>
                <option value=<%= ccm.getCupcakePartById(CupcakePartEnum.TOP, 5)%>>Femte</option>
                <option value=<%= ccm.getCupcakePartById(CupcakePartEnum.TOP, 6)%>>sjete</option>
                <option value=<%= ccm.getCupcakePartById(CupcakePartEnum.TOP, 7)%>>syvende</option>
                <option value=<%= ccm.getCupcakePartById(CupcakePartEnum.TOP, 8)%>>ottende</option>
                <option value=<%= ccm.getCupcakePartById(CupcakePartEnum.TOP, 9)%>>niende</option>
                --%>
            </select>
        </p>
    </fieldset>
    <fieldset>
        <legend>Cupcake Bottoms</legend>
        <p>
            <label>You have selected: </label>
            <select class="ccTopList" name="bottomOption">
                <option value=<%= ccm.getCupcakePartById(CupcakePartEnum.BOTTOM, 1)%>>første</option>
                <option value=<%= ccm.getCupcakePartById(CupcakePartEnum.BOTTOM, 2)%>>anden</option>
                <option value=<%= ccm.getCupcakePartById(CupcakePartEnum.BOTTOM, 3)%>>tredje</option>
                <option value=<%= ccm.getCupcakePartById(CupcakePartEnum.BOTTOM, 4)%>>Fjerde</option>
                <option value=<%= ccm.getCupcakePartById(CupcakePartEnum.BOTTOM, 5)%>>Femte</option>
            </select>
        </p>
    </fieldset>
            <input type="number" name="quantity" placeholder="Quantity">
            <button type="submit" class="btn btn-primary" 
                    formaction="${pageContext.request.contextPath}/CommandController?command=products">submit order</button>
</form>
            

<%@include file = "../footer.jsp" %>
