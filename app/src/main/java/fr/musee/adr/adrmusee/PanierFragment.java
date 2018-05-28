package fr.musee.adr.adrmusee;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;


public class PanierFragment extends Fragment {

    private Basket basket;
    private FirebaseAuth mAuth;
    private static String user_id;
    private DatabaseReference mDatabase;
    ListView listView;
    FirebaseClientBasket firebaseClient;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.activity_panier, null);
        mAuth = FirebaseAuth.getInstance();
        user_id= mAuth.getCurrentUser().getUid();

        listView=(ListView) view.findViewById(R.id.listview_basket);
        final String DB_URL= "https://adrmusee.firebaseio.com/Users/"+user_id.toString()+"/";
        firebaseClient= new FirebaseClientBasket(this.getActivity(), DB_URL,listView);
        basket = CompteActivity.userbasket;
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

