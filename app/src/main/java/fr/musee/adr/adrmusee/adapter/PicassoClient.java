package fr.musee.adr.adrmusee.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import fr.musee.adr.adrmusee.R;

public class PicassoClient {

    public  static  void downloadimg(Context c, String url, ImageView img)
    {
        if (url!=null && url.length()>0)
        {
            Picasso.with(c).load(url).placeholder(R.drawable.ic_home).into(img);

        }else
        {
            Picasso.with(c).load(R.drawable.ic_home).into(img);
        }
    }


}