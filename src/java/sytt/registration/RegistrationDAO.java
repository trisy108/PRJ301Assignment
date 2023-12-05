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
public class RegistrationDAO implements Serializable {

//    public boolean checkLogin(String username, String password)
//            throws SQLException, NamingException {
    public RegistrationDTO checkLogin(String username, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
//        boolean result = false;
        RegistrationDTO result = null;
        try {
            //1. Make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Select lastname, isAdmin "
                        + "From Registration "
                        + "Where username = ? "
                        + "And password = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process
                if (rs.next()) {
                    //map: get data from ResultSet & set data to properties's DTO
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");

                    result = new RegistrationDTO(username, null, fullName, role);
                }//end username and password is verified
            }//end connection is available
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    private List<RegistrationDTO> accountList;

    public List<RegistrationDTO> getAccountList() {
        return accountList;
    }

    public void searchLastName(String searchValue)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. Make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname Like ? ";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process(Luu y khi su dung if/while)
                while (rs.next()) {
                    //5.1 map
                    //5.1.1 get du lieu tu Result Set
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("lastName");
                    boolean role = rs.getBoolean("isAdmin");
                    //5.1.2 set properties 
                    RegistrationDTO dto = new RegistrationDTO(username, password, fullname, role);
                    //5.2 add data to list
                    if (this.accountList == null) {
                        this.accountList = new ArrayList<>();
                    }
                    this.accountList.add(dto);
                }//end username and password is verified
            }//end connection is available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }

    public boolean deleteAccount(String username)
            throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1.make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.create sql string
                String sql = "Delete From Registration "
                        + "Where username= ?";
                //3.create stm obj
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4.execute
                int effectRows = stm.executeUpdate();
                //5.process (Note: Luu y Khi SU DUNG IF/WHILE)
                if (effectRows > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                con.close();
            }
            if (con != null) {
                con.close();
            }

        }
        return result;
    }

    public boolean updateAccount(String username, String password, boolean role)
            throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1.make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.create sql string
                String sql = "Update Registration Set password = ? , "
                        + "isAdmin = ? "
                        + "Where username= ?";
                //3.create stm obj
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);
                //4.execute
                int effectRows = stm.executeUpdate();
                //5.process (Note: Luu y Khi SU DUNG IF/WHILE)
                if (effectRows > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                con.close();
            }
            if (con != null) {
                con.close();
            }

        }
        return result;
    }
    
    public boolean createAccount(RegistrationDTO account)
         throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1.make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.create sql string
                String sql = "Insert Into Registration("
                        + "username, password, lastname, isAdmin"
                        + ") Values("
                        + "?, ?, ?, ?"
                        + ")";
                //3.create stm obj
                stm = con.prepareStatement(sql);
                stm.setString(1, account.getUsername());
                stm.setString(2, account.getPassword());
                stm.setString(3, account.getFullname());
                stm.setBoolean(4, account.isRole());
                //4.execute
                int effectRows = stm.executeUpdate();
                //5.process (Note: Luu y Khi SU DUNG IF/WHILE)
                if (effectRows > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                con.close();
            }
            if (con != null) {
                con.close();
            }

        }
        return result;
    }
}
