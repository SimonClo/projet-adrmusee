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

        convertView = inflater.inflate(R.layout.adapter_command, null);
        Order currentOrder = getItem(position);

        double orderCost = currentOrder.getTotalCost();
        ArrayList<Product> orderProducts = currentOrder.getOrderList();
        Date orderDate = currentOrder.getDate();
        boolean orderReady = currentOrder.isReady();

        TextView orderProductsView = convertView.findViewById(R.id.command_name);
        String orderProductsString = new String();

        for (int i = 0; i < orderProducts.size(); i++){
            orderProductsString += orderProducts.get(i).getName() + "\n";

        }

        orderProductsView.setText(orderProductsString);

        TextView orderCostView = convertView.findViewById(R.id.command_price);
        orderCostView.setText(orderCost + "€");

        TextView orderReadyView = convertView.findViewById(R.id.command_ready);
        if (orderReady == true){
            orderReadyView.setText("Prêt");
        }
        else{
            orderReadyView.setText("En cours de préparation");
        }

        return convertView;
    }
}
