package com.meshway.www.omg_android;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by antony on 11/4/15.
 */
public class JSONAdapter extends BaseAdapter {
    private static final String IMAGE_URL_BASE = "http://covers.openlibrary.org/b/id/";

    Context mContext;
    LayoutInflater mInflater;
    JSONArray mJsonArray;

    public JSONAdapter(Context context, LayoutInflater inflater) {
        mContext = context;
        mInflater = inflater;
        mJsonArray = new JSONArray();
    }

    @Override
    public int getCount() {
        return mJsonArray.length();
    }

    @Override
    public Object getItem(int position) {
        return mJsonArray.optJSONObject(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        JSONObject jsonObject = (JSONObject) getItem(position);
        String bookTitle = "";
        String authorName = "";

        // check if the view already exists
        if(null == convertView) {
            convertView = mInflater.inflate(R.layout.row_book, null);
            holder = new ViewHolder();
            holder.thumbnailImageView = (ImageView) convertView.findViewById(R.id.img_thumbnail);
            holder.titleTextView = (TextView) convertView.findViewById(R.id.text_title);
            holder.authorTextView = (TextView) convertView.findViewById(R.id.text_author);

            // hang onto this holder for future recycle
            convertView.setTag(holder);
        } else {
            // What is the point??
            holder = (ViewHolder) convertView.getTag();
        }

        if(jsonObject.has("cover_i")) {
            String imageID = jsonObject.optString("cover_i");
            String imageURL = IMAGE_URL_BASE + imageID + "-L.jpg";

            Log.d("omg android", "Loading image: " + imageURL);
            Picasso.with(mContext).load(imageURL).placeholder(R.mipmap.ic_books).into(holder.thumbnailImageView);
        } else {
            holder.thumbnailImageView.setImageResource(R.mipmap.ic_books);
        }

        if(jsonObject.has("title")) {
            bookTitle = jsonObject.optString("title");
        }

        if(jsonObject.has("author_name")) {
            authorName = jsonObject.optJSONArray("author_name").optString(0);
        }

        holder.titleTextView.setText(bookTitle);
        holder.authorTextView.setText(authorName);

        return convertView;
    }

    public void updateData(JSONArray jsonArray) {
        mJsonArray = jsonArray;
        notifyDataSetChanged();
    }
}