/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.m3.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import amm.m3.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
/**
 *
 * @author utente
 */
@WebServlet(name = "Cliente", urlPatterns = {"/cliente.html"})
public class Cliente extends HttpServlet {

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
        Item thisItem;
        
	if(Item.itemList == null || Item.itemList.isEmpty()){
	    request.setAttribute("itemList", ItemFactory.getItemList());
	}
        
        if(session == null || session.getAttribute("loggedIn") == null || !((Boolean)session.getAttribute("loggedIn"))){
            request.getRequestDispatcher("M3/login.jsp").forward(request, response);
            return;
        }
        else if(session.getAttribute("user") == null || !(session.getAttribute("user") instanceof Customer)){
            request.setAttribute("authError", true);
            session.invalidate();
            request.getRequestDispatcher("M3/cliente.jsp").forward(request, response);
            return;
        }
        else if(request.getParameter("Logout") != null){
            session.invalidate();
            request.getRequestDispatcher("M3/descrizione.jsp").forward(request, response);
            return;
        }
        else if(request.getParameter("Cart") != null){
            thisItem = Item.itemList.get(Integer.parseInt(request.getParameter("obj")));
            request.setAttribute("thisItem", thisItem);
	    request.setAttribute("itemList", Item.itemList);
        }
        else if(request.getParameter("Buy") != null){
	    try {
		Seller seller = null;
		Customer customer = (Customer)session.getAttribute("user");
		Integer id = Integer.parseInt(request.getParameter("obj"));
		thisItem = Item.itemList.get(id);
		request.setAttribute("thisItem", thisItem);
		Connection con = (Connection) DriverManager.getConnection("jdbc:derby://localhost:1527/ammdb", "milestone4", "milestone4");
		con.setAutoCommit(false);
		
		Statement stmt = con.createStatement();
		
		Integer amount = thisItem.getAmount();
		thisItem.setAmount(--amount);
		
		for(User user: User.userList){
		    if(user instanceof Seller){
			if(((Seller)user).getItemList().contains(thisItem)){
			    seller = (Seller)user;	
			}
		    }
		}
		
                System.out.print(seller);
                
		id++;
		String sql = "";
		
		if(amount == 0){
		    Item.itemList.remove(thisItem);
		    
		    seller.getItemList().remove(thisItem);
		    
		    sql = "delete from ItemTable where id = " + id;
		    stmt.executeUpdate(sql);
		}
		else{
		    
		    sql = "update ItemTable set amount = " + amount + " where id = " + id;
		    stmt.executeUpdate(sql);
		}
		
		Double wallet = customer.getWallet().getAmount();
		if( wallet >= thisItem.getPrice()){
		    request.setAttribute("selled", true);
		    customer.addToWallet(-thisItem.getPrice());
		    Integer index = User.userList.indexOf(customer) + 1;
		    sql = "update UserTable set money = " + customer.getWallet().getAmount() + " where id = " + index;
		    stmt.executeUpdate(sql);
		    seller.addToWallet(thisItem.getPrice());
		    index = User.userList.indexOf(seller) + 1;
		    sql = "update UserTable set money = " + seller.getWallet().getAmount() + " where id = " + index;
		    stmt.executeUpdate(sql);
		    con.commit();
		}
		else{
		    request.setAttribute("lowBudget", true);
		    con.rollback();
		}
		
		stmt.close();
		con.close();
	    } catch (SQLException ex) {
		Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
	    }
        }
	else if(request.getParameter("Back")!=null){
	    request.setAttribute("itemList", Item.itemList);
	}
        
        request.getRequestDispatcher("M3/cliente.jsp").forward(request, response);
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
