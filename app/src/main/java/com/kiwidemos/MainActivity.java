package com.kiwidemos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kiwidemos.letterNavBarDemo.LetterNavBarDemoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toLetterNavBarDemo(View view) {
        startActivity(new Intent(this, LetterNavBarDemoActivity.class));
    }
}
