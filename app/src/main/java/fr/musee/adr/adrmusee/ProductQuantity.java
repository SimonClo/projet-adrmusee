package fr.musee.adr.adrmusee;

public class ProductQuantity {

    Product product;
    int quantity;

    public ProductQuantity(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
