<%-- 
    Document   : error.jsp
    Created on : Mar 4, 2019, 11:39:21 AM
    Author     : Andreas Vikke
--%>

<%@include file = "header.jsp" %>

<%
    String errormessage = "Error occurred...";
    if(request.getAttribute("errormessage") != null)
    {
        errormessage = (String) request.getAttribute("errormessage");
    }
%>

<h1>Error:</h1>
<p id="errorMessage"><%= errormessage %></p>

<%@include file = "footer.jsp" %>

