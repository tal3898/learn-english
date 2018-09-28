package com.taban.learnenglish.adpters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.taban.learnenglish.R;
import com.taban.learnenglish.models.Word;

import java.util.List;

public class WordsListAdapter extends ArrayAdapter<Word> {

    public WordsListAdapter(Context context, List<Word> words) {
        super(context, R.layout.word_item, words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View wordItem = inflater.inflate(R.layout.word_item, parent, false);

        Word word = getItem(position);
        TextView wordTextView = wordItem.findViewById(R.id.word);
        TextView definitionTextView = wordItem.findViewById(R.id.definition);

        wordTextView.setText(word.getWord());
        definitionTextView.setText(word.getDefinition());

        return wordItem;
    }

}
