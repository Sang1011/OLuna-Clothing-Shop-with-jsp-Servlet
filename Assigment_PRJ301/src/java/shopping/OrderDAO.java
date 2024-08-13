/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

import java.sql.Connection;
import java.sql.Date;
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
public class OrderDAO {

    private static final String NUMBER_OF_ORDERS = "SELECT COUNT(*) FROM orders";
    private static final String CREATE_ORDER = "INSERT INTO orders(userID ,date ,total) VALUES (?,?,?)";
    private static final String LIST_ALL_ORDERS = "SELECT orderID, userID ,date ,total FROM orders";
    private static final String GET_ID = "SELECT MAX(orderID) FROM orders";
    private static final String COUNT_ORDERS = "SELECT COUNT(*) from orders";
    private static final String SET_NULL_USERID = "UPDATE orders SET userID=NULL where userID=?";

    public int numberOfOrders() throws SQLException, ClassNotFoundException {
        int number = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(NUMBER_OF_ORDERS);
                if (rs.next()) {
                    number = rs.getInt(1);
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

    public boolean createOrder(OrderDTO order) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_ORDER);
                ptm.setString(1, order.getUserID());
                ptm.setDate(2, order.getDate());
                ptm.setDouble(3, order.getTotal());
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

    public List<OrderDTO> getListOrders() throws SQLException, ClassNotFoundException {
        List<OrderDTO> listOrder = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LIST_ALL_ORDERS);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    String userID = rs.getString("userID");
                    Date date = rs.getDate("date");
                    double price = rs.getDouble("price");
                    listOrder.add(new OrderDTO(orderID, userID, date, price));
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
        return listOrder;
    }

    public int getLatestOrderId() throws SQLException, ClassNotFoundException {
        int latestOrderId = 1;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    latestOrderId = rs.getInt(1);
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
        return latestOrderId;
    }

    public int countOrders() throws SQLException, ClassNotFoundException {
        int count = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(COUNT_ORDERS);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    count = rs.getInt(1);
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
        return count;
    }

    public boolean setNullUserID(String userID) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SET_NULL_USERID);
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
