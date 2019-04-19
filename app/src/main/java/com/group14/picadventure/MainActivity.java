package com.group14.picadventure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView;
import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import java.io.File;

public class MainActivity extends AppCompatActivity {

    //STORAGE PERMISSION REQUEST
    private static final int MY_PERMISSIONS_REQUEST = 100;

    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST);
        }
    }

    //STORAGE PATH FOR LOCAL IMAGES(needs to be fixed)
    //on first boot path has to be somewhere with a .jpg file otherwise it will crash //take a photo on first boot then change dir
    //static File directory = new File("/storage/emulated/0/Download"); //first boot
    static File directory = new File("/storage/emulated/0/Android/data/com.group14.picadventure/files/Pictures"); //after first photo (photo save location)
    public static File[] eatFoodyImages = directory.listFiles();

    //ACTIVITY CREATE TASKS
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestStoragePermission();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView listView = (GridView) findViewById(R.id.usage_example_listview);
        listView.setAdapter(
                new SimpleImageListAdapter(
                        MainActivity.this,
                        eatFoodyImages
                )
        );
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,(String)eatFoodyImages[position].getAbsolutePath(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,PhotoActivity.class);
                intent.putExtra("filename",(String)eatFoodyImages[position].getAbsolutePath());
                startActivity(intent);
            }
        });
    }
    //FOR REFRESH BUTTON
    protected void refresh() {
        finish();
        eatFoodyImages = directory.listFiles();
        startActivity(getIntent());
        Toast.makeText(this, "Refreshed", Toast.LENGTH_SHORT).show();
    }
    //FOR MENUS
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                refresh();
                return true;
            case R.id.about:
                Intent intent1 = new Intent(this, AboutActivity.class);
                this.startActivity(intent1);
                return true;
            case R.id.add:
                Intent intent2 = new Intent(this, AddActivity.class);
                this.startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
