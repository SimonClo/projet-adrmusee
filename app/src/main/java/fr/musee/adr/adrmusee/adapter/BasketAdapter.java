package fr.musee.adr.adrmusee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import fr.musee.adr.adrmusee.Basket;
import fr.musee.adr.adrmusee.Product;
import fr.musee.adr.adrmusee.ProductQuantity;
import fr.musee.adr.adrmusee.R;

public class BasketAdapter extends BaseAdapter{

    private Context context;
    private Basket basket;
    private LayoutInflater inflater;

    public BasketAdapter(Context context, Basket basket){
        this.context = context;
        this.basket = basket;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return basket.listProductQuantity().size();
    }

    @Override
    public ProductQuantity getItem(int position) {
        return basket.listProductQuantity().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater== null) {
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null) {
            convertView= inflater.inflate(R.layout.adapter_product,parent,false);
        }

        final ProductQuantity currentProduct = getItem(position);
        MyHolderBasket holder= new MyHolderBasket(convertView);

        double productPrice = currentProduct.getProduct().getPrice();
        String productName = currentProduct.getProduct().getName();
        int productQuantity = currentProduct.getQuantity();

        holder.price.setText(Double.toString(productPrice) + " €");
        holder.product.setText(productName);
        holder.quantity.setText("x" + Integer.toString(productQuantity));




        return convertView;
    }
}
