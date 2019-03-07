<%-- 
    Document   : shop.jsp
    Created on : Mar 5, 2019, 1:17:13 PM
    Author     : Andreas Vikke
--%>


<%@page import="data.DataSourceMySql"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %>
<%@page import="logic.CupcakeController"%>
<%@page import="data.mappers.CupcakeMapper"%>
<%@page import="logic.model.enums.CupcakePartEnum"%>
<%@page import="logic.model.CupcakePart"%>
<%@include file = "../header.jsp" %>


<%CupcakeController ccc = new CupcakeController(new DataSourceMySql().getDataSource());
ccc.setDataSource(new DataSourceMySql().getDataSource());
List<CupcakePart> asd = ccc.getCupcakeParts();%>

<div class="shopper">
    <form id="shopForm" method="POST">
        <div class="form-group col-md-4">
            <label>Cupcake Tops:</label>
            <select class="form-control shop" name="top">
                <% for (CupcakePart a : asd) { %>
                <%if (a.getPart() == CupcakePartEnum.TOP) {%>
                <option value=<%= a.getId()%>><%=a.getName()%></option>
                <% }
                    } %>
            </select>
        </div>
        <div class="form-group col-md-4">
            <label>Cupcake Bottoms:</label>
            <select class="form-control shop" name="bottom">
                <% for (CupcakePart a : asd) { %>
                <%if (a.getPart() == CupcakePartEnum.BOTTOM) {%>
                <option value=<%= a.getId()%>><%=a.getName()%></option>
                <% }
                    }%>
            </select>
        </div>
        <div class="form-group col-md-4">
            <label>Quantity</label>
            <input type="number" class="form-control-shop" name="qty" placeholder="Quantity" value="1">
        </div><br />
        <button type="submit" class="btn btn-primary" formaction="CommandController?command=addProduct">Submit Order</button>
    </form>
</div>


<%@include file = "../footer.jsp" %>
