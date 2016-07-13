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
    
    public Seller(int id, String name, String surname, String username, String password, double money){
        super(id, name, surname, username, password, money);
        itemList = new ArrayList<>();
    }
    
    public ArrayList<Item> getItemList(){
        if(this.itemList == null){
            this.itemList = new ArrayList<>();
        }
        return this.itemList;
    }
    
    public Item getItemByIndex(int index){
        return this.itemList.get(index);
    }
    
    public void addItem(Item item){
        if(!this.itemList.contains(item)) this.itemList.add(item);
        if(!Item.itemList.contains(item)) Item.itemList.add(item);
    }
    
    public void setItem(Item item){
	for(int i=0; i<this.itemList.size(); i++){
	    if(this.itemList.get(i).getId().equals(item.getId())){
		this.itemList.set(i, item); 
		return;
	    }
	} 
    }
    
    public void removeItem(int id){
	for(int i=0; i<this.itemList.size(); i++){
	    if(this.itemList.get(i).getId().equals(id)){
		Item item = this.itemList.remove(i);
		Item.itemList.remove(item);
		return;
	    }
	}   
    }
    
    public void printList(){	
	if(itemList.isEmpty()) System.out.println("VUOTA");
	for(Item i : itemList){
	    System.out.print(getUsername() + " -> " + i + "\n\n\n");
	}
    }
}
