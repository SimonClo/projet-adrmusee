package fr.musee.adr.adrmusee;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import fr.musee.adr.adrmusee.adapter.OrderAdapter;


public class CommandesFragment extends Fragment {
    // Classe d'affichage de la liste des commandes du client connecté

    @Nullable

    private DatabaseReference mDatabase;
    private ArrayList<Order> userOrders;
    private String user_id;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.activity_commandes, null);

         user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();

         userOrders = new ArrayList<>();

         mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Orders");
         mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 for(DataSnapshot ds: dataSnapshot.getChildren()){
                     Order currentOrder = new Order();
                     currentOrder.setOrderList(ds.getValue(Order.class).getOrderList());
                     currentOrder.setTotalCost(ds.getValue(Order.class).getTotalCost());
                     userOrders.add(currentOrder);

                 }

             }

             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         });

        ListView commandsListView = (ListView) view.findViewById(R.id.listview_commands);
        commandsListView.setAdapter(new OrderAdapter(this.getActivity(), userOrders));

        return view;
    }
}

