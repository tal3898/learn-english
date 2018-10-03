package com.taban.learnenglish.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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
import com.taban.learnenglish.models.Word;
import com.taban.learnenglish.utilities.WordsManager;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class WordsToMemorizeActivity extends AppCompatActivity {

    private ListView wordsListView;
    private WordsListAdapter wordsListAdapter;

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

        wordsListAdapter = new WordsListAdapter(this,wordsManager.newWordsToMemorize, wordsManager);

        wordsListView = (ListView)findViewById(R.id.wordsListView);
        wordsListView.setAdapter(wordsListAdapter);



        // Create the swipe menu in the list view
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "Memorized" item
                SwipeMenuItem memorizedItem = new SwipeMenuItem(
                        getApplicationContext());

                memorizedItem.setBackground(R.color.memorizedWordItem);
                memorizedItem.setWidth(270);
                memorizedItem.setTitle("O");
                memorizedItem.setTitleSize(18);
                memorizedItem.setTitleColor(Color.BLACK);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                deleteItem.setBackground(R.color.deleteWordItem);
                deleteItem.setWidth(270);
                deleteItem.setIcon(R.drawable.ic_settings_wheel);

                // add items to menu
                menu.addMenuItem(memorizedItem);
                menu.addMenuItem(deleteItem);
            }
        };



        // Initi the floating button
        wordPlayBtn = (FloatingActionButton) findViewById(R.id.words_play_btn);
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
