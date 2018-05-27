package fr.musee.adr.adrmusee;

import android.content.Context;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import fr.musee.adr.adrmusee.adapter.OrderAdapter;
import fr.musee.adr.adrmusee.adapter.ProductAdapter2;

public class FirebaseClientOrder {Context c;
    String DB_URL;
    ListView listView;
    Firebase firebase;
    ArrayList<Order> orderlist= new ArrayList<>();
    fr.musee.adr.adrmusee.adapter.ProductAdapter2 ProductAdapter2;



    public  FirebaseClientOrder(Context c, String DB_URL, ListView listView)
    {
        this.c= c;
        this.DB_URL= DB_URL;
        this.listView= listView;


        Firebase.setAndroidContext(c);
        firebase=new Firebase(DB_URL);

    }

    public  void savedata(ArrayList<ProductQuantity> orderList,float price, String url)
    {
        Order order= new Order();
        order.setOrderList(orderList);
        order.setTotalCost(price);
        firebase.push().setValue(order);

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

        orderlist.clear();
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            Order order = new Order();
            ArrayList<ProductQuantity> list = new ArrayList<>();

            for(int i=0; i<3; i++){
                list.add(ds.child("orderList").getValue(ProductQuantity.class));
            }

            Order currentOrder = new Order(((Long) ds.child("totalCost").getValue()).doubleValue(), list);
            orderlist.add(currentOrder);
            order.setTotalCost((double) ds.child("totalCost").getValue());
            orderlist.add(order);

        }
        if(orderlist.size()>0)
        {
            OrderAdapter o =new OrderAdapter(c, orderlist);
            listView.setAdapter(o);
        }else
        {
            Toast.makeText(c, "Base de données vide", Toast.LENGTH_SHORT).show();
        }
    }

}
