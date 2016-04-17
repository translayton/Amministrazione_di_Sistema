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
public class UserFactory {
    public static UserFactory instance;
    
    public static UserFactory getInstance() {
        if (instance == null) {
            instance = new UserFactory();
        }
        return instance;
    }
    
    public ArrayList<User> getUserList(){
        User.userListAdd(new Customer("Maurizio", "Santini", "mausan71", "customer1", 0.0));
        User.userListAdd(new Customer("Demetra", "Fini", "demfi89", "customer2", 0.0));
        User.userListAdd(new Seller("Enrico", "Righi", "enri75", "seller1", 0.0));
        User.userListAdd(new Seller("Livia", "Nave", "lina83", "seller2", 0.0));
        
        ((Seller)User.getUserById(2)).addItem(new Item("Aspirapolvere", "aspirapolver.jpg", 3, 149.99));
        ((Seller)User.getUserById(2)).addItem(new Item("Cellulare", "cellulare.jpg", 7, 69.99));
        ((Seller)User.getUserById(3)).addItem(new Item("Matite", "matite.jpg", 5, 19.99));
        ((Seller)User.getUserById(3)).addItem(new Item("Scarpe", "scarpe.jpg", 4, 99.99));
        ((Seller)User.getUserById(3)).addItem(new Item("Tagliaerba", "tagliaerba.jpg", 2, 54.90));
        
        return User.getUserList();
    }
}
