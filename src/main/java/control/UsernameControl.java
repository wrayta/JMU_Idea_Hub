/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dbCommand.UserUpdate;
import dbQuery.UserQuery;
import entities.ErrorChecker;
import entities.Futurepreneur;
import entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thomas
 */
public class UsernameControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UsernameControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UsernameControl at " + request.getContextPath() + "</h1>");
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
        String url = null;
        try {
            url = new URI(request.getHeader("referer")).getPath();
            System.out.println("Start of new url: " + url);
            
        } catch (URISyntaxException ex) {
            Logger.getLogger(IdeaHubControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (request.getParameter("username") != null) {
            System.out.println("doGet check for username");
            doCheckUsername(request, response);
        } else if (request.getParameter("editedUsername") != null) {
            System.out.println("doGet check for editedUsername");
            doCheckEditedUsername(request, response);
        } 
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
            throws ServletException, IOException {
        
        StringBuffer url = null;
        
        url = request.getRequestURL();
        
        if (url.indexOf("updateFuturepreneur") > 0) {
            System.out.println("Mapping to updateFuturepreneur success");
            doUpdateFut(request, response);
        }
        
    }
    
    private void doUpdateFut(HttpServletRequest request, HttpServletResponse response) {

        boolean added = true;
//        UserQuery query = new UserQuery();
        UserUpdate update = new UserUpdate();
        UserQuery user = new UserQuery();
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

//            System.out.println("Last name: " + request.getParameter("futurepreneurLastName"));
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

//            request.getSession().setAttribute("firstName", request.getParameter("futurepreneurFirstName"));
            User editedFuturepreneur = user.getFut((Integer) (request.getSession().getAttribute("accountNumber")));
            request.getSession().setAttribute("user", editedFuturepreneur);

//            response.setStatus(200);
            
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

    private void doCheckUsername(HttpServletRequest request, HttpServletResponse response) {
        ErrorChecker errorCheck = new ErrorChecker();
        UserQuery userQ = new UserQuery();

        response.setContentType("text/text");
        PrintWriter out = null;
        try {
            out = response.getWriter();

            if (!errorCheck.validateUsername(request.getParameter("username"))) {
                out.print("User name is invalid");
            } else if (userQ.getUser(request.getParameter("username"))) {
                out.print("User already exists");
            } else if (!userQ.getUser(request.getParameter("username"))) {
                out.print("User name is valid");
            }
        } catch (IOException ex) {
            Logger.getLogger(UsernameControl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void doCheckEditedUsername(HttpServletRequest request, HttpServletResponse response) {
        ErrorChecker errorCheck = new ErrorChecker();
        UserQuery userQ = new UserQuery();

        response.setContentType("text/text");
        PrintWriter out = null;
        try {
            out = response.getWriter();

            if (!errorCheck.validateUsername(request.getParameter("editedUsername"))) {
                out.print("User name is invalid");
            } else if (userQ.getUser((Integer) request.getSession().getAttribute("accountNumber")).equals(request.getParameter("editedUsername"))) {
                out.print("User name is valid");
            } else if (userQ.getUser(request.getParameter("editedUsername"))) {
                out.print("User already exists");
            } else if (!userQ.getUser(request.getParameter("editedUsername"))) {
                out.print("User name is valid");
            }
        } catch (IOException ex) {
            Logger.getLogger(UsernameControl.class.getName()).log(Level.SEVERE, null, ex);
        }

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
