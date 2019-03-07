<%-- 
    Document   : orderlist
    Created on : Mar 5, 2019, 12:15:41 PM
    Author     : Andreas Vikke
--%>

<%@page import="data.DataSourceMySql"%>
<%@page import="logic.model.Order"%>
<%@page import="logic.OrderController"%>
<%@page import="java.util.List"%>

<%
    OrderController oc = new OrderController(new DataSourceMySql().getDataSource());
    oc.setDataSource(new DataSourceMySql().getDataSource());
    String username = request.getParameter("username");
    List<Order> orders;
    if(username == null)
        orders = oc.getAllOrders();
    else
        orders = oc.getOrderByUser(username);
%>

<button class="btn btn-info" onclick="orderById();">Order by Id</button>
<button class="btn btn-info" onclick="orderByDate();">Order by Date</button>
<button class="btn btn-info" onclick="orderByCustomer();">Order by Username</button>
<br /><br />
<table class="table">
    <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Username</th>
            <th scope="col">Date</th>
            <th scope="col"></th>
        </tr>
    </thead>
    <tbody id="orderList">
    </tbody>
</table>

<script>
    var orderList = [<% for (Order o : orders) {
            out.println("[");
            out.println("\"" + o.getOrderId() + "\",");
            out.println("\"" + o.getUsername() + "\",");
            out.println("\"" + o.getOrderDate() + "\"");
            out.println("],");
        }%>];
    showOrders();

    function orderById() {
        orderList.sort((function (index) {
            return function (a, b) {
                return (a[index] === b[index] ? 0 : (a[index] < b[index] ? -1 : 1));
            };
        })(0));
        showOrders();
    }
    function orderByCustomer() {
        orderList.sort((function (index) {
            return function (a, b) {
                return (a[index] === b[index] ? 0 : (a[index] < b[index] ? -1 : 1));
            };
        })(1));
        showOrders();
    }
    function orderByDate() {
        orderList.sort((function (index) {
            return function (a, b) {
                return (a[index] === b[index] ? 0 : (a[index] < b[index] ? -1 : 1));
            };
        })(2));
        showOrders();
    }

    function showOrders() {
        var parenttbl = document.getElementById("orderList");
        parenttbl.innerHTML = "";

        orderList.forEach(function (entry) {
            var parenttbl = document.getElementById("orderList");
            var newel = document.createElement('tr');
            entry.forEach(function (eentry) {
                var newtd = document.createElement('td');
                newtd.innerHTML = eentry;
                newel.appendChild(newtd);
            });
            var newtd = document.createElement('td');
            newtd.innerHTML = '<a href="order?orderId=' + entry[0] + '"><button class="btn btn-info">Show Order</button></a>';
            newtd.setAttribute('class', 'tableButton');
            newel.appendChild(newtd);
            parenttbl.appendChild(newel);
        });
    }
</script>