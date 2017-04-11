/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dbCommand.SupportUpdate;
import dbQuery.SupportQuery;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet that handles support requests
 *
 * @author Joe Otis Thomas Wray
 */
public class SupportControl extends HttpServlet {

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
            out.println("<title>Servlet SupportControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SupportControl at " + request.getContextPath() + "</h1>");
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
        
        Boolean notAlreadySupported = true;
        SupportQuery query = new SupportQuery();
        
        notAlreadySupported = query.notAlreadySupp((Integer) (request.getSession().getAttribute("accountNumber")),
                Integer.parseInt(request.getParameter("supportNumber")));
        
        log(request.getParameter("supportNumber"));
                
        if(notAlreadySupported) {
            doIncrementSupps(request, response);
        }
        
        else {
            doDecrementSupps(request, response);
        }
        
//        log(request.getParameter("supportNumber"));
//        doIncrementSupps(request, response);
    }

    /** 
     * Decrements the supports
     */
    private void doDecrementSupps(HttpServletRequest request, HttpServletResponse response) {
        SupportQuery query = new SupportQuery();
        SupportUpdate update = new SupportUpdate();

        update.decrementSupps(Integer.parseInt(request.getParameter("supportNumber")),
                (Integer) request.getSession().getAttribute("accountNumber"));

        int newSupps = query.getSupps(Integer.parseInt(request.getParameter("supportNumber")));

        response.setContentType("text/text");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException ex) {
            Logger.getLogger(SupportControl.class.getName()).log(Level.SEVERE, null, ex);
        }

        out.print("" + newSupps);

    }
    
    /**
     * Increments the supports
     *
     * @param request
     * @param response
     */
    private void doIncrementSupps(HttpServletRequest request, HttpServletResponse response) {
        SupportQuery query = new SupportQuery();
        SupportUpdate update = new SupportUpdate();

        update.incrementSupps(Integer.parseInt(request.getParameter("supportNumber")),
                (Integer) request.getSession().getAttribute("accountNumber"));

        int newSupps = query.getSupps(Integer.parseInt(request.getParameter("supportNumber")));

        response.setContentType("text/text");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException ex) {
            Logger.getLogger(SupportControl.class.getName()).log(Level.SEVERE, null, ex);
        }

        out.print("" + newSupps);

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
        processRequest(request, response);
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
