<%-- 
    Document   : message
    Author     : Thomas Wray Joe Otis
--%>

<%@page import="entities.Message"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="mesQ" class="dbQuery.MessageQuery" />
<!DOCTYPE html>
<html>
    <head>
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="style/ideaHub.css"/>

    </head>

    <body>
        <%
            if (!(Boolean) request.getSession().getAttribute("loggedin")) {
                response.sendRedirect("homepage.jsp");
                return;
            }
        %>
        <jsp:include page="nonstdhead.jsp"/>

    <center><h1>Messages</h1></center>
    <center><div id="messageCompose">
            <form action="idea" method="post">
                <p><input type="text" name="to" placeholder="To:"/></p>
                <p><input type="text" name="title" placeholder="Title"/></p>
                <p><input type="hidden" name="from" value="<%= request.getSession().getAttribute("accountNum")%>"/></p>
                <p><textarea name="message" cols="30" rows="5" placeholder="Write your message..."></textarea></p>
                <p><input type="submit" value="Send"/></p>
            </form>
        </div></center>
    <center><table border="1">
            <%
                ArrayList<Object> ideaData = mesQ.getMessages((Integer) request.getSession().getAttribute("accountNumber"));
                Iterator it = ideaData.iterator();
                int rows = ((Integer) it.next()).intValue(); // WHY IS THIS LIKE THISSSSSSSS
                int counter = 1;
                while (it.hasNext()) {
                    Message mes = (Message) it.next();
                    out.println("<tr>" + "<td>" + "<a href=\"idea?messageNum="
                            + mes.getMessageNumber() + "\">"
                            + mes.getTitle() + "</a>" + "</td>" + "</tr>");
                    counter++;
                }
            %>
        </table></center>

</body>
</html>
