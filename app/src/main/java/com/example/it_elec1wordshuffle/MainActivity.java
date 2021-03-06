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

        Objects.requireNonNull(getSupportActionBar()).hide();

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
        txtLife = findViewById(R.id.txtLife);

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
    TextView txtLife;
    EditText getWordtoGuess;
    EditText txtAnswer;
    TextView testing;
    Random rand = new Random();
    int play = 0;
    final int MAX_SIZE = 6;
    String answer = "";
    String strInput = "";
    int life = 5;
    int input = 0;

    @Override
    public void onClick(View view) {
        String str = getWordtoGuess.getText().toString();
        strInput = str;

        if(view.getId() == R.id.btnPlay){
            if(lengthChecker()){
                play = 1;
                shuffleString(rand, str);
                resetLifeBar();
                answer = "";
                life = 5;
                input = 0;
                txtLife.setText(String.valueOf(life));
                testing.setText("");
            }
            else{
                //ERROR MESSAGE
                testing.setText("Input word must be 6 letters");
            }
        }
        else if(view.getId() == R.id.btnShuffle0 && play == 1){
            answer += btnShuffle[0].getText().toString();
            btnShuffle[0].setEnabled(false);
        }
        else if(view.getId() == R.id.btnShuffle1 && play == 1){
            answer += btnShuffle[1].getText().toString();
            btnShuffle[1].setEnabled(false);
        }
        else if(view.getId() == R.id.btnShuffle2 && play == 1){
            answer += btnShuffle[2].getText().toString();
            btnShuffle[2].setEnabled(false);
        }
        else if(view.getId() == R.id.btnShuffle3 && play == 1){
            answer += btnShuffle[3].getText().toString();
            btnShuffle[3].setEnabled(false);
        }
        else if(view.getId() == R.id.btnShuffle4 && play == 1){
            answer += btnShuffle[4].getText().toString();
            btnShuffle[4].setEnabled(false);
        }
        else if(view.getId() == R.id.btnShuffle5 && play == 1){
            answer += btnShuffle[5].getText().toString();
            btnShuffle[5].setEnabled(false);
        }

        checkInput();
        txtAnswer.setText(answer);
    }

    //Method for checking if the string length is equal to 6 and if not will prompt a
    //warning to alert the user to correct his/her input
    private boolean lengthChecker(){
        String newStr = getWordtoGuess.getText().toString().trim();
        return newStr.length() == MAX_SIZE;
    }

    //Method for shuffling the input string
    //Random rand will generate a number with range of 0 to input string length (0, 6)
    //The logic of this method is like Insertion sort but instead of sorting, its shuffle
    private void shuffleString(Random rand, String str){
        char[] convert = str.toCharArray();

        for(int i = 0; i < MAX_SIZE; i++){
            int j = rand.nextInt(MAX_SIZE);
            char temp = convert[i];
            convert[i] = convert[j];
            convert[j] = temp;
        }
        str = new String(convert).trim();

        for(int i = 0; i < MAX_SIZE; i++){
            btnShuffle[i].setText(String.valueOf(str.charAt(i)));
        }
    }

    private void isWinner(String answer, String str) {
        if(answer.equals(str) && !str.equals("")){
            testing.setText("CONGRATS");
            resetButtonText();
            play = 0;
        }
        else if(play == 1){
            life--;
            input = 1;
            txtLife.setText(String.valueOf(life));
            updateLife(life);

            if(life == 0){
                testing.setText("GAME OVER");
                resetButtonText();
                play = 0;
            }
        }
    }

    private void checkInput(){
        if(input == 6){
            isWinner(answer, strInput);
            answer = "";
            resetButtons();
        }
        else if(input < 6 && !strInput.equals("")){
            input++;
        }
    }

    private void resetButtonText(){
        for(int i = 0; i < MAX_SIZE; i++){
            btnShuffle[i].setText(" ");
        }
    }

    private void resetButtons(){
        for(int i = 0; i < MAX_SIZE; i++){
            btnShuffle[i].setEnabled(true);
        }
    }

    private void resetLifeBar(){
        for(int i = 0; i < 5; i++){
            btnLife[i].setBackgroundColor(getResources().getColor(R.color.black));
        }
    }

    private void updateLife(int life){
        btnLife[life].setBackgroundColor(getResources().getColor(R.color.white));
    }
}