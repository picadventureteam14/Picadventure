package com.group14.picadventure;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import android.provider.MediaStore;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddActivity extends AppCompatActivity {

    private ImageView imageView1;
    //Image clicking and camera opening
    File file = new File("/storage/emulated/0/Download/a.jpg");
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imageView1 = (ImageView) findViewById(R.id.imageviewadd);
        Glide
                .with(AddActivity.this)
                .load(file)
                .into(imageView1);
        imageView1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(AddActivity.this, "Opening Camera", Toast.LENGTH_SHORT).show();
                dispatchTakePictureIntent();
            }
        });


    }
    //Camera implementation
    String currentPhotoPath;
    private File createImageFile()throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            try {
                file = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (file != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        file);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }
    //Display thumbnail on return
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Glide
                .with(AddActivity.this)
                .load(file)
                .into(imageView1);
        Toast.makeText(AddActivity.this, file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
    }


}
