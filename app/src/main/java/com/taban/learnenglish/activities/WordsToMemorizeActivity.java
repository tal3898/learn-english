package com.taban.learnenglish.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.taban.learnenglish.R;
import com.taban.learnenglish.adpters.WordsListAdapter;
import com.taban.learnenglish.utilities.WordsManager;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class WordsToMemorizeActivity extends AppCompatActivity {

    private ListView wordsListView;
    private WordsManager wordsManager;
    private FloatingActionButton wordPlayBtn;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_to_memorize);

        // Set the tool bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Define the list view and its values
        wordsManager = new WordsManager();
        wordsManager.loadAllWords();

        ListAdapter wordsListAdapter = new WordsListAdapter(this,wordsManager.newWordsToMemorize);

        wordsListView = (ListView)findViewById(R.id.wordsListView);
        wordsListView.setAdapter(wordsListAdapter);

        // Initi the floating button
        wordPlayBtn = (FloatingActionButton) findViewById(R.id.words_play_btn);

        //MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.pardon);
        //mediaPlayer.start(); // no need to call prepare(); create() does that for you
/*
        try {
            int resId = getResources().getIdentifier("collaborate", "raw", getPackageName());
            MediaPlayer mediaPlayer = MediaPlayer.create(this, resId);
            mediaPlayer.start();
        } catch (Exception e) {
            Log.e("TAL", "could not play",e);
        }*/
    }

    public void playMyWords(View view) {
        Intent wordsPlayerActivityIntent = new Intent(this, WordPlayerActivity.class);
        wordsPlayerActivityIntent.putExtra("wordsToMemorize", (Serializable) wordsManager.getNewWordsToMemorize());
        startActivity(wordsPlayerActivityIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
