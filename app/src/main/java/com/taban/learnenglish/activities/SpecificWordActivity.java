package com.taban.learnenglish.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.taban.learnenglish.R;
import com.taban.learnenglish.models.Word;
import com.taban.learnenglish.utilities.Globals;

public class SpecificWordActivity extends AppCompatActivity {

    private Word activityWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_word);

        // setup the tool bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get the clicked activityWord
        activityWord = (Word) getIntent().getSerializableExtra("wordData");

        // init the textviews according the activityWord data
        TextView wordTextView = (TextView) findViewById(R.id.spesificWord);
        TextView definitionTextView = (TextView) findViewById(R.id.spesificWordDefinition);
        TextView examplesTextView = (TextView) findViewById(R.id.spesificWordExamples);

        wordTextView.setText(activityWord.getWord());
        definitionTextView.setText(activityWord.getDefinition());

        StringBuilder builder = new StringBuilder();
        for(String s : activityWord.getExamples()) {
            builder.append(s).append("\r\n");
        }

        examplesTextView.setText(builder.toString());

    }

    public void playWord(View view) {
        Globals.wordsAudioManager.getWordMediaPlayer(activityWord).start();
    }

}
