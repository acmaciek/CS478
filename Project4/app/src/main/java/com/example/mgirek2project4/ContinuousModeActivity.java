package com.example.mgirek2project4;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class ContinuousModeActivity extends AppCompatActivity {

    int[] guessedNumber = new int[100];
    private Button buttonStartThread;
    private TextView gopherTextView;
    private TextView winner;
    ListView p1ListView;
    ListView p2ListView;
    ArrayList<String> stringArrayList;
    ArrayAdapter<String> stringArrayAdapter;
    ArrayList<String> stringArrayList2;
    ArrayAdapter<String> stringArrayAdapter2;
    private Handler mainHandler = new Handler();
    private Integer gopher = 0;
    private Boolean win = false;
    private int i = 0;
    Button  button1,
            button2,
            button3,
            button4,
            button5,
            button6,
            button7,
            button8,
            button9,
            button10,
            button11,
            button12,
            button13,
            button14,
            button15,
            button16,
            button17,
            button18,
            button19,
            button20,
            button21,
            button22,
            button23,
            button24,
            button25,
            button26,
            button27,
            button28,
            button29,
            button30,
            button31,
            button32,
            button33,
            button34,
            button35,
            button36,
            button37,
            button38,
            button39,
            button40,
            button41,
            button42,
            button43,
            button44,
            button45,
            button46,
            button47,
            button48,
            button49,
            button50,
            button51,
            button52,
            button53,
            button54,
            button55,
            button56,
            button57,
            button58,
            button59,
            button60,
            button61,
            button62,
            button63,
            button64,
            button65,
            button66,
            button67,
            button68,
            button69,
            button70,
            button71,
            button72,
            button73,
            button74,
            button75,
            button76,
            button77,
            button78,
            button79,
            button80,
            button81,
            button82,
            button83,
            button84,
            button85,
            button86,
            button87,
            button88,
            button89,
            button90,
            button91,
            button92,
            button93,
            button94,
            button95,
            button96,
            button97,
            button98,
            button99,
            button100;

    Button buttonArray[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continuous_mode);

        setUpView();
        buttonStartThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startThread(v);
                stopThread(v);
            }
        });
    }

    public void startThread(View view) {
        ExampleRunnable runnable = new ExampleRunnable(1000);
        new Thread(runnable).start();
    }

    public void stopThread(View view) {
        ExampleThread runnable = new ExampleThread(1000);
        runnable.start();
    }

    class ExampleThread extends Thread {
        int seconds;

        ExampleThread(int seconds) {
            this.seconds = seconds;
        }

        @Override
        public void run() {

            Random rand = new Random();
            while(win != true){

                int n = rand.nextInt(100);
                if(n == gopher && win != true) {
                    win = true;
                    winner.setText("THREAD 2 WON !!!!");
                    gopherTextView.setVisibility(View.INVISIBLE);
                    buttonStartThread.setVisibility(View.INVISIBLE);
                    stringArrayList2.add(0,"THREAD 2 GUESS " + n);
                    buttonArray[n].setBackgroundColor(Color.BLACK);
//                    stringArrayAdapter2.notifyDataSetChanged();
//                    p2ListView.smoothScrollToPosition(0);
                    Thread.currentThread().interrupt();
                    return;
                }
                stringArrayList2.add(0,"THREAD 2 GUESS " + n);
//                gameConditions(n,"Player 2");
                buttonArray[n].setBackgroundColor(Color.BLUE);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        stringArrayAdapter2.notifyDataSetChanged();
                        p2ListView.smoothScrollByOffset(0);
                    }
                });

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class ExampleRunnable implements Runnable {
        int seconds;
        ExampleRunnable(int seconds) {
            this.seconds = seconds;
        }

        @Override
        public void run() {
            Random rand = new Random();
            int n = rand.nextInt(100);
//            for (int i = 0; i < seconds; i++) {
            while(win != true) {
                n = rand.nextInt(100);
                if(n == gopher && win != true) {
                    win = true;
                    winner.setText("THREAD 1 WON !!!!");
                    gopherTextView.setVisibility(View.INVISIBLE);
                    buttonStartThread.setVisibility(View.INVISIBLE);
                    stringArrayList.add(0,"THREAD 1 GUESS " + n);
//                    gameConditions(n,"Player 1");
                    buttonArray[n].setBackgroundColor(Color.BLACK);
//                    stringArrayAdapter.notifyDataSetChanged();
//                    p1ListView.smoothScrollToPosition(0);
                    Thread.currentThread().interrupt();
                    return;
                }
                stringArrayList.add(0,"THREAD 1q GUESS " + n);
                buttonArray[n].setBackgroundColor(Color.GREEN);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        stringArrayAdapter.notifyDataSetChanged();
                        p1ListView.smoothScrollByOffset(0);
                    }
                });
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void setUpView() {
        p1ListView = findViewById(R.id.P1LISTVIEW);
        p2ListView = findViewById(R.id.P2LISTVIEW);
        stringArrayList = new ArrayList<String>();
        stringArrayList2 = new ArrayList<String>();
        stringArrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,stringArrayList);
        stringArrayAdapter2 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,stringArrayList2);
        p1ListView.setAdapter(stringArrayAdapter);
        p2ListView.setAdapter(stringArrayAdapter2);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button10 = findViewById(R.id.button10);
        button11 = findViewById(R.id.button11);
        button12 = findViewById(R.id.button12);
        button13 = findViewById(R.id.button13);
        button14 = findViewById(R.id.button14);
        button15 = findViewById(R.id.button15);
        button16 = findViewById(R.id.button16);
        button17 = findViewById(R.id.button17);
        button18 = findViewById(R.id.button18);
        button19 = findViewById(R.id.button19);
        button20 = findViewById(R.id.button20);
        button21 = findViewById(R.id.button21);
        button22 = findViewById(R.id.button22);
        button23 = findViewById(R.id.button23);
        button24 = findViewById(R.id.button24);
        button25 = findViewById(R.id.button25);
        button26 = findViewById(R.id.button26);
        button27 = findViewById(R.id.button27);
        button28 = findViewById(R.id.button28);
        button29 = findViewById(R.id.button29);
        button30 = findViewById(R.id.button30);
        button31 = findViewById(R.id.button31);
        button32 = findViewById(R.id.button32);
        button33 = findViewById(R.id.button33);
        button34 = findViewById(R.id.button34);
        button35 = findViewById(R.id.button35);
        button36 = findViewById(R.id.button36);
        button37 = findViewById(R.id.button37);
        button38 = findViewById(R.id.button38);
        button39 = findViewById(R.id.button39);
        button40 = findViewById(R.id.button40);
        button41 = findViewById(R.id.button41);
        button42 = findViewById(R.id.button42);
        button43 = findViewById(R.id.button43);
        button44 = findViewById(R.id.button44);
        button45 = findViewById(R.id.button45);
        button46 = findViewById(R.id.button46);
        button47 = findViewById(R.id.button47);
        button48 = findViewById(R.id.button48);
        button49 = findViewById(R.id.button49);
        button50 = findViewById(R.id.button50);
        button51 = findViewById(R.id.button51);
        button52 = findViewById(R.id.button52);
        button53 = findViewById(R.id.button53);
        button54 = findViewById(R.id.button54);
        button55 = findViewById(R.id.button55);
        button56 = findViewById(R.id.button56);
        button57 = findViewById(R.id.button57);
        button58 = findViewById(R.id.button58);
        button59 = findViewById(R.id.button59);
        button60 = findViewById(R.id.button60);
        button61 = findViewById(R.id.button61);
        button62 = findViewById(R.id.button62);
        button63 = findViewById(R.id.button63);
        button64 = findViewById(R.id.button64);
        button65 = findViewById(R.id.button65);
        button66 = findViewById(R.id.button66);
        button67 = findViewById(R.id.button67);
        button68 = findViewById(R.id.button68);
        button69 = findViewById(R.id.button69);
        button70 = findViewById(R.id.button70);
        button71 = findViewById(R.id.button71);
        button72 = findViewById(R.id.button72);
        button73 = findViewById(R.id.button73);
        button74 = findViewById(R.id.button74);
        button75 = findViewById(R.id.button75);
        button76 = findViewById(R.id.button76);
        button77 = findViewById(R.id.button77);
        button78 = findViewById(R.id.button78);
        button79 = findViewById(R.id.button79);
        button80 = findViewById(R.id.button80);
        button81 = findViewById(R.id.button81);
        button82 = findViewById(R.id.button82);
        button83 = findViewById(R.id.button83);
        button84 = findViewById(R.id.button84);
        button85 = findViewById(R.id.button85);
        button86 = findViewById(R.id.button86);
        button87 = findViewById(R.id.button87);
        button88 = findViewById(R.id.button88);
        button89 = findViewById(R.id.button89);
        button90 = findViewById(R.id.button90);
        button91 = findViewById(R.id.button91);
        button92 = findViewById(R.id.button92);
        button93 = findViewById(R.id.button93);
        button94 = findViewById(R.id.button94);
        button95 = findViewById(R.id.button95);
        button96 = findViewById(R.id.button96);
        button97 = findViewById(R.id.button97);
        button98 = findViewById(R.id.button98);
        button99 = findViewById(R.id.button99);
        button100 = findViewById(R.id.button100);
        buttonArray = new Button[]{
                button1,  button2,  button3,  button4,  button5,  button6,  button7,  button8,  button9,  button10,
                button11, button12, button13, button14, button15, button16, button17, button18, button19, button20,
                button21, button22, button23, button24, button25, button26, button27, button28, button29, button30,
                button31, button32, button33, button34, button35, button36, button37, button38, button39, button40,
                button41, button42, button43, button44, button45, button46, button47, button48, button49, button50,
                button51, button52, button53, button54, button55, button56, button57, button58, button59, button60,
                button61, button62, button63, button64, button65, button66, button67, button68, button69, button70,
                button71, button72, button73, button74, button75, button76, button77, button78, button79, button80,
                button81, button82, button83, button84, button85, button86, button87, button88, button89, button90,
                button91, button92, button93, button94, button95, button96, button97, button98, button99, button100,
        };
        Random rand = new Random();
        gopher = rand.nextInt(100);
        buttonStartThread = findViewById(R.id.button_start_thread);
        winner = findViewById(R.id.winnerTextView);
        gopherTextView = findViewById(R.id.textView3);
        gopherTextView.setText("GOPHER POSITION : " + gopher);
        buttonArray[gopher].setBackgroundColor(Color.RED);
        Arrays.fill(guessedNumber, -1);
    }

    //Check game conditions
    public void gameConditions(int playerNum, String player) {
        for(int j = 0; j < 100; ++j) {
            if(guessedNumber[j] == playerNum) {
                Toast.makeText(getApplicationContext(),"COMPLETE DISASTER " + player,Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if(playerNum - 8 < gopher && playerNum + 8 > gopher) {
            Toast.makeText(getApplicationContext(),"NEAR MISS " + player,Toast.LENGTH_SHORT).show();

        }
        else if(playerNum - 2 < gopher && playerNum + 2 > gopher) {
            Toast.makeText(getApplicationContext(),"CLOSE GUESS " + player,Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(getApplicationContext(),"COMPLETE MISS " + player,Toast.LENGTH_SHORT).show();
        }
        guessedNumber[i] = playerNum;
        i++;
    }
}
