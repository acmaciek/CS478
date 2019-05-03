package com.example.a1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

public class ReceiverClass extends BroadcastReceiver {

    private static final String CUSTOM_PERMISSION = "edu.uic.cs478.s19.kaboom.";

    @Override
    public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("PERMISSION");
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            Intent intent1 = new Intent(context, Main2Activity.class);
            intent1.putExtra("PERMISSION",message);
            context.startActivity(intent1);//null pointer check in case package name was not found
    }


}
