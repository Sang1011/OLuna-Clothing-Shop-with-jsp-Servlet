/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

/**
 *
 * @author Sang1011
 */
public class OrderDetailDTO {
    private int orderID;
    private String productID;
    private double price;
    private int quantity;
    private int status;

    public OrderDetailDTO() {
        this.orderID = 0;
        this.productID = "";
        this.price = 0;
        this.quantity = 0;
        this.status = 0;
    }

    public OrderDetailDTO(int orderID, String productID, double price, int quantity) {
        this.orderID = orderID;
        this.productID = productID;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderDetailDTO(int orderID, String productID, double price, int quantity, int status) {
        this.orderID = orderID;
        this.productID = productID;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
