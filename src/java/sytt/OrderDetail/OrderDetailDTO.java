/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sytt.OrderDetail;

/**
 *
 * @author Admin
 */
public class OrderDetailDTO {
    
    private String sku;
    private String orderID;
    private float price;
    private int quantity;
    private float total;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String sku, String orderID, float price, int quantity, float total) {
        
        this.sku = sku;
        this.orderID = orderID;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
}
