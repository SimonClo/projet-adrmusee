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
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import fr.musee.adr.adrmusee.adapter.OrderAdapter;


public class CommandesFragment extends Fragment {
    // Classe d'affichage de la liste des commandes du client connecté

    @Nullable

    private String user_id;
    private ListView listView;
    private FirebaseClientOrder firebaseClient;
    private FirebaseAuth mAuth;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_commandes, null);
        mAuth = FirebaseAuth.getInstance();
        user_id= mAuth.getCurrentUser().getUid();
        final String DB_URL= "https://adrmusee.firebaseio.com/Users/"+ user_id + ("/Orders");

        listView = (ListView) view.findViewById(R.id.listview_commands);
        firebaseClient= new FirebaseClientOrder(this.getActivity(), DB_URL,listView);
        firebaseClient.refreshdata();
        return view;
    }
}

