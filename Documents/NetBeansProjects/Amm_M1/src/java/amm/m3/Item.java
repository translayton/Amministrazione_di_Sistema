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
    private Integer id;
    private Integer sellerId;
    private Integer imgHeight;
    private Integer imgWidth;
    private Integer amount;
    private double price;
    public static ArrayList<Item> itemList;
    
    public Item(String name, int id, int sellerId, String imgName, String imgAlt, int imgHeight, int imgWidth, int amount, double price){
        this.name = name;
	this.id = id;
	this.sellerId = sellerId;
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
     
    @Override
    public String toString(){
	return this.getName() + " " + this.getImgName() + " " + this.getImgAlt() + " " + 
		this.getImgHeight() + " " + this.getImgWidth() + " " +
		this.getAmount() + " " + this.getPrice();
    }
    
    @Override
    public boolean equals(Object obj){
	return obj != null && obj instanceof Item && this.getId().equals(((Item)obj).getId());
    }
    
    @Override
    public int hashCode(){
	return this.id;
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

    /**
     * @return the id
     */
    public Integer getId() {
	return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
	this.id = id;
    }

    /**
     * @return the sellerId
     */
    public Integer getSellerId() {
	return sellerId;
    }

    /**
     * @param sellerId the sellerId to set
     */
    public void setSellerId(Integer sellerId) {
	this.sellerId = sellerId;
    }

    /**
     * @return the imgHeight
     */
    public Integer getImgHeight() {
	return imgHeight;
    }

    /**
     * @param imgHeight the imgHeight to set
     */
    public void setImgHeight(Integer imgHeight) {
	this.imgHeight = imgHeight;
    }

    /**
     * @return the imgWidth
     */
    public Integer getImgWidth() {
	return imgWidth;
    }

    /**
     * @param imgWidth the imgWidth to set
     */
    public void setImgWidth(Integer imgWidth) {
	this.imgWidth = imgWidth;
    }

    /**
     * @return the amount
     */
    public Integer getAmount() {
	return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Integer amount) {
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
    
    public static Item getItemById(Integer id){
	for(Item i: itemList){
	    if(i.getId().equals(id)) return i;
	}
	
	return null;
    }
}
