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

        btn[0] = findViewById(R.id.btnShuffle1);
        btn[1] = findViewById(R.id.btnShuffle2);
        btn[2] = findViewById(R.id.btnShuffle3);
        btn[3] = findViewById(R.id.btnShuffle4);
        btn[4] = findViewById(R.id.btnShuffle5);
        btn[5] = findViewById(R.id.btnShuffle6);
        btnPlay = findViewById(R.id.btnPlay);
        getWordtoGuess = (EditText) findViewById(R.id.editText_wordToGuess);
        testing = findViewById(R.id.lblTESTING);

        btn[0].setOnClickListener(this);
        btn[1].setOnClickListener(this);
        btn[2].setOnClickListener(this);
        btn[3].setOnClickListener(this);
        btn[4].setOnClickListener(this);
        btn[5].setOnClickListener(this);
        btnPlay.setOnClickListener(this);
    }

    //Declarations
    static Button[] btn = new Button[6];
    Button[] life = new Button[5];
    Button btnPlay;
    EditText getWordtoGuess;
    TextView testing;
    Random rand = new Random();

    @Override
    public void onClick(View view) {
        String str = getWordtoGuess.getText().toString();

        if(view.getId() == R.id.btnPlay){
            testing.setText(str);
            shuffleString(rand, str);
        }
    }

    public static boolean lengthChecker(String[] array){
        return array.length == 6;
    }

    public static void shuffleString(Random rand, String str){
        char[] convert = str.toCharArray();

        for(int i = 0; i < convert.length; i++){
            int j = rand.nextInt(convert.length);
            char temp = convert[i];
            convert[i] = convert[j];
            convert[j] = temp;
            btn[i].setText(convert[i]);
        }
    }
}