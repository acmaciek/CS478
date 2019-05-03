package com.example.a1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private TextView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getIntent().getStringExtra("PERMISSION"))));
    }
}
