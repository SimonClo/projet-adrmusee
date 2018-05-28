package fr.musee.adr.adrmusee;

import android.content.Context;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import fr.musee.adr.adrmusee.adapter.OrderAdminAdapter;

public class FirebaseClientOrderAdmin {
    Context c;
    String DB_URL;
    ListView listView;
    Firebase firebase;
    ArrayList<Order> orderlist= new ArrayList<>();



    public FirebaseClientOrderAdmin(Context c, String DB_URL, ListView listView)
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

    public void getupdates(DataSnapshot dataSnapshot) {

        orderlist.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                String user = ds.child("name").getValue().toString();
                String user_id= ds.getKey().toString();

            for (DataSnapshot ds1 : ds.child("Orders").getChildren()) {
                String id_command = ds1.getKey().toString();
                ArrayList<ProductQuantity> list = new ArrayList<>();
                int i = 0;
                while (ds1.child("orderList").child(String.valueOf(i)).getValue(ProductQuantity.class) != null) {
                    list.add(ds1.child("orderList").child(String.valueOf(i)).getValue(ProductQuantity.class));
                    i++;
                }

                Order currentOrder = new Order(Double.parseDouble(ds1.child("totalCost").getValue().toString()), list);
                currentOrder.setUser(user);
                currentOrder.setUser_id(user_id);
                long isready = (Integer) ds1.child("ready").getValue();
                currentOrder.setReady(isready);
                currentOrder.setId(id_command);
                orderlist.add(currentOrder);

            }
            if (orderlist.size() > 0) {
                OrderAdminAdapter o = new OrderAdminAdapter(c, orderlist);
                listView.setAdapter(o);
            } else {
                Toast.makeText(c, "Base de données vide", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
