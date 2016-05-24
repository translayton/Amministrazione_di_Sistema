/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.m3.servlet;

import amm.m3.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author utente
 */
@WebServlet(name = "Login", urlPatterns = {"/login.html"}, loadOnStartup = 0)
public class Login extends HttpServlet {
    private static final String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_CLEAN_PATH = "../../web/WEB-INF/db/ammdb";
    private static final String DB_BUILD_PATH = "WEB-INF/db/ammdb";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        
        if(request.getParameter("Submit") != null){
            
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            request.removeAttribute("usernameError");
            request.removeAttribute("passwordError");
            
            if(username != null && password != null){
                ArrayList<User> userList = UserFactory.getInstance().getUserList();
                for(User u : userList){
                    if(u.getUsername().equals(username)){
                        if(u.getPassword().equals(password)){
                            session.setAttribute("loggedIn", true);
                            ArrayList<Item> itemList = ItemFactory.getItemList();
                            
                            if(u instanceof Seller) {
                                session.setAttribute("user", (Seller) u);
                                request.setAttribute("itemList", ((Seller)u).getItemList());
                                request.getRequestDispatcher("M3/venditore.jsp").forward(request, response);  
                            }
                            else{
                                session.setAttribute("user", (Customer) u);
                                request.setAttribute("itemList", itemList);
                                request.getRequestDispatcher("M3/cliente.jsp").forward(request, response);  
                            }
                            return;
                        }
                        request.setAttribute("passwordError", "Password errata");
                        request.getRequestDispatcher("M3/login.jsp").forward(request, response);
                        return;
                    }
                }
                request.setAttribute("usernameError", "Utente non registrato");
            }
            
            if(username.isEmpty()) request.setAttribute("usernameError", "Il campo Username non può essere vuoto");
            if(password.isEmpty()) request.setAttribute("passwordError", "Il campo Password non può essere vuoto");
        }
        else{
            if(session.getAttribute("loggedIn") != null && ((Boolean)session.getAttribute("loggedIn")) == true){
                if(((User)session.getAttribute("user")) instanceof Seller){
                    request.getRequestDispatcher("M3/venditore.jsp").forward(request, response);
                    return;
                }
                if(((User)session.getAttribute("user")) instanceof Customer){
                    request.getRequestDispatcher("M3/cliente.jsp").forward(request, response);
                    return;
                }
            }
        }
        
        request.getRequestDispatcher("M3/login.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
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
    
    @Override 
    public void init(){
        String dbConnection = "jdbc:derby:" + this.getServletContext().getRealPath("/") + DB_BUILD_PATH;
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        UserFactory.getInstance().setConnectionString(dbConnection);
        ItemFactory.getInstance().setConnectionString(dbConnection);
    }
}
