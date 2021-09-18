package com.example.it_elec1wordshuffle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShuffle[0] = findViewById(R.id.btnShuffle0);
        btnShuffle[1] = findViewById(R.id.btnShuffle1);
        btnShuffle[2] = findViewById(R.id.btnShuffle2);
        btnShuffle[3] = findViewById(R.id.btnShuffle3);
        btnShuffle[4] = findViewById(R.id.btnShuffle4);
        btnShuffle[5] = findViewById(R.id.btnShuffle5);
        btnPlay = findViewById(R.id.btnPlay);
        getWordtoGuess = (EditText) findViewById(R.id.editText_wordToGuess);
        testing = findViewById(R.id.lblTESTING);
        lblAnswer = findViewById(R.id.lblAnswer);
        btnShuffle[0].setOnClickListener(this);
        btnShuffle[1].setOnClickListener(this);
        btnShuffle[2].setOnClickListener(this);
        btnShuffle[3].setOnClickListener(this);
        btnShuffle[4].setOnClickListener(this);
        btnShuffle[5].setOnClickListener(this);
        btnPlay.setOnClickListener(this);
    }

    //Declarations
    static Button[] btnShuffle = new Button[6];
    Button[] life = new Button[5];
    Button btnPlay;
    EditText getWordtoGuess;
    TextView lblAnswer;
    static TextView testing;
    Random rand = new Random();
    static final int MAX_SIZE = 6;
    static String answer = "";

    @Override
    public void onClick(View view) {
        String str = getWordtoGuess.getText().toString();
        String[] newStr = str.split("");
        int len = newStr.length;

        if(view.getId() == R.id.btnPlay){
            //if(len == MAX_SIZE){
                testing.setText(str);
                shuffleString(rand, str);
            /*}
            else{
                //ERROR MESSAGE
                testing.setText("Input word that must be 6 letters");
            }*/
        }
        else if(view.getId() == R.id.btnShuffle0){
            answer += btnShuffle[0].getText().toString();
        }
        else if(view.getId() == R.id.btnShuffle1){
            answer += btnShuffle[1].getText().toString();
        }
        else if(view.getId() == R.id.btnShuffle2){
            answer += btnShuffle[2].getText().toString();
        }
        else if(view.getId() == R.id.btnShuffle3){
            answer += btnShuffle[3].getText().toString();
        }
        else if(view.getId() == R.id.btnShuffle4){
            answer += btnShuffle[4].getText().toString();
        }
        else if(view.getId() == R.id.btnShuffle5){
            answer += btnShuffle[5].getText().toString();
        }

        lblAnswer.setText(answer);
    }

    public static boolean lengthChecker(String str){
        String[] newStr = str.split("");
        return newStr.length == MAX_SIZE;
    }

    public static void shuffleString(Random rand, String str){
        char[] convert = str.toCharArray();

        for(int i = 0; i < MAX_SIZE; i++){
            int j = rand.nextInt(MAX_SIZE);
            char temp = convert[i];
            convert[i] = convert[j];
            convert[j] = temp;
        }
        str = new String(convert);
        testing.setText(str);
        String[] newStr = str.split("");

        for(int i = 0; i < MAX_SIZE; i++){
            btnShuffle[i].setText(newStr[i+1]);
        }
    }
}