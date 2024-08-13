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
import shopping.ProductDAO;
import shopping.ProductDTO;
import user.UserDAO;
import user.UserDTO;

/**
 *
 * @author Sang1011
 */
@WebServlet(name = "UpdateController", urlPatterns = { "/UpdateController" })
public class UpdateController extends HttpServlet {

    private static final String ERROR = "profile.jsp";
    private static final String SUCCESS = "profile.jsp";
    private static final String FAILED = "Cannot Update!";
    private static final String INFO_SUCCESS = "Update successfully!";
    private static final String SUCCESS_ADMIN = "SearchController";
    private static final String SUCCESS_PRODUCT = "SearchController";
    private static final String FAILED_PRODUCT = "SearchController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String updateType = request.getParameter("updateType");
            if (updateType.equals("updateUser")) {
                String userID = request.getParameter("userID");
                String name = request.getParameter("name");
                String address = request.getParameter("address");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                String roleID = request.getParameter("roleID");
                HttpSession session = request.getSession();
                UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
                UserDAO dao = new UserDAO();
                boolean check = false;
                UserDTO user = null;
                // admin update
                if (loginUser.getRoleID().equals("AD")) {
                    int status = Integer.parseInt(request.getParameter("status"));
                    UserDTO update = new UserDTO(userID, "", name, address, email, phone, roleID, status);
                    check = dao.update(update);
                } else if (loginUser.getRoleID().equals("US")) {
                    // profile update
                    user = new UserDTO(userID, "", name, address, email, phone, roleID, 1);
                    check = dao.update(user);
                }
                if (check) {
                    if (loginUser.getUserID().equals(userID)) {
                        session.setAttribute("LOGIN_USER", user);
                    }
                    request.setAttribute("INFO", INFO_SUCCESS);
                    if (loginUser.getRoleID().equals("AD")) {
                        url = SUCCESS_ADMIN;
                    } else {
                        url = SUCCESS;
                    }
                } else {
                    request.setAttribute("INFO", FAILED);
                }
                // product update
            } else if (updateType.equals("updateProduct")) {
                String productID = request.getParameter("productID");
                String productName = request.getParameter("productName");
                double price = Double.parseDouble(request.getParameter("price"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                String images = request.getParameter("images");
                String collection = request.getParameter("collection");
                String brand = request.getParameter("brand");
                int status = Integer.parseInt(request.getParameter("status"));
                ProductDAO dao = new ProductDAO();
                ProductDTO update = new ProductDTO(productID, productName, price, quantity, images, collection, brand,
                        status);
                boolean check = dao.update(update);
                if (check) {
                    request.setAttribute("INFO", INFO_SUCCESS);
                    url = SUCCESS_PRODUCT;
                } else {
                    request.setAttribute("INFO", FAILED);
                    url = FAILED_PRODUCT;
                }
            }
        } catch (Exception e) {
            log("Error at SearchController: " + e.toString());
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
