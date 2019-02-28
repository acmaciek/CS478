package com.example.project2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int[] images = {R.drawable.iphonexr,R.drawable.xs,R.drawable.eight,R.drawable.iphone7,R.drawable.galaxy10,R.drawable.nine,R.drawable.pixel};
    String[] descriptions = {"6.1” Liquid Retina\nprice: $749 - $899","5.8” Super Retina\nprice: $999 - $1,349 ","4.7-inch Retina HD display\nprice: $599 - $749 ","4.7-inch Retina HD display\nprice: $449 - $549 ","6.1” Quad HD+ Dynamic AMOLED Infinity Display1\nprice: $899 - 1149","5.8” Quad HD+ Dynamic AMOLED Infinity Display1\nprice: $619 - 749","6.3” display\nprice: $699 - $799"};
    String[] phones = {"iPhone Xr","iPhone Xs","iPhone 8","iPhone 7","Smasung Galaxy s10","Smasung Galaxy s9","Google Pixel 3XL"};
    String [] urls = {"https://www.apple.com/iphone-xr/","https://www.apple.com/iphone-xs/","https://www.apple.com/iphone-8/specs/","https://www.apple.com/iphone-7/specs/","https://www.samsung.com/us/mobile/galaxy-s10/performance/","https://www.samsung.com/global/galaxy/galaxy-s9/","https://store.google.com/us/product/pixel_3?hl=en-US"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView myListView = findViewById(R.id.phoneListView);
        CustomAdapter customAdapter = new CustomAdapter();
        myListView.setAdapter(customAdapter);

        myListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        openNewActivity(images[position], urls[position]);
                    }
                }
        );
    }


    public void openNewActivity(int image, String url) {
        Intent intent = new Intent(this,PictureDisplayActivity.class);
        intent.putExtra("data",image);
        intent.putExtra("url",url);
        startActivity(intent);
    }
}