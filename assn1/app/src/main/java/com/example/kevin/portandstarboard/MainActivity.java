package com.example.kevin.portandstarboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.kevin.portandstarboard.Game.*;

public class MainActivity extends AppCompatActivity {

    private Game mainGame;
    public static final String TAG = "InitialApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Part 1
        Button leftBtn = findViewById(R.id.leftButton);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i(TAG, "Port (left) is red");
                Toast.makeText(getApplicationContext(), "Port (left) is red", Toast.LENGTH_SHORT)
                        .show();
            }
        });
        Button rightBtn = findViewById(R.id.rightButton);
        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i(TAG, "Starboard (right) is green");
                Toast.makeText(getApplicationContext(), "Starboard (right) is green", Toast.LENGTH_SHORT)
                        .show();
            }
        });


        //Part 2
        mainGame = new Game();
        TextView answer = findViewById(R.id.txtAnswerDisplay);
        answer.setText(mainGame.getChosenSideName());

        Button leftAnswer = findViewById(R.id.btnAnswerLeft);
        leftAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainGame.checkIfCorrect(Side.PORT)) {
                    Log.i(TAG, "User guess of Port was correct!");
                    Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Log.i(TAG, "User guess of Port was incorrect.");
                    Toast.makeText(getApplicationContext(), "Incorrect. :(", Toast.LENGTH_SHORT)
                            .show();
                }

                mainGame = new Game();
                TextView answer = findViewById(R.id.txtAnswerDisplay);
                answer.setText(mainGame.getChosenSideName());

            }
        });

        Button rightAnswer = findViewById(R.id.btnAnswerRight);
        rightAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (mainGame.checkIfCorrect(Side.STARBOARD)) {
                        Log.i(TAG, "User guess of Starboard was correct!");
                        Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        Log.i(TAG, "User guess of Starboard was incorrect.");
                        Toast.makeText(getApplicationContext(), "Incorrect. :(", Toast.LENGTH_SHORT)
                                .show();
                    }

                mainGame = new Game();
                TextView answer = findViewById(R.id.txtAnswerDisplay);
                answer.setText(mainGame.getChosenSideName());
            }

        });

    }
}
