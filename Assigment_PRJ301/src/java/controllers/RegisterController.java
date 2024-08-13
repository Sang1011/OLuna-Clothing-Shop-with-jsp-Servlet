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
import user.UserDAO;
import user.UserDTO;
import user.UserError;

/**
 *
 * @author Sang1011
 */
@WebServlet(name = "RegisterController", urlPatterns = { "/RegisterController" })
public class RegisterController extends HttpServlet {

    private static final String ERROR = "register.jsp";
    private static final String SUCCESS = "HomeController";

    private static final String SUCCESS_ADMIN = "SearchController";
    private static final String ERROR_ADMIN = "SearchController";
    private static final String ADMIN = "AD";
    private static final String USER = "US";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserError userError = new UserError();
        UserDAO dao = new UserDAO();
        try {
            // register,create user
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            boolean checkValidation = true;
            String userID = request.getParameter("userID");
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            int status = 1;

            if (userID.length() < 2 || userID.length() > 10) {
                checkValidation = false;
                userError.setUserIDError("UserID not be in [2,10]");
            }

            if (name.length() < 5 || name.length() > 20) {
                checkValidation = false;
                userError.setNameError("Name not be in [5,20]");
            }
            String roleID = "";
            if (loginUser != null && loginUser.getRoleID().equals(ADMIN)) {
                url = ERROR_ADMIN;
                roleID = request.getParameter("roleID");
                if (!roleID.equals(ADMIN) && !roleID.equals(USER)) {
                    checkValidation = false;
                    userError.setRoleIDError("Role must be 'US' or 'AD'!");
                }
            }

            if (dao.getUserByEmail(email) != null) {
                checkValidation = false;
                userError.setEmailError("This email did exist!");
            }

            if (!phone.startsWith("0")) {
                checkValidation = false;
                userError.setPhoneError("Phone must be started with 0");
            }

            if (name.length() < 5 || name.length() > 20) {
                checkValidation = false;
                userError.setNameError("Name not be in [5,20]");
            }
            if (!confirmPassword.equals(password)) {
                checkValidation = false;
                userError.setConfirmError("The two passwords are not the same");
            }
            boolean checkInsert;
            UserDTO user;
            if (checkValidation) {
                if (loginUser != null && loginUser.getRoleID().equals("AD")) {
                    user = new UserDTO(userID, password, name, address, email, phone, roleID, status);
                    checkInsert = dao.insert(user);
                } else {
                    user = new UserDTO(userID, password, name, address, email, phone, "US", status);
                    checkInsert = dao.insert(user);
                }
                if (checkInsert) {
                    if (loginUser == null || USER.equals(loginUser.getRoleID())) {
                        session.setAttribute("LOGIN_USER", user);
                        url = SUCCESS;
                    } else if (ADMIN.equals(loginUser.getRoleID())) {
                        url = SUCCESS_ADMIN;
                    }
                } else {
                    userError.setError("Unknow error!");
                    request.setAttribute("USER_ERROR", userError);
                }

            } else {
                request.setAttribute("USER_ERROR", userError);
            }
            // create product

        } catch (Exception e) {
            log("Error at RegisterController: " + e.toString());
            if (e.toString().contains("duplicate")) {
                userError.setUserIDError("Your id is duplicated");
                request.setAttribute("USER_ERROR", userError);
            }
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
