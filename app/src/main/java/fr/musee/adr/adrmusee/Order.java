package fr.musee.adr.adrmusee;

import java.util.ArrayList;

public class Order {
    // Classe contenant les commandes des clients

    private int id;
    private static int cpt = 0;
    private ArrayList<Product> orderList;
    private String customerName; //a changer quand la classe User sera faite
    private float totalCost;

    private boolean paid;
    private boolean ready;

    private static ArrayList<Order> allOrdersList;

    Order(String customerName){
        this.customerName = customerName;
        cpt++;
        id =cpt;
        totalCost = 0;
        paid = false;
        ready = false;
        allOrdersList.add(this);
    }

    public void addProduct(Product product){

        int q = product.getQuantity();

        if (q > 0) {
            orderList.add(product);
            product.setQuantity(q - 1);
            this.totalCost += product.getPrice();
        }
        else{
            System.out.println("Ce produit n'est plus disponible");
        }

    }


    //// Getters and setters ////

    public int getId() {
        return id;
    }

    public ArrayList<Product> getOrderList() {
        return orderList;
    }

    public String getCustomerName() {
        return customerName;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
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
