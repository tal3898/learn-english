package com.taban.learnenglish.adpters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.daimajia.swipe.adapters.ArraySwipeAdapter;
import com.taban.learnenglish.R;
import com.taban.learnenglish.SpecificWordActivity;
import com.taban.learnenglish.activities.LearnEnglishApplication;
import com.taban.learnenglish.activities.WordPlayerActivity;
import com.taban.learnenglish.models.Word;
import com.taban.learnenglish.utilities.WordsManager;

import java.io.Serializable;
import java.util.List;

public class WordsListAdapter extends ArraySwipeAdapter<Word> {

    private WordsManager wordsManager;

    public WordsListAdapter(Context context, List<Word> words, WordsManager wordsManager) {
        super(context, R.layout.word_item, words);
        this.wordsManager = wordsManager;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View wordItem = inflater.inflate(R.layout.word_item, parent, false);

        final Word currentWordItem = (Word) getItem(position);
        TextView wordTextView = wordItem.findViewById(R.id.word);
        TextView definitionTextView = wordItem.findViewById(R.id.definition);

        wordTextView.setText(currentWordItem.getWord());
        definitionTextView.setText(currentWordItem.getDefinition());

        wordItem.findViewById(R.id.memorizedWordBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordsManager.memorizeWord(currentWordItem);
                notifyDataSetChanged();

                Log.i("TAL", "clicked btn " + currentWordItem.getWord());
            }
        });


        wordItem.findViewById(R.id.deleteWordBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordsManager.deleteWordTheUserAlreadyKnow(currentWordItem);
                notifyDataSetChanged();

                Log.i("TAL", "clicked btn " + currentWordItem.getWord());
            }
        });

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

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }
}
