/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.m3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author utente
 */
public class ItemFactory {
    public static ItemFactory instance;
    private String connectionString;
    private static final String CON_USERNAME = "milestone4";
    private static final String CON_PASSWORD = "milestone4";
    
    public static ItemFactory getInstance() {
        if (instance == null) {
            instance = new ItemFactory();
        }
        return instance;
    }
    
    public ArrayList<Item> getItemList(){
	Connection con = null;
        Statement stmt = null;
        ResultSet itemSet = null;
        
        try{           
            con = (Connection) DriverManager.getConnection(connectionString, CON_USERNAME, CON_PASSWORD);
            stmt = con.createStatement();
            
            String sql = "select count(*) from ItemTable";
            
            itemSet = stmt.executeQuery(sql);
            
            itemSet.next();
            int count = itemSet.getInt(1);

            if(Item.itemList == null || Item.itemList.isEmpty() || Item.itemList.size() < count){
                sql = "select * from ItemTable";
                itemSet = stmt.executeQuery(sql);
                
                while(itemSet.next()){
                    int sellerId = itemSet.getInt("sellerId");
                    String name = itemSet.getString("name");
                    Integer id = itemSet.getInt("id");
                    String imgName = itemSet.getString("imgName");
                    String imgAlt = itemSet.getString("imgAlt");
                    int imgHeight = itemSet.getInt("imgHeight");
                    int imgWidth = itemSet.getInt("imgWidth");
                    int amount = itemSet.getInt("amount");
                    double price = itemSet.getDouble("price"); 

                    Item item = new Item(name, id, sellerId, imgName, imgAlt, imgHeight, imgWidth, amount, price);
 
                    Item.itemList.add(item);
                    
                }
		
		itemSet.close();
            }

            stmt.close();
            con.close();
            
        }
        catch(SQLException e){
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, e);
        }
        finally{
            try {
                if(itemSet!=null && stmt!=null && con!=null){
                    itemSet.close();
                    stmt.close();
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ItemFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return Item.itemList;
    }
    
    public ArrayList<Item> getUserItemList(Seller s){
	try{
	    Connection con = DriverManager.getConnection(connectionString, CON_USERNAME, CON_PASSWORD);
	    Statement stmt = con.createStatement();
	    String sql = "select * from itemtable where sellerid = " + s.getId();
	    ResultSet itemSet = stmt.executeQuery(sql);
	    
	    while(itemSet.next()){
		int sellerId = itemSet.getInt("sellerId");
                String name = itemSet.getString("name");
                Integer id = itemSet.getInt("id");
                String imgName = itemSet.getString("imgName");
                String imgAlt = itemSet.getString("imgAlt");
                int imgHeight = itemSet.getInt("imgHeight");
                int imgWidth = itemSet.getInt("imgWidth");
                int amount = itemSet.getInt("amount");
                double price = itemSet.getDouble("price"); 

                Item item = new Item(name, id, sellerId, imgName, imgAlt, imgHeight, imgWidth, amount, price);
 
                s.getItemList().add(item);
	    }
	    
	    itemSet.close();
	    stmt.close();
	    con.close();
	}
	catch(SQLException e){
	    Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, e);
	}
	
	return s.getItemList();
    }
    
    public void addNewItem(Seller s, String name, Integer sellerId, String imgName, 
	    String imgAlt, int imgHeight, int imgWidth, int amount, double price){

	try{
            Connection con = (Connection) DriverManager.getConnection(connectionString, CON_USERNAME, CON_PASSWORD);
	     
            String sql = "select count(*) from ItemTable";
	    
            PreparedStatement stmt = con.prepareStatement(sql);
	    
	    ResultSet itemSet = stmt.executeQuery();
            
            itemSet.next();
            int count = itemSet.getInt(1);
	    
	    itemSet.close();
	    stmt.close();
	    
	    Item item = new Item(name, count+1, sellerId, imgName, imgAlt, imgHeight, imgWidth, amount, price);
	    
	    s.addItem(item);
	    
	    sql = "insert into ItemTable (id, sellerid, name, imgname, imgalt, imgheight, imgwidth, amount, price) " +
                                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    
	    stmt = con.prepareStatement(sql);

	    stmt.setInt(1, count+1);
            stmt.setInt(2, sellerId);
            stmt.setString(3, name);
            stmt.setString(4, imgName);
            stmt.setString(5, imgAlt);
            stmt.setInt(6, imgHeight);
            stmt.setInt(7, imgWidth);
            stmt.setInt(8, amount);
            stmt.setDouble(9, price);

	    stmt.executeUpdate();
			
	    stmt.close();
	    con.close();
	}
	catch(SQLException e){
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void editExistingItem(Seller s, String name, Integer id, Integer sellerId, String imgName, 
	    String imgAlt, int imgHeight, int imgWidth, int amount, double price){
	
	try{
            Connection con = (Connection) DriverManager.getConnection(connectionString, CON_USERNAME, CON_PASSWORD);
                    
            String sql = "update ItemTable set name = ?, imgname = ?, imgalt = ?, imgheight = ?, imgwidth = ?, amount = ?, price = ? " + 
                            "where id = " + id;
            PreparedStatement stmt = con.prepareStatement(sql);
                    
            stmt.setString(1, name);
            stmt.setString(2, imgName);
            stmt.setString(3, imgAlt);
            stmt.setInt(4, imgHeight);
            stmt.setInt(5, imgWidth);
            stmt.setInt(6, amount);
            stmt.setDouble(7, price);
                    
            stmt.executeUpdate();
	    
	    stmt.close();
	    con.close();
        }catch(SQLException e){
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, e);
        }
            
        s.setItem(new Item(name, id, sellerId, imgName, imgAlt, imgHeight, imgWidth, amount, price));
    }
    
    public void removeItem(Seller s, Integer removeItem){
	try{
	    Connection con = (Connection) DriverManager.getConnection(connectionString, CON_USERNAME, CON_PASSWORD);
                    
            String sql = "delete from ItemTable where id = " + removeItem;
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.executeUpdate();
	    
	    s.removeItem(removeItem);
	    
	    stmt.close();
	    con.close();
        }catch(SQLException e){
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void sellItem(Customer c, Integer id, HttpServletRequest request){
	final Integer AMOUNT;
	final Double WALLET;
	
	try{
	    Connection con = (Connection) DriverManager.getConnection(connectionString, CON_USERNAME, CON_PASSWORD);
	    con.setAutoCommit(false);
	    
	    Statement stmt = con.createStatement();
	    
	    Item item = Item.getItemById(id);
	    Integer amount = AMOUNT = item.getAmount();
	    if(amount > 0) item.setAmount(--amount);
							
	    String sql = "update ItemTable set amount = " + amount + " where id = " + id;
	    stmt.executeUpdate(sql);
	    
	    Double wallet = WALLET = c.getWallet().getAmount();
	    
	    if( wallet >= item.getPrice()){
		request.setAttribute("selled", true);
		c.addToWallet(-item.getPrice());
		sql = "update UserTable set money = " + c.getWallet().getAmount() + " where id = " + c.getId();
		stmt.executeUpdate(sql);
		
		sql = "select * from UserTable where id = " + item.getSellerId();
		ResultSet set = stmt.executeQuery(sql);
		
		if(set.next()){
		    Double money = set.getDouble("money");
		    money += item.getPrice();
		    sql = "update UserTable set money = " + money + " where id = " + item.getSellerId();
		    stmt.executeUpdate(sql);
		    con.commit();	
		}
	    }
	    else{
	        request.setAttribute("lowBudget", true);
	        con.rollback();
		item.setAmount(AMOUNT);
		c.setWallet(new Money(WALLET));
	    }

	    stmt.close();
	    con.close();
	}
	catch(SQLException e){
	    Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, e);
	}
    }
    
    private static boolean canAddToList(ArrayList<String> words, String filter){
	for(String word: words){
	    if(word.toLowerCase().contains(filter.toLowerCase())){
		return true;
	    }
	}
	
	return false;
    }
    
    public static ArrayList<Item> getSearchedItemList(String filter){
	ArrayList<Item> items = getInstance().getItemList();
	ArrayList<Item> searchedItems = new ArrayList<>();

	if(items!=null){
	    for(Item item: items){
		ArrayList<String> words = new ArrayList<>();
		words.addAll(Arrays.asList(item.getName().split(" ")));
		words.addAll(Arrays.asList(item.getImgAlt().split(" ")));
		    
	        if(canAddToList(words, filter)){
		    searchedItems.add(item);
	        }
	    }
	}
	
	return searchedItems;
    }
    
    public void setConnectionString(String s){
	this.connectionString = s;
    }
    public String getConnectionString(){
	return this.connectionString;
    }
}
