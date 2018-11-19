package com.taban.learnenglish.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.taban.learnenglish.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeToLearningLayout(View view) {
        Intent wordsToMemorizeActivityIntent = new Intent(this, WordsToMemorizeActivity.class);
        startActivity(wordsToMemorizeActivityIntent);
    }

    public void changeToMemorizedWordsLayout(View view) {
        Intent wordsToMemorizeActivityIntent = new Intent(this, MemorizedWordsActivity.class);
        startActivity(wordsToMemorizeActivityIntent);
    }

    public void changeToExamLayout(View view) {
        Intent examActivityIntent = new Intent(this, ExamActivity.class);
        startActivity(examActivityIntent);
    }
}
