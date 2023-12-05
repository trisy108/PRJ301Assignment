package sytt.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sytt.cart.CartObj;

/**
 *
 * @author Admin
 */
@WebServlet(urlPatterns = {"/AddItemToCartServlet"})
public class AddItemToCartServlet extends HttpServlet {

    private final String SHOPPING_PAGE = "searchProductPage";

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
        //get contextScope
        ServletContext context = this.getServletContext();
        //get sitemap
        Properties siteMap = (Properties) context.getAttribute("SITEMAPS");
        //use 
        String url = siteMap.getProperty(SHOPPING_PAGE);
        //String url=SHOPPING_PAGE;
        String searchValue = request.getParameter("lastProductSearch");
        String quantity = request.getParameter("quantity");
        try {
            int addquantity = Integer.parseInt(quantity);
            //1.Cust goes to the carts place
            HttpSession session = request.getSession();
            //2.Cust take his/her cart
            CartObj cart = (CartObj) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartObj();
            }
            //3.cust drop iteam
            String[] skus = request.getParameterValues("check");
            //cart.addIteamToCart(sku,addquantity);
            if (skus != null) {
                for (String sku : skus) {
                    if (cart.getProductbySku(sku).isStatus()) {
                        cart.addItemtoCart(sku);
                    }
                }
            }
            session.setAttribute("CART", cart);
            url = "DispatchServlet"
                    + "?btAction=Search_Product"
                    + "&txtSearch_Product=" + searchValue;
            //4.cust continue go shopping           
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            response.sendRedirect(url);
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
