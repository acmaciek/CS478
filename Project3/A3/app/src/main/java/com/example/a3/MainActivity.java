package com.example.a3;
// Maciej Girek
// CS 478
// UIC SPRING 2019
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class MainActivity extends AppCompatActivity implements MyFragment.ListSelectionListener {
    private MyFragment myFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private WebFragment webFragment;
    private String[] phoneNames;
    private String[] phoneUrls;
    private String[] phoneImages;
    private FrameLayout FrameLayoutName;
    private FrameLayout FrameLayoutWeb;
    private Boolean selected = false;
    private String url;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// check if item selected else display toast message
        if(item.getTitle().equals("Launch A1 and A2")) {
            if(selected) {
                Intent intent = new Intent("edu.uic.cs478.s19.kaboom.");
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

                intent.putExtra("PERMISSION", url);
                sendOrderedBroadcast(intent, "edu.uic.cs478.s19.kaboom.");
                Intent intent2 = getPackageManager().getLaunchIntentForPackage("com.example.a1");
                startActivity(intent2);
            }
            else {
                Toast.makeText(MainActivity.this,"YOU HAVE TO PICK A PHONE",Toast.LENGTH_LONG).show();
            }
        }
        else if(item.getTitle().equals("Exit A3")) {
            Toast.makeText(MainActivity.this,"EXITING A3",Toast.LENGTH_LONG).show();
            finishAndRemoveTask();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListSelection(int index) {
        if (!webFragment.isAdded()) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.browser, webFragment, "BrowserFragment");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            fragmentManager.executePendingTransactions();

        }
        selected = true;
        url = phoneUrls[index];
        Toast.makeText(MainActivity.this,"Selected " + phoneNames[index],Toast.LENGTH_LONG).show();
        webFragment.loadUrl(phoneImages[index]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(toolbar);

        phoneNames = getResources().getStringArray(R.array.phone_names);
        phoneUrls = getResources().getStringArray(R.array.phone_urls);
        phoneImages = getResources().getStringArray(R.array.phone_images);
        FrameLayoutName = (FrameLayout) findViewById(R.id.title);
        FrameLayoutWeb = (FrameLayout) findViewById(R.id.browser);

        // When user rotate screen reattach all fragments
        fragmentManager = getFragmentManager();

        myFragment = (MyFragment) fragmentManager.findFragmentByTag("Frag");
        webFragment = (WebFragment) fragmentManager.findFragmentByTag("BrowserFragment");

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {

            @Override
            public void onBackStackChanged() {
                setLayout();
            }
        });

        fragmentTransaction = fragmentManager.beginTransaction();

        if(myFragment == null) {
            myFragment = new MyFragment();
        }
        if(webFragment == null){
            webFragment = new WebFragment();
        } else {
            if(!webFragment.isAdded()) {
                fragmentTransaction.replace(R.id.browser, webFragment, "BrowserFragment");
                fragmentTransaction.addToBackStack(null);
            } else {
                setLayout();
            }
        }
        fragmentTransaction.replace(R.id.title, myFragment, "TitleFragment");
        fragmentTransaction.commit();
        fragmentManager.executePendingTransactions();
    }


    private void setLayout() {
        Configuration config = getResources().getConfiguration();
        //When in portrait orientation apply these layout parameters
        if( config.orientation == config.ORIENTATION_PORTRAIT) {
            if(!myFragment.isAdded()) {
                FrameLayoutName.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
                FrameLayoutWeb.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT));
            } else {
                FrameLayoutName.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT));
                FrameLayoutWeb.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
            }
        } else {
            //When in landscape orientation apply these layout parameters
            if(!myFragment.isAdded()) {
                FrameLayoutName.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
                FrameLayoutWeb.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT));
            } else {
                FrameLayoutName.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 1f));
                FrameLayoutWeb.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 2f));
            }
        }
    }

    @Override
    public String[] getTitleArray() {
        return this.phoneNames;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
