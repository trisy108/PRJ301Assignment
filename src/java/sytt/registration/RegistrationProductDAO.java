/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sytt.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sytt.util.DBHelper;

/**
 *
 * @author Admin
 */
public class RegistrationProductDAO implements Serializable{
    
    private List<RegistrationtblProduct> produclist;

    public List<RegistrationtblProduct> getProductlist() {
        return produclist;
    }
    
//    public void searchProduct(String searchIteam) throws NamingException, SQLException{
     public void searchProduct(String searchIteam) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
       
        try {
            //1.make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.create sql string
                String sql = "Select sku,name ,quantity ,price ,status "
                        + "From tblProduct "
                        + "Where name Like ? "
                        ;
                //3.create stm obj
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchIteam +"%");
                
                //4.execute
                rs = stm.executeQuery();
                //5.process (Note: Luu y Khi SU DUNG IF/WHILE)
                while(rs.next()){
                    //5.1 map
                    //5.1.1 get data from result set
                    String sku=rs.getString("sku");
                    String name=rs.getString("name");
                    int quantity=rs.getInt("quantity");
                    float price=rs.getFloat("price");
                    boolean status =rs.getBoolean("status");
                    //5.1.2 set properties of pro
                   RegistrationtblProduct product=new RegistrationtblProduct(sku, name, quantity, price, status);
                    
                    //5.2 add data to list
                    if(this.produclist==null){
                        this.produclist= new ArrayList<>();
                        
                    }//end account list has not existed
                    this.produclist.add(product);
                }//end map DB to DTO
            }//end connect is available
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stm != null) {
                con.close();
            }
            if (con != null) {
                con.close();
            }

        }
        
    }
    
    
    public RegistrationtblProduct searchProductbySku(String skuiteam) 
            throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1.make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.create sql string
                String sql = "Select sku ,name ,quantity ,price ,status "
                        + "From tblProduct "
                        + "Where sku = ? "
                        ;
                //3.create stm obj
                stm = con.prepareStatement(sql);
                stm.setString(1, skuiteam); 
                //4.execute
                rs = stm.executeQuery();
                //5.process (Note: Luu y Khi SU DUNG IF/WHILE)
                while(rs.next()){
                    //5.1 map
                    //5.1.1 get data from result set
                    String sku=rs.getString("sku");
                    String name=rs.getString("name");
                    int quantity=rs.getInt("quantity");
                    float price=rs.getFloat("price");
                    boolean status =rs.getBoolean("status");
                    //5.1.2 set properties of pro
                   RegistrationtblProduct product=new RegistrationtblProduct(sku, name, quantity, price, status);
                    return product;
                }//end map DB to DTO
                
            }
            //end connect is available
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stm != null) {
                con.close();
            }
            if (con != null) {
                con.close();
            }

        }
        
    }
}
