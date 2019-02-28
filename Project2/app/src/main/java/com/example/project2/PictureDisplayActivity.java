package com.example.project2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

public class PictureDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_display);
        ImageView pictureDisplay = findViewById(R.id.pictureDisplay);
        final WebView myWebView = findViewById(R.id.myWebView);

        Intent intent = getIntent();
        final int image = intent.getIntExtra("data",0);
        final String url = intent.getStringExtra("url");
        pictureDisplay.setImageResource(image);

        pictureDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                myWebView.setVisibility(View.VISIBLE);
//                myWebView.loadUrl(url);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });
    }
}
