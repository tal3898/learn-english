package com.taban.learnenglish.adpters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.daimajia.swipe.adapters.ArraySwipeAdapter;
import com.taban.learnenglish.R;
import com.taban.learnenglish.models.Word;

import java.util.List;

public class WordsListAdapter extends ArraySwipeAdapter<Word> {

    private List<Word> referenceCopy;

    public WordsListAdapter(Context context, List<Word> words) {
        super(context, R.layout.word_item, words);
        this.referenceCopy = words;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View wordItem = inflater.inflate(R.layout.word_item, parent, false);

        final Word word = (Word) getItem(position);
        TextView wordTextView = wordItem.findViewById(R.id.word);
        TextView definitionTextView = wordItem.findViewById(R.id.definition);

        wordTextView.setText(word.getWord());
        definitionTextView.setText(word.getDefinition());

        wordItem.findViewById(R.id.testbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAL", "clicked btn " + word.getWord());

                referenceCopy.remove(word);

                notifyDataSetChanged();
            }
        });

        return wordItem;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }
}
