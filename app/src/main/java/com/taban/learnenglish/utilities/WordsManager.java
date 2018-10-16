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
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class WordsManager {

    //DM
    public List<Word> newWordsToMemorize;
    public List<Word> memorizedWords;
    public List<Word> deletedWords;

    public Map<Difficulty, List<Word>> wordsNotMemorizedYet;


    public WordsManager() {
        this.newWordsToMemorize = new ArrayList<>();
        this.memorizedWords = new ArrayList<>();
        this.deletedWords = new ArrayList<>();

        this.wordsNotMemorizedYet = new HashMap<>();

        for (Difficulty difficulty : Difficulty.values()) {
            this.wordsNotMemorizedYet.put(difficulty, new ArrayList<Word>());
        }

    }

    // Assist method before creating the words files
    public void createWordsNotMemorizedYet() {
        for (Difficulty difficulty : Difficulty.values()) {
            this.wordsNotMemorizedYet.put(difficulty, new ArrayList<Word>());
        }

        this.wordsNotMemorizedYet.put(Difficulty.BEGINNER,  new ArrayList<Word>(Arrays.asList(new Word("obstruction", "a", new ArrayList<String>(), null, Difficulty.BEGINNER),
                new Word("protest", "b", new ArrayList<String>(), null, Difficulty.BEGINNER),
                new Word("controversial", "c", new ArrayList<String>(), null, Difficulty.BEGINNER),
                new Word("preface", "d", new ArrayList<String>(), null, Difficulty.BEGINNER),
                new Word("appetite", "e", new ArrayList<String>(), null, Difficulty.BEGINNER),
                new Word("mansion", "f", new ArrayList<String>(), null, Difficulty.BEGINNER)
        )));

        this.wordsNotMemorizedYet.put(Difficulty.NORMAL, new ArrayList<Word>(Arrays.asList(new Word("captain", "a", new ArrayList<String>(), null, Difficulty.BEGINNER),
                new Word("integrity", "b", new ArrayList<String>(), null, Difficulty.BEGINNER),
                new Word("aggression", "c", new ArrayList<String>(), null, Difficulty.BEGINNER),
                new Word("forget", "d", new ArrayList<String>(), null, Difficulty.BEGINNER),
                new Word("calculate", "e", new ArrayList<String>(), null, Difficulty.BEGINNER),
                new Word("scold", "f", new ArrayList<String>(), null, Difficulty.BEGINNER)
        )));

        this.wordsNotMemorizedYet.put(Difficulty.EXPERT, new ArrayList<Word>(Arrays.asList(new Word("tickle", "a", new ArrayList<String>(), null, Difficulty.BEGINNER),
                new Word("thanks to", "b", new ArrayList<String>(), null, Difficulty.BEGINNER),
                new Word("recycle", "c", new ArrayList<String>(), null, Difficulty.BEGINNER),
                new Word("inwards", "d", new ArrayList<String>(), null, Difficulty.BEGINNER),
                new Word("pride", "e", new ArrayList<String>(), null, Difficulty.BEGINNER),
                new Word("sway", "f", new ArrayList<String>(), null, Difficulty.BEGINNER)
        )));
    }

    public void createDeletedWords() {
        // when the this.deletedWords was a map
        /*        for (Difficulty difficulty : Difficulty.values()) {
            this.deletedWords.put(difficulty, new ArrayList<Word>());
        }

        this.deletedWords.put(Difficulty.BEGINNER, Arrays.asList(new Word("forbidden", "a", new ArrayList<String>(), null, Difficulty.BEGINNER),
                new Word("apparatus", "b", new ArrayList<String>(), null, Difficulty.BEGINNER),
                new Word("merchant", "c", new ArrayList<String>(), null, Difficulty.BEGINNER),
                new Word("lure", "d", new ArrayList<String>(), null, Difficulty.BEGINNER),
                new Word("collaborate", "e", new ArrayList<String>(), null, Difficulty.BEGINNER),
                new Word("rescue", "f", new ArrayList<String>(), null, Difficulty.BEGINNER)
        ));

        this.deletedWords.put(Difficulty.NORMAL, Arrays.asList(new Word("compassion", "a", new ArrayList<String>(), null, Difficulty.BEGINNER),
                new Word("luxury", "b", new ArrayList<String>(), null, Difficulty.BEGINNER),
                new Word("transmit", "c", new ArrayList<String>(), null, Difficulty.BEGINNER),
                new Word("under", "d", new ArrayList<String>(), null, Difficulty.BEGINNER),
                new Word("exactly", "e", new ArrayList<String>(), null, Difficulty.BEGINNER),
                new Word("matriarch", "f", new ArrayList<String>(), null, Difficulty.BEGINNER)
        ));

        this.deletedWords.put(Difficulty.EXPERT, Arrays.asList(new Word("though", "a", new ArrayList<String>(), null, Difficulty.BEGINNER),
                new Word("object", "b", new ArrayList<String>(), null, Difficulty.BEGINNER),
                new Word("simplify", "c", new ArrayList<String>(), null, Difficulty.BEGINNER),
                new Word("regular", "d", new ArrayList<String>(), null, Difficulty.BEGINNER),
                new Word("prohibition", "e", new ArrayList<String>(), null, Difficulty.BEGINNER),
                new Word("addict", "f", new ArrayList<String>(), null, Difficulty.BEGINNER)
        ));*/

        this.deletedWords.add(new Word("though", "a", new ArrayList<String>(), null, Difficulty.BEGINNER));
        this.deletedWords.add(new Word("object", "b", new ArrayList<String>(), null, Difficulty.BEGINNER));
        this.deletedWords.add(new Word("simplify", "c", new ArrayList<String>(), null, Difficulty.BEGINNER));
        this.deletedWords.add(new Word("regular", "d", new ArrayList<String>(), null, Difficulty.BEGINNER));
        this.deletedWords.add(new Word("prohibition", "e", new ArrayList<String>(), null, Difficulty.BEGINNER));
        this.deletedWords.add(new Word("addict", "f", new ArrayList<String>(), null, Difficulty.BEGINNER));
    }

    public void createMemorizedWords() {
        this.memorizedWords.add(new Word("plenty", "הרבה", new ArrayList<String>(), null, Difficulty.BEGINNER));
        this.memorizedWords.add(new Word("organization", "ארגון", new ArrayList<String>(), null, Difficulty.BEGINNER));
    }

    public void createNewWordsToMemorize() {
        List<String> examples = new ArrayList<String>();
        examples.add("I need to finish this app!!!");
        examples.add("I need to finish this app!!!");
        examples.add("I need to finish this app!!!");
        examples.add("I need to finish this app!!!");
        this.newWordsToMemorize.add(new Word("collaborate", "כלב", examples, null, Difficulty.BEGINNER));
        this.newWordsToMemorize.add(new Word("desirable", "חתול", new ArrayList<String>(), null, Difficulty.BEGINNER));
        this.newWordsToMemorize.add(new Word("evacuation", "אמא", new ArrayList<String>(), null, Difficulty.BEGINNER));
        this.newWordsToMemorize.add(new Word("expectation", "אבא", new ArrayList<String>(), null, Difficulty.BEGINNER));
        this.newWordsToMemorize.add(new Word("interference", "אח", new ArrayList<String>(), null, Difficulty.BEGINNER));
    }

    // Access Methods
    public List<Word> getNewWordsToMemorize() {
        return this.newWordsToMemorize;
    }

    // Methods

    /**
     * The method loads all the words lists from the files
     */
    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void loadAllWords() {
        this.createDeletedWords();
        this.createMemorizedWords();
        this.createNewWordsToMemorize();
        this.createWordsNotMemorizedYet();

        FileWriter fileWriter = new FileWriter();
        fileWriter.writeObjectIntoFile(this.newWordsToMemorize, "newWordsToMemorize.dat");

        //FileReader fileReader = new FileReader();
        //this.newWordsToMemorize = (List<Word>) fileReader.readObjectFromFile("wordsData.dat");
        //this.memorizedWords = (List<Word>) fileReader.readObjectFromFile("memorizedWords.dat");
        //this.wordsNotMemorizedYet = (Map<Difficulty, List<Word>>) fileReader.readObjectFromFile("wordsNotMemorizedYet.dat");
        //this.deletedWords = (Map<Difficulty, List<Word>>) fileReader.readObjectFromFile("deletedWords.dat");


    }

    /**
     * The method calls the method removeNewWordToAListAndAddNewWordToMemorize with the
     * memorized words list parameter
     * @param word - The word to memorize
     */
    public void memorizeWord(Word word) {
        removeNewWordToAListAndAddNewWordToMemorize(word, this.memorizedWords);
    }

    /**
     * The method calls the method removeNewWordToAListAndAddNewWordToMemorize with the
     * deleted words list parameter
     * @param word - The word to delete
     */
    public void deleteWordTheUserAlreadyKnow(Word word) {
        removeNewWordToAListAndAddNewWordToMemorize(word, this.deletedWords);
    }

    /**
     * The method remove a word from the list of the words the user need to memorize, put
     * it in the given list (usualy deleted words list/ memorized words list), put a new
     * word to memorize
     * @param word - The word to remove
     * @param wordsList - The list to put in the removing word
     */
    private void removeNewWordToAListAndAddNewWordToMemorize(Word word, List<Word> wordsList) {
        this.newWordsToMemorize.remove(word);
        wordsList.add(word);

        if (this.wordsNotMemorizedYet.get(Globals.getUserChosenDifficulty()).size() > 0) {
            Word newWordToMemorize = getRandomNotMemorizedWord();
            this.newWordsToMemorize.add(newWordToMemorize);
            this.wordsNotMemorizedYet.get(Globals.getUserChosenDifficulty()).remove(newWordToMemorize);
        }
    }

    /**
     * The method gets a random word from the list of words which were not memorized yet (in the lever
     * the user chose in the beginning)
     * @return A random word
     */
    private Word getRandomNotMemorizedWord() {
        Random random = new Random();
        int wordIndex = random.nextInt(this.wordsNotMemorizedYet.get(Globals.getUserChosenDifficulty()).size());
        return this.wordsNotMemorizedYet.get(Globals.getUserChosenDifficulty()).get(wordIndex);
    }


}
