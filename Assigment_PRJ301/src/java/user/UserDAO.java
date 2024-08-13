/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

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
public class UserDAO {

    private static final String LOGIN = "SELECT name, address, email, phone, roleID, status FROM users WHERE userID=? AND password=? AND status=1";
    private static final String SEARCH = "SELECT userID, name, address, email, phone, roleID, status FROM users WHERE name LIKE ?";
    private static final String DELETE = "DELETE users WHERE userID=?";
    private static final String UPDATE = "UPDATE users SET name=?, address=?, email=?, phone=?, roleID=?, status=? WHERE userID=?";
    private static final String UPDATE_WITHOUT_STATUS = "UPDATE users SET name=?, address=?, email=?, phone=?, roleID=? WHERE userID=?";
    private static final String DUPLICATE = "SELECT name FROM users WHERE userID=?";
    private static final String INSERT = "INSERT INTO users(userID, password, name, address, email, phone, roleID, status) VALUES (?,?,?,?,?,?,?,?)";
    private static final String GET_LAST_USERID = "SELECT MAX(userID) FROM users";
    private static final String GET_USER_BY_ID = "SELECT userID, password, name, address, email, phone, roleID FROM users WHERE userID=?";
    private static final String UPDATE_PASSWORD = "UPDATE users SET password=? WHERE userID=?";
    private static final String LIST_ID = "SELECT userID form users";
    private static final String GET_USER_BY_EMAIL = "SELECT userID, password, name, address, email, phone from users WHERE email=? AND roleID LIKE ?";
    private static final String COUNT_USER = "SELECT COUNT(*) FROM users";

    public UserDTO checkLogin(String userID, String password) throws SQLException, ClassNotFoundException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOGIN);
                ptm.setString(1, userID);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String roleID = rs.getString("roleID");
                    int status = rs.getInt("status");
                    user = new UserDTO(userID, password, name, address, email, phone, roleID, status);
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

        return user;
    }

    public List<UserDTO> getListUser(String search) throws SQLException, ClassNotFoundException {
        List<UserDTO> listUser = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String roleID = rs.getString("roleID");
                    String password = "***";
                    int status = rs.getInt("status");
                    listUser.add(new UserDTO(userID, password, name, address, email, phone, roleID, status));
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
        return listUser;
    }

    public boolean delete(String userID) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
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

    public boolean update(UserDTO user) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, user.getName());
                ptm.setString(2, user.getAddress());
                ptm.setString(3, user.getEmail());
                ptm.setString(4, user.getPhone());
                ptm.setString(5, user.getRoleID());
                ptm.setInt(6, user.getStatus());
                ptm.setString(7, user.getUserID());
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

    public boolean updateWithoutStatus(UserDTO user) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_WITHOUT_STATUS);
                ptm.setString(1, user.getName());
                ptm.setString(2, user.getAddress());
                ptm.setString(3, user.getEmail());
                ptm.setString(4, user.getPhone());
                ptm.setString(5, user.getRoleID());
                ptm.setString(6, user.getUserID());
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

    public boolean insert(UserDTO user) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT);
                ptm.setString(1, user.getUserID());
                ptm.setString(2, user.getPassword());
                ptm.setString(3, user.getName());
                ptm.setString(4, user.getAddress());
                ptm.setString(5, user.getEmail());
                ptm.setString(6, user.getPhone());
                ptm.setString(7, user.getRoleID());
                ptm.setInt(8, user.getStatus());
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

    public boolean checkDuplicate(String userID) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DUPLICATE);
                ptm.setString(1, userID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
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
        return check;
    }

    public int getLatestUserId() throws SQLException, ClassNotFoundException {
        int latestOrderId = -1;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LAST_USERID);
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

    public UserDTO getUserByID(String userID) throws SQLException, ClassNotFoundException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_USER_BY_ID);
                ptm.setString(1, userID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String password = rs.getString("password");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String roleID = rs.getString("roleID");
                    int status = rs.getInt("status");
                    user = new UserDTO(userID, password, name, address, email, phone, roleID, status);
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

        return user;
    }

    public boolean updatePassword(String password, String userID) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_PASSWORD);
                ptm.setString(1, password);
                ptm.setString(2, userID);
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

    public List<String> getListUserID() throws SQLException, ClassNotFoundException {
        List<String> listUserID = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LIST_ID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("userID");
                    listUserID.add(id);
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
        return listUserID;
    }

    public UserDTO getUserByEmail(String email) throws SQLException, ClassNotFoundException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_USER_BY_EMAIL);
                ptm.setString(1, email);
                ptm.setString(2, "US");
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String userID = rs.getString("userID");
                    String password = rs.getString("password");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    user = new UserDTO(userID, password, name, address, email, phone, "US");
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

        return user;
    }

    public int countUser() throws SQLException, ClassNotFoundException {
        int count = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(COUNT_USER);
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

}
