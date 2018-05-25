package fr.musee.adr.adrmusee;

import java.util.ArrayList;

public class Basket {

    private String userId;
    private ArrayList<String> listProducts = new ArrayList<String>();
    private double totalPrice = 0;

    private boolean paid;

    public Basket(String userId){
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public ArrayList<String> getListProducts() {
        return listProducts;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public boolean isPaid() {
        return paid;
    }
}
