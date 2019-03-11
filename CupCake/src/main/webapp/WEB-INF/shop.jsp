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
    List<CupcakePart> cupcakeParts = ccc.getCupcakeParts();
    List<CupcakePart> tops = new ArrayList();
    List<CupcakePart> bottoms = new ArrayList();

    for (CupcakePart ccp : cupcakeParts) {
        if (ccp.getPart() == CupcakePartEnum.TOP) {
            tops.add(ccp);
        } else {
            bottoms.add(ccp);
        }
    }

%>

<div class="shopper">
    <form id="shopForm" method="POST">
        <div class="form-group col-md-4">
            <label>Cupcake Tops:</label>
            <select class="form-control shop" name="top">
                <% for (CupcakePart a : cupcakeParts) { %>
                <%if (a.getPart() == CupcakePartEnum.TOP) {%>
                <option value=<%= a.getId()%>><%=a.getName()%></option>
                <% }
                    } %>
            </select>
        </div>
        <div class="form-group col-md-4">
            <label>Cupcake Bottoms:</label>
            <select class="form-control shop" name="bottom">
                <% for (CupcakePart a : cupcakeParts) { %>
                <%if (a.getPart() == CupcakePartEnum.BOTTOM) {%>
                <option value=<%= a.getId()%>><%=a.getName()%></option>
                <% }
                    }%>
            </select>
        </div>
        <div class="form-group col-md-4">
            <label>Quantity</label>
            <input type="number" class="form-control shop" name="qty" placeholder="Quantity" value="1">
        </div><br />
        <button type="submit" class="btn btn-primary" formaction="CommandController?command=addProduct">Add to cart</button>
    </form>
</div>

<% for (CupcakePart top : tops) {
        for (CupcakePart bot : bottoms) {%>

<div>
    <div class="card">
        <div class="card-body">
            <div class="container">
                <div class="row">
                    <div class="col-sm">
                        <h5><b>Topping</b></h5>
                    </div>
                    <div class="col-sm">
                        <h5><b>Bottom</b></h5>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm">
                        <h5><%= top.getName()%></h5>
                    </div>
                    <div class="col-sm">
                        <h5><%= bot.getName()%></h5>
                    </div>
                </div>
            </div>
            <form method="POST">
                <div class="form-group col-md-4">
                    <label>Quantity</label>
                    <input type="number" class="form-control" name="qty" placeholder="Quantity" value="1">
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary" formaction="CommandController?command=addProduct&top=<%= top.getId()%>&bottom=<%= bot.getId()%>">Add to cart</button>
                </div>
            </form>
        </div>
    </div>
</div>

<% }
    }%>        


<%@include file = "../footer.jsp" %>
