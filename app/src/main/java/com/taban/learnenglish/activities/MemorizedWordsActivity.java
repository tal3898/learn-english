package com.taban.learnenglish.activities;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
    private EditText wordsFilter;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorized_words);

        wordsManager = new WordsManager();
        wordsManager.loadAllWords();

        // Init the listview
        List<Word> memorizedWords = wordsManager.memorizedWords;

        final MemorizedWordsListAdapter memorizedWordsAdapter = new MemorizedWordsListAdapter(memorizedWords);
        ListView listView = findViewById(R.id.memorizedWordsListView);
        listView.setAdapter(memorizedWordsAdapter);

        // Init the filter
        wordsFilter = (EditText) findViewById(R.id.memorizedWordsFilterEditText);
        wordsFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                memorizedWordsAdapter.getFilter().filter(s);
                memorizedWordsAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
