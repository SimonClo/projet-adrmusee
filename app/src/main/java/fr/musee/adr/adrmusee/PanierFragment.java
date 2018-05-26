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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import fr.musee.adr.adrmusee.adapter.BasketAdapter;
import fr.musee.adr.adrmusee.adapter.OrderAdapter;


public class PanierFragment extends Fragment {

    private Basket basket;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.activity_panier, null);
        ListView basketListView = (ListView) getView().findViewById(R.id.listview_basket);
        basketListView.setAdapter(new BasketAdapter(this.getActivity(), basket.getListProducts()));


        return view;

    }

}

