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
        convertView = inflater.inflate(R.layout.adapter_panier, null);
        final ProductQuantity currentProduct = getItem(position);

        double productPrice = currentProduct.getProduct().getPrice();
        String productName = currentProduct.getProduct().getName();
        int productQuantity = currentProduct.getQuantity();

        TextView productPriceView = convertView.findViewById(R.id.productBasket_price);
        productPriceView.setText(productPrice + " €");
        TextView productNameView = convertView.findViewById(R.id.productBasket_name);
        productNameView.setText(productName);
        TextView productQuantityView = convertView.findViewById(R.id.productBasket_quantity);
        productQuantityView.setText("x" + productQuantity);

        final Button delButton = convertView.findViewById(R.id.delButton);
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The user just clicked
                int n = currentProduct.getQuantity();
                currentProduct.setQuantity(n-1);
                if (currentProduct.getQuantity() == 0) {
                    basket.delProduct(currentProduct.getProduct());
                }

            }
        });


        return convertView;
    }
}
