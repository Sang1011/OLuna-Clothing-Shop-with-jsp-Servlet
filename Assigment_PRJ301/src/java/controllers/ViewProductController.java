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
@WebServlet(name = "ViewProductController", urlPatterns = { "/ViewProductController" })
public class ViewProductController extends HttpServlet {

    private static final String SUCCESS = "singleProduct.jsp";
    private static final String ERROR = "HomeController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String productID = request.getParameter("productID");
            ProductDAO dao = new ProductDAO();
            ProductDTO product = new ProductDTO();
            Random random = new Random();
            ProductDTO productById = dao.getProductByID(productID);
            if (productById != null) {
                List<ProductDTO> listAll = dao.getListProduct();
                List<ProductDTO> listRandom4Product = new ArrayList<>();
                String[] parts = productById.getName().split(" ");
                String type = null;
                for (int i = 0; i < parts.length; i++) {
                    if (parts[i].equals("-")) {
                        type = parts[i - 1];
                        break;
                    }
                }
                List<ProductDTO> listByCategory = product.getCategory(productID, listAll, type);
                while (listRandom4Product.size() < 4 && !listByCategory.isEmpty()) {
                    int randomIndex = random.nextInt(listByCategory.size());
                    ProductDTO randomProduct = listByCategory.get(randomIndex);
                    listRandom4Product.add(randomProduct);
                    listByCategory.remove(randomIndex);

                }
                request.setAttribute("GET_PRODUCT", productById);
                request.setAttribute("LIST_RELATED", listRandom4Product);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at ShoppingController: " + e.toString());
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
