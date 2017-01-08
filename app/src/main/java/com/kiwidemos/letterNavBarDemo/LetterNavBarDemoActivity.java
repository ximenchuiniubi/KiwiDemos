package com.kiwidemos.letterNavBarDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kiwi.library.widget.LetterNavBar;
import com.kiwidemos.R;

public class LetterNavBarDemoActivity extends AppCompatActivity {
    LetterNavBar letterNavBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_nav_bar_demo);
        letterNavBar = (LetterNavBar) findViewById(R.id.letter_bar);
        letterNavBar.setLetters("123456");
    }
}
