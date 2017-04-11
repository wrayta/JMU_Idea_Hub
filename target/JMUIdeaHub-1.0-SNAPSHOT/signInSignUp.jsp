<%-- 
    Document   : signInSignUp
    Author     : Thomas Wray Joe Otis
--%>

<%@page import="entities.UrlRewriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%--<%
    if (!request.isSecure()) {
        UrlRewriter ur = new UrlRewriter();
        String x = ur.rewriteUrl("signInSignUp.jsp", request);
        response.sendRedirect(x);
    }
%>--%>

<!DOCTYPE html>
<html>

    <head>
        <link href="style/signInNice.css" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=PT+Sans+Narrow|Ranga" rel="stylesheet">
        
        <%
            if(session.getAttribute("loggedin") != null && 
                    (Boolean)(session.getAttribute("loggedin")) == true) {
                request.getSession().setAttribute("loggedin", false);
                request.getSession().setAttribute("notLoggedin", null);
            }
            
        %>
    </head>

    <body>
        <jsp:include page="nonstdhead.jsp"/>
        <div id="contact">
            <center><img src="img/signIn.bmp" alt="signInpic"/></center>
            <form action="idea" method="post">
                <fieldset>
                    <!--<center><label for="name">User Name</label></center>-->
                    <center><input type="text" name="user" id="user" placeholder="User Name" /></center>

                    <!--<center><label for="email">Password</label></center>-->
                    <center><input type="password" name="password" id="password" placeholder="Password" /></center>         

                    <div id="signInOrForgotPassword">
                        
                        <div id="signIn">
                            <input type="submit" value="Sign In" id="signInButton"/>
                        </div>

                        <!--<div id="forgottenPassword">-->
                            <a class="forgot-link" href="forgotPass.jsp">Forgot Password?</a>
                        <!--</div>-->

                    </div>

                    <center>
                        
                        <div id="futurepreneurRegister">
                            <a class="register-futurepreneur-link" href="futurepreneurSignUp.jsp" id="futureRegisterButton">Register As<br/>Futurepreneur</a>
                        </div>

                        <div id="investorRegister">
                            <a class="register-investor-link" href="investorSignUp.jsp" id="investorRegisterButton">Register As<br/>Investor</a>
                        </div>
                        
                    </center>

                </fieldset>
                
                <%
                    HttpSession sesh = request.getSession(true);

                    if(sesh.getAttribute("notLoggedin") != null && (Boolean)(sesh.getAttribute("notLoggedin")) == true) {
                        System.out.println("Inside the false login if");
                %>
            
                    <p>
                        <div id="failedLoginMessage">
                            User Name or Password is incorrect
                        </div> 
                    </p>
            
                <%
                    }
                %>
            
            </form>
            
        </div>
        
    </body>

    <!--<div id="botBanner"><jsp:include page="stdfoot.jsp"/></div>-->
</html>
