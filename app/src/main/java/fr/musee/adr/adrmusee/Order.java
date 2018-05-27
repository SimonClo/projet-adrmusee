package fr.musee.adr.adrmusee;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    // Classe contenant les commandes des clients

    private int id;
    private static int cpt = 0;
    private ArrayList<ProductQuantity> orderList;
    private String customerId;
    private double totalCost;
    private Date date;

    private boolean ready;

    Order(String customerId, Basket basket){

        if (basket.isPaid() == true) {
            this.customerId = customerId;
            cpt++;
            id = cpt;
            ready = false;
            date = new Date();
            totalCost = basket.getTotalPrice();

            orderList = basket.listProductQuantity();
        }
    }

    public Order(){

    }

    //// Getters and setters ////

    public int getId() {
        return id;
    }

    public ArrayList<ProductQuantity> getOrderList() {
        return orderList;
    }

    public String getCustomerId() {
        return customerId;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public Date getDate() {
        return date;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
