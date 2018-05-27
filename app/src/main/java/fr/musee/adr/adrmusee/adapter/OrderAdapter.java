package fr.musee.adr.adrmusee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.musee.adr.adrmusee.Order;
import fr.musee.adr.adrmusee.Product;
import fr.musee.adr.adrmusee.ProductQuantity;
import fr.musee.adr.adrmusee.R;

public class OrderAdapter extends BaseAdapter {

    private Context context;
    private List<Order> orderList;
    private LayoutInflater inflater;

    public OrderAdapter(Context context, List<Order> orderList){
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
            convertView= inflater.inflate(R.layout.adapter_command,parent,false);

        }

        Order currentOrder = getItem(position);
        MyHolderOrder holder= new MyHolderOrder(convertView);
        holder.price.setText(Double.toString(currentOrder.getTotalCost())+"€");

        ArrayList<ProductQuantity> orderProducts = currentOrder.getOrderList();

        String orderProductsString = new String();

        for (int i = 0; i < orderProducts.size(); i++){
            orderProductsString += orderProducts.get(i).getProduct().getName() + " x" + orderProducts.get(i).getQuantity() + "\n";

        }

        holder.listProduct.setText(orderProductsString);

        boolean orderReady = currentOrder.isReady();
        if (orderReady == true){
            holder.ready.setText("Prêt");
        }
        else{
            holder.ready.setText("En cours de préparation");
        }

        return convertView;
    }
}
