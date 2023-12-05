/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sytt.order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import sytt.util.DBHelper;

/**
 *
 * @author Admin
 */
public class tblOrderDAO implements Serializable {
     public boolean InsertOrder(tblOrderDTO order)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1.make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.create sql string
                String sql = "Insert Into tblorder("
                        +"orderID, date, total"
                        +") Values ("
                        +"?, ?, ?"
                        +")";
                //3.create stm obj
                stm = con.prepareStatement(sql);
                stm.setString(1, order.getId());
                stm.setDate(2, order.getDatetime());
                stm.setFloat(3, order.getTotal());
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
}
