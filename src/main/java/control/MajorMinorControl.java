/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import dbQuery.MajorQuery;
import dbQuery.MinorQuery;
import entities.Futurepreneur;
import entities.MajorMinor;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
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
public class MajorMinorControl extends HttpServlet {

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
            out.println("<title>Servlet MajorMinorControl</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MajorMinorControl at " + request.getContextPath() + "</h1>");
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
        StringBuffer url = null;
        
        url = request.getRequestURL();
      
        if (url.indexOf("getMinorsForEdit") > 0) {
            System.out.println("doGetMinorsForEditFuturepreneur");
            doGetMinorsForEditFuturepreneur(request, response);
        }
        else if (url.indexOf("getMajorsForEdit") > 0) {
            System.out.println("doGetMajorsForEditFuturepreneur");
            doGetMajorsForEditFuturepreneur(request, response);
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
        processRequest(request, response);
    }
    
    private void doGetMinorsForEditFuturepreneur(HttpServletRequest request, HttpServletResponse response) {
        MinorQuery minorQ = new MinorQuery();
        Futurepreneur user = (Futurepreneur) (request.getSession().getAttribute("user"));
        
        ArrayList<Object> minorData = minorQ.getMinorTable();
        Iterator it2 = minorData.iterator();
//        int rows = ((Integer) it2.next()).intValue();
        
        response.setContentType("text/html;charset=UTF-8");

        try {
            PrintWriter out = response.getWriter();
            int counter2 = 2;
            while (it2.hasNext()) {
                MajorMinor min = (MajorMinor) it2.next();

                if (counter2 == user.getMinor()) {
                    out.print("<option selected=\"selected\"");
                } else {
                    out.print("<option");
                }

                out.println(" value=\"" + counter2 + "\">" + min.getName() + "</option>");
                counter2++;
            }
        } catch (IOException ex) {
            Logger.getLogger(IdeaHubControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void doGetMajorsForEditFuturepreneur(HttpServletRequest request, HttpServletResponse response) {
        MajorQuery majorQ = new MajorQuery();
        Futurepreneur user = (Futurepreneur) (request.getSession().getAttribute("user"));
        
        ArrayList<Object> majorData = majorQ.getMajorTable();
        Iterator it = majorData.iterator();
//        int rows = ((Integer) it.next()).intValue();
         
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            PrintWriter out = response.getWriter();
            int counter = 2;
            while (it.hasNext()) {
                MajorMinor maj = (MajorMinor) it.next();
                if (counter == user.getMajor()) {
                    out.print("<option selected=\"selected\"");
                } else {
                    out.print("<option");
                }

                out.print(" value=\"" + counter + "\">" + maj.getName() + "</option>");
                counter++;
            }
        } catch (IOException ex) {
            Logger.getLogger(IdeaHubControl.class.getName()).log(Level.SEVERE, null, ex);
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
