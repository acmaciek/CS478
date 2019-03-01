package com.example.project2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;
//Activity that displays full size picture of the phone
public class PictureDisplayActivity extends AppCompatActivity {
    Data data = new Data();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_display);
        //initialize
        ImageView pictureDisplay = findViewById(R.id.pictureDisplay);
        Intent intent = getIntent();
        //get position of the cell from list view
        final int position = intent.getIntExtra("position",0);
        pictureDisplay.setImageResource(data.images[position]); //display the image of the phone

        pictureDisplay.setOnClickListener(new View.OnClickListener() {//function triggered when picture is clicked on
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.urls[position])); //web browser activity
                startActivity(browserIntent);
            }
        });
    }
}
