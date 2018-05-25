package fr.musee.adr.adrmusee;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Product> list_products;
    private LayoutInflater inflater;


    public ProductAdapter(Context context, ArrayList<Product> list_products){
        this.context=context;
        this.list_products=list_products;
        this.inflater=LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return list_products.size();
    }

    @Override
    public Product getItem(int position) {
        return list_products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=inflater.inflate(R.layout.adapter_product,null);

        Product currentproduct=getItem(position);
        String product_name=currentproduct.getName();
        double product_price=currentproduct.getPrice();
        String product_image=currentproduct.getimage();

        TextView ProductName= view.findViewById(R.id.product_name);
        TextView ProductPrice= view.findViewById(R.id.product_price);
        ImageView ProductImage= view.findViewById(R.id.product_image);

        ProductName.setText(product_name);
        ProductPrice.setText(String.valueOf(product_price+"€"));
        if (!currentproduct.getimage().equals("")) {
            ProductImage.setImageURI(Uri.parse(product_image));
        } else {
            ProductImage.setImageResource(R.mipmap.ic_launcher);
        }
        return view;
    }
}
