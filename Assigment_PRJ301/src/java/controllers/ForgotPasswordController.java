/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import email.Email;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import user.UserDAO;
import user.UserDTO;

/**
 *
 * @author Sang1011
 */
@WebServlet(name = "ForgotPasswordController", urlPatterns = { "/ForgotPasswordController" })
public class ForgotPasswordController extends HttpServlet {

    private static final String ERROR = "forgotPassword.jsp";
    private static final String SUCCESS = "forgotPassword.jsp";
    private static final String NOT_HAVE_ACCOUNT = "This email has not registered an account";
    private static final String ERROR_INFO = "Somthing wrong with your email, you should register a new account";
    private static final String SEND_SUCCESS = "Sent successfully!";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            UserDAO dao = new UserDAO();
            String email = request.getParameter("email");
            if (email != null) {
                UserDTO user = dao.getUserByEmail(email);
                if (user == null) {
                    request.setAttribute("INFO", NOT_HAVE_ACCOUNT);
                } else {
                    boolean sendEmail = Email.sendEmail(email, "Forgot Password",
                            "Your password is " + user.getPassword());
                    if (!sendEmail) {
                        request.setAttribute("INFO", ERROR_INFO);
                    } else {
                        request.setAttribute("INFO", SEND_SUCCESS);
                        url = SUCCESS;
                    }
                }
            }
        } catch (Exception e) {
            log("Error at ForgotPasswordController: " + e.toString());
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
