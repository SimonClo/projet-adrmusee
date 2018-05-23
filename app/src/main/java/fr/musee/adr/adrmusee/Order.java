package fr.musee.adr.adrmusee;

import java.util.ArrayList;

public class Order {

    private int id;
    private static int cpt = 0;
    private ArrayList<Product> orderList;
    private String customerName; //a changer quand la classe User sera faite

    Order(String customerName){
        this.customerName = customerName;
        cpt++;
        id =cpt;
    }

    public void addProduct(Product product){

        int q = product.getQuantity();

        if (q > 0) {
            orderList.add(product);
            product.setQuantity(q - 1);
        }
        else{
            System.out.println("Ce produit n'est plus disponible");
        }

    }

}
