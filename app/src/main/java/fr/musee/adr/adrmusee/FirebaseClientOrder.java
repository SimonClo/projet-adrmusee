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

public class FirebaseClientOrder {
    Context c;
    String DB_URL;
    ListView listView;
    Firebase firebase;
    ArrayList<Order> orderlist= new ArrayList<>();



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

            ArrayList<ProductQuantity> list = new ArrayList<>();
            int i = 0;
            while(ds.child("orderList").child(String.valueOf(i)).getValue(ProductQuantity.class) != null){
                list.add(ds.child("orderList").child(String.valueOf(i)).getValue(ProductQuantity.class));
                i++;
            }

            Order currentOrder = new Order(Double.parseDouble(ds.child("totalCost").getValue().toString()), list);
            if(ds.child("ready").getValue()!=null){
            long ready = Long.parseLong(ds.child("ready").getValue().toString());
            System.out.println(ds.child("ready").getValue());
            if (ready == 1L){
            currentOrder.setReady(1L);}else{
                currentOrder.setReady(0L);
            }}
            orderlist.add(currentOrder);

        }
        if(orderlist.size()>0)
        {
            OrderAdapter o = new OrderAdapter(c, orderlist);
            listView.setAdapter(o);
        }else
        {
            Toast.makeText(c, "Vous n'avez pas de commande en attente", Toast.LENGTH_SHORT).show();
        }
    }

}
