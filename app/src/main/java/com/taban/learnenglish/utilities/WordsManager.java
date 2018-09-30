package com.taban.learnenglish.utilities;

import android.annotation.TargetApi;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.taban.learnenglish.R;
import com.taban.learnenglish.activities.MainActivity;
import com.taban.learnenglish.models.Difficulty;
import com.taban.learnenglish.models.Word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordsManager {
    public List<Word> newWordsToMemorize;
    public List<Word> memorizedWords;

    public Map<Difficulty,List<Word>> wordsNotMemorizedYet;
    public Map<Difficulty,List<Word>> deletedWords;

    public WordsManager() {
        this.newWordsToMemorize = new ArrayList<>();
        this.memorizedWords = new ArrayList<>();
        this.wordsNotMemorizedYet = new HashMap<>();
        this.deletedWords = new HashMap<>();
    }

    // Access Methods
    public List<Word> getNewWordsToMemorize() {
        return this.newWordsToMemorize;
    }

    // Methods
    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void loadAllWords() {
        this.newWordsToMemorize.add(new Word("collaborate", "כלב", new ArrayList<String>(), null, Difficulty.BEGINNER));
        this.newWordsToMemorize.add(new Word("desirable", "חתול", new ArrayList<String>(), null, Difficulty.BEGINNER));
        this.newWordsToMemorize.add(new Word("evacuation", "אמא", new ArrayList<String>(), null, Difficulty.BEGINNER));
        this.newWordsToMemorize.add(new Word("expectation", "אבא", new ArrayList<String>(), null, Difficulty.BEGINNER));
        this.newWordsToMemorize.add(new Word("interference", "אח", new ArrayList<String>(), null, Difficulty.BEGINNER));

        FileWriter fileWriter = new FileWriter();
        fileWriter.writeObjectIntoFile(this.newWordsToMemorize, "newWordsToMemorize.dat");

        //FileReader fileReader = new FileReader();
        //this.newWordsToMemorize = (List<Word>) fileReader.readObjectFromFile("wordsData.dat");
        //this.memorizedWords = (List<Word>) fileReader.readObjectFromFile("memorizedWords.dat");
        //this.wordsNotMemorizedYet = (Map<Difficulty, List<Word>>) fileReader.readObjectFromFile("wordsNotMemorizedYet.dat");
        //this.deletedWords = (Map<Difficulty, List<Word>>) fileReader.readObjectFromFile("deletedWords.dat");


    }


}
