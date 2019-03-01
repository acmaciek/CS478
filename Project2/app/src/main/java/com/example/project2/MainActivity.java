package com.example.project2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CustomAdapter customAdapter = new CustomAdapter(this); //create instance of Custom Adapter and pass the context form Main Activity
    Data data = new Data();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize
        ListView myListView = findViewById(R.id.phoneListView);
        myListView.setAdapter(customAdapter);
        //Registers a context menu to be shown for the this view
        registerForContextMenu(myListView);

        myListView.setOnItemClickListener( //when cell in a list view is clicked execute this method
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        openPicDisplayActivity(position); //pass the position integer into a function
                        //postion variable stores the cell # that was clicked by the user
                    }
                }
        );
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu"); //Context Menu title
        menu.add(0, v.getId(), 1, "Show specs"); //First cell, assigned order to distinguish from other items in context menu
        menu.add(0, v.getId(), 2, "View Picture"); // Second cell, order = 2
        menu.add(0, v.getId(), 3, "Go to web page");// Third cell, order = 3
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if(item.getOrder() == 1) { // show specs, order = 1
            openSpecsActivity(info.position); // call the function and pass position variable as parameter so we can access data at that position
            return true;
        }
        else if(item.getOrder() == 2){ // show picture, order = 2
            openPicDisplayActivity(info.position); //call function that opens new activity and displays full size picture
            return true;
        }
        else if(item.getOrder() == 3){ //show website, order = 3
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.urls[info.position]));//web browser intent to display website, accessed throud the postion of the url array
            startActivity(browserIntent);
            return true;
        }
        return false;
    }

    public void openPicDisplayActivity(int position) { //function that passes position variable into a PictureDisplayActivity class as an intent, picture is accessed as a postion in image array
        Intent intent = new Intent(this,PictureDisplayActivity.class);
        intent.putExtra("position",position);
        startActivity(intent);
    }

    public void openSpecsActivity(int position) { //function that passes postion array into a SpecsActivity class as an intent, in the third activity postion is utilized to display name specs and image o the device at given position
        Intent intent = new Intent(this,SpecsActivity.class);
        intent.putExtra("position",position);
        startActivity(intent);
    }
}