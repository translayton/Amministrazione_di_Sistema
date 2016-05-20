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
public class Seller extends User{
    
    private ArrayList<Item> itemList;
    
    public Seller(String name, String surname, String username, String password, double money){
        super(name, surname, username, password, money);
        itemList = new ArrayList<>();
    }
    
    public ArrayList<Item> getItemList(){
        return this.itemList;
    }
    
    public Item getItemById(int id){
        return this.itemList.get(id);
    }
    
    public void addItem(Item item){
        this.itemList.add(item);
        Item.itemList.add(item);
    }
    
    public void removeItem(int id){
        Item.itemList.remove(this.itemList.get(id));
        this.itemList.remove(id);
    }
}
