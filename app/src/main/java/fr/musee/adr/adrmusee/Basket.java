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
        ArrayList<ProductQuantity> listProductQuantity = new ArrayList<ProductQuantity>();
        Product currentProduct = new Product();

        while (listTest.isEmpty() == false){
            int cpt = 1;
            currentProduct = listProducts.get(0);
            int j = 1;

            while (j < listTest.size()){

                if (currentProduct.getId() == listProducts.get(j).getId()){
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
}
