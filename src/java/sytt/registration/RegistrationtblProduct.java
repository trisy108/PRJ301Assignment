/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sytt.registration;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class RegistrationtblProduct implements Serializable{
    private String sku;
    private String name;
    private int quantity;
    private float price;
    private boolean status;

    public RegistrationtblProduct() {
    }

    public RegistrationtblProduct(String sku, String name, int quantity, float price, boolean status) {
        this.sku = sku;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
