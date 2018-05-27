package fr.musee.adr.adrmusee.adapter;

import android.view.View;
import android.widget.TextView;

import fr.musee.adr.adrmusee.R;

public class MyHolderOrder {


    TextView listProduct;
    TextView price;
    TextView ready;

    public MyHolderOrder(View itemView){
        listProduct = (TextView) itemView.findViewById(R.id.command_name);
        price = (TextView) itemView.findViewById(R.id.command_price);
        ready = (TextView) itemView.findViewById(R.id.command_ready);

    }
}
