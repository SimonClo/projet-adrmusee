package fr.musee.adr.adrmusee;

public class Product {
    // Classe représentant les produits en vente (un objet = un type de produit)

    private static int cpt = 0;
    private String name;
    private int id;
    private String producttype;
    private float price;
    private int quantity;
    public String image;

    Product(String name, float price, String image){
        this.name = name;
        this.price = price;
        this.image = image;
        cpt++;
        this.id = cpt;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public String getimage() {
        return image;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getProducttype() { return producttype; }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProductImage(String link_image) {
        this.image = link_image;
    }
}
