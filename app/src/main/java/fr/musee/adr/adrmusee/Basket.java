package fr.musee.adr.adrmusee;

import java.util.ArrayList;

public class Basket {

    private String userId;
    private ArrayList<Product> listProducts = new ArrayList<Product>();
    private double totalPrice = 0;

    private boolean paid;

    public Basket(String userId){
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public ArrayList<Product> getListProducts() {
        return listProducts;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public boolean isPaid() {
        return paid;
    }
}
