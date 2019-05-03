package com.example.a2;


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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ReceiverClass newReceiver;
    private IntentFilter myFilter;
    private static final String PERMISSION_CODE = "edu.uic.cs478.s19.kaboom.";
    private Button permissionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permissionButton=findViewById(R.id.PermissionButon);

        //Check/request permissions
        permissionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfPermissionGranted();
            }
        });
    }

    //check if permission is granted
    private void checkIfPermissionGranted() {

        //if user already allowed the permission, this condition will be true
        if (ContextCompat.checkSelfPermission(this, PERMISSION_CODE)
                == PackageManager.PERMISSION_GRANTED) {
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.example.a3");
            if (launchIntent != null) {
                startActivity(launchIntent);//null pointer check in case package name was not found
            }
        }
        //if user didn't allow the permission yet, then ask for permission
        else {
            ActivityCompat.requestPermissions(this, new String[]{PERMISSION_CODE}, 0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //USER CLICKED EITHER ALLOW OR DENY BUTTON ON THE POP UP WINDOW
        if (grantResults.length > 0)
        {
            //ALLOW
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                //CREATE AND REGISTER BROADCAST RECEIVER PROGRAMATICALLY

                //setting up intent filter and priority
                IntentFilter filter = new IntentFilter(PERMISSION_CODE);
                ReceiverClass receiver = new ReceiverClass();
                registerReceiver(receiver, filter);

                //Launch A3
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.example.a3");
                if (launchIntent != null) {
                    startActivity(launchIntent);//null pointer check in case package name was not found
                }
            }
            //DENY
            else
            {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT)
                        .show();
                finishAndRemoveTask();
            }
        }
    }
}