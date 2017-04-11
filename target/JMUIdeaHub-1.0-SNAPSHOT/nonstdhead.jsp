<%-- 
    Document   : nonstdhead
    Author     : Thomas Wray Joe Otis
--%>
<%@page import="dbQuery.UserQuery"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <!--    <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
            <link rel="stylesheet" type="text/css" href="style/ideaHub.css"/>
    
        </head>-->

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JMU Idea Hub</title>
        <link rel="stylesheet" type="text/css" href="style/header.css"/>
        <!--<link href="https://fonts.googleapis.com/css?family=PT+Sans+Narrow|Ranga" rel="stylesheet">-->

    </head>

    <body>

        <div id="wrapper">
            
            <div id="banner">

                <div id="bannerText">

                    <div id="headerFont">JMU Idea Hub</div>
                    <!--        </div>-->

                    <%

                        if (session.getAttribute("loggedin") == null) {
                            session.setAttribute("loggedin", new Boolean(false));
                        }

                        if (!(Boolean) (session.getAttribute("loggedin"))) {
                            out.print("<div id=\"home\"><a href=\"homepage.jsp\">Home</a></div>"
                                    + "<div id=\"signInSignUp\"><a href=\"signInSignUp.jsp\">Login</a></div>"
                                    + "<div id=\"about\"><a href=\"aboutUs.jsp\">About Us</a></div>");
                        } else {

//                            UserQuery query = new UserQuery();

                            out.print("<div id=\"home\"><a href=\"homepage.jsp\">Home</a></div>"
//                                    + "<div id=\"logout\"><a href=\"logout.jsp\">Log Out</a></div>"
                                    + "<div id=\"ideas\"><a href=\"idea.jsp\">Ideas</a></div>"
                                    + "<div id=\"investorListing\"><a href=\"investorListing.jsp\">Investor Listing</a></div>"
                                    + "<div id=\"messages\"><a href=\"message.jsp\">Messages</a></div>"
                                    + "<div id=\"about\"><a href=\"aboutUs.jsp\">About Us</a></div>");

//                            if (query.isInv((Integer) request.getSession().getAttribute("accountNumber"))) {
//                                out.print("<div id=\"editInvestor\"><a href=\"editInvestor.jsp\">Edit Profile</a></div>");
//                            } else {
//                                out.print("<div id=\"editFuture\"><a href=\"editFuturepreneur.jsp\">Edit Profile</a></div>");
//                            }
                        }


                    %>   

                </div>

                <img id="logo" src="img/jmu-logo-cropped-1.png"/>

                <%
                    if ((Boolean) (session.getAttribute("loggedin"))) {
                        
                        UserQuery query = new UserQuery();
                        String editInfo = null;
                        
                        if (query.isInv((Integer) request.getSession().getAttribute("accountNumber"))) {
                            editInfo = ("<div id=\"editInvestor\"><a href=\"editInvestor.jsp\">Edit Profile</a></div>");
                        } else {
                            editInfo = ("<div id=\"editFuture\"><a href=\"editFuturepreneur.jsp\">Edit Profile</a></div>");
                        }
                        
                        String firstName = query.getUserFirstName((Integer) request.getSession().getAttribute("accountNumber"));
                   
                        out.print("<div class=\"dropdown\">"
                                + "<img id=\"profileDropDown\" src=\"img/signIn.bmp\">"
                                + "<div class=\"dropdown-content\">"
                                + editInfo
                                + "<div id=\"logout\">"
                                + "<a href=\"signInSignUp.jsp\">Log Out</a>"
                                + "</div>"   
                                + "</div>"
                                + "</div>"
                                + "<div id=\"welcomeUserString\">Welcome, " + firstName 
                                + "</div>");
                    }
                %>

            </div>  
                    
        </div>

    </body>
    
</html>
