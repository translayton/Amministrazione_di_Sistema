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
    private String imgAlt;
    private int imgHeight;
    private int imgWidth;
    private int amount;
    private double price;
    public static ArrayList<Item> itemList;
    
    public Item(String name, String imgName, String imgAlt, int imgHeight, int imgWidth, int amount, double price){
        this.name = name;
        this.imgName = imgName;
        this.imgAlt = imgAlt;
        this.imgHeight = imgHeight;
        this.imgWidth = imgWidth;
        this.amount = amount;
        this.price = price;
        if(itemList == null){
            itemList = new ArrayList<>();
        }
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

    /**
     * @return the imgHeight
     */
    public int getImgHeight() {
        return imgHeight;
    }

    /**
     * @param imgHeight the imgHeight to set
     */
    public void setImgHeight(int imgHeight) {
        this.imgHeight = imgHeight;
    }

    /**
     * @return the imgWidth
     */
    public int getImgWidth() {
        return imgWidth;
    }

    /**
     * @param imgWidth the imgWidth to set
     */
    public void setImgWidth(int imgWidth) {
        this.imgWidth = imgWidth;
    }

    /**
     * @return the imgAlt
     */
    public String getImgAlt() {
        return imgAlt;
    }

    /**
     * @param imgAlt the imgAlt to set
     */
    public void setImgAlt(String imgAlt) {
        this.imgAlt = imgAlt;
    }
}
