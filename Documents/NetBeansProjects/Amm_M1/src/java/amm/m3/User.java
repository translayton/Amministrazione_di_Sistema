/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.m3;

import java.util.ArrayList;

/**
 *
 * @author utente
 */
public class User {
    private Integer id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private Money wallet;
    public static ArrayList<User> userList;
    
    public User(int id, String name, String surname, String username, String password, double money){
	this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.wallet = new Money(money);
	if(userList==null) initList();
	userList.add(this);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    public static void initList(){
        userList = new ArrayList<>();
    }
    
    public static void userListAdd(User user){
	if(userList == null) initList();
        userList.add(user);
    }
    
    public static ArrayList<User> getUserList(){
        return userList;
    }
    
    public static User getUserById(int id){
        for(User u: userList){
	    if(u.getId().equals(id)){
		return u;
	    }
	}
	
	return null;
    }

    /**
     * @return the wallet
     */
    public Money getWallet() {
        return wallet;
    }

    /**
     * @param wallet the wallet to set
     */
    public void setWallet(Money wallet) {
        this.wallet = wallet;
    }
    
    public void addToWallet(double money){
	this.wallet.addAmount(money);
    }

    /**
     * @return the id
     */
    public Integer getId() {
	return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
	this.id = id;
    }
    
    public static void printUserList(){	
	if(userList.isEmpty()) System.out.println("NESSUN USER");
	for(User u : userList){
	    System.out.println(u.getUsername());
	}
    }
    
    public static User getByUsername(String username){
	if(userList == null) initList();
	
	for(User u: userList){
	    if(u.name.equals(username)) return u;
	}
	
	return null;
    }
}
