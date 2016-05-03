/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.m3.servlet;

import amm.m3.Customer;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "Venditore", urlPatterns = {"/venditore.html"})
public class Venditore extends HttpServlet {

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
        
        HttpSession session = request.getSession(false);
        Boolean itemSelled = true;

        if(session == null || session.getAttribute("loggedIn") == null || !((Boolean)session.getAttribute("loggedIn"))){
            request.getRequestDispatcher("M3/login.jsp").forward(request, response);
            return;
        }
        else if(session.getAttribute("user") == null || session.getAttribute("user") instanceof Customer){
            request.setAttribute("authError", true);
            session.invalidate();
            request.getRequestDispatcher("M3/venditore.jsp").forward(request, response);
            return;
        }
        else if(request.getParameter("Logout") != null){
            session.invalidate();
            request.getRequestDispatcher("M3/descrizione.jsp").forward(request, response);
            return;
        }
        else if(request.getParameter("Sell") != null){
            request.removeAttribute("nameError");
            request.removeAttribute("imageError");
            request.removeAttribute("priceError");
            request.removeAttribute("amountError");
            request.removeAttribute("descError");
            
            String itemName = request.getParameter("Name");
            
            if(itemName == null || itemName.isEmpty()){
                request.setAttribute("nameError", "L'oggetto deve avere un nome");
                itemSelled = false;
            }
            else{
                request.setAttribute("name", itemName);
            }
            
            String itemImage = request.getParameter("Image");

            if(itemImage == null || itemImage.isEmpty()){
                request.setAttribute("imageError", "L'oggetto deve avere una foto");
                itemSelled = false;
            }
            else{
                request.setAttribute("image", itemImage);
            }
            
            try{
                Double itemPrice = Double.parseDouble(request.getParameter("Price"));
                if(itemPrice <= 0){
                    request.setAttribute("priceError", "Il prezzo deve essere positivo");
                    itemSelled = false;
                }
                else{
                    request.setAttribute("price", itemPrice);
                }
            }
            catch(NumberFormatException | NullPointerException e){
                request.setAttribute("priceError", "L'oggetto deve avere un prezzo");
                itemSelled = false;
            }
            
            try{
                Integer itemAmount = Integer.parseInt(request.getParameter("Amount"));
                if(itemAmount <= 0){
                    request.setAttribute("amountError", "La quantità deve essere positiva");
                    itemSelled = false;
                }
                else{
                    request.setAttribute("amount", itemAmount);
                }
            }
            catch(NumberFormatException | NullPointerException e){
                request.setAttribute("amountError", "L'oggetto deve avere un prezzo");
                itemSelled = false;
            }
            
            String itemDesc = request.getParameter("Desc");
            
            if(itemDesc == null || itemDesc.isEmpty()){
                request.setAttribute("descError", "L'oggetto deve avere una descrizione");
                itemSelled = false;
            }
            else{
                request.setAttribute("desc", itemDesc);
            }
        }
        else if(request.getParameter("Back") != null){
            itemSelled = false;
        }
        request.setAttribute("itemSelled", itemSelled);
        request.getRequestDispatcher("M3/venditore.jsp").forward(request, response);
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

}
