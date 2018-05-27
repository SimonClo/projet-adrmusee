package fr.musee.adr.adrmusee.adapter;

import android.view.View;
import android.widget.TextView;

import fr.musee.adr.adrmusee.R;

public class MyHolderBasket {

    TextView product;
    TextView quantity;
    TextView price;
    public MyHolderBasket(View itemView){
        product = (TextView) itemView.findViewById(R.id.productBasket_name);
        quantity = (TextView) itemView.findViewById(R.id.productBasket_quantity);
        price = (TextView) itemView.findViewById(R.id.productBasket_price);

    }
}
