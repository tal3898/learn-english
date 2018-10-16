package com.taban.learnenglish.adpters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.taban.learnenglish.R;
import com.taban.learnenglish.activities.LearnEnglishApplication;
import com.taban.learnenglish.models.Word;

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

        TextView wordTextView = wordItem.findViewById(R.id.w1);
        TextView definitionTextView = wordItem.findViewById(R.id.w2);
        wordTextView.setText(currentWordItem.getWord());
        definitionTextView.setText(currentWordItem.getDefinition());

        return wordItem;
    }
}
