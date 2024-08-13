/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Sang1011
 */
public class OrderDTO {
    private int orderID;
    private String userID;
    private Date date;
    private double total;
    private int status;

    public OrderDTO() {
        LocalDate day = LocalDate.now();
        this.orderID = 0;
        this.userID = "";
        this.date = Date.valueOf(day);
        this.total = 0;
        this.status = 0;
    }

    public OrderDTO(String userID, Date date, double total) {
        this.userID = userID;
        this.date = date;
        this.total = total;
    }

    public OrderDTO(int orderID, String userID, Date date, double total) {
        this.orderID = orderID;
        this.userID = userID;
        this.date = date;
        this.total = total;
    }

    public OrderDTO(int orderID, String userID, Date date, double total, int status) {
        this.orderID = orderID;
        this.userID = userID;
        this.date = date;
        this.total = total;
        this.status = status;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Orders{" + "orderID=" + orderID + ", userID=" + userID + ", date=" + date + ", total=" + total
                + ", status=" + status + '}';
    }

}
