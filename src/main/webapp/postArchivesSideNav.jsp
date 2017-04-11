<%-- 
    Document   : postArchivesSideNav
    Created on : Apr 4, 2017, 4:17:24 PM
    Author     : thomas
--%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style/sideNavigation.css"/>
    </head>
    <body>
        <div id="mySidenav" class="sideNav">
        <%!
            String getMonthForInt(int num) {
                String month = "wrong";
                DateFormatSymbols dfs = new DateFormatSymbols();
                String[] months = dfs.getMonths();
                if (num >= 0 && num <= 11 ) {
                    month = months[num];
                }
                return month;
            }
        %>
        <%                
                Calendar now = Calendar.getInstance();
                
                int month = (now.get(Calendar.MONTH));
                
                for (int i = month; i >= 0; i--) {
                    if(i == month) {
                        String latestMonthStr = getMonthForInt(i);
                        out.print("<a href=\"#\""
                                + "onclick=\"requestIdeasForMonth(" + -1 + ")\"" 
                                + "id=\"latestMonth\"" 
                                + "class=\"month\">"
                                + latestMonthStr
                                + "</a>"); 
                    }
                    else {
                        String monthStr = getMonthForInt(i);
                        out.print("<a href=\"#\""
                                + "onclick=\"requestIdeasForMonth(" + i + ")\"" 
                                + "id=\"month" + i + "\"" 
                                + "class=\"month\">"
                                + monthStr
                                + "</a>");
                    }
                }
        %>
        </div>
        
        <script>
            var months = document.getElementsByClassName("month");
            var topNum = 25;
            var i;

            for (i = 0; i < months.length; i++) {

                months[i].style.top = topNum + "px";
                topNum += 60;
            }
        </script>
    </body>
</html>
