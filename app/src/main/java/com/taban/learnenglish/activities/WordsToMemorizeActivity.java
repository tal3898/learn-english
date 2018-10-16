package com.taban.learnenglish.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import com.taban.learnenglish.R;
import com.taban.learnenglish.SettingsActivity;
import com.taban.learnenglish.adpters.WordsListAdapter;
import com.taban.learnenglish.utilities.WordsManager;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.shareBtn) {
            // launch settings activity
            startActivity(new Intent(LearnEnglishApplication.getAppContext(), SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
