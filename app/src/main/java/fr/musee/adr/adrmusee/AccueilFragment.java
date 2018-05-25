package fr.musee.adr.adrmusee;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AccueilFragment extends Fragment {
    private DatabaseReference mDatabase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_accueil, null);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Product");
        final ArrayList<Product> Productlist = new ArrayList<>();
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    Productlist.add(new Product(dsp.child("product_name").getValue().toString(), (float) dsp.child("product_price").getValue(), dsp.child("product_image").getValue().toString()));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ListView list_products=(ListView) getView().findViewById(R.id.list_products);
        list_products.setAdapter(new ProductAdapter(this.getActivity(), Productlist));
        return view;

    }

}

