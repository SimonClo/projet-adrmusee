package fr.musee.adr.adrmusee;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    // Classe contenant les commandes des clients

    private int id;
    private static int cpt = 0;
    private ArrayList<String> orderList;
    private String customerId; //a changer quand la classe User sera faite
    private float totalCost;
    private Date date;

    private boolean ready;

    private static ArrayList<Order> allOrdersList;

    Order(String customerId, Basket basket){

        if (basket.isPaid() == true) {
            this.customerId = customerId;
            cpt++;
            id = cpt;
            totalCost = 0;
            ready = false;
            allOrdersList.add(this);
            date = new Date();

            orderList = basket.getListProducts();
        }
    }

    //// Getters and setters ////

    public int getId() {
        return id;
    }

    public ArrayList<String> getOrderList() {
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

    public float getTotalCost() {
        return totalCost;
    }

    public static ArrayList<Order> getAllOrdersList() {
        return allOrdersList;
    }
}
