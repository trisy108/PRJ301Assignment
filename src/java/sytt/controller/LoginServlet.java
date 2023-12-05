package sytt.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sytt.registration.RegistrationDAO;
import sytt.registration.RegistrationDTO;
import sytt.util.MyApplicationConStants;

/**
 *
 * @author Admin
 */
public class LoginServlet extends HttpServlet {

//    private final String SEARCH_PAGE = "Search.html";
//    private final String SEARCH_PAGE = "Search.jsp";
//    private final String SEARCH_PAGE = "searchPage";
//    private final String INVALID_PAGE = "Invalid.html";
//    private final String INVALID_PAGE = "invalidPage";
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
        String username = request.getParameter("txtUserName");
        String password = request.getParameter("txtPassword");
//        String url = INVALID_PAGE;
        //1.Get context scope
        ServletContext context = this.getServletContext();
        //2. Get SiteMaps
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        //3. Use SiteMaps to get url Pattern
//        String url = LOGIN_PAGE;
        String url = siteMaps.getProperty(
                MyApplicationConStants.LoginFeatures.INVALID_PAGE);
        try {
            //1.call Model- DAO
            //1.1 new DAO object
            RegistrationDAO dao = new RegistrationDAO();
            //1.2 call method of DAO
//            boolean result = dao.checkLogin(username, password);
            RegistrationDTO result = dao.checkLogin(username, password);
            //2. process result
            if (result != null) {
                url = siteMaps.getProperty(
                        MyApplicationConStants.LoginFeatures.SEARCH_PAGE);
                HttpSession session = request.getSession(true);
                session.setAttribute("USER", result);
//                Cookie cookie = new Cookie(username, password);
//                cookie.setMaxAge(60 * 10);
//                response.addCookie(cookie);
            }//end user has authenticated

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        } finally {
            response.sendRedirect(url);
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
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
