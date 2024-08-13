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
public class ProductDAO {

    private static final String LIST_ALL_RPODUCTS = "SELECT productID, name, price, quantity, images, collection, brand FROM products WHERE status=1";
    private static final String INSERT = "INSERT INTO products(productID, name, price, quantity, images, collection, brand, status) VALUES (?,?,?,?,?,?,?,?)";
    private static final String DUPLICATE = "SELECT name FROM products WHERE collection=? AND brand=?";
    private static final String SEARCH = "SELECT productID, name, price, quantity, images, collection, brand FROM products WHERE status=1 AND name LIKE ?";
    private static final String SEARCH_NO_STATUS = "SELECT productID, name, price, quantity, images, collection, brand, status FROM products WHERE name LIKE ?";
    private static final String GET_PRODUCT_BY_ID = "SELECT name, price, quantity, images, collection, brand FROM products WHERE productID LIKE ?";
    private static final String GET_LAST_ID = "SELECT SUBSTRING(productId, 2, LEN(productId)) FROM products WHERE productId = (SELECT MAX(productID) FROM products);";
    private static final String UPDATE_QUANTITY = "UPDATE products SET quantity=? WHERE productID=?";
    private static final String SELECT_TOP4_LAST = "SELECT TOP 4 * FROM products ORDER BY productID DESC";
    private static final String SELECT_TOP9_LAST = "SELECT TOP 9 * FROM products ORDER BY productID DESC";
    private static final String UPDATE = "UPDATE products SET name=?, price=?, quantity=?, collection=?, brand=?, status=? where productID LIKE ?";
    private static final String SELECT_TOP_9_BESTSELLING = "SELECT TOP 9 " + "od.productID,"
            + "SUM(od.quantity) AS total_quantity,"
            + "p.name,"
            + "MAX(p.price) AS price,"
            + "MAX(p.quantity) AS product_quantityInStock,"
            + "MAX(p.images) AS product_images,"
            + "MAX(p.collection) AS product_collection,"
            + "MAX(p.brand) AS product_brand "
            + "FROM orderDetails od "
            + "LEFT JOIN products p ON od.productID = p.productID "
            + "WHERE p.status = 1"
            + "GROUP BY od.productID, p.name "
            + "ORDER BY total_quantity DESC;";
    private static final String SELECT_TOP4_BESTSELLING = "SELECT TOP 4 "
            + "od.productID,"
            + "SUM(od.quantity) AS total_quantity,"
            + "p.name,"
            + "MAX(p.price) AS price,"
            + "MAX(p.quantity) AS product_quantityInStock,"
            + "MAX(p.images) AS product_images,"
            + "MAX(p.collection) AS product_collection,"
            + "MAX(p.brand) AS product_brand "
            + "FROM orderDetails od "
            + "LEFT JOIN products p ON od.productID = p.productID "
            + "WHERE p.status = 1"
            + "GROUP BY od.productID, p.name "
            + "ORDER BY total_quantity DESC;";

    public List<ProductDTO> getListProduct() throws SQLException, ClassNotFoundException {
        List<ProductDTO> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LIST_ALL_RPODUCTS);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    String images = rs.getString("images");
                    String collection = rs.getString("collection");
                    String brand = rs.getString("brand");
                    listProduct.add(new ProductDTO(productID, name, price, quantity, images, collection, brand));
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
        return listProduct;
    }

    public List<ProductDTO> searchListProduct(String search) throws SQLException, ClassNotFoundException {
        List<ProductDTO> listProduct = new ArrayList<>();
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
                    String productID = rs.getString("productID");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    String images = rs.getString("images");
                    String collection = rs.getString("collection");
                    String brand = rs.getString("brand");
                    listProduct.add(new ProductDTO(productID, name, price, quantity, images, collection, brand));
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
        return listProduct;
    }

    public List<ProductDTO> searchListProductNoStatus(String search) throws SQLException, ClassNotFoundException {
        List<ProductDTO> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_NO_STATUS);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    String images = rs.getString("images");
                    String collection = rs.getString("collection");
                    String brand = rs.getString("brand");
                    int status = rs.getInt("status");
                    listProduct
                            .add(new ProductDTO(productID, name, price, quantity, images, collection, brand, status));
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
        return listProduct;
    }

    public ProductDTO getProductByID(String productID) throws SQLException, ClassNotFoundException {
        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_PRODUCT_BY_ID);
                ptm.setString(1, productID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    String images = rs.getString("images");
                    String collection = rs.getString("collection");
                    String brand = rs.getString("brand");
                    product = new ProductDTO(productID, name, price, quantity, images, collection, brand);
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
        return product;
    }

    public boolean updateQuantity(String productID, int quantity) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_QUANTITY);
                ptm.setInt(1, quantity);
                ptm.setString(2, productID);
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

    public List<ProductDTO> getTop4ProductLast() throws SQLException, ClassNotFoundException {
        List<ProductDTO> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SELECT_TOP4_LAST);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    String images = rs.getString("images");
                    String collection = rs.getString("collection");
                    String brand = rs.getString("brand");
                    listProduct.add(new ProductDTO(productID, name, price, quantity, images, collection, brand));
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
        return listProduct;
    }

    public List<ProductDTO> getTop9ProductLast() throws SQLException, ClassNotFoundException {
        List<ProductDTO> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SELECT_TOP9_LAST);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    String images = rs.getString("images");
                    String collection = rs.getString("collection");
                    String brand = rs.getString("brand");
                    listProduct.add(new ProductDTO(productID, name, price, quantity, images, collection, brand));
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
        return listProduct;
    }

    public List<ProductDTO> getTop4BestSelling() throws SQLException, ClassNotFoundException {
        List<ProductDTO> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SELECT_TOP4_BESTSELLING);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    int quantityInStock = rs.getInt("product_quantityInStock");
                    String images = rs.getString("product_images");
                    String collection = rs.getString("product_collection");
                    String brand = rs.getString("product_brand");
                    listProduct.add(new ProductDTO(productID, name, price, quantityInStock, images, collection, brand));
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
        return listProduct;
    }

    public List<ProductDTO> getBestSelling() throws SQLException, ClassNotFoundException {
        List<ProductDTO> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SELECT_TOP_9_BESTSELLING);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    int quantityInStock = rs.getInt("product_quantityInStock");
                    String images = rs.getString("product_images");
                    String collection = rs.getString("product_collection");
                    String brand = rs.getString("product_brand");
                    listProduct.add(new ProductDTO(productID, name, price, quantityInStock, images, collection, brand));
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
        return listProduct;
    }

    public boolean update(ProductDTO product) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, product.getName());
                ptm.setDouble(2, product.getPrice());
                ptm.setInt(3, product.getQuantity());
                ptm.setString(4, product.getCollection());
                ptm.setString(5, product.getBrand());
                ptm.setInt(6, product.getStatus());
                ptm.setString(7, product.getProductID());
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

    public boolean insert(ProductDTO product) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT);
                ptm.setString(1, product.getProductID());
                ptm.setString(2, product.getName());
                ptm.setDouble(3, product.getPrice());
                ptm.setInt(4, product.getQuantity());
                ptm.setString(5, product.getImages());
                ptm.setString(6, product.getCollection());
                ptm.setString(7, product.getBrand());
                ptm.setInt(8, product.getStatus());
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

    public boolean checkDuplicate(String collection, String brand) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DUPLICATE);
                ptm.setString(1, collection);
                ptm.setString(2, brand);
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

    public String createIDProduct() throws SQLException, ClassNotFoundException {
        String index = "";
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LAST_ID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    index = rs.getInt(1) + 1 + "";
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
        return index;
    }

}
