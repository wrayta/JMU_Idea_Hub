<%--
    Document   : detIdea
    Author     : Thomas Wray Joe Otis
--%>

<%@page import="dbQuery.SupportQuery"%>
<%@page import="entities.Comment"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dbQuery.UserQuery"%>
<%@page import="entities.Idea"%>
<%@page import="dbQuery.IdeaQuery"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="comQ" class="dbQuery.CommentQuery" />
<jsp:useBean id="suppQ" class="dbQuery.SupportQuery" />
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style/idea.css"/>
        <link href="https://fonts.googleapis.com/css?family=PT+Sans+Narrow|Ranga" rel="stylesheet">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <%  IdeaQuery query = new IdeaQuery();
            UserQuery usQuery = new UserQuery();
            SupportQuery supQuery = new SupportQuery();

            Idea idea = query.getIdea(Integer.parseInt(request.getParameter("ideaNum")));
            String name = usQuery.getUserFullName(idea.getAccountNumber());
            
            Calendar now = Calendar.getInstance();     
            int month = (now.get(Calendar.MONTH) + 1); 
            String latestMonthStr = getMonthForInt(month - 1);
        %>
        
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
        
        <script>
            function loadXMLDoc()
            {
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
                        if (document.getElementById("supports").getAttribute('src') === "img/thumbs-up-icon.png") {
                            document.getElementById("supports").src = "img/thumbs-up-icon-liked.png";
                        }
                        
                        else {
                            document.getElementById("supports").src = "img/thumbs-up-icon.png";
                        }
                        
                        document.getElementById("supportNumCounter").value = xmlhttp.responseText;
                        
                        if (xmlhttp.responseText > "0") {
                            document.getElementById("supportNumCounter").style.visibility = 'visible';
                        }
                        
                        else {
                            document.getElementById("supportNumCounter").style.visibility = 'hidden';
                        }
                    }
                }

                xmlhttp.open("GET", "support?supportNumber=" + <%= idea.getSupports()%> 
                + "&accountNumber=" + <%= request.getSession().getAttribute("accountNumber")%>, true);
                xmlhttp.send();
            }
            function loadXMLDoc2(counter)
            {
                var xmlhttp2;
                if (window.XMLHttpRequest)
                {// code for IE7+, Firefox, Chrome, Opera, Safari
                    xmlhttp2 = new XMLHttpRequest();
                }
                else
                {// code for IE6, IE5
                    xmlhttp2 = new ActiveXObject("Microsoft.XMLHTTP");
                }
                xmlhttp2.onreadystatechange = function()
                {
                    if (xmlhttp2.readyState === 4 && xmlhttp2.status === 200)
                    {
                        if (document.getElementById("supports" + counter).getAttribute('src') === "img/thumbs-up-icon.png") {
                            document.getElementById("supports" + counter).src = "img/thumbs-up-icon-liked.png";
                        }
                        
                        else {
                            document.getElementById("supports" + counter).src = "img/thumbs-up-icon.png";
                        }

                        document.getElementById("commentSupportNumCounter" + counter).value = xmlhttp2.responseText;
                        
                        if (xmlhttp2.responseText > "0") {
                            document.getElementById("commentSupportNumCounter" + counter).style.visibility = 'visible';
                        }
                        
                        else {
                            document.getElementById("commentSupportNumCounter" + counter).style.visibility = 'hidden';
                        }
                    }
                }
                xmlhttp2.open("GET", "support?supportNumber=" + document.getElementById("numOfSup" + counter).value 
                        + "&accountNumber=" + <%= request.getSession().getAttribute("accountNumber")%>, true);
                xmlhttp2.send();
            }
            
            function updateIdea() {  
                var confirmUpdate = confirm("Update your idea with these changes?");
                
                if(confirmUpdate) {
                    var xmlhttp3;
                    if (window.XMLHttpRequest)
                    {// code for IE7+, Firefox, Chrome, Opera, Safari
                        xmlhttp3 = new XMLHttpRequest();
                    }
                    else
                    {// code for IE6, IE5
                        xmlhttp3 = new ActiveXObject("Microsoft.XMLHTTP");
                    }
                    xmlhttp3.onreadystatechange = function()
                    {
                        if (xmlhttp3.readyState === 4 && xmlhttp3.status === 200)
                        {  
//                            location.reload();
                            $('#editedOldPost').load(document.URL +  ' #oldPost');

                        }

                    }

                    var ideaTitle = document.getElementById("ideaTextInput").value;
                    
                    var ideaContent = document.getElementById("ideaTextAreaInput").value;
                                        
                    var updateIdeaNumber = <%= Integer.parseInt(request.getParameter("ideaNum"))%>;
                    
                    var supports = <%= idea.getSupports()%>;
                    
                    var accountNum = <%= request.getSession().getAttribute("accountNumber")%>;
                    
                    var latestMonth = document.getElementById("detIdeaCurrentMonth").value;
                    
                    var postData = "ideaTitle=" + encodeURIComponent(unescape(ideaTitle))
                        + "&ideaContent=" + encodeURIComponent(unescape(ideaContent))
                        + "&updateIdeaNumber=" + encodeURIComponent(unescape(updateIdeaNumber))  
                        + "&supports=" + encodeURIComponent(unescape(supports))  
                        + "&accountNumber=" + encodeURIComponent(unescape(accountNum))  
                        + "&latestMonth=" + encodeURIComponent(unescape(latestMonth));
                   
                    xmlhttp3.open("POST", "idea", true);
                    xmlhttp3.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    xmlhttp3.setRequestHeader("Content-length", postData.length);
                    xmlhttp3.send(postData);     
                            
                    editButton = document.getElementById("editPostButton");
                    deleteButton = document.getElementById("deletePostButton");
                    inputText = document.getElementById("ideaTextInput");
                    inputTextArea = document.getElementById("ideaTextAreaInput");

                    editButton.innerHTML = "Edit";
                    deleteButton.innerHTML = "Delete";
                    inputText.setAttribute("readonly", "readonly");
                    inputTextArea.setAttribute("readonly", "readonly");
                    deleteButton.onclick = deleteIdea;
                    editButton.onclick = changeToEditButtons;
                }
           
            }
            
            function deleteIdea() {
                var confirmDelete = confirm("Are you sure you wish to delete this idea?");
                
                if(confirmDelete) {
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
                              window.location.replace("./idea.jsp");
                        }

                    }

                    //TODO
                    xmlhttp.open("POST", "idea", true);
                    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    xmlhttp.send("ideaTitle=" + document.getElementById("ideaTextInput").value 
                            + "&ideaContent=" + document.getElementById("ideaTextAreaInput").value 
                            + "&deleteIdeaNumber=" + <%= Integer.parseInt(request.getParameter("ideaNum"))%>
                            + "&supports=" + <%= idea.getSupports()%>
                            + "&accountNumber=" + <%= request.getSession().getAttribute("accountNumber")%>
                            + "&latestMonth=" + document.getElementById("detIdeaCurrentMonth").value);
                }
                        
            }
            
            function changeToEditButtons() {
           
                console.log("\nInside changeToEditButtons");
                editButton = document.getElementById("editPostButton");
                deleteButton = document.getElementById("deletePostButton");
                inputText = document.getElementById("ideaTextInput");
                inputTextArea = document.getElementById("ideaTextAreaInput");

                originalIdeaTitle = inputText.value; 
                originalIdeaContent = inputTextArea.value;

                editButton.innerHTML = "Cancel";
                editButton.onclick = function() {
                    inputText.value = originalIdeaTitle;
                    inputTextArea.value = originalIdeaContent;
                    editButton.innerHTML = "Edit";
                    deleteButton.innerHTML = "Delete";
                    inputText.setAttribute("readonly", "readonly");
                    inputTextArea.setAttribute("readonly", "readonly");
                    editButton.onclick = changeToEditButtons;
                    deleteButton.onclick = deleteIdea;
                };


                deleteButton.innerHTML = "Update";
                deleteButton.onclick = updateIdea;

                inputText.removeAttribute("readonly"); 
                inputTextArea.removeAttribute("readonly");
                    
        
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

        <input id="detIdeaCurrentMonth" type="hidden" name="detIdeaCurrentMonth" value="<%=latestMonthStr%>"> 
        
        <div id="editedOldPost">
            
            <div id="oldPost">
                
                <div id="detPostHeaderBar">
                    <div id="postAuthor">
                        <label><%=name%></label> 
                    </div>
                    <div id="postDate">
                        <label><%=idea.getDate()%></label> 
                    </div>
                </div>
                    
                <img id="typeOfAccountPic"
                    <%
                        if (usQuery.isInv(idea.getAccountNumber())) {
                   
                            out.print("src=\"img/purple_investor_35x70.png\"/>"); 
                   
                        } else {
                   
                            out.print("src=\"img/purple_futurepreneur_40x70.png\"/>");              
                        }
                    %>
                

                <p>
                    <div id="postIdeaTitle"> 
                        <input type="text"
                               id="ideaTextInput"
                               class="userInput"
                               value="<%=idea.getIdeaTitle()%>" 
                               readonly="readonly"/>
                    </div>
                </p>
                <p> 
                    <div id="postIdeaContent">
                        <textarea id="ideaTextAreaInput" readonly="readonly"><%=idea.getIdea()%></textarea>
                    </div> 
                </p>
        
        <%

            if (suppQ.notAlreadySupp((Integer) request.getSession().getAttribute("accountNumber"), idea.getSupports())) {
                out.print("<div id=\"postSupports\">"
                        + "<div id=\"supportImageButton\">"
                        + "<input type=\"image\" src=\"img/thumbs-up-icon.png\""
                        + "onclick=\"loadXMLDoc()\""
                        + "id=\"supports\""
                        + "name=\"supports\""
                        + "/>" 
                        + "</div>"); 
                        
            } else {
                out.print("<div id=\"postSupports\">"
                        + "<div id=\"supportImageButtonLiked\">"
                        + "<input type=\"image\" src=\"img/thumbs-up-icon-liked.png\""
                        + "onclick=\"loadXMLDoc()\""
                        + "id=\"supports\""
                        + "name=\"supports\"/>"
//                        + "\" disabled=\"disabled\"/>"
                        + "</div>");
            }
            
            int numSupports = supQuery.getSupps(idea.getSupports());
            
            if (numSupports > 0) {
                out.print("<div id=\"supportNumberLabel\">"
                        + "<label>"
                        + "<input type=\"text\""
                        + "class=\"userInput\""
                        + "id=\"supportNumCounter\""
                        + "name=\"supportNumCounter\""
                        + "value=\""
                        + numSupports 
                        + "\" readonly=\"readonly\"/>"
                        + "</label>"
                        + "</div>"
                        + "</div>"
                        + "</div>");
            }
            
            else {
                out.print("<div id=\"supportNumberLabel\">"
                        + "<label>"
                        + "<input type=\"text\""
                        + "class=\"userInput\""
                        + "id=\"supportNumCounter\""
                        + "name=\"supportNumCounter\""
                        + "readonly=\"readonly\"/>"
                        + "</label>"
                        + "</div>"
                        + "</div>"
                        + "</div>");
            }
        %>
        
        </div>
        
        <div id="postOptions">
            <div id="lessIdea">
                <a class="lessIdeaLink" href="idea.jsp">See Less...</a>
            </div>

            <%

                int currentUserAccount = (Integer)(request.getSession().getAttribute("accountNumber"));
                int postUserAccount = idea.getAccountNumber();

                if (currentUserAccount == postUserAccount) {
                    out.print("<button type=\"button\""
                            + "id=\"editPostButton\""
                            + "onclick=\"changeToEditButtons()\""
                            + "class=\"postEdit\">"
                            + "Edit"
                            + "</button>"
                            + "<button type=\"button\""
                            + "id=\"deletePostButton\""
                            + "onclick=\"deleteIdea()\""
                            + "class=\"postDelete\">"
                            + "Delete"
                            + "</button>");
                }

            %>

        </div>
        
        <div id="detIdeaCommentSection">
            <%
                ArrayList<Object> comData = comQ.getComs(idea.getIdeaNumber());
                Iterator it = comData.iterator();
                int rows = ((Integer) it.next()).intValue(); // WHY IS THIS LIKE THISSSSSSSS

                while (it.hasNext()) {
                    Comment com = (Comment) it.next();
                    int counter = com.getSupports();
                    String comName = usQuery.getUserFullName(com.getAccountNumber());
                    out.println("<div id=\"userComment\">"
                            + "<div id=\"detCommentHeaderBar\">"
                            + "<div id=\"commentAuthor\">"
                            + "<label>" + comName + "</label>"
                            + "</div>"
                            + "<div id=\"commentDate\">"
                            + "<label>" + com.getDate() + "</label>" 
                            + "</div>"
                            + "</div>"
                            + "<img id=\"typeOfAccountPic\"");
               
                    if (usQuery.isInv(com.getAccountNumber())) {

                        out.print("src=\"img/purple_investor_35x70.png\"/>"); 

                    } else {

                        out.print("src=\"img/purple_futurepreneur_40x70.png\"/>");              
                    }
                   
                    out.print("<p>"
                         + "<div id=\"commentContent\">"
                         + "<textarea class=\"commentReadOnlyTextArea\" readonly=\"readonly\">"
                         + com.getComment()
                         + "</textarea>"
                         + "</div>"
                         + "</p>");

                    if (suppQ.notAlreadySupp((Integer) request.getSession().getAttribute("accountNumber"), com.getSupports())) {
                        out.print("<div id=\"commentSupports\">"
                                + "<div id=\"commentSupportImageButton\">"
                                + "<input type=\"image\" src=\"img/thumbs-up-icon.png\""
                                + "onclick=\"loadXMLDoc2(" + counter + ")\""
                                + "id=\"supports" + counter + "\""
                                + "name=\"suppComments\""
                                + "/>"
                                + "<input type=\"hidden\""
                                + "id=\"numOfSup" + counter + "\""
                                + "value=\"" + com.getSupports() + "\"/>"
                                + "</div>");
//                                        + "value=\"" + supQuery.getSupps(com.getSupports()) + "\"/>"
//                                        + "<input type=\"hidden\""
//                                        + "id=\"numOfSup" + counter + "\""
//                                        + "value=\"" + com.getSupports() + "\"/>");
                    } else {
                        out.print("<div id=\"commentSupports\">"
                                + "<div id=\"commentSupportImageButtonLiked\">"
                                + "<input type=\"image\" src=\"img/thumbs-up-icon-liked.png\""
                                + "onclick=\"loadXMLDoc2(" + counter + ")\""
                                + "id=\"supports" + counter + "\""
                                + "name=\"suppComments\""
                                + "/>"
                                + "<input type=\"hidden\""
                                + "id=\"numOfSup" + counter + "\""
                                + "value=\"" + com.getSupports() + "\"/>"
//                                        + "\" disabled=\"disabled\"/>"
                                + "</div>");                       

                    }

                    int numComSupports = supQuery.getSupps(com.getSupports());

                    if(numComSupports > 0) {
                        out.print("<div id=\"commentSupportNumberLabel\">"
                            + "<label>"
                            + "<input type=\"text\""
                            + "id=\"commentSupportNumCounter" + counter +"\""
                            + "class=\"commentSupportNumClass\""
                            + "name=\"commentSupportNumCounter\""
                            + "value=\""
                            + supQuery.getSupps(com.getSupports()) 
                            + "\" readonly=\"readonly\"/>"
                            + "</label>"
                            + "</div>"
                            + "</div>"
                            + "</div>");
                    }

                    else {
                        out.print("<div id=\"commentSupportNumberLabel\">"
                            + "<label>"
                            + "<input type=\"text\""
                            + "id=\"commentSupportNumCounter" + counter +"\""
                            + "class=\"commentSupportNumClass\""
                            + "name=\"commentSupportNumCounter\"" 
                            + "readonly=\"readonly\"/>"
                            + "</label>"
                            + "</div>"
                            + "</div>"
                            + "</div>");
                    }                            
                }
            %>

            <form action="comment" onsubmit="return checkIfBlank();" method="post">
                <div id="newCommentContainer">
                    <textarea name="comment" 
                              id="commentTextArea" 
                              onchange="enableBeforeUnload();"
                              onkeyup="enableBeforeUnload();"
                              placeholder="Add a comment..."></textarea>
                    <p>
                        <input type="hidden" name="number" value="<%= idea.getIdeaNumber()%>"/>
                    </p>
                    <p>
                        <input type="reset" class="commentReset" value="Cancel"/>
                        <input type="submit" class="commentSubmit" value="Comment"/>
                    </p>
                </div>
            </form>          
        </div>
                    
        <script>
//            $(document).ready(function() {
//
//                $('.lessIdeaLink').click(function() {
//                    return confirm('Return to ideas?');
//                });
//    
//            });
            function checkIfBlank() {
                if(document.getElementById("commentTextArea").value.toString().trim() === "") {
                    alert("Comment can't be blank");
                    return false;
                }
                
                disableBeforeUnload();
                return true;
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