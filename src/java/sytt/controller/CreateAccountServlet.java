    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sytt.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sytt.registration.RegistrationCreateError;
import sytt.registration.RegistrationDAO;
import sytt.registration.RegistrationDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {

    private final String RESULT_PAGE = "createNewAccountPage";
    private final String LOGIN_PAGE = "";

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
        String url=RESULT_PAGE;
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullname");
        boolean foundErr = false;
        RegistrationCreateError Error = new RegistrationCreateError();
        try {
            //1.Check all user's errors
            if (username.trim().length() < 6
                    || username.trim().length() > 20) {
                foundErr = true;
                Error.setUsernameLengthErr("Username is inputed from 6 to 20 characters");
            }
            if (password.trim().length() < 8
                    || password.trim().length() > 30) {
                foundErr = true;
                Error.setPasswordLengthErr("Password is inputed from 8 to 30 characters");
            } else if (!confirm.trim().equals(password.trim())) {
                foundErr = true;
                Error.setConfirmNotMatched("Confirm must match password");
            }
            if (fullName.trim().length() < 2
                    || fullName.trim().length() > 50) {
                foundErr = true;
                Error.setFullNameLengthErr("Full name inputed from 2 to 50 characters");
            }
            if (foundErr) {
                request.setAttribute("CREATE_ERROR", Error);
            } else {
                //2.Call DAO
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO account = new RegistrationDTO(
                        username, password, fullName, false);
                boolean result = dao.createAccount(account);
                //3.Process Result 
                if (result) {
                    url = LOGIN_PAGE;
                }//Creating action is successful
            }//do nothhing 
        } catch (NamingException ex) {
            log("CreateNewAccount_naming" + ex.getMessage());
        } catch (SQLException ex) {
            log("CreateNewAccount_SQL" + ex.getMessage());
            Error.setUsernameIsExisted(username + " is Existed in system");
            request.setAttribute("CREATE_ERROR", Error);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
