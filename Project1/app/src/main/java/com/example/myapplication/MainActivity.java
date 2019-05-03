package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button addressButton;
    private Button mapButon;
    private String addresString;
    private Boolean RESULT = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context context = getApplicationContext();
        addressButton = findViewById(R.id.button1); //initialize address button
        mapButon =  findViewById(R.id.button2);  // initialize map button

        // executes when address button is clicked
        addressButton.setOnClickListener(
            new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2(); // opens new activity when address button is clicked
            }
        });

        // executes when map button is clicked
        mapButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(RESULT == true) { // if user entered the address, the interaction with button is enabled
                    Uri map = Uri.parse("geo:0,0?q=" + Uri.encode(addresString)); // address string parser
                    Intent googleMapIntent = new Intent(Intent.ACTION_VIEW, map); // google map intent
                    googleMapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(googleMapIntent);
                }
                else {
                    Toast.makeText(context,"You did not enter any address",Toast.LENGTH_SHORT).show(); // if user did not enter the address prior to clicking map button, toast message is displayed
                }
            }
        });
    }

    //activity 2 initializer
    public void openActivity2() {
        Intent intent = new Intent(this, Main2Activity.class); //initialize new intent
        startActivityForResult(intent,1); // start new activity with request code : 1
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);


        // Check which request we're responding to
        if (requestCode == 1) {
//            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                RESULT = true; // set boolean to true after address is entered successfully
                addresString = data.getStringExtra("address"); //getStringExtra method enables to send string values between activities
            }
            else if (resultCode == RESULT_CANCELED) {
                RESULT = false;
            }
        }
    }
}
