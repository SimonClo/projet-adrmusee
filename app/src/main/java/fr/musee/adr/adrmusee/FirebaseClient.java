package fr.musee.adr.adrmusee;

import android.content.Context;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

import fr.musee.adr.adrmusee.adapter.ProductAdapter;

/**
     * Created by Admin on 5/26/2017.
     */

    public class FirebaseClient  {

        Context c;
        String DB_URL;
        ListView listView;
        Firebase firebase;
        ArrayList<Product> productlist= new ArrayList<>();
        fr.musee.adr.adrmusee.adapter.ProductAdapter ProductAdapter;


        public  FirebaseClient(Context c, String DB_URL, ListView listView)
        {
            this.c= c;
            this.DB_URL= DB_URL;
            this.listView= listView;


            Firebase.setAndroidContext(c);
            firebase=new Firebase(DB_URL);
        }

        public  void savedata(String name,double price, String url)
        {
            Product product= new Product(name,price,url);
            product.setName(name);
            product.setProductImage(url);
            product.setPrice(price);


            firebase.child("Product").push().setValue(product);


        }

        public  void refreshdata()
        {
            firebase.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    getupdates(dataSnapshot);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    getupdates(dataSnapshot);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }

        public void getupdates(DataSnapshot dataSnapshot){

            productlist.clear();

            for(DataSnapshot ds :dataSnapshot.getChildren()){
                Product product= new Product("",1,"");
                product.setName(ds.getValue(Product.class).getName());
                product.setProductImage(ds.getValue(Product.class).getimage());
                productlist.add(product);

            }
            if(productlist.size()>0)
            {
                ProductAdapter=new ProductAdapter(c, productlist);
                listView.setAdapter((ListAdapter) ProductAdapter);
            }else
            {
                Toast.makeText(c, "Base de données vide", Toast.LENGTH_SHORT).show();
            }
        }
    }

