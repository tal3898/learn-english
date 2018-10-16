package com.taban.learnenglish.adpters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.taban.learnenglish.R;
import com.taban.learnenglish.activities.LearnEnglishApplication;
import com.taban.learnenglish.activities.SpecificWordActivity;
import com.taban.learnenglish.models.Word;

import java.io.Serializable;
import java.util.List;

public class MemorizedWordsListAdapter extends ArrayAdapter<Word> {

    public MemorizedWordsListAdapter(List<Word> memorizedWords) {
        super(LearnEnglishApplication.getAppContext(), R.layout.memorized_word_item, memorizedWords);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View wordItem = inflater.inflate(R.layout.memorized_word_item, parent, false);
        final Word currentWordItem = (Word) getItem(position);

        // set the item text
        TextView wordTextView = wordItem.findViewById(R.id.word);
        TextView definitionTextView = wordItem.findViewById(R.id.definition);
        wordTextView.setText(currentWordItem.getWord());
        definitionTextView.setText(currentWordItem.getDefinition());

        // define the info button
        wordItem.findViewById(R.id.wordInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wordsPlayerActivityIntent = new Intent(LearnEnglishApplication.getAppContext(), SpecificWordActivity.class);
                wordsPlayerActivityIntent.putExtra("wordData", (Serializable) currentWordItem);
                wordsPlayerActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                LearnEnglishApplication.getAppContext().startActivity(wordsPlayerActivityIntent);
            }

        });

        return wordItem;
    }
}
