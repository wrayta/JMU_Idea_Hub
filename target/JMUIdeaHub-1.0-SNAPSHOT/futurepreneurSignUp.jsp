<%-- 
    Document   : futurepreneurSignUp
    Author     : Thomas Wray Joe Otis
--%>
<%@page import="entities.UrlRewriter"%>
<%@page import="entities.MajorMinor"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>

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

        <title>JMU Idea Hub</title>
        <link rel="stylesheet" type="text/css" href="style/signInNice.css"/>
        <link href="https://fonts.googleapis.com/css?family=PT+Sans+Narrow|Ranga" rel="stylesheet">
        <link href="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/theme-default.min.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" href="style/formValidation.css"/>
        
        <script>
            function checkExist(){
                var xmlhttp = new XMLHttpRequest();
                var username = encodeURIComponent(document.forms["regFuturepreneur"]["futurepreneurUsername"].value);
                var url = "username?username=" + username;

                xmlhttp.onreadystatechange = function(){
                    if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                        if(xmlhttp.responseText == "User already exists"
                            || xmlhttp.responseText == "User name is invalid") {
                            document.getElementById("futurepreneurExist").style.color = "red";
                            document.getElementById("futurepreneurUsernameInputId").className = "error";
                        }
                        else {
                            document.getElementById("futurepreneurExist").style.color = "green";
                        }
                        document.getElementById("futurepreneurExist").innerHTML = xmlhttp.responseText;
                        
                    }

                };

                try{
                    xmlhttp.open("GET", url, true);
                    xmlhttp.send();
                }catch(e) {
                    alert("unable to connect to server");
                }
            }
        </script>
    </head>

    <body>
        <jsp:include page="nonstdhead.jsp"/>
        <div id="futurepreneurFormWrapper">
        <!--<h2>Futurepreneur Registration</h2>-->
        
        <!--<div id="futurepreneurFormWrapper">-->
            
            <form method="post" name="regFuturepreneur" action="idea" onsubmit="disableBeforeUnload();" id="futurepreneur-registration-form">

                <fieldset>      

                    <div id="futureFirstName">
                        <label>First Name:</label>
                        <input type="text" data-validation="alphanumeric" 
                               data-validation="required" 
                               data-validation-allowing="-_" 
                               name="futurepreneurFirstName"
                               onchange="enableBeforeUnload();"
                               onkeyup="enableBeforeUnload();"/>
                    </div>

                    <p>
                        <div id="futureLastName">

                            <label>Last Name:</label>
                            <input type="text" data-validation="alphanumeric" 
                                   data-validation="required" 
                                   data-validation-allowing="-_"
                                   name="futurepreneurLastName"
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
                                   onchange="enableBeforeUnload();"
                                   onkeyup="enableBeforeUnload();"/>
                        </div>
                    </p>

                    <div id="futureAcademicStanding">
                        <label>Academic Standing:</label>
                        <select id="gradeLevelSelect" onchange="enableBeforeUnload();"
                                onkeyup="enableBeforeUnload();" name="gradesel">
                            <option>Freshman</option>
                            <option>Sophomore</option>
                            <option>Junior</option>
                            <option>Senior</option>
                            <option>Senior+</option>
                        </select>
                    </div>

                    <%
                        ArrayList<Object> majorData = majorQ.getMajorTable();
                        Iterator it = majorData.iterator();
                        int rows = ((Integer) it.next()).intValue(); // WHY IS THIS LIKE THISSSSSSSS
                    %>

                    <div id="futureMajor">
                        <label>Major:</label>
                        <select id="majorSelect" onchange="enableBeforeUnload();"
                                onkeyup="enableBeforeUnload();" name="majors">
                            <%
                                int counter = 1;
                                while (it.hasNext()) {
                                    MajorMinor maj = (MajorMinor) it.next();
                                    out.println("<option value=\"" + counter + "\">" + maj.getName() + "</option>");
                                    counter++;
                                }
                            %>
                        </select>
                    </div>

                    <%
                        ArrayList<Object> minorData = minorQ.getMinorTable();
                        Iterator it2 = minorData.iterator();
                        int rows2 = ((Integer) it2.next()).intValue(); // WHY IS THIS LIKE THISSSSSSSS
                    %>

                    <div id="futureMinor">
                        <label>Minor:</label>
                        <select id="minorSelect" onchange="enableBeforeUnload();"
                                onkeyup="enableBeforeUnload();" name="minors">
                            <%
                                int counter2 = 1;
                                while (it2.hasNext()) {
                                    MajorMinor min = (MajorMinor) it2.next();
                                    out.println("<option value=\"" + counter2 + "\">" + min.getName() + "</option>");
                                    counter2++;
                                }
                            %>
                        </select>
                    </div>

                    <p>
                        <div id="futureGradDate">
                            <label>Expected Graduation Date:</label> 
                            <input type="text" 
                                   data-validation="date" 
                                   data-validation-help="yyyy-mm-dd"
                                   name="year"
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
                                   id="futurepreneurUsernameInputId"
                                   onblur="checkExist();"
                                   onchange="enableBeforeUnload();"
                                   onkeyup="enableBeforeUnload();"/>
                                   <span id="futurepreneurExist"></span>
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
                                   onchange="enableBeforeUnload();"
                                   onkeyup="enableBeforeUnload();"/>
                        </div>
                    </p>

                </fieldset>

                <p>
                
                    <div id="registerFuturepreneur">
                    
                        <input type="submit" value="Submit" id="futureRegisterSubmitButton"/>

                        <a href="signInSignUp.jsp"><input type="button" value="Cancel" id="futureRegisterCancelButton"/></a>

                        <input type="reset" value="Reset Form" id="futureRegisterResetButton"/> 
                    
                    </div>
                
                </p>

        </form>
                            
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>
    
        <script>

            $.validate({
              form : '#futurepreneur-registration-form',
              ignore: ['#gradesel', '#majors', '#minors' ],
//              validateOnBlur : false,
              borderColorOnError : '#440088',
              modules : 'date, security',


//              onError : function($form) {
//                alert('Validation of form '+$form.attr('id')+' failed!');
//              },
              
//              onSuccess : function($form) {
//                alert('The form '+$form.attr('id')+' is valid!');
//                return false; // Will stop the submission of the form
//              },
              
//              onValidate : function($form) {
//                return {
//                    element : $('#gradesel'),
//                    message : 'This input has an invalid value for some reason'
//                }
//              },
              
//              onElementValidate : function(valid, $el, $form, errorMess) {
//                alert('Input ' +$el.attr('name')+ ' is ' + ( valid ? 'VALID':'NOT VALID') );
//              }
              
            });

            // Restrict presentation length
//            $('#presentation').restrictLength( $('#pres-max-length') );

            $(document).ready(function() {
                $('form input[type=submit]').click(function() {
                    return confirm('Ready to submit?');
                });
                
                $('form input[type=reset]').click(function() {
                    return confirm('Are you sure you want to clear all fields?');
                });
                
//                $('form input[type=button]').click(function() {
//                    return confirm('Leave current page?');
//                });
            });
            
            function enableBeforeUnload() {
                window.onbeforeunload = function (e) {
                    return "Discard changes?";
                };
            }
            function disableBeforeUnload() {
                window.onbeforeunload = null;
            }
            
        </script>
        
    </div>

    <div style="clear: both;">&nbsp;</div>

</body>

</html>
