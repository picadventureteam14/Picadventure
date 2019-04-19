package com.group14.picadventure;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import java.io.File;

public class SimpleImageListAdapter extends ArrayAdapter {
    private Context context;
    private LayoutInflater inflater;
    private File[] imageUrls;

    public SimpleImageListAdapter(Context context, File[] imageUrls) {
        super(context, R.layout.gridview_item, imageUrls);

        this.context = context;
        this.imageUrls = imageUrls;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.gridview_item, parent, false);
        }
        Glide
                .with(context)
                .load(imageUrls[position])
                .into((ImageView) convertView);
        return convertView;
    }
}
