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

import java.text.DecimalFormat;

import fr.musee.adr.adrmusee.adapter.BasketAdapter;


public class PanierFragment extends Fragment {

    private Basket basket;
    private FirebaseAuth mAuth;
    private static String user_id;
    private DatabaseReference mDatabase;
    ListView listView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.activity_panier, null);
        mAuth = FirebaseAuth.getInstance();
        user_id= mAuth.getCurrentUser().getUid();
        basket=AdminOrNot.userbasket;
        listView=(ListView) view.findViewById(R.id.listview_basket);
        BasketAdapter productAdapter2=new BasketAdapter(this.getActivity(), basket.listProductQuantity());
        listView.setAdapter(productAdapter2);

        final TextView totalPriceView = view.findViewById(R.id.basketTotalPrice);
        final DecimalFormat df2 = new DecimalFormat(".##");
        totalPriceView.setText(df2.format(basket.getTotalPrice()) + " €");

        Button payButton = view.findViewById(R.id.buttonPay);

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The user just clicked
                basket.setPaid(Boolean.TRUE);
                Order newOrder = new Order(basket);
                newOrder.saveOrder();
            }

        });
        return view;
    }

}

