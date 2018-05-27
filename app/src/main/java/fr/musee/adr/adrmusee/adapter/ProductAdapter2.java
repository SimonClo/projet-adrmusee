package fr.musee.adr.adrmusee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import fr.musee.adr.adrmusee.Product;
import fr.musee.adr.adrmusee.R;

public class ProductAdapter2 extends BaseAdapter {
    Context c;
    ArrayList<Product> productlist;
    LayoutInflater inflater;


    public ProductAdapter2(Context c, ArrayList<Product> productlist) {
        this.c = c;
        this.productlist = productlist;
    }





    @Override
    public int getCount() {
        return productlist.size();
    }

    @Override
    public Object getItem(int i) {
        return productlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertview, ViewGroup viewGroup) {

        if (inflater== null)
        {
            inflater=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        } if(convertview==null)
        {
            convertview= inflater.inflate(R.layout.adapter_product,viewGroup,false);

        }

        MyHolder holder= new MyHolder(convertview);
        holder.nameTxt.setText(productlist.get(i).getName());
        holder.price.setText(Double.toString(productlist.get(i).getPrice())+"€");
        PicassoClient.downloadimg(c,productlist.get(i).getimage(),holder.img);



        return convertview;
    }
}