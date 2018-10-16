package com.taban.learnenglish.activities;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.taban.learnenglish.R;
import com.taban.learnenglish.adpters.MemorizedWordsListAdapter;
import com.taban.learnenglish.models.Word;
import com.taban.learnenglish.utilities.WordsManager;

import java.util.ArrayList;
import java.util.List;

public class MemorizedWordsActivity extends AppCompatActivity {

    private WordsManager wordsManager;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorized_words);

        wordsManager = new WordsManager();
        wordsManager.loadAllWords();

        List<Word> memorizedWords = wordsManager.memorizedWords;

        MemorizedWordsListAdapter adapter = new MemorizedWordsListAdapter(memorizedWords);
        ListView listView = findViewById(R.id.memorizedWordsListView);
        listView.setAdapter(adapter);

    }
}
