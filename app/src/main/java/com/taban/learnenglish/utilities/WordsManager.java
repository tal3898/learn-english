package com.taban.learnenglish.utilities;

import com.taban.learnenglish.models.Difficulty;
import com.taban.learnenglish.models.Word;

import java.util.ArrayList;
import java.util.List;

public class WordsManager {
    public List<Word> newWordsToMemorize;

    public WordsManager() {
        this.newWordsToMemorize = new ArrayList<>();
    }

    // Methods
    public void loadAllWords() {
        this.newWordsToMemorize.add(new Word("Hello", "shalom", new ArrayList<String>(), null, Difficulty.BEGINNER));
        this.newWordsToMemorize.add(new Word("Hello", "shalom", new ArrayList<String>(), null, Difficulty.BEGINNER));
        this.newWordsToMemorize.add(new Word("Hello", "shalom", new ArrayList<String>(), null, Difficulty.BEGINNER));
        this.newWordsToMemorize.add(new Word("Hello", "shalom", new ArrayList<String>(), null, Difficulty.BEGINNER));
        this.newWordsToMemorize.add(new Word("Hello", "shalom", new ArrayList<String>(), null, Difficulty.BEGINNER));
    }
}
