package com.taban.learnenglish.utilities;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.taban.learnenglish.activities.LearnEnglishApplication;
import com.taban.learnenglish.models.Word;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The class is responsible for the part of memorizing the words in the application:
 * show the words randomly and play their audio.
 */
public class WordsMemorizer {

    // DM
    private List<Word> wordsToMemorize;
    private Word prevPlayedWord;
    private WordsAudioManager wordsAudioManager;
    private Random random;

    private TextView wordTextView;
    private TextView definitionTextView;

    // CTRO
    public WordsMemorizer(TextView wordTextView, TextView definitionTextView, List<Word> wordsToMemorize) {
        this.wordsToMemorize = wordsToMemorize;
        this.wordTextView = wordTextView;
        this.definitionTextView = definitionTextView;

        this.wordsAudioManager = new WordsAudioManager();
        this.random = new Random();
    }

    // Methods
    private void playAudioWord(Word wordToDisplay) {
        MediaPlayer mediaPlayer = wordsAudioManager.getWordMediaPlayer(wordToDisplay);

        mediaPlayer.start();
    }

    private Word getRandomWord() {
        int wordIndex = random.nextInt(wordsToMemorize.size());
        Word randomWord = wordsToMemorize.get(wordIndex);

        return randomWord;
    }

    private Word popRandomWord() {
        Word randomWord = getRandomWord();
        this.wordsToMemorize.remove(randomWord);

        return randomWord;
    }

    public void displayAndPlayRandomWord() {
        // Pop a word and insert back the prev popped word
        Word randomWord = popRandomWord();
        wordsToMemorize.add(prevPlayedWord);
        prevPlayedWord = randomWord;

        // Display and play the word
        wordTextView.setText(randomWord.getWord());
        definitionTextView.setText(randomWord.getDefinition());
        wordsAudioManager.getWordMediaPlayer(randomWord).start();
    }
}
