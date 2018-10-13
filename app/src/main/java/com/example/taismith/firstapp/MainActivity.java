package com.example.taismith.firstapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        findViewById(R.id.flashcard_question).setBackgroundColor(getResources().getColor(R.color.my_red_color, null));
        findViewById(R.id.flashcard_answer).setBackgroundColor(getResources().getColor(R.color.colorPrimary, null));

        final TextView questionTxt = findViewById(R.id.flashcard_question);
        final TextView answerTxt = findViewById(R.id.flashcard_answer);
        questionTxt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        answerTxt.setVisibility(View.VISIBLE);
                        questionTxt.setVisibility(View.INVISIBLE);
                        answerTxt.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        answerTxt.setVisibility(View.INVISIBLE);
                                        questionTxt.setVisibility(View.VISIBLE);
                                    }


                                });


                    }

                });


    }
}

