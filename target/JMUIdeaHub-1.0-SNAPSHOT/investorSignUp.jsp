<%-- 
    Document   : investorSignUp
    Author     : Thomas Wray Joe Otis
--%>
<%@page import="entities.UrlRewriter"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->

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
                var username = encodeURIComponent(document.forms["regInvestor"]["investorUsernameInputId"].value);
                var url = "username?username=" + username;

                xmlhttp.onreadystatechange = function(){
                    if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                        if(xmlhttp.responseText == "User already exists"
                            || xmlhttp.responseText == "User name is invalid") {
                            document.getElementById("investorExist").style.color = "red";
                            document.getElementById("investorUsernameInputId").className = "error";
                        }
                        else {
                            document.getElementById("investorExist").style.color = "green";
                        }
                        document.getElementById("investorExist").innerHTML = xmlhttp.responseText;
                        
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

        <div id="investorFormWrapper">

            <form method="post" name="regInvestor" action="idea" onsubmit="disableBeforeUnload();" id="investor-registration-form"> 

                <fieldset>

                    <div id="investorFirstName">
                        <label>First Name:</label>
                        <input type="text" 
                               data-validation="alphanumeric" 
                               data-validation="required" 
                               data-validation-allowing="-_"
                               name="investorFirstName"
                               onchange="enableBeforeUnload();"
                               onkeyup="enableBeforeUnload();"/>
                    </div>

                    <p>
                        <div id="investorLastName">
                            <label>Last Name:</label>
                            <input type="text" 
                                   data-validation="alphanumeric" 
                                   data-validation="required" 
                                   data-validation-allowing="-_"
                                   name="investorLastName"
                                   onchange="enableBeforeUnload();"
                                   onkeyup="enableBeforeUnload();"/>
                        </div>
                    </p>

                    <p>
                        <div id="investorEmail">
                            <label>Email:</label>
                            <input type="text" 
                                   data-validation="email"
                                   name="investorEmail"
                                   onchange="enableBeforeUnload();"
                                   onkeyup="enableBeforeUnload();"/>
                        </div>
                    </p>

                    <p>
                        <div id="investorOccupation">
                            <label>Occupation:</label>
                            <input type="text" 
                                   data-validation="alphanumeric"
                                   name="occupation"
                                   onchange="enableBeforeUnload();"
                                   onkeyup="enableBeforeUnload();"/>
                        </div>
                    </p>

                    <p>
                        <div id="investorInterests">
                            <label>Interests:</label>
                            <textarea id="interestTextArea" onchange="enableBeforeUnload();"
                                      onkeyup="enableBeforeUnload();" name="interests"></textarea>    
                            <span id="max-length-element">200</span> <div id="charCount">chars left</div>
                        </div>
                    </p>

                    <p>
                        <div id="investorWebsite">
                            <label>Website:</label>
                            <input type="text"
                                   data-validation="url"
                                   data-validation-help="http://"
                                   name="website"
                                   onchange="enableBeforeUnload();"
                                   onkeyup="enableBeforeUnload();"/>
                        </div>
                    </p>

                    <p>
                        <div id="investorUserName">
                            <label>User Name:</label>
                            <input type="text" 
                                   data-validation="length alphanumeric" 
                                   data-validation-length="3-12" 
                                   data-validation-error-msg="User name has to be an alphanumeric value (3-12 chars)"
                                   name="investorUsername" 
                                   id="investorUsernameInputId"
                                   onblur="checkExist();"
                                   onchange="enableBeforeUnload();"
                                   onkeyup="enableBeforeUnload();"/>
                                   <span id="investorExist"></span>
                        </div>
                    </p>

                    <p>
                        <div id="investorPassword0">
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
                        <div id="investorPassword1">
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
                
                    <div id="registerInvestor">

                        <input type="submit" value="Submit" id="investorRegisterSubmitButton"/>

                        <a href="signInSignUp.jsp"><input type="button" value="Cancel" id="investorRegisterCancelButton"/></a>

                        <input type="reset" value="Reset Form" id="investorRegisterResetButton"/> 

                    </div>
                </p>

            </form>
            
            <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
            <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>

            <script>

                $.validate({
                  form : '#investor-registration-form',
                  ignore: ['#investorInterests', '#investorWebsite' ],
//                  validateOnBlur : false,
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
                $('#interestTextArea').restrictLength( $('#max-length-element') );

                // Restrict presentation length
    //            $('#presentation').restrictLength( $('#pres-max-length') );

                $(document).ready(function() {
                    $('form input[type=submit]').click(function() {
                        return confirm('Ready to submit?');
                    });

                    $('form input[type=reset]').click(function() {
                        return confirm('Are you sure you want to clear all fields?');
                    });
                    
//                    $('form input[type=button]').click(function() {
//                        return confirm('Leave current page?');
//                    });
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
