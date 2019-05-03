package com.example.mgirek2project4;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MoveByMoveActivity extends AppCompatActivity {

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
    private int gopher = 0;
    private static int turn=1;
    private static int i=0;
    private static int player1Turn=0;
    private static int player2Turn=1;
    private boolean win = false;
    public MainThread MainThread;
    public ThreadPlayer1 ThreadPlayer1;
    public ThreadPlayer2 ThreadPlayer2;
    private static int guess1, guess2;
    TextView textView3, winnerTextView;
    Button start_btn;
    ListView p1ListView;
    ListView p2ListView;
    ArrayList<String> stringArrayList;
    ArrayAdapter<String> stringArrayAdapter;
    ArrayList<String> stringArrayList2;
    ArrayAdapter<String> stringArrayAdapter2;
    int[] guessedNumber = new int[100];
    public static final int WAIT = 1;
    public static final int MOVE = 2;
    public static final int GAME_OVER = 3;
    public static final int WAITING_IS_OVER = 5;
    public static final int BOARD = 6;
    public static final int MAKE_MOVE = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continuous_mode);
        setUpView();

        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });
    }

    private class MainThread extends Handler {
        @Override
        public void handleMessage(Message msg) {
            int what=msg.what;
            Log.i("asd","MESSAGE HANDLER IS " + what);
            switch (what) {
                case BOARD:
                    boolean winner = checkWin();
                    gameState(winner,msg);
                    break;

                case MAKE_MOVE:
                    switchPlayers(turn,msg);
                    break;
            }
        }
    }
    //Function that stops threads if the game is won
    public void gameState(boolean winner, Message msg) {
        //run threads as long as gopher's position hasn't been guessed
        if(winner == false){
            msg = MainThread.obtainMessage(MAKE_MOVE);
            MainThread.sendMessage(msg);

        }
        //If game is won terminate threads
        else if(winner == true){
            msg = ThreadPlayer1.handlerPlayer1.obtainMessage(GAME_OVER);
            ThreadPlayer1.handlerPlayer1.sendMessage(msg);
            msg = ThreadPlayer2.handlerPlayer2.obtainMessage(GAME_OVER);
            ThreadPlayer2.handlerPlayer2.sendMessage(msg);
        }
    }
    // Function that switches the players and sends message to a main thread
    public void switchPlayers(int turn, Message msg) {
        if(turn  == player1Turn){
            msg = ThreadPlayer1.handlerPlayer1.obtainMessage(MOVE);
            ThreadPlayer1.handlerPlayer1.sendMessage(msg);
            stringArrayList.add(0,"THREAD 1 GUESS " + guess1);
            buttonArray[guess1].setBackgroundColor(Color.BLUE);
            stringArrayAdapter.notifyDataSetChanged();
            p1ListView.smoothScrollToPosition(0);
        }
        else if(turn  == player2Turn){
            msg = ThreadPlayer2.handlerPlayer2.obtainMessage(MOVE);
            ThreadPlayer2.handlerPlayer2.sendMessage(msg);
            stringArrayList2.add(0,"THREAD 2 GUESS " + guess2);
            buttonArray[guess2].setBackgroundColor(Color.GREEN);
            stringArrayAdapter2.notifyDataSetChanged();
            p2ListView.smoothScrollToPosition(0);
        }
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


    // Initialize fields, threads and views
    public void setUpView() {
        p1ListView = findViewById(R.id.P1LISTVIEW);
        p2ListView = findViewById(R.id.P2LISTVIEW);
        winnerTextView = findViewById(R.id.winnerTextView);
        stringArrayList = new ArrayList<String>();
        stringArrayList2 = new ArrayList<String>();
        stringArrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,stringArrayList);
        stringArrayAdapter2 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,stringArrayList2);
        p1ListView.setAdapter(stringArrayAdapter);
        p2ListView.setAdapter(stringArrayAdapter2);
        textView3=findViewById(R.id.textView3);
        start_btn=findViewById(R.id.button_start_thread);
        MainThread = new MainThread();
        ThreadPlayer1 = new ThreadPlayer1(MainThread);
        ThreadPlayer2 = new ThreadPlayer2(MainThread);
        ThreadPlayer1.start();
        ThreadPlayer2.start();
        gopher = generateNum();
        textView3.setText("GOPHER'S HOLE: " + gopher);

        Arrays.fill(guessedNumber, -1);
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
        buttonArray[gopher].setBackgroundColor(Color.RED);
    }

    //Function that starts the game
    public void start() {
        Message msg = ThreadPlayer1.handlerPlayer1.obtainMessage(MOVE);
        ThreadPlayer1.handlerPlayer1.sendMessage(msg);
    }
    //Function that checks whether one of the players won the game
    public boolean checkWin(){
        if(guess1 == gopher){ //PLAYER 1 WON
            stringArrayList.add(0,"THREAD 1 GUESS " + guess1);
            stringArrayAdapter.notifyDataSetChanged();
            p1ListView.smoothScrollToPosition(0);
            winnerTextView.setText("THREAD 1 WON");
            start_btn.setVisibility(View.INVISIBLE);
            textView3.setVisibility(View.INVISIBLE);
            win=true;
            return true;
        }
        else if(guess2 == gopher){ //PLAYER 2 WON
            stringArrayList2.add(0,"THREAD 2 GUESS " + guess2);
            stringArrayAdapter2.notifyDataSetChanged();
            p2ListView.smoothScrollToPosition(0);
            winnerTextView.setText("THREAD 2 WON");
            start_btn.setVisibility(View.INVISIBLE);
            textView3.setVisibility(View.INVISIBLE);
            win=true;
            return true;
        }
        return false;
    }

    //Generate random number
    public int generateNum(){
        Random rand =new Random();
        int randomNumber = rand.nextInt(100) +1;
        return randomNumber;
    }
    // Player 1 thread
    private class ThreadPlayer1 extends Thread{
        public Handler handlerPlayer1;
        public MainThread handler;
        public ThreadPlayer1(MainThread h){
            handler = h;
        }
        public void run(){
            Looper.prepare();
            handlerPlayer1 = new Handler(){
                @Override
                public void handleMessage(Message msg){
                    int what = msg.what ;
                    Log.i("asd","MESSAGE PLAYER1 IS " + what);
                    switch (what) {
                        case WAIT:
                            handlerPlayer1.post(new Runnable() {
                                @Override
                                public void run() {
                                    slowDown();
                                    //Broadcast message to the handler
                                    Message message = handler.obtainMessage(WAITING_IS_OVER);
                                    handler.sendMessage(message);
                                }
                            });
                        case MOVE:
                            handlerPlayer1.post(new Runnable() {
                                @Override
                                public void run() {
                                    slowDown();
                                    turn=1;
                                    guess1=generateNum();

                                    gameConditions(guess1,"Player 1");

                                    // SEND MESSAGE TO UPDATE THE BOARD
                                    Message message = handler.obtainMessage(BOARD);
                                    handler.sendMessage(message);
                                }
                            });
                            break;
                        case GAME_OVER:
                            handlerPlayer1.post(new Runnable() {
                                @Override
                                public void run() {
                                    slowDown();
                                }
                            });
                            break;
                    }
                }
            };
            Looper.loop();
        }
    }

    //Player 2 thread
    private class ThreadPlayer2 extends Thread{
        public Handler handlerPlayer2;
        private MainThread handler;
        public ThreadPlayer2(MainThread h){
            handler = h;
        }

        public void run(){
            Looper.prepare();
            handlerPlayer2 = new Handler(){
                @Override
                public void handleMessage(Message msg){
                    int what = msg.what ;
                    Log.i("asd","MESSAGE PLAYER2 IS " + what);
                    switch (what) {
                        case WAIT:
                            handlerPlayer2.post(new Runnable() {
                                @Override
                                public void run() {
                                    slowDown();
                                    //Broadcast message to the handler
                                    Message message = handler.obtainMessage(WAITING_IS_OVER);
                                    handler.sendMessage(message);
                                }
                            });

                        case MOVE:
                            handlerPlayer2.post(new Runnable() {
                                @Override
                                public void run() {
                                    slowDown();
                                    turn=0;
                                    guess2=generateNum();

                                    gameConditions(guess2,"Player 2");

                                    // SEND MESSAGE TO UPDATE THE BOARD
                                    Message message = handler.obtainMessage(BOARD);
                                    handler.sendMessage(message);

                                }
                            });
                            break;
                        case GAME_OVER:
                            handlerPlayer2.post(new Runnable() {
                                @Override
                                public void run() {
                                    slowDown();
                                }
                            });
                            break;
                    }
                }//end handleMessage()
            };
            Looper.loop();
        }
    }

    public void slowDown() {
        try { Thread.sleep(2000); }
        catch (InterruptedException e) { System.out.println("Thread interrupted!"); }
    }
}