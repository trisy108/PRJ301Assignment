/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sytt.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sytt.registration.RegistrationProductDAO;
import sytt.registration.RegistrationtblProduct;

/**
 *
 * @author Admin
 */
@WebServlet(name = "SearchProduct", urlPatterns = {"/SearchProduct"})
public class SearchProduct extends HttpServlet {
private String SEARCH_PRODUCT_PAGE="searchProductPage";
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
        ServletContext context= this.getServletContext();
        //get sitemap
        Properties siteMap=(Properties)context.getAttribute("SITEMAPS");
        //use 
        String url=siteMap.getProperty(SEARCH_PRODUCT_PAGE);
//        String url=SEARCH_PRODUCT_PAGE;
        try {
             String searchvalue=request.getParameter("txtSearch_Product");
            //1.check valid search value
            if(!searchvalue.trim().isEmpty()){
                //2.call model
                //2.1 new DAO obj
                RegistrationProductDAO dao=new RegistrationProductDAO();
                //2.2 call method of DAO
                dao.searchProduct(searchvalue);
                //3. process result
                List<RegistrationtblProduct> result=dao.getProductlist();
                if(result!=null){
//                url=SEARCH_PRODUCT_PAGE;
                request.setAttribute("SEARCH_PRODUCT_RESULT", result);
                }
            }//end search value has valid value
        
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(NamingException ex){
            ex.printStackTrace();
        } 
        finally{
             RequestDispatcher rd=request.getRequestDispatcher(url);
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
