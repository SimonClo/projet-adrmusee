package fr.musee.adr.adrmusee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import fr.musee.adr.adrmusee.Order;
import fr.musee.adr.adrmusee.ProductQuantity;
import fr.musee.adr.adrmusee.R;

public class OrderAdminAdapter extends BaseAdapter {

    private Context context;
    private List<Order> orderList;
    private LayoutInflater inflater;

    public OrderAdminAdapter(Context context, List<Order> orderList){
        this.context = context;
        this.orderList = orderList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Order getItem(int position) {
        return orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater== null)
        {
            inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null)
        {
            convertView= inflater.inflate(R.layout.adapter_command_admin,parent,false);

        }

        final Order currentOrder = getItem(position);
        MyHolderOrderAdmin holder= new MyHolderOrderAdmin(convertView);
        DecimalFormat df2 = new DecimalFormat(".##");
        String price = df2.format(currentOrder.getTotalCost())+"€";
        holder.price.setText(price);

        ArrayList<ProductQuantity> orderProducts = currentOrder.getOrderList();

        String orderProductsString = new String();

        for (int i = 0; i < orderProducts.size(); i++){
            orderProductsString += orderProducts.get(i).getProduct().getName() + " x" + orderProducts.get(i).getQuantity() + "\n";

        }

        holder.listProduct.setText(orderProductsString);

        final String Username = currentOrder.getUser();
        final String user_id = currentOrder.getUser_id();
        final String id_command= currentOrder.getId();
        holder.nameuser.setText(Username);
        final Button  button_ready= (Button) convertView.findViewById(R.id.button_ready);
        if(currentOrder.isReady()){button_ready.setText("Prise");}
        button_ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!currentOrder.isReady()){
                FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Orders").child(id_command).child("ready").setValue(Boolean.TRUE);
                currentOrder.isReady();
                }else{
                    FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Orders").child(id_command).removeValue();
                }

            }
        });

        return convertView;
    }
}
