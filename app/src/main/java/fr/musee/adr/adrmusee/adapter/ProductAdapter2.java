package fr.musee.adr.adrmusee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.text.DecimalFormat;
import java.util.ArrayList;

import fr.musee.adr.adrmusee.AdminOrNot;
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
    public Product getItem(int i) {
        return productlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertview, final ViewGroup viewGroup) {

        if (inflater== null)
        {
            inflater=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        } if(convertview==null)
        {
            convertview= inflater.inflate(R.layout.adapter_product,viewGroup,false);

        }


        final Product currentProduct = getItem(i);
        MyHolder holder= new MyHolder(convertview);
        holder.nameTxt.setText(currentProduct.getName());
        final String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DecimalFormat df2 = new DecimalFormat(".##");
        holder.price.setText(df2.format(currentProduct.getPrice())+"€");
        PicassoClient.downloadimg(c,currentProduct.getimage(),holder.img);

        convertview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(viewGroup.getContext(), currentProduct.getName() + " ajouté(e) au panier", Toast.LENGTH_SHORT).show();
                AdminOrNot.userbasket.addProduct(currentProduct);
            }
        });


        return convertview;
    }
}