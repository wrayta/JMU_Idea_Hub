<%-- 
    Document   : editFuturepreneur
    Author     : Thomas Wray Joe Otis
--%>
<%@page import="entities.UrlRewriter"%>
<%@page import="entities.Futurepreneur"%>
<%@page import="entities.User"%>
<%@page import="entities.MajorMinor"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<jsp:useBean id="userQ" class="dbQuery.UserQuery" />

<jsp:useBean id="majorQ" class="dbQuery.MajorQuery" /> <!--Add an error page!!!!!!-->
<jsp:useBean id="minorQ" class="dbQuery.MinorQuery" /> <!--Add an error page!!!!!!-->

<%--<%
    if (!request.isSecure()) {
        UrlRewriter ur = new UrlRewriter();
        String x = ur.rewriteUrl("signInSignUp.jsp", request);
        response.sendRedirect(x);
    }
%>--%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JMU Idea Hub</title>
        <link rel="stylesheet" type="text/css" href="style/signInNice.css"/>
        <link href="https://fonts.googleapis.com/css?family=PT+Sans+Narrow|Ranga" rel="stylesheet">
        <link href="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/theme-default.min.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" href="style/formValidation.css"/>

        <script>
            function checkExist() {
                var xmlhttp = new XMLHttpRequest();
                var username = encodeURIComponent(document.forms["editFuturepreneur"]["futurepreneurUsername"].value);
                var url = "username?editedUsername=" + username
                        + "&accountNumber=" + <%= request.getSession().getAttribute("accountNumber")%>;

                xmlhttp.onreadystatechange = function() {
                    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                        if (xmlhttp.responseText == "User already exists"
                                || xmlhttp.responseText == "User name is invalid") {
                            document.getElementById("futurepreneurEditedExist").style.color = "red";
                            document.getElementById("futurepreneurUsernameInputEditId").className = "error";
                        }
                        else {
                            document.getElementById("futurepreneurEditedExist").style.color = "green";
                        }
                        document.getElementById("futurepreneurEditedExist").innerHTML = xmlhttp.responseText;

                    }

                };

                try {
                    xmlhttp.open("GET", url, true);
                    xmlhttp.send();
                } catch (e) {
                    alert("unable to connect to server");
                }
            }
            
        </script>

    </head>
    
    <body>
        <%
            if (!(Boolean) request.getSession().getAttribute("loggedin")) {
                response.sendRedirect("homepage.jsp");
                return;
            }
        %>
        <jsp:include page="nonstdhead.jsp"/>
        <div id="futurepreneurFormWrapper">
            <!--<h2>Futurepreneur Registration</h2>-->

            <!--<div id="futurepreneurFormWrapper">-->

            <form method="post" action="updateFuturepreneur" name="editFuturepreneur" onsubmit="disableBeforeUnload();" id="futurepreneur-edit-form" accept-charset="utf-8">

                <fieldset>      

                    <div id="futureFirstName">
                        <label>First Name:</label>
                        <input type="text" 
                               data-validation="alphanumeric" 
                               data-validation="required" 
                               data-validation-allowing="-_" 
                               name="futurepreneurFirstName"
                               id="futurepreneurFirstName"
                               onchange="enableBeforeUnload();"
                               onkeyup="enableBeforeUnload();"/>
                    </div>

                    <p>
                    <div id="futureLastName">

                        <label>Last Name:</label>
                        <input type="text" 
                               data-validation="alphanumeric" 
                               data-validation="required" 
                               data-validation-allowing="-_"
                               name="futurepreneurLastName"
                               id="futurepreneurLastName"
                               onchange="enableBeforeUnload();"
                               onkeyup="enableBeforeUnload();"/>

                    </div>
                    </p>

                    <p>
                    <div id="futureEmail">

                        <label>Email:</label>
                        <input type="text" 
                               data-validation="email"
                               name="futurepreneurEmail"
                               id="futurepreneurEmail"
                               onchange="enableBeforeUnload();"
                               onkeyup="enableBeforeUnload();"/>
                    </div>
                    </p>

                    <p>
                    <div id="futureGPA">
                        <label>GPA:</label>
                        <input type="text" 
                               data-validation="number" 
                               data-validation-allowing="range[0.00;4.0],float"
                               name="gpa"
                               id="gpa"
                               onchange="enableBeforeUnload();"
                               onkeyup="enableBeforeUnload();"/>
                    </div>
                    </p>

                    <p>
                    <div id="futureAcademicStanding">
                        <label>Academic Standing:</label>

                        <select id="gradeLevelSelect" onchange="enableBeforeUnload();"
                                onkeyup="enableBeforeUnload();" name="gradesel">
                            <%
                                String[] gradeLevels = {"Freshman", "Sophomore", "Junior", "Senior", "Senior+"};
                                for(int i = 1; i <= gradeLevels.length; i++) {
//                                    if (gradeLevels[i - 1].equals(user.getAcademic())) {
//                                        out.print("<option selected=\"selected\"");
//                                    } else {
                                        out.print("<option");
//                                    }

                                    out.print(">" + gradeLevels[i - 1] + "</option>");

                                }
                            %>
                        </select>   
                        
                    </div>
                    </p>

                    <p>
                    <div id="futureMajor">
                        <label>Major:</label>
                        <select id="majorSelect" onchange="enableBeforeUnload();"
                                onkeyup="enableBeforeUnload();" name="majors">
                            <option value="1">General Studies</option>
                            <option value="2">Computer Science</option>
                            <option value="3">Biology</option>
                        </select>
                    </div>
                    </p>

                    <p>
                    <div id="futureMinor">
                        <label>Minor:</label>
                        <select id="minorSelect" onchange="enableBeforeUnload();"
                                onkeyup="enableBeforeUnload();" name="minors">
                            <option value="1">General Studies</option>
                            <option value="2">General Music</option>
                            <option value="3">English</option>
                        </select>
                    </div>
                    </p>

                    <p>
                    <div id="futureGradDate">
                        <label>Expected Graduation Date:</label> 
                        <input type="text" 
                               data-validation="date" 
                               data-validation-help="yyyy-mm-dd"
                               name="year" 
                               id="year"
                               onchange="enableBeforeUnload();"
                               onkeyup="enableBeforeUnload();"/>
                    </div>
                    </p>

                    <p>
                    <div id="futureUserName">
                        <label>User Name:</label>
                        <input type="text"
                               data-validation="length alphanumeric" 
                               data-validation-length="3-12" 
                               data-validation-error-msg="User name has to be an alphanumeric value (3-12 chars)"
                               name="futurepreneurUsername"
                               id="futurepreneurUsernameInputEditId"
                               onblur="checkExist();"
                               onchange="enableBeforeUnload();"
                               onkeyup="enableBeforeUnload();"/>
                        <span id="futurepreneurEditedExist"></span>
                    </div>
                    </p>

                    <p>
                    <div id="futurePassword0">
                        <label>Password:</label>
                        <input type="password" 
                               data-validation="strength" 
                               data-validation-strength="2"
                               data-validation-error-msg="The password isn't strong enough (must include a number or special character)"
                               name="pwd1_confirmation"
                               id="pwd1_confirmation"
                               onchange="enableBeforeUnload();"
                               onkeyup="enableBeforeUnload();"/>
                    </div>
                    </p>

                    <p>
                    <div id="futurePassword1">
                        <label>Confirm Password:</label>
                        <input type="password" 
                               data-validation="confirmation"
                               name="pwd1"
                               id="pwd1"
                               onchange="enableBeforeUnload();"
                               onkeyup="enableBeforeUnload();"/>
                    </div>
                    </p>

                </fieldset>

                <p>

                <div id="registerFuturepreneur">

                    <input type="submit" value="Update" id="futureRegisterSubmitButton"/>

                    <a href="idea.jsp"><input type="button" value="Cancel" id="futureRegisterCancelButton"/></a>

                    <input type="reset" value="Reset Form" id="futureRegisterResetButton"/> 

                </div>

                </p>

            </form>

        </div>

        <!--<div style="clear: both;">&nbsp;</div>-->

         <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
            <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>

            <script>

                                   $.validate({
                                       form: '#futurepreneur-edit-form',
                                       ignore: ['#gradesel', '#majors', '#minors'],
                                       borderColorOnError: '#440088',
                                       modules: 'date, security',
                                   });

                                   $(document).ready(function() {
                                       $('form input[type=submit]').click(function() {
                                           return confirm('Confirm user profile update...');
                                       });

                                       $('form input[type=reset]').click(function() {
                                           return confirm('Are you sure you want to clear all fields?');
                                       });

                                       //                $('form input[type=button]').click(function() {
                                       //                    return confirm('Leave current page?');
                                       //                });
                                       
                                       
//                                           Futurepreneur user = (Futurepreneur) (request.getSession().getAttribute("user"));
                                       $.ajax({
                                            type: "get",
                                            url: "getFuturepreneur",
                                            async: "true"
                                        }).done(function(data) { 
                                            console.log("Data: " + data);
                                            var firstName=data[0], lastName=data[1], email=data[2], gpa=data[3], gradeLevel=data[4], major=data[5], minor=data[6], gradDate=data[7], userName=data[8];
                                            $('#futurepreneurFirstName').val(firstName);
                                            $('#futurepreneurLastName').val(lastName);
                                            $('#futurepreneurEmail').val(email);
                                            $('#gpa').val(parseFloat(gpa));
                                            $('#gradeLevelSelect').val(gradeLevel);
                                            $('#majorSelect').val(major);
                                            $('#minorSelect').val(minor);
                                            $('#year').val(gradDate);
                                            $('#futurepreneurUsernameInputEditId').val(userName);
                                       });
//                                       $.ajax({
//                                            type: "get",
//                                            url: "getMajorsForEdit",
//                                            async: "true",
//                                            tryCount: 0,
//                                            retryLimit: 3,
//                                            timeout: 3000
//                                        }).done(function(data) {
//                                            console.log("The data is: " + data);
//                                            console.log("Data length: " + data.length);
//                                            if(data.length === 0) {
//                                                this.tryCount++;
//                                                if (this.tryCount <= this.retryLimit) {
//                                                    //try again
//                                                    $.ajax(this);
//                                                }        
//                                            }
//                                            else {
//                                                $('#majorSelect').append(data);
//                                            }
//                                        }).fail(function(xhr, textStatus) {
//                                            console.log("Failed: " + xhr.status);
//                                            if (textStatus === 'timeout') {
//                                                this.tryCount++;
//                                                if (this.tryCount <= this.retryLimit) {
//                                                    //try again
//                                                    $.ajax(this);
//                                                    return;
//                                                }            
//                                                return;
//                                            }
//                                        });
//                                       $.ajax({
//                                            type: "get",
//                                            url: "getMinorsForEdit",
//                                            async: "true",
//                                            tryCount: 0,
//                                            retryLimit: 3,
//                                            timeout: 3000
//                                        }).done(function(data) {
//                                            console.log("The data is: " + data);
//                                            console.log("Data length: " + data.length);
//                                            if(data.length === 0) {
//                                                this.tryCount++;
//                                                if (this.tryCount <= this.retryLimit) {
//                                                    //try again
//                                                    $.ajax(this);
//                                                }   
//                                            }
//                                            else {
//                                                $('#minorSelect').append(data);
//                                            }
//                                        }).fail(function(xhr, textStatus) {
//                                            console.log("Failed: " + xhr.status);
//                                            if (textStatus === 'timeout') {
//                                                this.tryCount++;
//                                                if (this.tryCount <= this.retryLimit) {
//                                                    //try again
//                                                    $.ajax(this);
//                                                    return;
//                                                }            
//                                                return;
//                                            }
//                                        });
                                       
                                   });

                                   function enableBeforeUnload() {
                                       window.onbeforeunload = function(e) {
                                           return "Discard changes?";
                                       };
                                   }
                                   function disableBeforeUnload() {
                                       window.onbeforeunload = null;
                                   }
            </script>
            
        <div style="clear: both;">&nbsp;</div>

    </body>
</html>
