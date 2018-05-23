package fr.musee.adr.adrmusee;

import java.util.ArrayList;

public class Order {
    // Classe contenant les commandes des clients

    private int id;
    private static int cpt = 0;
    private ArrayList<Product> orderList;
    private String customerName; //a changer quand la classe User sera faite
    private float totalCost;

    Order(String customerName){
        this.customerName = customerName;
        cpt++;
        id =cpt;
        this.totalCost = 0;
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

    public int getId() {
        return id;
    }

    public ArrayList<Product> getOrderList() {
        return orderList;
    }

    public String getCustomerName() {
        return customerName;
    }

    public float getTotalCost() {
        return totalCost;
    }
}
