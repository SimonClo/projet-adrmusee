package fr.musee.adr.adrmusee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import fr.musee.adr.adrmusee.CompteActivity;
import fr.musee.adr.adrmusee.Product;
import fr.musee.adr.adrmusee.R;

public class ProductAdapter2 extends BaseAdapter {
    Context c;
    ArrayList<Product> productlist;
    LayoutInflater inflater;
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;


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
        mAuth = FirebaseAuth.getInstance();
        String user_id = mAuth.getCurrentUser().getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);

        final Product currentProduct = getItem(i);
        MyHolder holder= new MyHolder(convertview);
        holder.nameTxt.setText(currentProduct.getName());
        holder.price.setText(Double.toString(currentProduct.getPrice())+"€");
        PicassoClient.downloadimg(c,currentProduct.getimage(),holder.img);

        convertview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompteActivity.userbasket.addProduct(currentProduct);
                Toast.makeText(viewGroup.getContext(), currentProduct.getName() + " ajouté(e) au panier", Toast.LENGTH_SHORT).show();
                mDatabase.child("userbasket").removeValue();
                mDatabase.child("userbasket").setValue(CompteActivity.userbasket);
            }
        });

        return convertview;
    }
}