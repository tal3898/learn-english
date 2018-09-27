package com.taban.learnenglish;

import android.content.Intent;
import android.provider.UserDictionary;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

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
}
