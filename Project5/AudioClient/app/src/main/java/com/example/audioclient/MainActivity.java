//Maciej Girek mgirek2
//CS 478 Spring 2019
//University of Illinois at Chicago
package com.example.audioclient;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.example.commonData.*;
import java.util.List;


public class MainActivity extends Activity {
    private Button playMusicButton;
    private Button stopMusicButton;
    private Button startServiceButton;
    private Button stopServiceButton;
    private Button pauseMusicButton;
    protected  IMyAidlInterface AIDL;
    private List<String> songTitles;
    private ListView listView;
    ArrayAdapter<String> arrayAdapter;
    boolean songIsPlaying = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startServiceButton = findViewById(R.id.startServiceButton);
        playMusicButton = findViewById(R.id.playMusicButton);
        pauseMusicButton = findViewById(R.id.pauseMusicButton);
        stopMusicButton = findViewById(R.id.stopMusicButton);
        stopServiceButton = findViewById(R.id.stopServiceButton);
        listView = findViewById(R.id.songsListView);
        playMusicButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ //when resume button is pressed make a service call to execute resumePlaying() function from AIDL
                try {
                    if(!songIsPlaying) {
                        AIDL.resumePlaying();
                        songIsPlaying = true;
                    }
                } catch (RemoteException e){
                    e.printStackTrace();
                }
            }
        });

        stopMusicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//when stop button is pressed make a service call to execute stopPlayer() function from AIDL
                try {
                    AIDL.stopPlayer();
                }catch(RemoteException e){
                    e.printStackTrace();
                }
            }
        });

        pauseMusicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//when pause button is pressed make a service call to execute pauseSong() function from AIDL
                try {
                    if(songIsPlaying) {
                        AIDL.pauseSong();
                        songIsPlaying = false;
                    }
                }catch(RemoteException e){
                    e.printStackTrace();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { //when item from listView is selected make a service call to execute playSong() function at selected position from AIDL
                if(AIDL != null){
                    try {
                        AIDL.playSong(position);
                        songIsPlaying = true;
                    }catch (RemoteException e){
                        e.printStackTrace();
                    }
                }
            }
        });

        startServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Start and bind service
                Intent intent = new Intent(IMyAidlInterface.class.getName());
                intent.setAction("mgirek2.clipservice"); // set action
                intent.setPackage("com.example.clipserver"); // set location
                bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);
                startService(intent);
                playMusicButton.setVisibility(View.VISIBLE);
                stopMusicButton.setVisibility(View.VISIBLE);
                pauseMusicButton.setVisibility(View.VISIBLE);
                stopServiceButton.setVisibility(View.VISIBLE);
            }
        });

        stopServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //unbind and stop service
                Intent intent = new Intent(IMyAidlInterface.class.getName());
                intent.setAction("mgirek2.clipservice"); // set action
                intent.setPackage("com.example.clipserver"); // set location
                unbindService(serviceConnection);
                stopService(intent);
            }
        });
    }


    public void init() { //initailize intent taht will be used to bind and start the service
        Intent intent = new Intent(IMyAidlInterface.class.getName());
        intent.setAction("mgirek2.clipservice"); // set action
        intent.setPackage("com.example.clipserver"); // set location
        bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);
        startService(intent);
    }
    //Service connection
    //When connected, add songs to listView to be played from clip server
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            AIDL = IMyAidlInterface.Stub.asInterface((IBinder) iBinder);
            try{
                songTitles= AIDL.songList(); // get songs from AIDL service
                addSongsToListView(songTitles);
            }catch (RemoteException e){
                e.printStackTrace();
            }
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            AIDL = null;
        }
    };

    @Override
    protected void onDestroy() { //Unbind Service on Destroy
        super.onDestroy();
        unbindService(serviceConnection);
    }

    void addSongsToListView(List<String> array){ //Add song titles to listView
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);
        ListView listView = (ListView) findViewById(R.id.songsListView);
        listView.setAdapter(arrayAdapter);
    }
}