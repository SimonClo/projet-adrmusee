package fr.musee.adr.adrmusee;

public class Product {
    private static int cpt = 0;
    public enum ProductList {pizzaReine, jambonBeurre, patesBolognaise}
    private ProductList name;
    private int id;

    Product(ProductList name){
        this.name = name;
        cpt++;
        this.id = cpt;

    }

    public int getId() {
        return id;
    }

    public ProductList getName() {
        return name;
    }

    public void setName(ProductList name) {
        this.name = name;
    }
}
