<%-- 
    Document   : error
    Author     : Thomas Wray Joe Otis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Errors:</h1>

        <%=request.getSession().getAttribute("errMsg")%>

        <a href="homepage.jsp">Return to Home.</a>


    </body>
</html>
