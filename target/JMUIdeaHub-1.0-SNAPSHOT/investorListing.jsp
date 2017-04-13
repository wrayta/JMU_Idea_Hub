<%-- 
    Document   : investorListing
    Author     : Thomas Wray Joe Otis
--%>
<%@page import="entities.Investor"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="invQ" class="dbQuery.UserQuery" /> <!--Add an error page!!!!!!-->
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style/ideaHub.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <%
            if (!(Boolean) request.getSession().getAttribute("loggedin")) {
                response.sendRedirect("homepage.jsp");
                return;
            }
        %>
        <jsp:include page="nonstdhead.jsp"/>

    <center><h1>Investor Listing</h1></center>
    <div id="investorListingTable">
        <center><table border="1">
                <tr>
                    <th>Name</th>
                    <th>Occupation</th>
                    <th>Email</th>
                </tr>

                <%
                    ArrayList<Object> majorData = invQ.getInvTable();
                    Iterator it = majorData.iterator();
                    int rows = ((Integer) it.next()).intValue(); // WHY IS THIS LIKE THISSSSSSSS

                    int counter = 1;
                    while (it.hasNext()) {
                        Investor inv = (Investor) it.next();
                        out.println("<tr><td><a href=\"idea?accountNum="
                                + inv.getAccountNumber() + "\">" + inv.getListName() + "</a></td>"
                                + "<td>" + inv.getOccupation() + "</td>"
                                + "<td>" + inv.getListEmail() + "</td></tr>");
                        counter++;
                    }
                %>

            </table>
        </center>
    </div>
</body>
</html>
