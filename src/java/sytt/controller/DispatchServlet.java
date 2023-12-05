/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sytt.controller;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sytt.util.MyApplicationConStants;

/**
 *
 * @author Admin
 */
@WebServlet(urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {
//   private final String LOGIN_PAGE = "Login.html";
//  private final String LOGIN_PAGE = "";
//  private final String LOGIN_CONTROLLER = "LoginServlet";
//   private final String LOGIN_CONTROLLER = "loginContrller";
//    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastnameServlet";
//    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
//    private final String UPDATE_ACCOUNT_CONTRLLER = "UpdateServlet";
//    private final String START_UP_CONTROLLER = "StartUpServlet";
//    private final String ADD_ITEM_TO_CART_CONTROLLER = " AddItemToCartServlet";
//    private final String VIEW_PAGE = "viewCart.jsp";
//    private final String REMOVE_ITEM_FROM_YOUR_CART = "RemoveItemsFromCartServlet";
//    private final String CREATE_ACCOUNT_CONTROLLER = "CreateAccountServlet";

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
        //Which button did user click?
        String button = request.getParameter("btAction");
        //1.Get context scope
        ServletContext context = this.getServletContext();
        //2. Get SiteMaps
        Properties siteMap = (Properties) context.getAttribute("SITEMAPS");
        //3. Use SiteMaps to get url Pattern
//        String url = LOGIN_PAGE;
        String url = siteMap.getProperty(
                MyApplicationConStants.DispatchFeatures.LOGIN_PAGE);
        try {
            if (button == null) {
                //trigger welcome file
                //url=START_UP_CONTROLLER;
                url = siteMap.getProperty(
                        MyApplicationConStants.DispatchFeatures.START_UP_CONTROLLER);
            } else if (button.equals("Login")) {//user click Login
                url = siteMap.getProperty(
                        MyApplicationConStants.DispatchFeatures.LOGIN_CONTROLLER);
            } else if (button.equals("Search")) {
                //url=SEARCH_LASTNAME_CONTROLLER;
                url = siteMap.getProperty(
                        MyApplicationConStants.DispatchFeatures.SEARCH_LASTNAME_CONTROLLER);
            } else if (button.equals("delete")) {
                //url=DELETE_ACCOUNT_CONTROLLER;
                url = siteMap.getProperty(
                        MyApplicationConStants.DispatchFeatures.DELETE_ACCOUNT_CONTROLLER);
            } else if (button.equals("Update")) {
                //url=UPDATE_ACCOUNT_CONTRLLER;
                url = siteMap.getProperty(
                        MyApplicationConStants.DispatchFeatures.UPDATE_ACCOUNT_CONTRLLER);
            } else if (button.equals("logout")) {
                //url=LOG_OUT;
                url = siteMap.getProperty(
                        MyApplicationConStants.DispatchFeatures.LOG_OUT);
            } else if (button.equals("AddItem")) {
                //url=ADD_TO_CART;
                url = siteMap.getProperty(
                        MyApplicationConStants.DispatchFeatures.ADD_TO_CART);
            } else if (button.equals("ViewCart")) {
                //url=VIEW_PAGE_;
                url = siteMap.getProperty(
                        MyApplicationConStants.DispatchFeatures.VIEW_PAGE);
            } else if (button.equals("remove")) {
                //url=REMOVE_CART;
                url = siteMap.getProperty(
                        MyApplicationConStants.DispatchFeatures.REMOVE_CART);
            } else if (button.equals("Search_Product")) {
                //url=SEARCH_PRODUCT;
                url = siteMap.getProperty(
                        MyApplicationConStants.DispatchFeatures.SEARCH_PRODUCT);
            } else if (button.equals("createAcount")) {
                //url=CREATE_ACCOUNT;
                url = siteMap.getProperty(
                        MyApplicationConStants.DispatchFeatures.CREATE_ACCOUNT);
            } else if (button.equals("checkout")) {
                url = siteMap.getProperty(
                        MyApplicationConStants.DispatchFeatures.CHECK_OUT_CONTROLLER);
            }
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
