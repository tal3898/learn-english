package com.taban.learnenglish.utilities;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

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
    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void loadAllWords() {
/*        this.newWordsToMemorize.add(new Word("Dog", "כלב", new ArrayList<String>(), null, Difficulty.BEGINNER));
        this.newWordsToMemorize.add(new Word("Cat", "חתול", new ArrayList<String>(), null, Difficulty.BEGINNER));
        this.newWordsToMemorize.add(new Word("Mom", "אמא", new ArrayList<String>(), null, Difficulty.BEGINNER));
        this.newWordsToMemorize.add(new Word("Dad", "אבא", new ArrayList<String>(), null, Difficulty.BEGINNER));
        this.newWordsToMemorize.add(new Word("Bro", "אח", new ArrayList<String>(), null, Difficulty.BEGINNER));

        FileWriter fileWriter = new FileWriter();
        fileWriter.writeObjectIntoFile(this.newWordsToMemorize, "wordsData.dat");*/

        loadWordsToMemorize();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void loadWordsToMemorize() {
        FileReader fileReader = new FileReader();
        this.newWordsToMemorize = (List<Word>) fileReader.readObjectFromFile("wordsData.dat");
    }
}
