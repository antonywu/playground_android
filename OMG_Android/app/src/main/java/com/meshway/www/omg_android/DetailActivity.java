package com.meshway.www.omg_android;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by antony on 11/4/15.
 */
public class DetailActivity extends AppCompatActivity {
    private static final String IMAGE_URL_BASE = "http://covers.openlibrary.org/b/id/";
    String mImageURL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String coverID = this.getIntent().getExtras().getString("coverID");

        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView imageView = (ImageView) findViewById(R.id.img_cover);

        if(coverID.length() > 0) {
            mImageURL = IMAGE_URL_BASE + coverID + "-L.jpg";

            Picasso.with(this).load(mImageURL).placeholder(R.mipmap.img_books_loading).into(imageView);
        }
    }
}
