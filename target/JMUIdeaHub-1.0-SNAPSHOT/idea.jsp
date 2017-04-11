<%-- 
    Document   : idea
    Author     : Thomas Wray Joe Otis
--%>
<%@page import="entities.Investor"%>
<%@page import="entities.User"%>

<?xml version="1.0" encoding="utf-8"?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<jsp:useBean id="ideaQ" class="dbQuery.IdeaQuery" /> <!--Add an error page!!!!!!-->

<html xmlns="http://www.w3.org/1999/xhtml">

    <head>
        <title>JMU Idea Hub</title>
        <link rel="stylesheet" type="text/css" href="style/idea.css"/>
        <link href="https://fonts.googleapis.com/css?family=PT+Sans+Narrow|Ranga" rel="stylesheet">

            <%
                if (!(Boolean) request.getSession().getAttribute("loggedin")) {
                    response.sendRedirect("homepage.jsp");
                    return;
                }
            %>

            <script>
                
                function createNewIdea() {
                    
                    
                    
//                        disableBeforeUnload();

                        var latestMonth = document.getElementById("latestMonth").innerHTML;

                        var xmlhttp;

                        if (window.XMLHttpRequest)
                        {// code for IE7+, Firefox, Chrome, Opera, Safari
                            xmlhttp = new XMLHttpRequest();
                        }
                        else
                        {// code for IE6, IE5
                            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                        }
                        xmlhttp.onreadystatechange = function()
                        {
                            if (xmlhttp.readyState === 4 && xmlhttp.status === 200)
                            {
    //                            location.reload();
                                $('#ideasList').load(document.URL +  ' #ideasList');
                            }
                        }

                        var idea = document.getElementById("ideaId").value;

                        var title = document.getElementById("titleId").value;

                        var postData = "idea=" + encodeURIComponent(unescape(idea)) +
                        "&title=" + encodeURIComponent(unescape(title)) +
                        "&latestMonth=" + encodeURIComponent(unescape(latestMonth));

                        xmlhttp.open("POST", "idea", false);     
                        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                        xmlhttp.setRequestHeader("Content-length", postData.length);
                        xmlhttp.send(postData);
                    
                            
                }
            </script>
    </head>

    <body>

        <jsp:include page="nonstdhead.jsp"/>

        <form id="newIdeaForm" accept-charset="utf-8">

            <div id="newIdeaContainer">
                <p>
                    <div id="ideaTitle">
                        <input type="text" 
                               class="userInput"
                               id="titleId"
                               name="title" 
                               placeholder="Idea title"
                               onchange="enableBeforeUnload();"
                               onkeyup="enableBeforeUnload();"/>
                    </div>
                </p>
                <p>
                    <div id="ideaTextArea">
                        <textarea name="idea" 
                                  id="ideaId"
                                  onchange="enableBeforeUnload();"
                                  onkeyup="enableBeforeUnload();"
                                  placeholder="Idea details..."></textarea>
                    </div>
                </p>
                <p>
                    <input type="reset" class="ideaReset" value="Cancel"/>
                    <input type="submit" class="ideaSubmit" value="Post"/>
                </p>
            </div>

        </form>
        
        <jsp:include page="postArchivesSideNav.jsp"/>
 
        <jsp:include page="listOfIdeas.jsp"/>

        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

        <script>
            $(document).ready(function() {

                $('form input[type=submit]').click(function() {
                    if(confirm('Submit idea?')) {
//                        disableBeforeUnload();

                        disableBeforeUnload();

                        if (!checkIfBlank()) {
                            createNewIdea();
                        }
                        
                        else {
                            alert("Neither title nor idea can be blank");
                        }
                    }
                        
                });

                $('form input[type=reset]').click(function() {
                    return confirm('Discard idea?');
                });
    
            });
            
            function checkIfBlank() {
                if(document.getElementById("titleId").value.toString().trim() === ""
                        || document.getElementById("ideaId").value.toString().trim() === "") {
                    return true;
                }
                
                return false;
            }
            
            function enableBeforeUnload() {
                window.onbeforeunload = function(e) {
                    return "Discard changes?";
                };
            }
            
            function disableBeforeUnload() {
                window.onbeforeunload = null;
            }
        </script>

    </body>

</html>
