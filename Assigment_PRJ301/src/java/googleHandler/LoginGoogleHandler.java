/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package googleHandler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Form;
import user.UserDAO;
import user.UserDTO;

/**
 *
 * @author Sang1011
 */
@WebServlet(name = "LoginGoogleHandler", urlPatterns = { "/LoginGoogleHandler" })
public class LoginGoogleHandler extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String SUCCESS = "homepage.jsp";
    private static final String INFO = "This email is logged in with a regular account";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDAO dao = new UserDAO();
            String code = request.getParameter("code");
            String accessToken = getToken(code);
            UserDTO user = getUserInfo(accessToken);
            if (user != null) {
                UserDTO userCheck = dao.getUserByEmail(user.getEmail());
                if (userCheck == null) {
                    user.setRoleID("US");
                    Random generate = new Random();
                    do {
                        user.setUserID("G" + generate.nextInt(10000) + "%");
                    } while (dao.checkDuplicate(user.getUserID()));
                    user.setStatus(1);
                    boolean insert = dao.insert(user);
                    session.setAttribute("LOGIN_USER", user);
                    url = SUCCESS;
                } else {
                    if (userCheck.getUserID().startsWith("G") && userCheck.getUserID().endsWith("%")) {
                        session.setAttribute("LOGIN_USER", userCheck);
                        url = SUCCESS;
                    } else {
                        request.setAttribute("ERROR", INFO);
                    }
                }
            }
        } catch (Exception e) {
            log("Error at LoginGoogleHandler: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    public static String getToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static UserDTO getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        UserDTO googlePojo = new UserDTO();
        googlePojo.setName(jobj.get("id").getAsString());
        googlePojo.setName(jobj.get("name").getAsString());
        googlePojo.setEmail(jobj.get("email").getAsString());
        return googlePojo;
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
