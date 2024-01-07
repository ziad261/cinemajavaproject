/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.io.Serializable;

/**
 *
 * @author Ali
 */
public class Snack implements Serializable {
    private static final long serialVersionUID = 3570081285649915464L;
    
    private String name;
    private double price;
    private Customer customer;
    
    public Snack(){
        this.name = "";
        this.price = 0f;
    }

    public Snack(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public Customer getCustomer(){
        return customer;
    }
    
    public void setCustomer(Customer customer){
        this.customer = customer;
    }
}
