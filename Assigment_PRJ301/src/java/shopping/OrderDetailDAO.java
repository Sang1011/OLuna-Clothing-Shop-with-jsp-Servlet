/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author Sang1011
 */
public class OrderDetailDAO {

    private static final String CREATE_ORDER_DETAILS = "INSERT INTO orderDetails(orderID, productID, price, quantity) VALUES (?,?,?,?)";
    private static final String LIST_ALL_ORDERS_DETAIL = "SELECT orderID, productID, price, quantity FROM orderDetails";
    private static final String SUM_PRICE = "SELECT SUM(price) FROM orderDetails as [total]";
    private static final String DELETE_ORDER_DETAIL = "DELETE orderDetails WHERE userID=?";

    public double sumPrice() throws SQLException, ClassNotFoundException {
        double number = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SUM_PRICE);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    number = rs.getDouble(1);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return number;
    }

    public boolean createOrder(OrderDetailDTO orderDetails) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_ORDER_DETAILS);
                ptm.setInt(1, orderDetails.getOrderID());
                ptm.setString(2, orderDetails.getProductID());
                ptm.setDouble(3, orderDetails.getPrice());
                ptm.setInt(4, orderDetails.getQuantity());
                if (ptm.executeUpdate() > 0) {
                    check = true;
                }
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public List<OrderDetailDTO> getListOrders() throws SQLException, ClassNotFoundException {
        List<OrderDetailDTO> Details = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LIST_ALL_ORDERS_DETAIL);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    String productID = rs.getString("productID");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    Details.add(new OrderDetailDTO(orderID, productID, price, quantity));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return Details;
    }

    public boolean delete(String userID) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_ORDER_DETAIL);
                ptm.setString(1, userID);
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

}
