package com.taban.learnenglish.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.taban.learnenglish.R;
import com.taban.learnenglish.utilities.WordsManager;

public class WordsToMemorizeActivity extends AppCompatActivity {

    private ListView wordsListView;
    private WordsManager wordsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_to_memorize);

        // Set the tool bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        wordsManager = new WordsManager();
        wordsManager.loadAllWords();

        String[] arr = {"tal", "ben"};
        ListAdapter wordsListAdapter = new WordsListAdapter(this,wordsManager.newWordsToMemorize);

        wordsListView = (ListView)findViewById(R.id.wordsListView);
        wordsListView.setAdapter(wordsListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
