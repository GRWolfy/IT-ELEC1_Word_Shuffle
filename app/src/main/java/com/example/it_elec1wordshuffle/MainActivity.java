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
        btnLife[0] = findViewById(R.id.btnLife1);
        btnLife[1] = findViewById(R.id.btnLife2);
        btnLife[2] = findViewById(R.id.btnLife3);
        btnLife[3] = findViewById(R.id.btnLife4);
        btnLife[4] = findViewById(R.id.btnLife5);
        btnPlay = findViewById(R.id.btnPlay);
        getWordtoGuess = (EditText) findViewById(R.id.editText_wordToGuess);
        testing = findViewById(R.id.lblTESTING);
        txtAnswer = findViewById(R.id.txtAnswer);

        btnShuffle[0].setOnClickListener(this);
        btnShuffle[1].setOnClickListener(this);
        btnShuffle[2].setOnClickListener(this);
        btnShuffle[3].setOnClickListener(this);
        btnShuffle[4].setOnClickListener(this);
        btnShuffle[5].setOnClickListener(this);
        btnPlay.setOnClickListener(this);
    }

    //Declarations
    Button[] btnShuffle = new Button[6];
    Button[] btnLife = new Button[5];
    Button btnPlay;
    EditText getWordtoGuess;
    EditText txtAnswer;
    TextView testing;
    Random rand = new Random();
    int play = 0;
    final int MAX_SIZE = 6;
    String answer = "";
    boolean[] checkLife = new boolean[5];

    @Override
    public void onClick(View view) {
        String str = getWordtoGuess.getText().toString();

        if(view.getId() == R.id.btnPlay){
            if(lengthChecker()){
                play = 1;
                shuffleString(rand, str);
                resetLifeBar();
                answer = "";

                for(int i = 0; i < MAX_SIZE; i++){
                    btnShuffle[i].setEnabled(true);
                }
            }
            else{
                //ERROR MESSAGE
                testing.setText("Input word that must be 6 letters");
            }
        }
        else if(view.getId() == R.id.btnShuffle0 && play == 1){
            answer += btnShuffle[0].getText().toString();
        }
        else if(view.getId() == R.id.btnShuffle1 && play == 1){
            answer += btnShuffle[1].getText().toString();
        }
        else if(view.getId() == R.id.btnShuffle2 && play == 1){
            answer += btnShuffle[2].getText().toString();
        }
        else if(view.getId() == R.id.btnShuffle3 && play == 1){
            answer += btnShuffle[3].getText().toString();
        }
        else if(view.getId() == R.id.btnShuffle4 && play == 1){
            answer += btnShuffle[4].getText().toString();
        }
        else if(view.getId() == R.id.btnShuffle5 && play == 1){
            answer += btnShuffle[5].getText().toString();
        }

        isWinner(answer, str);
        txtAnswer.setText(answer);
    }

    //Method for checking if the string length is equal to 6 and if not will prompt a
    //warning to alert the user to correct his/her input
    private boolean lengthChecker(){
        String[] newStr = getWordtoGuess.getText().toString().split("");
        return newStr.length == MAX_SIZE+1;
    }

    //Method for shuffling the input string
    //Random rand will generate a number with range of 0 to input string length (0, 6)
    //The logic of this method is just like Insertion sort but instead of sorting, its shuffle
    private void shuffleString(Random rand, String str){
        char[] convert = str.toCharArray();

        for(int i = 0; i < MAX_SIZE; i++){
            int j = rand.nextInt(MAX_SIZE);
            char temp = convert[i];
            convert[i] = convert[j];
            convert[j] = temp;
        }
        str = new String(convert);
        String[] newStr = str.split("");

        for(int i = 0; i < MAX_SIZE; i++){
            btnShuffle[i].setText(newStr[i+1]);
        }
    }

    private void isWinner(String answer, String str){
        if(answer.equals(str)){
            testing.setText("CONGRATULATIONS!");
        }
    }

    private void resetLifeBar(){
        for(int i = 0; i < 5; i++){
            btnLife[i].setBackgroundResource(R.color.black);
            checkLife[i] = true;
        }
    }

    private void playerLife(){

    }
}