/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import shopping.OrderDAO;
import shopping.OrderDetailDAO;
import shopping.ProductDAO;
import shopping.ProductDTO;
import user.UserDAO;
import user.UserDTO;

/**
 *
 * @author Sang1011
 */
@WebServlet(name = "SearchController", urlPatterns = { "/SearchController" })
public class SearchController extends HttpServlet {

    private static final String ERROR = "adminSearch.jsp";
    private static final String SUCCESS = "adminSearch.jsp";
    private static final String ADMIN_PAGE = "admin.jsp";
    private static final String SEARCH_PRODUCT_SUCCESS = "shopping.jsp";
    private static final String SEARCH_PRODUCT_FAILED = "shopping.jsp";
    private static final String ADMIN = "AD";
    private static final String BEST_SELLING = "bestSelling";
    private static final String NEW_ARRIVALS = "newArrivals";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            ProductDTO pro = new ProductDTO();
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            ProductDAO productdao = new ProductDAO();
            UserDAO dao = new UserDAO();
            String searchType = request.getParameter("searchType");
            String search = request.getParameter("search");
            if (searchType != null && search != null) {
                if (searchType.equals("searchUser")) {
                    List<UserDTO> listUser = dao.getListUser(search);
                    if (listUser.size() > 0) {
                        request.setAttribute("LIST", listUser);
                        request.setAttribute("TYPE", searchType);
                        url = SUCCESS;
                    }
                } else if (searchType.equals("searchProduct")) {
                    // admin
                    if (loginUser != null && ADMIN.equals(loginUser.getRoleID())) {
                        List<ProductDTO> listProducts = productdao.searchListProductNoStatus(search);
                        if (listProducts.size() > 0) {
                            request.setAttribute("LIST", listProducts);
                            request.setAttribute("TYPE", searchType);
                            url = SUCCESS;
                        }
                    } else {
                        // user or null
                        url = SEARCH_PRODUCT_FAILED;
                        List<ProductDTO> listProduct = productdao.searchListProduct(search);
                        String collection = request.getParameter("collection");
                        String brand = request.getParameter("brand");

                        int lower = 0;
                        int upper = 0;
                        boolean isRangeValid = true;

                        try {
                            lower = Integer.parseInt(request.getParameter("lower"));
                            upper = Integer.parseInt(request.getParameter("upper"));
                        } catch (NumberFormatException e) {
                            isRangeValid = false;
                        }

                        if (search.equals("none")) {
                            if (collection != null) {
                                listProduct = pro.getListByCollection(productdao.searchListProduct(""), collection);
                            } else if (brand != null) {
                                listProduct = pro.getListByBrand(productdao.searchListProduct(""), brand);
                            } else if (isRangeValid) {
                                if (upper == -1) {
                                    upper = 1000;
                                }
                                listProduct = pro.getListByRangePrice(productdao.searchListProduct(""), lower, upper);
                            }
                        } else {
                            switch (search) {
                                case BEST_SELLING:
                                    listProduct = productdao.getBestSelling();
                                    break;
                                case NEW_ARRIVALS:
                                    listProduct = productdao.getTop9ProductLast();
                                    break;
                            }
                        }
                        // paging
                        // list tổng
                        int page;
                        int numberPerPage = 9; // số phần tử trên 1 trang
                        int size = listProduct.size(); // tổng phần tử all product
                        int num = (size % numberPerPage == 0 ? (size / numberPerPage) : (size / numberPerPage) + 1); // số
                                                                                                                     // trang
                        String pageNow = request.getParameter("page");
                        if (pageNow == null) {
                            page = 1;
                        } else {
                            page = Integer.parseInt(pageNow);
                        }
                        int start, end;
                        start = (page - 1) * numberPerPage;
                        end = Math.min(page * numberPerPage, size);
                        List<ProductDTO> list = pro.getListByPage(listProduct, start, end);
                        // list 1 trang
                        request.setAttribute("DATA", list);
                        request.setAttribute("PAGE", page);
                        request.setAttribute("NUM", num);
                        url = SEARCH_PRODUCT_SUCCESS;
                    }

                }
            } else {
                OrderDetailDAO detaildao = new OrderDetailDAO();
                OrderDAO orderdao = new OrderDAO();
                int countUser = dao.countUser();
                int countOrder = orderdao.countOrders();
                double sumPrice = detaildao.sumPrice();
                request.setAttribute("USER_TOTAL", countUser);
                request.setAttribute("ORDER_TOTAL", countOrder);
                request.setAttribute("MONEY_TOTAL", sumPrice);
                url = ADMIN_PAGE;
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
