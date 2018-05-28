package fr.musee.adr.adrmusee.adapter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import fr.musee.adr.adrmusee.R;

public class MyHolderBasket {

    TextView product;
    TextView quantity;
    TextView price;
    ImageView image;
    ImageButton delbutton;

    public MyHolderBasket(View itemView){
        product = (TextView) itemView.findViewById(R.id.productBasket_name);
        quantity = (TextView) itemView.findViewById(R.id.productBasket_quantity);
        price = (TextView) itemView.findViewById(R.id.productBasket_price);
        image = (ImageView) itemView.findViewById(R.id.productBasket_image);
        delbutton = (ImageButton) itemView.findViewById(R.id.delButton);

    }
}
