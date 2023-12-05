/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sytt.cart;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import sytt.registration.RegistrationProductDAO;
import sytt.registration.RegistrationtblProduct;

/**
 *
 * @author Admin
 */
public class CartObj implements Serializable {
    private Map<String, Integer> items;
    
    public Map<String, Integer> getItems() {
        return items;
    }
    
    public RegistrationtblProduct getProductbySku(String sku) 
            throws NamingException, SQLException{
        RegistrationProductDAO productDAO= new RegistrationProductDAO();
        RegistrationtblProduct product= productDAO.searchProductbySku(sku);
        if(product!=null){
            return product;
        }return null;
    }
    
    public void addItemtoCart(String sku) {
        if (sku == null){
            return;
        }
        if (sku.trim().isEmpty()) {
            return;
        }
        //1.Check existed items
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        //2.Check existed item
        int quantity = 1;
        if (this.items.containsKey(sku)){
            quantity = this.items.get(sku) + 1;
        }
        //3.Update items
        this.items.put(sku, quantity);
    }
    
    public void removeItemFromCart(String sku) {
        //1.Check existed items
        if (this.items == null) {
            return;
        }
        //2.Check existed item
        if (this.items.containsKey(sku)){       
        //3.Remove item
            this.items.remove(sku);
            if (this.items.isEmpty()){
                this.items = null;
            }
        }
    }
}
