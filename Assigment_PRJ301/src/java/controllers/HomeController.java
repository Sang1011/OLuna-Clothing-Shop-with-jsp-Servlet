/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shopping.ProductDAO;
import shopping.ProductDTO;

/**
 *
 * @author Sang1011
 */
@WebServlet(name = "HomeController", urlPatterns = { "/HomeController" })
public class HomeController extends HttpServlet {

    private static final String SUCCESS = "homepage.jsp";
    private static final String ERROR = "homepage.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            ProductDAO productdao = new ProductDAO();
            List<ProductDTO> listAllProducts = productdao.getListProduct();
            List<ProductDTO> listNewArrivals = productdao.getTop4ProductLast();
            List<ProductDTO> listBestSelling = productdao.getTop4BestSelling();
            List<ProductDTO> listRandom = new ArrayList<>();
            Random generate = new Random();
            while (listRandom.size() < 5 && !listAllProducts.isEmpty()) {
                int randomIndex = generate.nextInt(listAllProducts.size());
                ProductDTO randomProduct = listAllProducts.get(randomIndex);
                listRandom.add(randomProduct);
                listAllProducts.remove(randomIndex);
            }
            if (listRandom.size() > 0 && listNewArrivals.size() > 0 && listBestSelling.size() > 0) {
                request.setAttribute("LIST_RANDOM", listRandom);
                request.setAttribute("LIST_NEW_ARRIVALS", listNewArrivals);
                request.setAttribute("LIST_BESTSELLING", listBestSelling);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at HomeController: " + e.toString());
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
