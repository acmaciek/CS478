package com.example.project2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

//Activity triggered when long click on list view is performed, displays Image of the phone along with specs and Header
public class SpecsActivity extends AppCompatActivity {
    Data data = new Data();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specs);
        //initialize
        ImageView pictureDisplay = findViewById(R.id.PhoneImage);
        TextView title = findViewById(R.id.PhoneName);
        TextView specs = findViewById(R.id.Specs);
        Intent intent = getIntent();
        //get position of the cell
        final int position = intent.getIntExtra("position",0);
        pictureDisplay.setImageResource(data.images[position]); //display image
        title.setText(data.phones[position]); // display the name of the device
        specs.setText(data.specs[position]); // displat specs for the device

    }
}
