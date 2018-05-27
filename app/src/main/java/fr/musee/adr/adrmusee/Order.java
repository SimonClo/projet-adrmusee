package fr.musee.adr.adrmusee;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    // Classe contenant les commandes des clients

    private int id;
    private static int cpt = 0;
    private ArrayList<ProductQuantity> orderList;
    private double totalCost;
    private Date date;
    private FirebaseAuth mAuth;
    private String user_id;
    private DatabaseReference mDatabase;

    private boolean ready;


    // fonction de création d'une commande depuis le panier
    public Order(Basket basket){

        if (basket.isPaid() == true) {
            cpt++;
            id = cpt;
            ready = false;
            date = new Date();
            totalCost = basket.getTotalPrice();

            orderList = basket.listProductQuantity();

        }
        else{
            System.out.println("La commande n'a pas été payée");
        }
    }

    public Order(){

    }

    public Order(double totalCost, ArrayList<ProductQuantity> orderList){
        this.totalCost = totalCost;
        this.orderList = orderList;
    }


    // Ajout de la commande à la db
    public void saveOrder(){

        mAuth = FirebaseAuth.getInstance();
        user_id= mAuth.getCurrentUser().getUid();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Orders");
        mDatabase.push().setValue(this);
    }

    //// Getters and setters ////

    public int getId() {
        return id;
    }

    public ArrayList<ProductQuantity> getOrderList() {
        return orderList;
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

    public void setOrderList(ArrayList<ProductQuantity> orderList) {
        this.orderList = orderList;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
