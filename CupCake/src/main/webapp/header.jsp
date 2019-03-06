<%-- 
    Document   : header.jsp
    Created on : Mar 4, 2019, 9:54:06 AM
    Author     : Andreas Vikke
--%>

<%@page import="logic.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    HttpSession headerSession = request.getSession();
    User u = (User) headerSession.getAttribute("user");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CupCake WebShop</title>
        
        <base href="${pageContext.request.contextPath}/" />
        
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/style.css">

        <script src="https://code.jquery.com/jquery-3.1.1.min.js" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="js/main.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">

            <a class="navbar-brand" href=""></a>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="shop"><i class="fas fa-shopping-cart"></i> Shop</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="cart"><i class="fas fa-shopping-basket"></i> Basket</a>
                    </li>
                    <% if (u == null) { %>
                    <li class="nav-item">
                        <a class="nav-link" href="login"><i class="fas fa-sign-in-alt"></i> Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="register"><i class="far fa-edit"></i> Register</a>
                    </li>
                    <% } else {%>
                    <li class="nav-item">
                        <a class="nav-link" href="customer"><i class="far fa-user"></i> Account</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="CommandController?command=logout"><i class="fas fa-sign-out-alt"></i> Logout</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-user"><%= u.getUsername()%>&#160&#160&#160Balance: <%= u.getBalance()%></a>
                    </li>
                    <% }%>
                </ul>
            </div>
        </nav>
