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
        User.userList = new ArrayList<>();
        User.userList.add(new Customer("Maurizio", "Santini", "mausan71", "customer1", 0.0));
        User.userList.add(new Customer("Demetra", "Fini", "demfi89", "customer2", 0.0));
        User.userList.add(new Seller("Enrico", "Righi", "enri75", "seller1", 0.0));
        User.userList.add(new Seller("Livia", "Nave", "lina83", "seller2", 0.0));
        
        return User.userList;
    }
}
