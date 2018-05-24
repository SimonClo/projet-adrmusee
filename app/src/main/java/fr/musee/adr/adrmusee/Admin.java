package fr.musee.adr.adrmusee;

import java.util.ArrayList;

public class Admin {

    private static int cpt = 0;
    private int id;
    private String name;
    private static ArrayList<Product> allProducts;

    Admin(String name){

        this.name = name;
        cpt++;
        this.id = cpt;
    }

    private void addProduct(Product product){
        allProducts.add(product);
    }

    private void markOrderReady(Order order){

        if (order.isPaid() == true){
            order.setReady(true);
        }
        else{
            System.out.println("La commande n'a pas été payée");
        }
    }

    private void deleteOrder(Order order){

        Order.getAllOrdersList().remove(order);

    }

}
