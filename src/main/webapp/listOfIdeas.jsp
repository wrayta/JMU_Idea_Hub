<%-- 
    Document   : listOfIdeas
    Created on : Apr 4, 2017, 6:59:50 PM
    Author     : thomas
--%>
<%@page import="dbQuery.UserQuery"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entities.Idea"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css?family=PT+Sans+Narrow|Ranga" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="style/listOfIdeas.css"/>
        
        <script>
            function requestIdeasForMonth(monthNum)
                {
                    console.log("Inside requestIdeasForMonth");
                    
                    var month;
                    
                    if (monthNum === -1) {
                        month = document.getElementById("latestMonth").innerHTML;
                    }
                    
                    else {
                        month = document.getElementById("month" + monthNum).innerHTML;
                    }
                   
                    console.log("The month is: " + month);
                    
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
                            $('#ideasList').load(document.URL +  ' #ideasList');
                        }
                    }

                    xmlhttp.open("GET", "idea?currentMonth=" + month, true);
                    xmlhttp.send();
                }
        </script>
    </head>
    <body>
        <%
            ArrayList<Object> ideaData = (ArrayList<Object>) (request.getSession().getAttribute("ideaData"));
//            System.out.println("ideaData size: " + ideaData.size());
            Iterator it = ideaData.iterator();
            int rows = ((Integer) it.next()).intValue(); // WHY IS THIS LIKE THISSSSSSSS
            int counter = 1; 
            UserQuery usQuery = new UserQuery();

            out.println("<div id=\"ideasList\">");
            
            while (it.hasNext()) {
                Idea idea = (Idea) it.next();
                String name = usQuery.getUserFullName(idea.getAccountNumber());
                out.println(
                    "<button class=\"accordion\""
                    + ">"
                    + "<div id=\"listIdeaTitle\">"
                    + idea.getIdeaTitle()
                    + "</div>"
                    + "<div id=\"listIdeaAuthor\">"
                    + name
                    + "</div>"
                    + "<div id=\"listIdeaDate\">"
                    + idea.getDate()
                    + "</div>"
                    + "</button>"
                    + "<div class=\"panel\">"
                    + "<p>"
                    + "<div id=\"listIdeaContent\">"
                    + idea.getIdea()
                    + "</div>"
                    + "<div id=\"moreIdea\">"
                    + "<a class=\"moreIdeaLink\" href=\"idea?ideaNum="
                    + idea.getIdeaNumber() + "\">"
                    + "See More..."
                    + "</a>"
                    + "</div>"
                    + "</p>"
                    + "</div>");

                counter++;
            }

            out.println("</div>");
        %>
        
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

        <script>
            var acc = document.getElementsByClassName("accordion");

//            if (acc.length > 0) {
//                acc[0].classList.toggle("active");
//                var firstPanel = acc[0].nextElementSibling;
//                firstPanel.style.maxHeight = firstPanel.scrollHeight + "px";

                $(document).on('click', '.accordion', function() {
                    this.classList.toggle("active");
                    var panel = this.nextElementSibling;
                    if (panel.style.maxHeight) {
                        panel.style.maxHeight = null;
                    } else {
                        panel.style.maxHeight = panel.scrollHeight + "px";
                    }
                });
//            }
            
        </script>
        
    </body>
</html>
