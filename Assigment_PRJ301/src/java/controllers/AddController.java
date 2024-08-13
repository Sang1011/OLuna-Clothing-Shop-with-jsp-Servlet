/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import shopping.Cart;
import shopping.ProductDAO;
import shopping.ProductDTO;
import user.UserDTO;

/**
 *
 * @author Sang1011
 */
@WebServlet(name = "AddController", urlPatterns = { "/AddController" })
public class AddController extends HttpServlet {

    private static final String ERROR_LOGIN = "login.jsp";
    private static final String ERROR = "ViewProductController";
    private static final String SUCCESS = "ViewProductController";
    private static final String INFO = "Quantity that you order did exceed in stock!";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser != null && loginUser.getRoleID().equals("US")) {
                int quantityBuy = Integer.parseInt(request.getParameter("quantityBuy"));
                String productID = request.getParameter("productID");
                ProductDAO dao = new ProductDAO();
                ProductDTO product = dao.getProductByID(productID);
                ProductDTO newProduct = new ProductDTO(product);
                newProduct.setQuantity(quantityBuy);
                Cart cart = (Cart) session.getAttribute("CART");
                boolean checkContinue = true;
                if (product.getQuantity() < quantityBuy) {
                    checkContinue = false;
                }
                if (checkContinue) {
                    if (cart == null) {
                        cart = new Cart();
                    }

                    boolean checkAdd = cart.add(newProduct);
                    if (checkAdd) {
                        session.setAttribute("CART", cart);
                        request.setAttribute("MESSAGE",
                                "Add " + quantityBuy + " " + newProduct.getName() + "successfully!");
                        url = SUCCESS;
                    }
                } else {
                    request.setAttribute("MESSAGE", INFO);
                }
            } else {
                url = ERROR_LOGIN;
            }
        } catch (Exception e) {
            log("Error at AddController: " + e.toString());
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
