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
public class ItemFactory {
    public static ItemFactory instance;
    
    public static ItemFactory getInstance() {
        if (instance == null) {
            instance = new ItemFactory();
        }
        return instance;
    }
    
    public static ArrayList<Item> getItemList(){
        Item.itemList = new ArrayList<>();
        Item.itemList.add(new Item("Aspirapolvere", "aspirapolvere.jpg", "Foto aspirapolvere", 96, 96, 3, 149.99));
        Item.itemList.add(new Item("Cellulare", "cellulare.jpg", "Foto cellulare", 96, 96, 7, 69.99));
        Item.itemList.add(new Item("Matite", "matite.jpg", "Foto matite", 96, 96, 5, 19.99));
        Item.itemList.add(new Item("Scarpe", "scarpe.jpg", "Foto scarpe", 96, 96, 4, 99.99));
        Item.itemList.add(new Item("Tagliaerba", "tagliaerba.jpg", "Foto tagliaerba", 96, 96, 2, 54.90));
        
        return Item.itemList;
    }
}
