package com.example.a2;
// Maciej Girek
// CS 478
// UIC SPRING 2019
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

public class ReceiverClass extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String message = "APP 2 received permission: " + intent.getAction();
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage("com.example.a3");
        context.startActivity(launchIntent);//null pointer check in case package name was not found

    }
}
