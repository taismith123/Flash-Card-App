package com.example.taismith.firstapp;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Flashcard> allFlashcards;

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
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        findViewById(R.id.nextButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Animation leftOutAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.left_out);
                final Animation rightInAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.right_in);


                leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        // this method is called when the animation first starts
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // this method is called when the animation is finished playing
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // we don't need to worry about this method
                    }
                });

                findViewById(R.id.flashcard_question).startAnimation(leftOutAnim);
                findViewById(R.id.flashcard_question).startAnimation(rightInAnim);

                final TextView questionTxt = findViewById(R.id.flashcard_question);
                final TextView answerTxt = findViewById(R.id.flashcard_answer);

                answerTxt.setVisibility(View.INVISIBLE);
                questionTxt.setVisibility(View.VISIBLE);


                // advance our pointer index so we can show the next card
                currentCardDisplayedIndex++;

                // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                    currentCardDisplayedIndex = 0;
                }


                if (currentCardDisplayedIndex > 0)

                // set the question and answer TextViews with data from the database
                ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());

                answerTxt.setVisibility(View.INVISIBLE);
                questionTxt.setVisibility(View.VISIBLE);

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

                        View answerSideView = findViewById(R.id.flashcard_answer);

                        View questionSideView = findViewById(R.id.flashcard_question);
// get the center for the clipping circle
                        int cx = answerSideView.getWidth() / 2;
                        int cy = answerSideView.getHeight() / 2;

// get the final radius for the clipping circle
                        float finalRadius = (float) Math.hypot(cx, cy);

// create the animator for this view (the start radius is zero)
                        Animator anim = ViewAnimationUtils.createCircularReveal(answerSideView, cx, cy, 0f, finalRadius);

// hide the question and show the answer to prepare for playing the animation!
                        questionSideView.setVisibility(View.INVISIBLE);
                        answerSideView.setVisibility(View.VISIBLE);

                        anim.setDuration(3000);
                        anim.start();
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

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();

        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(0).getAnswer());



            answerTxt.setVisibility(View.INVISIBLE);
            questionTxt.setVisibility(View.VISIBLE);
        }
    }


    int currentCardDisplayedIndex = 0;


    FlashcardDatabase flashcardDatabase;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 100) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            String string1 = data.getExtras().getString("string1"); // 'string1' needs to match the key we used when we put the string in the Intent
            String string2 = data.getExtras().getString("string2");
            final TextView questionTxt = findViewById(R.id.flashcard_question);
            final TextView answerTxt = findViewById(R.id.flashcard_answer);
            questionTxt.setText(string1);
            answerTxt.setText(string2);
            flashcardDatabase.insertCard(new Flashcard(string1, string2));
            allFlashcards = flashcardDatabase.getAllCards();



            answerTxt.setVisibility(View.INVISIBLE);
            questionTxt.setVisibility(View.VISIBLE);
        }
    }
}

