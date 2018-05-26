package fr.musee.adr.adrmusee;

public class User {

    private static int cpt;
    private int id;
    private String emailAddress;
    private String name;
    private String password;

    User(String name, String emailAddress, String password){
        this.name = name;
        this.emailAddress = emailAddress;
        this.password = password;
        cpt++;
        id = cpt;
    }

    //public void createOrder(){
    //    new Order(this.name);
    //}

    public void addProduct(Order order, Product product){
      //  order.addProduct(product);
    }

    public void delProduct(Order order, Product product){
       // order.delProduct(product);
    }
}
