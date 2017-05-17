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
//                        $('#ideasList').load(document.URL + ' #ideasList');
                        $('#ideasList').empty().append(xmlhttp.responseText);
                    }
                }

                xmlhttp.open("GET", "idea?currentMonth=" + month, true);
                xmlhttp.send();
            }
        </script>
    </head>
    <body>
        
        <div id="ideasList">          
        </div>

        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

        <script>
//            var acc = document.getElementsByClassName("accordion");

//            if (acc.length > 0) {
//                acc[0].classList.toggle("active");
//                var firstPanel = acc[0].nextElementSibling;
//                firstPanel.style.maxHeight = firstPanel.scrollHeight + "px";

            $(document).ready(function() {
                $.ajax({
                    type: "get",
                    url: "getIdeaList",
                    async: "true"
                }).done(function(data){
                    $('#ideasList').append(data);
                });
            });
            
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
