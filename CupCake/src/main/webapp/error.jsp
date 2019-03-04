<%-- 
    Document   : error.jsp
    Created on : Mar 4, 2019, 11:39:21 AM
    Author     : Andreas Vikke
--%>

<%@include file = "header.jsp" %>

<h1>Error:</h1>
<p><% out.println(request.getParameter("error")); %></p>

<%@include file = "footer.jsp" %>

