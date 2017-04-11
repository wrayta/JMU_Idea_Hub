package control;

import dbCommand.CommentUpdate;
import entities.Comment;
import entities.ErrorChecker;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet that handles comment post requests.
 *
 * @author Joe Otis Thomas Wray
 */
public class CommentControl extends HttpServlet {

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
            out.println("<title>Servlet CommentControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CommentControl at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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

        doAddComment(request, response);
    }

    /**
     * Adds comment
     *
     * @param request
     * @param response
     * @return true if added successfully
     */
    private boolean doAddComment(HttpServletRequest request, HttpServletResponse response) {
        boolean added = true;
        HttpSession sesh = request.getSession(true);
//        ErrorChecker err = new ErrorChecker();
//        String msg = "";
        Comment com;

//        if (!err.validComment(request.getParameter("comment"))) {
//            added = false;
//            msg += "<p>Comment must be less than 500 characters</p>";
//        }

        if (added) {
            com = new Comment(Integer.parseInt(request.getParameter("number")),
                    (Integer) sesh.getAttribute("accountNumber"),
                    request.getParameter("comment")
            );
            CommentUpdate update = new CommentUpdate();
            update.addComment(com);
            try {
                response.sendRedirect("./idea?ideaNum=" + Integer.parseInt(request.getParameter("number")));
            } catch (IOException ex) {
                Logger.getLogger(CommentControl.class.getName()).log(Level.SEVERE, null, ex);
            }
//        } else {
//            sesh.setAttribute("errMsg", msg);
//            try {
//                response.sendRedirect("./error.jsp");
//            } catch (IOException ex) {
//                Logger.getLogger(IdeaHubControl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
        }

        return added;
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
