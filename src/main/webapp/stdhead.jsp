<%-- 
    Document   : stdhead
    Author     : Thomas Wray Joe Otis
--%>
<?xml version="1.0" encoding="utf-8"?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>JMU Idea Hub</title>
        <link rel="stylesheet" type="text/css" href="style/header.css"/>
    </head>
    <body>
        <div id="banner">
            <div id="headerFont">JMU Idea Hub</div>

        </div>

        <%
            if (!(Boolean) (session.getAttribute("loggedin"))) {
                out.print("<div style=\"position:relative;min-width:960px\"><a href=\"signInSignUp.jsp\"><img alt=\"signIn\""
                        + "height=\"40\" width=\"40\" align=\"right\" src=\"img/signIn.bmp\""
                        + "style=\"position: absolute;right:65px;top:0;\" /></a>"
                        + "<div id=\"signIn\">"
                        + "<a href=\"signInSignUp.jsp\">Log In</a></div>"
                        + "<div id=\"messages\"><a"
                        + " href=\"homepage.jsp\">Home</a></div>"
                        + "<div id=\"messages\"><a"
                        + " href=\"aboutUs.jsp\">About Us</a></div></div>");
            } else {
                out.print("<div style=\"position:relative;min-width:960px\">"
                        + "<img alt=\"signOut\""
                        + "height=\"40\" width=\"40\" align=\"right\" src=\"img/signIn.bmp\""
                        + "style=\"position: absolute;right:65px;top:0;\" />"
                        + "<div id=\"signIn\">"
                        + "<a href=\"homepage.jsp\">Log Out</a></div><div id=\"messages\"><a"
                        + " href=\"message.jsp\">Messages</a></div><div id=\"messages\"><a"
                        + " href=\"idea.jsp\">Ideas</a></div></div>");

                session.setAttribute("loggedin", new Boolean(false));
            }
        %>
        <div id="siteNavigation">
            <ul>

            </ul>
        </div>
        <p></p>
    </body>
</html>
