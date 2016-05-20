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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author utente
 */
public class UserFactory {
    public static UserFactory instance;
    private String connectionString;
    
    public static UserFactory getInstance() {
        if (instance == null) {
            instance = new UserFactory();
        }
        return instance;
    }
    
    public ArrayList<User> getUserList(){
        User.userList = new ArrayList<>();
        
         try{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            
            Connection con = (Connection) DriverManager.getConnection("jdbc:derby://localhost:1527/ammdb", "milestone4", "milestone4");
            Statement stmt = con.createStatement();
            
            String sql = "select * from UserTable";
            
            ResultSet userSet = stmt.executeQuery(sql);
            
            while(userSet.next()){
                String name = userSet.getString("name");
                String surname = userSet.getString("surname");
                String username = userSet.getString("username");
                String password = userSet.getString("password");
                double money = userSet.getDouble("money");
                boolean isSeller = userSet.getBoolean("isSeller");
                
                if(isSeller)    User.userList.add(new Seller(name, surname, username, password, money));
                else            User.userList.add(new Customer(name, surname, username, password, money));
            }
            
            con.close();
            
        }catch(ClassNotFoundException | SQLException e){
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, e);
        }
 
        return User.userList;
    }
    
    public void setConnectionString(String s){
	this.connectionString = s;
    }
    public String getConnectionString(){
	return this.connectionString;
    }
}
