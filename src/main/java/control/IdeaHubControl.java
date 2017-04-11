package control;

import dbCommand.CommentUpdate;
import dbCommand.IdeaUpdate;
import dbCommand.MessageUpdate;
import dbCommand.UserUpdate;
import dbQuery.UserQuery;
import entities.Comment;
import entities.ErrorChecker;
import entities.Futurepreneur;
import entities.Idea;
import entities.Investor;
import entities.Message;
import entities.User;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
//import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dbQuery.IdeaQuery;
import java.text.DateFormatSymbols;
import java.util.Calendar;

/**
 * Main servlet for the web application and handles most requests
 * 
 * @author Joe Otis Thomas Wray
 */
public class IdeaHubControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String x)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet IdeaHubControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>asdlfkjasdlf " + x + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Inside doGet");
        
        RequestDispatcher dispatcher;

        if (request.getParameter("ideaNum") != null) {
            dispatcher = getServletContext().getRequestDispatcher("/detIdea.jsp");
            dispatcher.forward(request, response);
        } else if (request.getParameter("accountNum") != null) {
            dispatcher = getServletContext().getRequestDispatcher("/profile.jsp");
            dispatcher.forward(request, response);
        } else if (request.getParameter("messageNum") != null) {
            dispatcher = getServletContext().getRequestDispatcher("/detMessage.jsp");
            dispatcher.forward(request, response);
        } else if (request.getParameter("currentMonth") != null) {
            System.out.println("Inside currentMonth param");
            doGetIdeasForMonth(request, response);
        } else;
        // I dont know what

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { // DO ERROR CHECKINGGGG!!!!!!!!
        // TO SELECT a major query for the majors and put in drop down
        
//        System.out.println("Inside doPost");
        
        String url = null;
        try {
            url = new URI(request.getHeader("referer")).getPath();
//            System.out.println("Start of new url: " + url);
        } catch (URISyntaxException ex) {
            Logger.getLogger(IdeaHubControl.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (request.getParameter("message") == null) {
            if (url.indexOf("futurepreneurSignUp") > 0) {
                doAddFuture(request, response);
            } else if (url.indexOf("investorSignUp") > 0) {
                doAddInvestor(request, response);
            } else if (url.indexOf("signInSignUp") > 0) {
                doSignIn(request, response);
//            } else if (url.indexOf("updateIdeaNum") > 0) {
//                System.out.println("Inside idea update!");
//                doUpdateIdea(request, response);
            } else if (url.indexOf("idea") > 0) {
//                System.out.println("Inside indexOf(idea)");
                
                if (request.getParameter("updateIdeaNumber") != null) {
//                    System.out.println("Inside update idea...");
                    doUpdateIdea(request, response);
                } else if (request.getParameter("deleteIdeaNumber") != null) {
                    doDeleteIdea(request, response);
                } else {
//                    System.out.println("Inside add idea...");
                    doAddIdea(request, response);
                }
                
            } else if (url.indexOf("forgotPass") > 0) {
                doRememberPass(request, response);
            } else if (url.indexOf("editFuturepreneur") > 0) {
                doUpdateFut(request, response);
            } else if (url.indexOf("editInvestor") > 0) {
                doUpdateInv(request, response);
            } else {
//                System.out.println("No URL matches");
            }
        } else {
            doAddMessage(request, response);
        }

    }
    
//    @Override
//    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        
//        String url = null;
//        try {
//            url = new URI(request.getHeader("referer")).getPath();
//            System.out.println("doDelete - Start of new url: " + url);
//        } catch (URISyntaxException ex) {
//            Logger.getLogger(IdeaHubControl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        if (url.indexOf("idea") > 0) {
//            doDeleteIdea(request, response);
//        }
//    }
    /**
     * Updates an investor entry
     *
     * @param request
     * @param response
     */
    private void doUpdateInv(HttpServletRequest request, HttpServletResponse response) {

        boolean added = true;
//        HttpSession sesh = request.getSession(true);
//        UserQuery query = new UserQuery();
        UserUpdate update = new UserUpdate();

//        String msg = "";
//
//        ErrorChecker err = new ErrorChecker();
        //out.print(request.getParameter("investorFirstName"));

//        if (!err.validFirstName(request.getParameter("investorFirstName"))) {
//            added = false;
//            msg += "<p>Invalid First Name: Must be smaller than 30 characters</p>";
//        }
//
//        if (!err.validLastName(request.getParameter("investorLastName"))) {
//            added = false;
//            msg += "<p>Invalid Last Name: Must be smaller than 40 characters</p>";
//
//        }
//
//        if (!err.validEmail(request.getParameter("investorEmail"))) {
//            added = false;
//            msg += "<p>Invalid Email: Must be smaller than 50 characters</p>";
//        }
//
//        if (!err.validUserName(request.getParameter("investorUsername"))) {
//            added = false;
//            msg += "<p>Invalid User Name: Must be smaller than 20 characters</p>";
//        }
//
//        if (!err.validPassword(request.getParameter("pwd1"))) {
//            added = false;
//            msg += "<p>Invalid Password: Must be between 8 and 12 characters</p>";
//        }
//
//        if (!err.validOccupation(request.getParameter("occupation"))) {
//            added = false;
//            msg += "<p>Invalid Occupation: Must be less than 50 characters</p>";
//        }
//
//        if (!err.validInterests(request.getParameter("interests"))) {
//            added = false;
//            msg += "<p>Invalid Interests: Must be less than 70 characters</p>";
//        }
//
//        if (!err.validWebsite(request.getParameter("website"))) {
//            added = false;
//            msg += "<p>Invalid Website: Must be less than 60 characters</p>";
//        }

        if (added) {
            String password = IdeaHubControl.hashPassword(request.getParameter("pwd1"));

            Investor inv = new Investor(request.getParameter("investorFirstName"),
                    request.getParameter("investorLastName"), 
                    request.getParameter("investorEmail"),
                    password, 
                    request.getParameter("investorUsername"), 
                    request.getParameter("occupation"),
                    request.getParameter("interests"), 
                    request.getParameter("website"),
                    (Integer) request.getSession().getAttribute("accountNumber"));

            update.updateInv(inv);

            try {
                response.sendRedirect("idea.jsp");
            } catch (IOException ex) {
                Logger.getLogger(IdeaHubControl.class.getName()).log(Level.SEVERE, null, ex);
            }
//        } else {
//
//            try {
//                sesh.setAttribute("errMsg", msg);
//
//                response.sendRedirect("./error.jsp");
//
//            } catch (IOException ex) {
//                Logger.getLogger(IdeaHubControl.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }

    /**
     * Updates a futurepreneur entry
     *
     * @param request
     * @param response
     */
    private void doUpdateFut(HttpServletRequest request, HttpServletResponse response) {

        boolean added = true;
//        UserQuery query = new UserQuery();
        UserUpdate update = new UserUpdate();
//        HttpSession sesh = request.getSession(true);
//
//        String msg = "";
//
//        ErrorChecker err = new ErrorChecker();

//        if (!err.validFirstName(request.getParameter("futurepreneurFirstName"))) {
//            added = false;
//            msg += "<p>Invalid First Name: Must be smaller than 30 characters</p>";
//        }
//
//        if (!err.validLastName(request.getParameter("futurepreneurLastName"))) {
//            added = false;
//            msg += "<p>Invalid Last Name: Must be smaller than 40 characters</p>";
//
//        }
//
//        if (!err.validEmail(request.getParameter("futurepreneurEmail"))) {
//            added = false;
//            msg += "<p>Invalid Email: Must be smaller than 50 characters</p>";
//        }
//
//        if (!err.validGPA(request.getParameter("gpa"))) {
//            added = false;
//            msg += "<p>Invalid GPA: Must be between 0.00 and 4.00</p>";
//        }
//
//        if (!err.validStanding(request.getParameter("gradesel"))) {
//            added = false;
//            msg += "<p>Invalid Academic Standing: Must be smaller than 9 characters</p>";
//        }
//
//        if (!err.validUserName(request.getParameter("futurepreneurUsername"))) {
//            added = false;
//            msg += "<p>Invalid User Name: Must be smaller than 20 characters</p>";
//        }
//
//        if (!err.validPassword(request.getParameter("pwd1"))) {
//            added = false;
//            msg += "<p>Invalid Password: Must be between 8 and 12 characters</p>";
//        }
//
//        if (!err.validGradDate(request.getParameter("year"))) {
//            added = false;
//            msg += "<p>Invalid Graduation Date: Must be smaller than 50 characters</p>";
//        }

        if (added) {
            String password = IdeaHubControl.hashPassword(request.getParameter("pwd1"));

            Futurepreneur fut = new Futurepreneur(request.getParameter("futurepreneurFirstName"),
                    request.getParameter("futurepreneurLastName"), 
                    request.getParameter("futurepreneurEmail"), 
                    password, 
                    request.getParameter("futurepreneurUsername"), 
                    request.getParameter("gradesel"), 
                    request.getParameter("year"), 
                    Double.parseDouble(request.getParameter("gpa")), 
                    Integer.parseInt(request.getParameter("majors")), 
                    Integer.parseInt(request.getParameter("minors")), 
                    (Integer) request.getSession().getAttribute("accountNumber"));

            update.updateFut(fut);

            try {
                response.sendRedirect("idea.jsp");
            } catch (IOException ex) {
                Logger.getLogger(IdeaHubControl.class.getName()).log(Level.SEVERE, null, ex);
            }
//        } else {
//            sesh.setAttribute("errMsg", msg);
//            try {
//                response.sendRedirect("./error.jsp");
//            } catch (IOException ex) {
//                Logger.getLogger(IdeaHubControl.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }

    /**
     * Allows the user to enter a new password
     *
     * @param request
     * @param response
     */
    private void doRememberPass(HttpServletRequest request, HttpServletResponse response) {
        UserUpdate update = new UserUpdate();
        boolean pass = update.updatePassword(request.getParameter("userName"),
                request.getParameter("password"));
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();

            if (pass) {
                out.print("<html><head><title>Password</title></head>"
                        + "<body><h3>Password Reset!</h3>"
                        + "<a href=\"signInSignUp.jsp\">Sign in to continue</a></body></html>");
            } else {
                out.print("<html><head><title>Password</title></head>"
                        + "<body><h3>Password not reset. Invalid UserName.</h3>"
                        + "<a href=\"forgotPass.jsp\">Try Again</a></body></html>");
            }
        } catch (IOException ex) {
            Logger.getLogger(IdeaHubControl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Adds a message
     *
     * @param request
     * @param response
     * @return
     */
    private boolean doAddMessage(HttpServletRequest request, HttpServletResponse response) {

        boolean added = true;

        HttpSession sesh = request.getSession(true);
        Message mes;
        ErrorChecker err = new ErrorChecker();
        String msg = "";
        UserQuery query = new UserQuery();
        int to = query.getUserNumber(request.getParameter("to"));

        if (!err.validMessageTitle(request.getParameter("title"))) {
            added = false;
            msg += "<p>Message Title must be less than 40 characters</p>";
        }
        if (!err.validMessage(request.getParameter("message"))) {
            added = false;
            msg += "<p>Message must be less than 1000 characters</p>";
        }
        if (to == -1) {
            added = false;
            msg += "<p>User does not exist</p>";
        }

        if (added) {
            mes = new Message(request.getParameter("message"), (Integer) sesh.getAttribute("accountNumber"),
                    to, request.getParameter("title"));

            MessageUpdate update = new MessageUpdate();
            update.addIdea(mes);
            try {
                response.sendRedirect("./message.jsp");
            } catch (IOException ex) {
                Logger.getLogger(IdeaHubControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            sesh.setAttribute("errMsg", msg);
            try {
                response.sendRedirect("./error.jsp");
            } catch (IOException ex) {
                Logger.getLogger(IdeaHubControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return added;
    }

    /**
     * Adds an idea
     *
     * @param request
     * @param response
     * @return
     */
    private boolean doAddIdea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean added = true;
        HttpSession sesh = request.getSession(true);
//        ErrorChecker err = new ErrorChecker();
        Idea idea;
//        String msg = "";

//        if (!err.validIdeaTitle(request.getParameter("title"))) {
//            added = false;
//            msg += "<p>Title must be less than 28 characters</p>";
//        }
//        if (!err.validIdea(request.getParameter("idea"))) {
//            added = false;
//            msg += "<p>Idea must be less than 500 characters</p>";
//        }

        if (added) {
            System.out.println("Make it inside of added?");
            
            idea = new Idea(((Integer) sesh.getAttribute("accountNumber")).intValue(),
                    request.getParameter("idea"), request.getParameter("title")
            );
            IdeaUpdate update = new IdeaUpdate();
            update.addIdea(idea);
            
            IdeaQuery ideaQ = new IdeaQuery();
       
//        ArrayList<Object> ideaData = ideaQ.getIdeas();
            ArrayList<Object> ideaData = ideaQ.getIdeasForMonth(request.getParameter("latestMonth"));

        
            sesh.setAttribute("ideaData", ideaData);
            request.getRequestDispatcher("/idea.jsp").forward(request, response);

//            try {
//                response.sendRedirect("./idea.jsp");
//            } catch (IOException ex) {
//                Logger.getLogger(IdeaHubControl.class.getName()).log(Level.SEVERE, null, ex);
//            }
            
            
            
//        } else {
//            sesh.setAttribute("errMsg", msg);
//            try {
//                response.sendRedirect("./error.jsp");
//            } catch (IOException ex) {
//                Logger.getLogger(IdeaHubControl.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }

        return added;
    }
    
    /**
     * Updates an existing idea
     * 
     * @param request
     * @param response
     * @return 
     */
    private boolean doUpdateIdea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean added = true;

        HttpSession sesh = request.getSession(true);
        
        Idea idea;
        
        if(added) {
            
            System.out.println("Inside doUpdateIdea added if-statement");
            
            int accountNumber = ((Integer) sesh.getAttribute("accountNumber")).intValue();
            
            System.out.println("Inside doUpdateIdea after accountNumber: " + accountNumber);
            
            String ideaContent = request.getParameter("ideaContent");
            
            System.out.println("Inside doUpdateIdea after ideaContent: " + ideaContent);
            
            int ideaNumber = Integer.parseInt(request.getParameter("updateIdeaNumber"));
            
            System.out.println("Inside doUpdateIdea after ideaNumber: " + ideaNumber);

            int supports = Integer.parseInt(request.getParameter("supports"));
            
            System.out.println("Inside doUpdateIdea after supports: " + supports);

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("'Modified:' MMMM dd, yyyy 'at' hh:mm a");
            String modifiedDate = sdf.format(new java.util.Date()).toString();
            
            String ideaTitle = request.getParameter("ideaTitle");
            
            System.out.println("Inside doUpdateIdea after ideaTitle: " + ideaTitle);

            
            idea = new Idea(accountNumber, ideaContent, ideaNumber, supports, modifiedDate, ideaTitle);
            
            IdeaUpdate update = new IdeaUpdate();
            update.updateIdea(idea);
            
            IdeaQuery ideaQ = new IdeaQuery();
            ArrayList<Object> ideaData = ideaQ.getIdeasForMonth(request.getParameter("latestMonth"));
            sesh.setAttribute("ideaData", ideaData);
            request.getRequestDispatcher("/idea.jsp").forward(request, response);
            
//            try {
//                response.sendRedirect("./idea.jsp");
//            } catch (IOException ex) {
//                Logger.getLogger(IdeaHubControl.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
        return added;
    }
    
    /**
     * deletes an existing idea
     * 
     * @param request
     * @param response 
     */
    private boolean doDeleteIdea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean added = true;
        HttpSession sesh = request.getSession(true);
        Idea idea;


        if (added) {
            System.out.println("Inside IdeaHubControl doDeleteIdea after added...");
            
            int accountNumber = ((Integer) sesh.getAttribute("accountNumber")).intValue();
            System.out.println("Inside doUpdateIdea after accountNumber: " + accountNumber);
            
            String ideaContent = request.getParameter("ideaContent");
            System.out.println("Inside doUpdateIdea after ideaContent: " + ideaContent);
                        
            int ideaNumber = Integer.parseInt(request.getParameter("deleteIdeaNumber"));
            System.out.println("Inside doUpdateIdea after ideaNumber: " + ideaNumber);

            int supports = Integer.parseInt(request.getParameter("supports"));
            System.out.println("Inside doUpdateIdea after supports: " + supports);

            
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("'Deleted:' MMMM dd, yyyy 'at' hh:mm a");
            String deletedDate = sdf.format(new java.util.Date()).toString();
            
            String ideaTitle = request.getParameter("ideaTitle");
            System.out.println("Inside doUpdateIdea after ideaTitle: " + ideaTitle);


            idea = new Idea(accountNumber, ideaContent, ideaNumber, supports, deletedDate, ideaTitle);
            
            IdeaUpdate update = new IdeaUpdate();
            update.deleteIdea(idea);
            
            IdeaQuery ideaQ = new IdeaQuery();
            ArrayList<Object> ideaData = ideaQ.getIdeasForMonth(request.getParameter("latestMonth"));
            sesh.setAttribute("ideaData", ideaData);
            request.getRequestDispatcher("/idea.jsp").forward(request, response);
//            try {
//                System.out.println("Trying to send redirect");
//                
//                response.sendRedirect("./homepage.jsp");
//                
//            } catch (IOException ex) {
//                Logger.getLogger(IdeaHubControl.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }

        return added;
    }

    /**
     * 
     */
    private void doGetIdeasForMonth(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Inside doGetIdeasForMonth");  
        HttpSession sesh = request.getSession(true);

        IdeaQuery ideaQ = new IdeaQuery();
       
//        ArrayList<Object> ideaData = ideaQ.getIdeas();
        ArrayList<Object> ideaData = ideaQ.getIdeasForMonth(request.getParameter("currentMonth"));

        
        sesh.setAttribute("ideaData", ideaData); 
        request.getRequestDispatcher("/idea.jsp").forward(request, response);

    }
    
    /**
     * 
     * @param num
     * @return 
     */
    String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month;
    }
    /**
     * Signs a user in
     *
     * @param request
     * @param response
     */
    private void doSignIn(HttpServletRequest request, HttpServletResponse response) {
        HttpSession sesh = request.getSession(true);
        UserQuery query = new UserQuery();
        IdeaQuery ideaQuery = new IdeaQuery();
        Calendar now = Calendar.getInstance();
        int month = (now.get(Calendar.MONTH));

        if (query.checkPass(request.getParameter("user").toLowerCase(), IdeaHubControl.hashPassword(request.getParameter("password")))) {
            sesh.setAttribute("loggedin", true);
            sesh.setAttribute("accountNumber", new Integer(query.getAccountNum(request.getParameter("user"))));
            
            String monthStr = getMonthForInt(month);
            ArrayList<Object> ideaData = ideaQuery.getIdeasForMonth(monthStr);
//            System.out.println("ideaData in IdeaHubControl size: " + ideaData.size());
            sesh.setAttribute("ideaData", ideaData);
            try {
                response.sendRedirect("./idea.jsp");
            } catch (IOException ex) {
                Logger.getLogger(IdeaHubControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            sesh.setAttribute("loggedin", false);
            sesh.setAttribute("notLoggedin", true);
            try {
                response.sendRedirect("./signInSignUp.jsp");
            } catch (IOException ex) {
                Logger.getLogger(IdeaHubControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Adds an investor
     *
     * @param request
     * @param response
     * @return
     */
    private boolean doAddInvestor(HttpServletRequest request, HttpServletResponse response) {
        boolean added = true;
        User person;
        HttpSession sesh = request.getSession(true);
        UserQuery query = new UserQuery();
        String password = IdeaHubControl.hashPassword(request.getParameter("pwd1"));
        String msg = "";

//        ErrorChecker err = new ErrorChecker();
//        out.print(request.getParameter("investorFirstName"));
//
//        if (!err.validFirstName(request.getParameter("investorFirstName"))) {
//            added = false;
//            msg += "<p>Invalid First Name: Must be smaller than 30 characters</p>";
//        }
//
//        if (!err.validLastName(request.getParameter("investorLastName"))) {
//            added = false;
//            msg += "<p>Invalid Last Name: Must be smaller than 40 characters</p>";
//
//        }
//
//        if (!err.validEmail(request.getParameter("investorEmail"))) {
//            added = false;
//            msg += "<p>Invalid Email: Must be smaller than 50 characters</p>";
//        }
//
//        if (!err.validUserName(request.getParameter("investorUsername"))) {
//            added = false;
//            msg += "<p>Invalid User Name: Must be smaller than 20 characters</p>";
//        }
//
//        if (!err.validPassword(request.getParameter("pwd1"))) {
//            added = false;
//            msg += "<p>Invalid Password: Must be between 8 and 12 characters</p>";
//        }
//
//        if (!err.validOccupation(request.getParameter("occupation"))) {
//            added = false;
//            msg += "<p>Invalid Occupation: Must be less than 50 characters</p>";
//        }
//
//        if (!err.validInterests(request.getParameter("interests"))) {
//            added = false;
//            msg += "<p>Invalid Interests: Must be less than 70 characters</p>";
//        }
//
//        if (!err.validWebsite(request.getParameter("website"))) {
//            added = false;
//            msg += "<p>Invalid Website: Must be less than 60 characters</p>";
//        }

//        if (added) {
            person = new Investor(request.getParameter("investorFirstName"),
                    request.getParameter("investorLastName"),
                    request.getParameter("investorEmail"),
                    password, request.getParameter("investorUsername"), request.getParameter("occupation"),
                    request.getParameter("interests"), request.getParameter("website"));

            UserUpdate update = new UserUpdate();
            update.addUser(person);

            sesh.setAttribute("loggedin", true);
            sesh.setAttribute("accountNumber", new Integer(query.getNextAccountNum()));
            try {
                response.sendRedirect("./idea.jsp");
            } catch (IOException ex) {
                Logger.getLogger(IdeaHubControl.class.getName()).log(Level.SEVERE, null, ex);
            }

//        } else {
//
//            try {
//                sesh.setAttribute("errMsg", msg);
//
//                response.sendRedirect("./error.jsp");
//
//            } catch (IOException ex) {
//                Logger.getLogger(IdeaHubControl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }

        return added;
    }

    /**
     * Adds a futurepreneur
     *
     * @param request
     * @param response
     * @return
     */
    private boolean doAddFuture(HttpServletRequest request, HttpServletResponse response) {
        boolean added = true;
        User person;
        HttpSession sesh = request.getSession(true);
        UserQuery query = new UserQuery();
//        String msg = "";
//
//        ErrorChecker err = new ErrorChecker();
//
//        if (!err.validFirstName(request.getParameter("futurepreneurFirstName"))) {
//            added = false;
//            msg += "<p>Invalid First Name: Must be smaller than 30 characters</p>";
//        }
//
//        if (!err.validLastName(request.getParameter("futurepreneurLastName"))) {
//            added = false;
//            msg += "<p>Invalid Last Name: Must be smaller than 40 characters</p>";
//
//        }
//
//        if (!err.validEmail(request.getParameter("futurepreneurEmail"))) {
//            added = false;
//            msg += "<p>Invalid Email: Must be smaller than 50 characters</p>";
//        }
//
//        if (!err.validGPA(request.getParameter("gpa"))) {
//            added = false;
//            msg += "<p>Invalid GPA: Must be between 0.00 and 4.00</p>";
//        }
//
//        if (!err.validStanding(request.getParameter("gradesel"))) {
//            added = false;
//            msg += "<p>Invalid Academic Standing: Must be smaller than 9 characters</p>";
//        }
//
//        if (!err.validUserName(request.getParameter("futurepreneurUsername"))) {
//            added = false;
//            msg += "<p>Invalid User Name: Must be smaller than 20 characters</p>";
//        }
//
//        if (!err.validPassword(request.getParameter("pwd1"))) {
//            added = false;
//            msg += "<p>Invalid Password: Must be between 8 and 12 characters</p>";
//        }
//
//        if (!err.validGradDate(request.getParameter("year"))) {
//            added = false;
//            msg += "<p>Invalid Graduation Date: Must be smaller than 50 characters</p>";
//        }
//
//        if (added) {
            String password = IdeaHubControl.hashPassword(request.getParameter("pwd1"));

            person = new Futurepreneur(request.getParameter("futurepreneurFirstName"),
                    request.getParameter("futurepreneurLastName"),
                    request.getParameter("futurepreneurEmail"),
                    password, request.getParameter("futurepreneurUsername"),
                    request.getParameter("gradesel"), request.getParameter("year"),
                    Double.parseDouble(request.getParameter("gpa")), Integer.parseInt(request.getParameter("majors")),
                    Integer.parseInt(request.getParameter("minors")));
            
            
            UserUpdate update = new UserUpdate();
            update.addUser(person);

            sesh.setAttribute("loggedin", true);
            sesh.setAttribute("accountNumber", query.getNextAccountNum());

            try {
                response.sendRedirect("idea.jsp");
            } catch (IOException ex) {
                Logger.getLogger(IdeaHubControl.class.getName()).log(Level.SEVERE, null, ex);
            }

//        } else {
//            sesh.setAttribute("errMsg", msg);
//            try {
//                response.sendRedirect("./error.jsp");
//            } catch (IOException ex) {
//                Logger.getLogger(IdeaHubControl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        }

        return added;
    }

    /**
     * Hashes a password
     *
     * @param password
     * @return
     */
    public static String hashPassword(String password) {
        String digest;
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            md.reset();
            byte[] bytes = md.digest(password.getBytes());
            digest = new BigInteger(1, bytes).toString(16);
        } catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
            digest = null;
        }
        return digest;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
