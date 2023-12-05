/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sytt.order;

import java.io.Serializable;
import java.sql.Date;



/**
 *
 * @author Admin
 */
public class tblOrderDTO implements Serializable{
    private String id;
    private Date datetime;
    private float total;

    public tblOrderDTO() {
    }

    public tblOrderDTO(String id, Date datetime, float total) {
        this.id = id;
        this.datetime = datetime;
        this.total = total;
    }
     public tblOrderDTO( Date datetime, float total) {
        
        this.datetime = datetime;
        this.total = total;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
}
