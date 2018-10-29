package com.example.taismith.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        findViewById(R.id.flashcard_question).setBackgroundColor(getResources().getColor(R.color.my_red_color, null));
        findViewById(R.id.flashcard_answer).setBackgroundColor(getResources().getColor(R.color.colorPrimary, null));

        Button addButton;
        addButton= findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });


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



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 100) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            String string1 = data.getExtras().getString("string1"); // 'string1' needs to match the key we used when we put the string in the Intent
            String string2 = data.getExtras().getString("string2");
            final TextView questionTxt = findViewById(R.id.flashcard_question);
            final TextView answerTxt = findViewById(R.id.flashcard_answer);
            questionTxt.setText(string1);
            answerTxt.setText(string2);
        }
    }
}

