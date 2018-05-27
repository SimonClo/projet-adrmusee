package fr.musee.adr.adrmusee;

import java.util.ArrayList;

public class Basket {
    // Classe représentant le panier de l'utilisateur

    private String userId;
    private ArrayList<Product> listProducts = new ArrayList<Product>();
    private double totalPrice = 0;

    private boolean paid;

    public Basket(String userId){
        this.userId = userId;
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
    public ArrayList<ProductQuantity> listProductQuantity(){
        ArrayList<Product> listTest = this.listProducts;
        ArrayList<ProductQuantity> listProductQuantity = new ArrayList<ProductQuantity>();
        Product currentProduct = new Product();

        while (listTest.isEmpty() == false){
            int cpt = 1;
            currentProduct = listProducts.get(0);
            int j = 1;

            while (j < listTest.size()){

                if (currentProduct.getName() == listProducts.get(j).getName()){
                    cpt++;
                    listProducts.remove(j);
                }
                else{
                    j++;
                }

            }

            listProductQuantity.add(new ProductQuantity(currentProduct, cpt));
            listTest.remove(0);
        }

        return listProductQuantity;
    }


    /// Getters and setters

    public String getUserId() {
        return userId;
    }

    public ArrayList<Product> getListProducts() {
        return listProducts;
    }

    public double getTotalPrice() {

        for (int i=0; i < listProducts.size(); i++){
            totalPrice += listProducts.get(i).getPrice();
        }

        return totalPrice;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
