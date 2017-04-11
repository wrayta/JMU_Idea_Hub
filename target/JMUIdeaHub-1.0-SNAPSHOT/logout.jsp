<%-- 
    Document   : logout
    Author     : Thomas Wray Joe Otis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<title>JSP Page</title>-->
        <%
            request.getSession().setAttribute("loggedin", false);
            request.getSession().setAttribute("notLoggedin", null);
            
        %>
        
        <script>
            window.location.replace("./signInSignUp.jsp");
        </script>
    </head>

    <%--<jsp:include page="nonstdhead.jsp"/>--%>
    <body>
<!--        <p>You have successfully logged out!</p>-->
    </body>
</html>
