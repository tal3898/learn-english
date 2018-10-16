package com.taban.learnenglish.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.taban.learnenglish.R;
import com.taban.learnenglish.models.Word;
import com.taban.learnenglish.utilities.Globals;

public class SpecificWordActivity extends AppCompatActivity {

    private Word word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_word);

        // setup the tool bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get the clicked word
        word = (Word) getIntent().getSerializableExtra("wordData");

        // init the textviews according the word data
        TextView wordTextView = (TextView) findViewById(R.id.spesificWord);
        TextView definitionTextView = (TextView) findViewById(R.id.spesificWordDefinition);
        TextView examplesTextView = (TextView) findViewById(R.id.spesificWordExamples);

        wordTextView.setText(word.getWord());
        definitionTextView.setText(word.getDefinition());

        StringBuilder builder = new StringBuilder();
        for(String s : word.getExamples()) {
            builder.append(s).append("\r\n");
        }

        examplesTextView.setText(builder.toString());
    }

    public void playWord(View view) {
        Globals.wordsAudioManager.getWordMediaPlayer(word).start();
        // test 3
        // test 3
    }

}
