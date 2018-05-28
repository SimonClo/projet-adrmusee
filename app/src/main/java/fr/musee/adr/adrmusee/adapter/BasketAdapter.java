package fr.musee.adr.adrmusee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;

import fr.musee.adr.adrmusee.AdminOrNot;
import fr.musee.adr.adrmusee.ProductQuantity;
import fr.musee.adr.adrmusee.R;

public class BasketAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<ProductQuantity> productQuantity;
    private LayoutInflater inflater;

    public BasketAdapter(Context context, ArrayList<ProductQuantity> productQuantity){
        this.context = context;
        this.productQuantity = productQuantity;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return productQuantity.size();
    }

    @Override
    public ProductQuantity getItem(int position) {
        return productQuantity.get(position);
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
             convertView= inflater.inflate(R.layout.adapter_panier,parent,false);
        }

        final ProductQuantity currentProduct = getItem(position);
        MyHolderBasket holder= new MyHolderBasket(convertView);
        DecimalFormat df2 = new DecimalFormat(".##");
        double productPrice = currentProduct.getProduct().getPrice();
        String productName = currentProduct.getProduct().getName();
        int productQuantity = currentProduct.getQuantity();

        holder.price.setText(df2.format(productPrice*productQuantity) + " €");
        holder.product.setText(productName);
        holder.quantity.setText("x " + Integer.toString(productQuantity));
        PicassoClient.downloadimg(context,currentProduct.getProduct().getimage(),holder.image);

        holder.delbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminOrNot.userbasket.delProduct(currentProduct.getProduct());
            }
        });
        return convertView;
    }
}
