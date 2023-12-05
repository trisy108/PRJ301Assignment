/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sytt.OrderDetail;

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
public class OrderDetailDAO {
      private List<OrderDetailDTO> OrderDetailList;

    public List<OrderDetailDTO> getOrderDetailList() {
        return OrderDetailList;
    }
    
    
    public boolean InsertOrderDetail(OrderDetailDTO orderDetail)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1.make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.create sql string
                String sql = "Insert Into orderdetail("
                        +"sku, orderID, price, quantity, total"
                        +") Values ("
                        +"?, ?, ?, ?, ?"
                        +")";
                //3.create stm obj
                stm = con.prepareStatement(sql);
                stm.setString(1, orderDetail.getSku());
                stm.setString(2, orderDetail.getOrderID());
                stm.setFloat(3, orderDetail.getPrice());
                stm.setInt(4, orderDetail.getQuantity());
                stm.setFloat(5, orderDetail.getTotal());
                //4.execute
                int effectRows=stm.executeUpdate();
                //5.process (Note: Luu y Khi SU DUNG IF/WHILE)
                if(effectRows>0){
                    result=true;
                }
            }
        }  finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
       return result;
    }
    
    public void searchOrderDetailbyOrderId(String orderid) 
            throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1.make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.create sql string
                String sql = "Select sku,price ,quantity ,total "
                        + "From orderdetail "
                        + "Where orderID = ? "
                        ;
                //3.create stm obj
                stm = con.prepareStatement(sql);
                stm.setString(1, orderid); 
                //4.execute
                rs = stm.executeQuery();
                //5.process (Note: Luu y Khi SU DUNG IF/WHILE)
                while(rs.next()){
                    //5.1 map
                    //5.1.1 get data from result set
                    String sku=rs.getString("sku");
                    int quantity=rs.getInt("quantity");
                    float price=rs.getFloat("price");
                    float total=rs.getFloat("total");
                    //5.1.2 set properties of pro
                   OrderDetailDTO orderDetail=new OrderDetailDTO(sku, orderid, price, quantity, total);
                     if(this.OrderDetailList==null){
                        this.OrderDetailList= new ArrayList<>();
                        
                    }//end account list has not existed
                    this.OrderDetailList.add(orderDetail);
                }//end map DB to DTO
                
            }
            //end connect is available
           
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
