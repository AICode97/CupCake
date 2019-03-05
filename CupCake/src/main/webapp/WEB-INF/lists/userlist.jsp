<%-- 
    Document   : userlist
    Created on : Mar 5, 2019, 12:11:41 PM
    Author     : Andreas Vikke
--%>

<%@page import="java.util.List"%>
<%@page import="logic.UserController"%>
<%@page import="logic.model.User"%>

<%
    UserController uc = new UserController();
    List<User> users = uc.getUsers();
    int count = 1;
%>

<button class="btn btn-info" onclick="orderById();">Order by Id</button>
<button class="btn btn-info" onclick="orderByUsername();">Order by Username</button>
<button class="btn btn-info" onclick="orderByEmail();">Order by Email</button>
<table class="table">
    <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Username</th>
            <th scope="col">Email</th>
        </tr>
    </thead>
    <tbody id="userList">
    </tbody>
</table>

<script>
    var orderList = [<% for (User u : users) {
            out.println("[");
            out.println("\"" + count++ + "\",");
            out.println("\"" + u.getUsername() + "\",");
            out.println("\"" + u.getEmail() + "\"");
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
    function orderByUsername() {
        orderList.sort((function (index) {
            return function (a, b) {
                return (a[index] === b[index] ? 0 : (a[index] < b[index] ? -1 : 1));
            };
        })(1));
        showOrders();
    }
    function orderByEmail() {
        orderList.sort((function (index) {
            return function (a, b) {
                return (a[index] === b[index] ? 0 : (a[index] < b[index] ? -1 : 1));
            };
        })(2));
        showOrders();
    }

    function showOrders() {
        var parenttbl = document.getElementById("userList");
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
            newtd.innerHTML = '<a href="${pageContext.request.contextPath}/order?orderId=' + entry[0] + '"><button class="btn btn-info">Show Order</button></a>';
            newtd.setAttribute('class', 'tableButton');
            newel.appendChild(newtd);
            parenttbl.appendChild(newel);
        });
    }
</script>