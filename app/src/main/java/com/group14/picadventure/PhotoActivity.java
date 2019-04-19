package com.group14.picadventure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import java.io.File;

public class PhotoActivity extends AppCompatActivity {
    private ImageView imageView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        imageView1 = (ImageView) findViewById(R.id.imageview1);
        Intent intent = getIntent();
        String Path = intent.getStringExtra("filename");
        File file = new File(Path);
        Glide
                .with(PhotoActivity.this)
                .load(file)
                .into(imageView1);
    }
}
