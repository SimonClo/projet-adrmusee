package fr.musee.adr.adrmusee;

import android.content.Context;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import fr.musee.adr.adrmusee.adapter.BasketAdapter;


/**
     * Created by Admin on 5/26/2017.
     */

    public class FirebaseClientBasket {

        Context c;
        String DB_URL;
        ListView listView;
        Firebase firebase;
        ArrayList<Product> productlist= new ArrayList<>();
        fr.musee.adr.adrmusee.adapter.BasketAdapter BasketAdapter;
        Basket basket=new Basket();



        public FirebaseClientBasket(Context c, String DB_URL, ListView listView)
        {
            this.c= c;
            this.DB_URL= DB_URL;
            this.listView= listView;


            Firebase.setAndroidContext(c);
            firebase=new Firebase(DB_URL);

        }
        public  void savedata(Basket basket)
        {
            firebase.child("userbasket").push().setValue(basket);

        }

        public  void refreshdata()
        {
            firebase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    getupdates(dataSnapshot);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });}

        public void getupdates(DataSnapshot dataSnapshot){
            String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
            basket.setUserId(user_id);
            basket.clearBasket();
            productlist.clear();
            for(DataSnapshot ds : dataSnapshot.child("userbasket").child("listProducts").getChildren()){
                Product product= new Product();
                product.setName((String) ds.child("name").getValue());
                product.setProductImage((String) ds.child("image").getValue());
                product.setPrice((double) ds.child("price").getValue());
                productlist.add(product);
                basket.addProduct(product);
            }

            if(productlist.size()>0)
            {
                BasketAdapter=new BasketAdapter(c, basket);
                listView.setAdapter(BasketAdapter);
            }else
            {
                Toast.makeText(c, "Base de données vide", Toast.LENGTH_SHORT).show();
            }
        }

    }

