/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import dbQuery.UserQuery;
import entities.ErrorChecker;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
        if (request.getParameter("username") != null) {
            System.out.println("doGet check for username");
            doCheckUsername(request, response);
        }
        else if (request.getParameter("editedUsername") != null) {
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
    }

    private void doCheckUsername(HttpServletRequest request, HttpServletResponse response) {
        ErrorChecker errorCheck = new ErrorChecker();
        UserQuery userQ = new UserQuery();
        
        response.setContentType("text/text");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            
            if(!errorCheck.validateUsername(request.getParameter("username")) ) {
                out.print("User name is invalid");                
            } else if(userQ.getUser(request.getParameter("username"))) {
                out.print("User already exists");
            } else if(!userQ.getUser(request.getParameter("username"))) {
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
            
            if(!errorCheck.validateUsername(request.getParameter("editedUsername")) ) {
                out.print("User name is invalid");                
            } 
            else if(userQ.getUser((Integer) request.getSession().getAttribute("accountNumber")).equals(request.getParameter("editedUsername"))) {
                out.print("User name is valid");
            }            
            else if(userQ.getUser(request.getParameter("editedUsername"))) {
                out.print("User already exists");
            } else if(!userQ.getUser(request.getParameter("editedUsername"))) {
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
