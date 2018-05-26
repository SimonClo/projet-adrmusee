package fr.musee.adr.adrmusee.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fr.musee.adr.adrmusee.R;

public class MyHolder {

    TextView nameTxt;
    ImageView img;
    TextView price;
    public MyHolder(View itemView) {


        nameTxt= (TextView) itemView.findViewById(R.id.product_name);
        img=(ImageView) itemView.findViewById(R.id.product_image);
        price= (TextView) itemView.findViewById(R.id.product_price);


    }
}