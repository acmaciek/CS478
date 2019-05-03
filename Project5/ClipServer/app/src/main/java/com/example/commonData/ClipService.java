//Maciej Girek mgirek2
//CS 478 Spring 2019
//University of Illinois at Chicago

package com.example.commonData;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;
import com.example.clipserver.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClipService extends Service {
    private MediaPlayer mediaPlayer;
    ArrayList<Integer> songNames = new ArrayList<>();
    boolean mediaPlayerStarted = false;
    List<String> songTitles;
    int mSongId;

    @Override
    public void onCreate() {
        super.onCreate();
        setUpView();
    }

    public void setUpView() {  // Set up the variables and sonngs to a list to be played
        songNames.add(R.raw.bellaciao);
        songNames.add(R.raw.breakonthrough);
        songNames.add(R.raw.bumbumtamtam);
        songNames.add(R.raw.immigrantsong);
        songNames.add(R.raw.paranoid);
        songTitles = Arrays.asList(getResources().getStringArray(R.array.titles));
        if (mediaPlayer != null) {
            mediaPlayer.setLooping(false);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopSelf();
                    NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.cancelAll();
                }
            });
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startid) {
        return START_NOT_STICKY;
    }
    // Initialize Android media player whenever song is changed or service is started
    private void startNewSong(int songId) {
        mediaPlayer = MediaPlayer.create(this, songNames.get(songId));
        mediaPlayerStarted = true;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return true;
    }


    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return mBinder;
    }

    //Shared AIDL
    private final IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {

        @Override
        public void stopPlayer() { //When stop button is clicked media player should stop
            mediaPlayer.stop();
            mediaPlayer = null;
            mediaPlayerStarted = false;
        }

        public List<String> songList() { //Append titles to a listView
            return Arrays.asList(getResources().getStringArray(R.array.titles));
        }

        public void pauseSong() {
            mediaPlayer.pause();
        } //Pause a song

        public void playSong(int id) { //Play song or if song is currently being played, stop mediaPlayer and play new song
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
            mSongId = id;
            startNewSong(id);
            mediaPlayer.start();
        }

        public void resumePlaying() throws RemoteException { //Resume playing song if paused
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.start();
                }
        }
    };
}
