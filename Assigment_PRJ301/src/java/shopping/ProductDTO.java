/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sang1011
 */
public class ProductDTO {

    private String productID;
    private String name;
    private double price;
    private int quantity;
    private String images;
    private String collection;
    private String brand;
    private int status;

    public ProductDTO() {
        this.productID = "";
        this.name = "";
        this.price = 0;
        this.quantity = 0;
        this.images = "";
        this.collection = "";
        this.brand = "";
        this.status = 0;
    }

    public ProductDTO(String productID, String name, double price, int quantity, String images, String collection,
            String brand) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.images = images;
        this.collection = collection;
        this.brand = brand;
    }

    public ProductDTO(String productID, String name, double price, int quantity, String images, String collection,
            String brand, int status) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.images = images;
        this.collection = collection;
        this.brand = brand;
        this.status = status;
    }

    public ProductDTO(ProductDTO product) {
        this.productID = product.productID;
        this.name = product.name;
        this.price = product.price;
        this.quantity = product.quantity;
        this.images = product.images;
        this.collection = product.collection;
        this.brand = product.brand;
        this.status = product.status;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<ProductDTO> getCategory(String productID, List<ProductDTO> allProducts, String category) {
        List<ProductDTO> list = new ArrayList<>();
        for (ProductDTO product : allProducts) {
            if (product.getName().contains(category) && !product.getProductID().equals(productID)) {
                list.add(product);
            }
        }
        return list;
    }

    public List<ProductDTO> getListByCollection(List<ProductDTO> allProducts, String collection) {
        List<ProductDTO> list = new ArrayList<>();
        for (ProductDTO product : allProducts) {
            if (product.getCollection().contains(collection)) {
                list.add(product);
            }
        }
        return list;
    }

    public List<ProductDTO> getListByBrand(List<ProductDTO> allProducts, String brand) {
        List<ProductDTO> list = new ArrayList<>();
        for (ProductDTO product : allProducts) {
            if (product.getBrand().contains(brand)) {
                list.add(product);
            }
        }
        return list;
    }

    public List<ProductDTO> getListByRangePrice(List<ProductDTO> allProducts, int lower, int upper) {
        List<ProductDTO> list = new ArrayList<>();
        for (ProductDTO product : allProducts) {
            if (product.getPrice() >= lower && product.getPrice() <= upper) {
                list.add(product);
            }
        }
        return list;
    }

    public List<ProductDTO> getListByPage(List<ProductDTO> list, int start, int end) {
        List<ProductDTO> listarr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            listarr.add(list.get(i));
        }
        return listarr;
    }

    @Override
    public String toString() {
        return "ProductDTO{" + "productID=" + productID + ", name=" + name + ", price=" + price + ", quantity="
                + quantity + ", images=" + images + ", collection=" + collection + ", brand=" + brand + ", status="
                + status + '}';
    }

}
