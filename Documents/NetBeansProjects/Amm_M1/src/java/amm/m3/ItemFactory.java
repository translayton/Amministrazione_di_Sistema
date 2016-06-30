/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.m3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author utente
 */
public class ItemFactory {
    public static ItemFactory instance;
    private String connectionString;
    
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
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            
            con = (Connection) DriverManager.getConnection(connectionString, "milestone4", "milestone4");
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

                    Item item = new Item(name, id-1, imgName, imgAlt, imgHeight, imgWidth, amount, price);

                    if(User.userList.get(sellerId-1) instanceof Seller){
                        ((Seller)User.userList.get(sellerId-1)).addItem(item);
                    }
                    else{
                        Item.itemList.add(item);
                    }
                }
            }
            stmt.close();
            con.close();
            
        }
        catch(ClassNotFoundException | SQLException e){
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
