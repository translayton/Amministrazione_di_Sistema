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
public class Item {

    /**
     * @return the itemList
     */
    private String name;
    private String imgName;
    private int amount;
    private double price;
    private static ArrayList<Item> itemList;
    
    public Item(String name, String imgName, int amount, double price){
        this.name = name;
        this.imgName = imgName;
        this.amount = amount;
        this.price = price;
        itemList.add(this);
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
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    public void initList(){
        itemList = new ArrayList<>();
    }
    
    public static ArrayList<Item> getItemList() {
        return itemList;
    }
    
    public static void itemListAdd(Item item){
        itemList.add(item);
    }

    /**
     * @return the imgName
     */
    public String getImgName() {
        return imgName;
    }

    /**
     * @param imgName the imgName to set
     */
    public void setImgName(String imgName) {
        this.imgName = imgName;
    }
}
