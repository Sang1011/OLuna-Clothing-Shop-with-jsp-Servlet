/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import shopping.Cart;
import shopping.OrderDAO;
import shopping.OrderDetailDTO;
import shopping.OrderDetailDAO;
import shopping.OrderDTO;
import shopping.ProductDAO;
import shopping.ProductDTO;
import user.UserDTO;
import email.Email;

/**
 *
 * @author Sang1011
 */
@WebServlet(name = "CheckoutController", urlPatterns = { "/CheckoutController" })
public class CheckoutController extends HttpServlet {

    private static final String ERROR = "viewcart.jsp";
    private static final String SUCCESS = "checkoutSuccess.jsp";
    private static final String EMPTY_CART = "Cart is empty!";
    private static final String LOGIN = "You must log in before checking out!";
    private static final String LOSS_INFO = "You must update your address and phone number before checking out!";
    private static final String EMAIL = "Something wrong with your email, please make sure it exist so that we can allow you to buy products and send bill";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {

            double total = Double.parseDouble(request.getParameter("total"));
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null) {
                request.setAttribute("INFO", LOGIN);
            } else {
                if (loginUser.getAddress().isEmpty() || loginUser.getPhone().isEmpty()) {
                    request.setAttribute("INFO", LOSS_INFO);
                } else {
                    Cart cart = (Cart) session.getAttribute("CART");
                    if (cart == null) {
                        request.setAttribute("INFO", EMPTY_CART);
                    } else {
                        boolean checkContinue = true;
                        List<String> productOutOfStocks = new ArrayList<>();
                        for (ProductDTO productInCart : cart.getCart().values()) {
                            String id = productInCart.getProductID();
                            ProductDAO product_dao = new ProductDAO();
                            ProductDTO product = product_dao.getProductByID(id);
                            int quantityInCart = productInCart.getQuantity();
                            if (product.getQuantity() < quantityInCart) {
                                productOutOfStocks.add("The item " + product.getName()
                                        + " that you requested has a quantity that exceeds the quantity in stock");
                                checkContinue = false;
                            }
                        }
                        if (!checkContinue) {
                            request.setAttribute("LIST_OUTOFSTOCK", productOutOfStocks);
                        } else {
                            List<ProductDTO> productList = new ArrayList<>();
                            OrderDAO order_dao = new OrderDAO();
                            LocalDate today = LocalDate.now();
                            Date currentDate = Date.valueOf(today);
                            order_dao.createOrder(new OrderDTO(loginUser.getUserID(), currentDate, total));
                            int orderID = order_dao.getLatestOrderId();
                            for (ProductDTO productInCart : cart.getCart().values()) {
                                productList.add(productInCart);
                                String id = productInCart.getProductID();
                                ProductDAO product_dao = new ProductDAO();
                                ProductDTO product = product_dao.getProductByID(id);
                                OrderDetailDAO orderdetails_dao = new OrderDetailDAO();
                                int quantityInCart = productInCart.getQuantity();
                                double priceInCart = productInCart.getPrice();
                                orderdetails_dao.createOrder(new OrderDetailDTO(orderID, product.getProductID(),
                                        priceInCart, quantityInCart));
                                int quantity = product.getQuantity() - quantityInCart;
                                product_dao.updateQuantity(id, quantity);
                            }
                            boolean sendBill = Email.sendBill(loginUser.getEmail(), "Bill", productList);
                            if (sendBill) {
                                session.setAttribute("CART", null);
                                url = SUCCESS;
                            } else {
                                request.setAttribute("INFO", EMAIL);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            log("Error at CheckoutController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
