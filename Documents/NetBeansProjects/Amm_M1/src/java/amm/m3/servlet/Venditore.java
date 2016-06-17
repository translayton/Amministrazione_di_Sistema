/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.m3.servlet;

import amm.m3.Customer;
import amm.m3.Item;
import amm.m3.ItemFactory;
import amm.m3.Seller;
import amm.m3.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
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
    @SuppressWarnings("CallToPrintStackTrace")
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        Boolean itemSelled = true;
        Boolean isEditing = false;
        Integer editItem = 0;
        
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
            if(request.getParameter("editItem")!=null){
                editItem = Integer.parseInt(request.getParameter("editItem"));
            }
            request.removeAttribute("nameError");
            request.removeAttribute("imageError");
            request.removeAttribute("priceError");
            request.removeAttribute("amountError");
            request.removeAttribute("descError");
            
            isEditing = Boolean.parseBoolean(request.getParameter("isEditing"));
            
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
            
            User u = (User)session.getAttribute("user");
            if(u!=null && u instanceof Seller){
                request.setAttribute("itemList", ((Seller)u).getItemList());
            }
        }
        else if(request.getParameter("Back") != null){
            User u = (User)session.getAttribute("user");
            isEditing = Boolean.parseBoolean(request.getParameter("isEditing"));
            
            if(!isEditing){
                if(u != null && u instanceof Seller){
		    editItem = Integer.parseInt(request.getParameter("editItem"));
		    
                    Item item = new Item(request.getParameter("name"), editItem, request.getParameter("image"), request.getParameter("desc"), 96, 96, 
                            Integer.parseInt(request.getParameter("amount")), Double.parseDouble(request.getParameter("price")));

                    ((Seller)u).addItem(item);

                    try{
                        Connection con = (Connection) DriverManager.getConnection("jdbc:derby://localhost:1527/ammdb", "milestone4", "milestone4");

                        String sql = "insert into ItemTable (id, sellerid, name, imgname, imgalt, imgheight, imgwidth, amount, price) " +
                                    "values (default, ?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement stmt = con.prepareStatement(sql);

                        stmt.setInt(1, User.userList.indexOf(u)+1);
                        stmt.setString(2, item.getName());
                        stmt.setString(3, item.getImgName());
                        stmt.setString(4, item.getImgAlt());
                        stmt.setInt(5, item.getImgHeight());
                        stmt.setInt(6, item.getImgWidth());
                        stmt.setInt(7, item.getAmount());
                        stmt.setDouble(8, item.getPrice());

                        stmt.executeUpdate();
			
			stmt.close();
			con.close();
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }
            }
            else{
                editItem = Integer.parseInt(request.getParameter("editItem"));

                Item item = new Item(request.getParameter("name"), editItem, request.getParameter("image"), request.getParameter("desc"), 96, 96, 
                    Integer.parseInt(request.getParameter("amount")), Double.parseDouble(request.getParameter("price")));

                try{
                    Connection con = (Connection) DriverManager.getConnection("jdbc:derby://localhost:1527/ammdb", "milestone4", "milestone4");
                    Integer itemIndex = Item.itemList.indexOf(((Seller)u).getItemById(editItem)) + 1;
                    
                    String sql = "update ItemTable set name = ?, imgname = ?, imgalt = ?, imgheight = ?, imgwidth = ?, amount = ?, price = ? " + 
                            "where id = " + itemIndex;
                    PreparedStatement stmt = con.prepareStatement(sql);
                    
                    stmt.setString(1, item.getName());
                    stmt.setString(2, item.getImgName());
                    stmt.setString(3, item.getImgAlt());
                    stmt.setInt(4, item.getImgHeight());
                    stmt.setInt(5, item.getImgWidth());
                    stmt.setInt(6, item.getAmount());
                    stmt.setDouble(7, item.getPrice());
                    
                    Integer n = stmt.executeUpdate();
		    
		    stmt.close();
		    con.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            
                ((Seller)u).setItem(editItem, item);
            }
            
            itemSelled = false;
            isEditing = false;
            request.setAttribute("itemList", ((Seller)u).getItemList());
        }
        else if(request.getParameter("Edit")!=null){
            isEditing = true;
            itemSelled = false;
            editItem = Integer.parseInt(request.getParameter("editItem"));
            User u = (User)session.getAttribute("user");
            if(u!=null && u instanceof Seller){
                request.setAttribute("itemList", ((Seller)u).getItemList());
            }
        }
        else if(request.getParameter("Remove")!=null){
            itemSelled = false;
            Seller u = (Seller)session.getAttribute("user");
            Integer removeItem = Integer.parseInt(request.getParameter("removeItem"));
            u.removeItem(removeItem);
            request.setAttribute("itemList", u.getItemList());
            
            try{
		Connection con = (Connection) DriverManager.getConnection("jdbc:derby://localhost:1527/ammdb", "milestone4", "milestone4");
                Integer itemIndex = Item.itemList.indexOf(u.getItemById(removeItem)) + 1;
                    
                String sql = "delete from ItemTable where id = " + itemIndex;
                PreparedStatement stmt = con.prepareStatement(sql);

                Integer n = stmt.executeUpdate();

		stmt.close();
		con.close();
            }catch(SQLException e){
	        e.printStackTrace();
            }
        }
        
        ArrayList<Item> itemList = (ArrayList<Item>)request.getAttribute("itemList");
        User user = (User)session.getAttribute("user");
        if((itemList==null || itemList.isEmpty()) && user != null && user instanceof Seller){
	    request.setAttribute("itemList", ((Seller)user).getItemList());
	}
        
        request.setAttribute("editItem", editItem);
        request.setAttribute("isEditing", isEditing);
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
