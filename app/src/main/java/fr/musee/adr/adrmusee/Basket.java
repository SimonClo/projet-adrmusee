package fr.musee.adr.adrmusee;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;

public class Basket {
    // Classe représentant le panier de l'utilisateur

    private String userId;
    private ArrayList<Product> listProducts;
    private double totalPrice = 0.0;

    private boolean paid;

    public Basket(String userId){
        this.userId = userId;
        listProducts = new ArrayList<>();
    }

    public Basket(){

        listProducts = new ArrayList<>();
        totalPrice = 0.0;

    }

    public void clearBasket(){
        listProducts.clear();
        totalPrice = 0.0;
    }


    // Méthode permettant de supprimer un produit
    public void delProduct(Product product){
        listProducts.remove(product);
    }

    // Méthode permettant d'ajouter un produit au panier
    public void addProduct(Product product){
        listProducts.add(product);
    }

    // Méthode permettant de récupérer la quantité de chaque produit
    public ArrayList<ProductQuantity> listProductQuantity() {
        ArrayList<ProductQuantity> listProductQuantity = new ArrayList<ProductQuantity>();
        ArrayList<Product> listprod = new ArrayList<Product>();
        HashMap<Product, Integer> countMap = new HashMap<Product, Integer>();
        for (Product product : listProducts) {
            if (!countMap.containsKey(product)) {
                countMap.put(product, 1);
            } else {
                Integer count = countMap.get(product);
                count = count + 1;
                countMap.put(product, count);
            }
        }
        for (Product product : listProducts) {
            if(!listprod.contains(product)){
            listProductQuantity.add(new ProductQuantity(product, countMap.get(product)));
            listprod.add(product);}
        }
        return listProductQuantity;


    }

    /// Getters and setters

    public String getUserId() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
    }

    public ArrayList<Product> getListProducts() {
        return listProducts;
    }

    public double getTotalPrice() {

        this.totalPrice = 0.0;

        for (int i=0; i < listProducts.size(); i++){
            this.totalPrice += listProducts.get(i).getPrice();
        }

        return this.totalPrice;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }


    public void setListProducts(ArrayList<Product> listProducts) {this.listProducts=listProducts;}

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
