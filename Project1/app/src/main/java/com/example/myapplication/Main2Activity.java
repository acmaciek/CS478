package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private EditText addressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        addressText = findViewById(R.id.editText);
        addressText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                if(addressText.getText().length() == 0) { // if address has not been set prior to interacting with button, set result to Cancelled
                    setResult(RESULT_CANCELED, intent);
                    finish();
                }
                else {
                    //dismisses the keyboard
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(addressText.getWindowToken(), 0);
                    // if the address is set, then get its value, and set result to Ok
                    intent.putExtra("address",addressText.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish(); //end activity, go back to previous activity
                }
            }
        });
    }
}
