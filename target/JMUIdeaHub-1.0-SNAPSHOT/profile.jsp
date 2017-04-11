<%-- 
    Document   : profile
    Author     : Thomas Wray Joe Otis
--%>

<%@page import="entities.Investor"%>
<%@page import="entities.User"%>
<%@page import="dbQuery.UserQuery"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <%
            if (!(Boolean) request.getSession().getAttribute("loggedin")) {
                response.sendRedirect("homepage.jsp");
                return;
            }
        %>
        <jsp:include page="nonstdhead.jsp"/>

        <%
            UserQuery query = new UserQuery();

            Investor user = (Investor) query.getUserProfile(Integer.parseInt(request.getParameter("accountNum")));
            out.print("<p><h2>User: " + user.getUserName() + "</h2></p>");
            out.print("<div id=\"detIdea\"> <p>Name: " + user.getFirstName() + user.getLastName() + "</p><p>Email: "
                    + user.getEmail() + "</p><p>Interests: " + user.getInterest() + "</p>"
                    + "<p>Website: " + user.getWebsite() + "</p></div>");
        %>
    </body>
</html>
