/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sang1011
 */
public class MainController extends HttpServlet {

    private static final String WELCOME = "HomeController";
    private static final String SEARCH = "Search";
    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String SEARCH_CONTROLLER = "SearchController";
    private static final String LOGIN_PAGE = "Login_Page";
    private static final String LOGIN_PAGE_VIEW = "login.jsp";
    private static final String LOGOUT = "Logout";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    private static final String SHOPPING_PAGE = "Shopping_Page";
    private static final String SHOPPING_PAGE_VIEW = "shopping.jsp";
    private static final String VIEW = "View";
    private static final String VIEW_PAGE = "viewcart.jsp";
    private static final String ADD = "Add";
    private static final String ADD_CONTROLLER = "AddController";
    private static final String REMOVE = "Remove";
    private static final String REMOVE_CONTROLLER = "RemoveController";
    private static final String CHECKOUT = "Checkout";
    private static final String CHECKOUT_CONTROLLER = "CheckoutController";
    private static final String PROFILE_PAGE = "Profile_Page";
    private static final String PROFILE_PAGE_VIEW = "profile.jsp";
    private static final String UPDATE = "Update";
    private static final String UPDATE_CONTROLLER = "UpdateController";
    private static final String ADD_PRODUCT_PAGE = "Add_Product";
    private static final String ADD_PRODUCT_PAGE_VIEW = "addProduct.jsp";
    private static final String REGISTER_PAGE = "Register_Page";
    private static final String REGISTER_PAGE_VIEW = "register.jsp";
    private static final String REGISTER = "Register";
    private static final String REGISTER_CONTRONLLER = "RegisterController";
    private static final String FORGOTPASSWORD_PAGE = "ForgotPassword_Page";
    private static final String FORGOTPASSWORD_PAGE_VIEW = "forgotPassword.jsp";
    private static final String FORGOTPASSWORD = "ForgotPassword";
    private static final String FORGOTPASSWORD_CONTROLLER = "ForgotPasswordController";
    private static final String CHANGEPASSWORD_PAGE = "ChangePasssword_Page";
    private static final String CHANGEPASSWORD_PAGE_VIEW = "changePassword.jsp";
    private static final String CHANGE = "Change";
    private static final String CHANGE_CONTROLLER = "ChangeController";
    private static final String VIEW_PRODUCT = "View_Product";
    private static final String VIEW_PRODUCT_CONTROLLER = "ViewProductController";
    private static final String EDIT = "Edit";
    private static final String EDIT_CONTROLLER = "EditController";
    private static final String CONTACT_PAGE = "Contact_Page";
    private static final String CONTACT_PAGE_VIEW = "contact.jsp";
    private static final String CONTACT = "Contact";
    private static final String CONTACT_CONTROLLER = "ContactController";
    private static final String DELETE = "Delete";
    private static final String DELETE_CONTROLLER = "DeleteController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = WELCOME;
        try {
            String action = request.getParameter("action");
            if (LOGIN.equals(action)) {
                url = LOGIN_CONTROLLER;
            } else if (SEARCH.equals(action)) {
                url = SEARCH_CONTROLLER;
            } else if (LOGOUT.equals(action)) {
                url = LOGOUT_CONTROLLER;
            } else if (ADD.equals(action)) {
                url = ADD_CONTROLLER;
            } else if (REMOVE.equals(action)) {
                url = REMOVE_CONTROLLER;
            } else if (CHECKOUT.equals(action)) {
                url = CHECKOUT_CONTROLLER;
            } else if (VIEW.equals(action)) {
                url = VIEW_PAGE;
            } else if (LOGIN_PAGE.equals(action)) {
                url = LOGIN_PAGE_VIEW;
            } else if (PROFILE_PAGE.equals(action)) {
                url = PROFILE_PAGE_VIEW;
            } else if (UPDATE.equals(action)) {
                url = UPDATE_CONTROLLER;
            } else if (REGISTER_PAGE.equals(action)) {
                url = REGISTER_PAGE_VIEW;
            } else if (REGISTER.equals(action)) {
                url = REGISTER_CONTRONLLER;
            } else if (FORGOTPASSWORD_PAGE.equals(action)) {
                url = FORGOTPASSWORD_PAGE_VIEW;
            } else if (CHANGEPASSWORD_PAGE.equals(action)) {
                url = CHANGEPASSWORD_PAGE_VIEW;
            } else if (CHANGE.equals(action)) {
                url = CHANGE_CONTROLLER;
            } else if (VIEW_PRODUCT.equals(action)) {
                url = VIEW_PRODUCT_CONTROLLER;
            } else if (EDIT.equals(action)) {
                url = EDIT_CONTROLLER;
            } else if (CONTACT.equals(action)) {
                url = CONTACT_CONTROLLER;
            } else if (CONTACT_PAGE.equals(action)) {
                url = CONTACT_PAGE_VIEW;
            } else if (FORGOTPASSWORD.equals(action)) {
                url = FORGOTPASSWORD_CONTROLLER;
            } else if (DELETE.equals(action)) {
                url = DELETE_CONTROLLER;
            } else if (SHOPPING_PAGE.equals(action)) {
                url = SHOPPING_PAGE_VIEW;
            } else if (ADD_PRODUCT_PAGE.equals(action)) {
                url = ADD_PRODUCT_PAGE_VIEW;
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
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
