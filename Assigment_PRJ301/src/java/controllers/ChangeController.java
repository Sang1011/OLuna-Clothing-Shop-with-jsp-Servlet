/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.UserDAO;
import user.UserDTO;

/**
 *
 * @author Sang1011
 */
@WebServlet(name = "ChangeController", urlPatterns = { "/ChangeController" })
public class ChangeController extends HttpServlet {

    private static final String WELCOME = "HomeController";
    private static final String ERROR = "changePassword.jsp";
    private static final String SUCCESS = "profile.jsp";
    private static final String WRONG_OLDPASS = "Your old password is incorrect";
    private static final String WRONG_CONFIRM = "Your confirm password is incorrect";
    private static final String CHANGE_SUCCESS = "Change password successfully!";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            String oldpass = request.getParameter("oldPass");
            String newpass = request.getParameter("newPass");
            String confirmpass = request.getParameter("confirmPass");
            if (loginUser.getUserID() == null || (loginUser.getRoleID().equals("AD"))) {
                url = WELCOME;
            } else {
                UserDAO dao = new UserDAO();
                if (!loginUser.getPassword().equals(oldpass)) {
                    request.setAttribute("INFO", WRONG_OLDPASS);
                } else {
                    if (!newpass.equals(confirmpass)) {
                        request.setAttribute("INFO", WRONG_CONFIRM);
                    } else {
                        boolean check = dao.updatePassword(newpass, loginUser.getUserID());
                        if (check) {
                            Cookie[] cookies = request.getCookies();
                            if (cookies != null) {
                                for (Cookie cookie : cookies) {
                                    if ("CookiesPassword".equals(cookie.getName())) {
                                        cookie.setValue(newpass);
                                        response.addCookie(cookie);
                                        break;
                                    }
                                }
                            }

                            loginUser.setPassword(newpass);
                            request.setAttribute("INFO", CHANGE_SUCCESS);
                            url = SUCCESS;
                        }
                    }
                }
            }

        } catch (Exception e) {
            log("Error at ChangeController: " + e.toString());
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
