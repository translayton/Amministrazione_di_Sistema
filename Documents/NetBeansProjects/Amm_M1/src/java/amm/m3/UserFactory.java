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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author utente
 */
public class UserFactory {
    public static UserFactory instance;
    private String connectionString;
    private static final String CON_USERNAME = "milestone4";
    private static final String CON_PASSWORD = "milestone4";
    
    public static UserFactory getInstance() {
        if (instance == null) {
            instance = new UserFactory();
        }
        return instance;
    }
    
    public ArrayList<User> getUserList(){
        User.userList = new ArrayList<>();
        
         try{
            
            Connection con = (Connection) DriverManager.getConnection(connectionString, CON_USERNAME, CON_PASSWORD);
            Statement stmt = con.createStatement();
            
            String sql = "select * from UserTable";
            
            ResultSet userSet = stmt.executeQuery(sql);
            
            while(userSet.next()){
		int id = userSet.getInt("id");
                String name = userSet.getString("name");
                String surname = userSet.getString("surname");
                String username = userSet.getString("username");
                String password = userSet.getString("password");
                double money = userSet.getDouble("money");
                boolean isSeller = userSet.getBoolean("isSeller");
                
                if(isSeller)    User.userList.add(new Seller(id, name, surname, username, password, money));
                else            User.userList.add(new Customer(id, name, surname, username, password, money));
            }
	    
	    userSet.close();
            stmt.close();
            con.close();
            
        }catch(SQLException e){
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, e);
        }
 
        return User.userList;
    }
    
    public User getUser(String username){
	User u = User.getByUsername(username);
	
	if(u == null){
	    try{
		Connection con = (Connection) DriverManager.getConnection(connectionString, CON_USERNAME, CON_PASSWORD);

		String sql = "select * from UserTable where username = ?";

		PreparedStatement stmt = con.prepareStatement(sql);

		stmt.setString(1, username);

		ResultSet userSet = stmt.executeQuery();

		if(userSet.next()){
		    if(userSet.getBoolean("isSeller")){
			u = new Seller(userSet.getInt("id"), userSet.getString("name"), userSet.getString("surname"), username, userSet.getString("password"), userSet.getDouble("money"));
		    }
		    else{
			u = new Customer(userSet.getInt("id"), userSet.getString("name"), userSet.getString("surname"), username, userSet.getString("password"), userSet.getDouble("money"));
		    }	
		}

		userSet.close();
		stmt.close();
		con.close();
	    }
	    catch(SQLException e){
		Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, e);
	    }
	}
	
	return u;
    }
    
    public void setConnectionString(String s){
	this.connectionString = s;
    }
    public String getConnectionString(){
	return this.connectionString;
    }
}
