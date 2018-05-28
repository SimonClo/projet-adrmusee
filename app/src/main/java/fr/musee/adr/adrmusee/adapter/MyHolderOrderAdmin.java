package fr.musee.adr.adrmusee.adapter;

import android.view.View;
import android.widget.TextView;

import fr.musee.adr.adrmusee.R;

public class MyHolderOrderAdmin {


    TextView listProduct;
    TextView price;
    TextView nameuser;

    public MyHolderOrderAdmin(View itemView){
        listProduct = (TextView) itemView.findViewById(R.id.command_name_admin);
        price = (TextView) itemView.findViewById(R.id.command_price_admin);
        nameuser = (TextView) itemView.findViewById(R.id.command_user_admin);

    }
}
