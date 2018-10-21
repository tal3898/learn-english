package com.taban.learnenglish.adpters;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.taban.learnenglish.R;
import com.taban.learnenglish.activities.LearnEnglishApplication;
import com.taban.learnenglish.activities.SpecificWordActivity;
import com.taban.learnenglish.models.Word;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemorizedWordsListAdapter extends ArrayAdapter<Word> {

    private List<Word> filteredWords;
    private List<Word> memorizedWordsOriginal;
    private Filter wordsFilter;

    public MemorizedWordsListAdapter(List<Word> memorizedWords) {
        super(LearnEnglishApplication.getAppContext(), R.layout.memorized_word_item, memorizedWords);
        this.filteredWords = new ArrayList<>(memorizedWords);
        this.memorizedWordsOriginal = new ArrayList<>(memorizedWords);
        this.initFilter();
    }

    public void initFilter() {
        wordsFilter = new Filter() {
            @Override
            public CharSequence convertResultToString(Object resultValue) {
                Word wordObj = (Word) resultValue;
                return wordObj.getWord() + " " + wordObj.getDefinition();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                // Filter the words
                filteredWords.clear();

                // If the filter text is empty
                if (constraint != null && constraint.length() == 0) {
                    filteredWords.addAll(memorizedWordsOriginal);
                } else {

                    // Go over all the words and check which one fits to the wordsFilter.
                    for (Word w : memorizedWordsOriginal) {

                        // If the word contains the constraint (in non case sensitive)
                        // or the definition contains the constraint (because the definition is in hebrew there
                        // is no need to toLower
                        if (w.getDefinition().contains(constraint.toString()) ||
                                w.getWord().toLowerCase().contains(constraint.toString().toLowerCase())) {

                            filteredWords.add(w);
                        }
                    }
                }

                // Define the wordsFilter result object
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredWords;
                filterResults.count = filteredWords.size();
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                List<Word> filteredWordsList =  (ArrayList<Word> )results.values ;

                clear();

                // If the result is not empty
                if (results != null && results.count > 0) {
                    for (Word currentFilteredWord : filteredWordsList) {
                        add(currentFilteredWord);
                    }
                }

                notifyDataSetChanged();
            }
        };
    }

    @Override
    public Filter getFilter() {
        return this.wordsFilter;
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
