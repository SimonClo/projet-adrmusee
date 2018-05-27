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

    public void delProduct(Product product){
        listProducts.remove(product);
    }

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

    public ArrayList<ProductQuantity> listProductQuantity(){
        ArrayList<Product> listTest = this.listProducts;
        int i = 0;
        ArrayList<ProductQuantity> listProductQuantity = new ArrayList<ProductQuantity>();
        Product currentProduct = new Product();

        while (listTest.isEmpty() == false){
            int cpt = 1;
            currentProduct = listProducts.get(i);

            for (int j = i + 1; j < listTest.size(); j++){

                if (currentProduct )

            }

            listProductQuantity.add(new ProductQuantity(currentProduct, cpt));
            listTest.remove(0);
        }
    }
}
