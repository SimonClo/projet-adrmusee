package fr.musee.adr.adrmusee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import fr.musee.adr.adrmusee.Order;
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
        return convertView;
    }
}
