package fr.musee.adr.adrmusee;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;


public class AccueilFragment extends Fragment {
    final static  String DB_URL= "https://adrmusee.firebaseio.com/Product";
    ListView listView;
    FirebaseClient firebaseClient;
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_accueil, null);
        listView=(ListView) view.findViewById(R.id.list_products);
        firebaseClient= new FirebaseClient(this.getActivity(), DB_URL,listView);
        firebaseClient.refreshdata();
        return view;

    }

}

