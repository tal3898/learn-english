package com.taban.learnenglish.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.taban.learnenglish.R;
import com.taban.learnenglish.utilities.Globals;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Globals.initVariables(this);
    }

    public void changeToLearningLayout(View view) {
        Intent wordsToMemorizeActivityIntent = new Intent(this, WordsToMemorizeActivity.class);
        startActivity(wordsToMemorizeActivityIntent);
    }
}
