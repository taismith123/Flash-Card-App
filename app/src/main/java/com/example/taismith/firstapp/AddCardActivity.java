package com.example.taismith.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCardActivity extends AppCompatActivity {

   // @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        Button cancelButton;
        cancelButton= findViewById(R.id.cancelButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {


                @Override
            public void onClick(View v) {
                    Intent intent = new Intent(AddCardActivity.this, MainActivity.class);
                    AddCardActivity.this.startActivity(intent);


                }
        });
                    Button saveButton;
                    saveButton= findViewById(R.id.saveButton);


                    saveButton.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {


                            ((EditText) findViewById(R.id.editText)).getText().toString();
                            ((EditText) findViewById(R.id.editText2)).getText().toString();
                            Intent data = new Intent(); // create a new Intent
                            data.putExtra("string1", ((EditText) findViewById(R.id.editText)).getText().toString());
                            data.putExtra("string2", ((EditText) findViewById(R.id.editText2)).getText().toString());
                            setResult(RESULT_OK, data); // set result code and bundle data for response
                            finish(); // closes this activity

                        }
                        });





    }




        }



