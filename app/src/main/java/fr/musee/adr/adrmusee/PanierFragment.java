package fr.musee.adr.adrmusee;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import fr.musee.adr.adrmusee.adapter.BasketAdapter;
import fr.musee.adr.adrmusee.adapter.OrderAdapter;


public class PanierFragment extends Fragment {

    private Basket basket;
    private FirebaseAuth mAuth;
    private String user_id;
    private DatabaseReference mDatabase;
    final static  String DB_URL= "https://adrmusee.firebaseio.com/Product";
    ListView listView;
    FirebaseClient firebaseClient;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        user_id= mAuth.getCurrentUser().getUid();
        basket = CompteActivity.userbasket;

        View view=inflater.inflate(R.layout.activity_panier, null);
        listView=(ListView) view.findViewById(R.id.listview_basket);
        firebaseClient= new FirebaseClient(this.getActivity(), DB_URL,listView);
        firebaseClient.refreshdata();

        TextView totalPriceView = view.findViewById(R.id.basketTotalPrice);
        totalPriceView.setText(basket.getTotalPrice() + " €");

        Button payButton = view.findViewById(R.id.buttonPay);

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The user just clicked
                basket.setPaid(true);
                Order newOrder = new Order(basket);
                newOrder.saveOrder();


            }

        });

        return view;

    }

}

