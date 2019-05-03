package com.example.mgirek2project4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button continuous_mode_button;
    private Button move_by_move_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        move_by_move_button = findViewById(R.id.move_by_move_button);
        continuous_mode_button = findViewById(R.id.continuous_mode_button);

        move_by_move_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open MOVE BY MOVE MODE
                Intent intent = new Intent(MainActivity.this, MoveByMoveActivity.class);
                startActivity(intent);
            }
        });

        continuous_mode_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open CONTINUOUS MODE
                Intent intent = new Intent(MainActivity.this, ContinuousModeActivity.class);
                startActivity(intent);
            }
        });

    }
}
