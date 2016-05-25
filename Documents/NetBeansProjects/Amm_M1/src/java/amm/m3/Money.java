/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.m3;

/**
 *
 * @author utente
 */
public class Money {
    private double amount;
    
    public Money(double money){
        this.amount = money;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public void addAmount(double money){
	this.amount+=money;
    }
}
