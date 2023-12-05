/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sytt.controller;

import sytt.cart.CartObj;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sytt.OrderDetail.OrderDetailDAO;
import sytt.OrderDetail.OrderDetailDTO;
import sytt.order.tblOrderDAO;
import sytt.order.tblOrderDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

    private final String VIEW_PAGE = "viewPage";
    private final String RESULT_PAGE = "checkoutsuccess";

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
        String url = siteMap.getProperty(VIEW_PAGE);
        long millis = System.currentTimeMillis();
        java.sql.Date datetime = new java.sql.Date(millis);
        float total = (float) 0.0;

        try {
            //1.cust goes cart place
            HttpSession session = request.getSession();
            //2.cust take his/her cart
            CartObj cart = (CartObj) session.getAttribute("CART");
            if (cart != null) {
                String uniqueID = UUID.randomUUID().toString().substring(0, 7);
                Map<String, Integer> items = cart.getItems();
                if (items != null) {
                    for (String key : items.keySet()) {
                        total += items.get(key) * cart.getProductbySku(key).getPrice();
                    }
                    tblOrderDTO order = new tblOrderDTO(uniqueID, datetime, total);
                    tblOrderDAO orderDao = new tblOrderDAO();
                    orderDao.InsertOrder(order);

                    for (String key : items.keySet()) {
                        String productID = cart.getProductbySku(key).getSku();
                        int quantity = items.get(key);
                        String orderID = order.getId();
                        float price = (float) cart.getProductbySku(key).getPrice();
                        float totalEachProduct = price * quantity;
                        OrderDetailDTO orderDetail
                                = new OrderDetailDTO(productID, orderID, price, quantity, totalEachProduct);
                        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                        orderDetailDAO.InsertOrderDetail(orderDetail);
                    }
                    url = siteMap.getProperty(RESULT_PAGE);
                }
                session.setAttribute("CART", null);

                //tìm cách hiện ra bill tiền
                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                orderDetailDAO.searchOrderDetailbyOrderId(uniqueID);
                List<OrderDetailDTO> OderDetail = orderDetailDAO.getOrderDetailList();
                session.setAttribute("OrderID", uniqueID);
                session.setAttribute("DateTime", datetime);
                session.setAttribute("YourOrderDetailList", OderDetail);
                session.setAttribute("total", total);
                CartObj cartnew = new CartObj();
                session.setAttribute("cart", cartnew);
            }
            session.setAttribute("CART", null);
            response.sendRedirect(url);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        } finally {

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
