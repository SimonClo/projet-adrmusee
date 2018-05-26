package fr.musee.adr.adrmusee;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    // Classe contenant les commandes des clients

    private int id;
    private static int cpt = 0;
    private ArrayList<Product> orderList;
    private String customerId;
    private double totalCost;
    private Date date;

    private boolean ready;

    private static ArrayList<Order> allOrdersList;

    Order(String customerId, Basket basket){

        if (basket.isPaid() == true) {
            this.customerId = customerId;
            cpt++;
            id = cpt;
            ready = false;
            allOrdersList.add(this);
            date = new Date();
            totalCost = basket.getTotalPrice();

            orderList = basket.getListProducts();
        }
    }

    //// Getters and setters ////

    public int getId() {
        return id;
    }

    public ArrayList<Product> getOrderList() {
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

    public static ArrayList<Order> getAllOrdersList() {
        return allOrdersList;
    }

    public Date getDate() {
        return date;
    }
}
