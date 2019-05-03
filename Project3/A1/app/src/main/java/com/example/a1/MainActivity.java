package com.example.a1;
// Maciej Girek
// CS 478
// UIC SPRING 2019
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String PERMISSION_CODE = "edu.uic.cs478.s19.kaboom.";
    private ReceiverClass MyReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        TextView textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfPermissionGranted();
            }
        });
    }

    private void checkIfPermissionGranted() {
        //if user hasn't allowed the permission yet, this condition will be true
            if (ContextCompat.checkSelfPermission(this, PERMISSION_CODE)
                    != PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(this, "DOES NOT HAVE A PERMISSION", Toast.LENGTH_SHORT)
//                        .show();
                ActivityCompat.requestPermissions(this, new String[]{PERMISSION_CODE}, 0);
            }
            else {
//                Toast.makeText(this, "HAS PERMISSION", Toast.LENGTH_SHORT)
//                        .show();
//                Launch Second Activity
                Intent intent1 = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent1);
            }

        }

    @Override
    //REQUEST THE PERMISSION OF THE USER
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //USER CLICKED EITHER ALLOW OR DENY BUTTON ON THE POP UP WINDOW
        if (grantResults.length > 0)
        {
            //ALLOW
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                //CREATE AND REGISTER BROADCAST RECEIVER PROGRAMATICALLY
                IntentFilter filter = new IntentFilter(PERMISSION_CODE);
                ReceiverClass receiver = new ReceiverClass();
                registerReceiver(receiver, filter);


                //Launch A2
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.example.a2");
                if (launchIntent != null) {
                    startActivity(launchIntent);//null pointer check in case package name was not found
                }
            }
            //DENY
            else
            {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT)
                        .show();
//                finishAndRemoveTask();
            }
        }
    }
}
