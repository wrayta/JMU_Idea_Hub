<%@page import="dbQuery.MessageQuery"%>
<%@page import="dbQuery.UserQuery"%>
<%@page import="entities.Message"%>
<%-- 
    Document   : detMessage
    Author     : Thomas Wray Joe Otis
--%>
<?xml version="1.0" encoding="utf-8"?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

    <head>
        <title>JMU Idea Hub</title>
        <link rel="stylesheet" type="text/css" href="style/ideaHub.css"/>
    </head>
    <body>
        <%
            if (!(Boolean) request.getSession().getAttribute("loggedin")) {
                response.sendRedirect("homepage.jsp");
                return;
            }
        %>
        <jsp:include page="nonstdhead.jsp"/>

        <center>
            <%
                MessageQuery query = new MessageQuery();
                UserQuery usQuery = new UserQuery();

                Message mes = query.getMessage(Integer.parseInt(request.getParameter("messageNum")));
                String name = usQuery.getUser(mes.getSender());

                out.print("<h2>" + mes.getTitle() + "</h2>"
                        + "<p>" + mes.getMessage() + "</p><p>" + "<label>From: " + name + "</label>" + "</p><p>"
                        + "<label>Sent: " + mes.getDate() + "</label>" + "</p>");
            %>
        </center>
    </body>
</html>
