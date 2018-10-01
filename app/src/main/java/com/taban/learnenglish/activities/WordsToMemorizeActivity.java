package com.taban.learnenglish.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.taban.learnenglish.R;
import com.taban.learnenglish.adpters.WordsListAdapter;
import com.taban.learnenglish.utilities.WordsManager;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class WordsToMemorizeActivity extends AppCompatActivity {

    private SwipeMenuListView wordsListView;
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

        wordsListView = (SwipeMenuListView)findViewById(R.id.wordsListView);
        wordsListView.setAdapter(wordsListAdapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(170);
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_settings_wheel);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        // set creator
        wordsListView.setMenuCreator(creator);

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
