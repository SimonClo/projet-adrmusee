package fr.musee.adr.adrmusee;

public class Product {
    private static int id = 0;
    public enum ProductList {pizzaReine, jambonBeurre, patesBolognaise}
    private ProductList name;

    Product(ProductList name){

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
