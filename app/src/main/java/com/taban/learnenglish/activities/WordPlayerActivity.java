package com.taban.learnenglish.activities;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.taban.learnenglish.R;
import com.taban.learnenglish.models.Word;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class WordPlayerActivity extends AppCompatActivity {

    private static final int WORD_PLAY_INTERVAL = 4*1000;
    private static final int WORD_PLAY_DELAY = (int) (0.75*1000);

    private Word prevPlayedWord;
    private List<Word> wordsToMemorize;

    private TextView wordTextView;
    private TextView definitionTextView;

    private Random random = new Random();
    TimerTask playerTask;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_player);

        wordsToMemorize = (List<Word>) getIntent().getSerializableExtra("wordsToMemorize");

        // Init the textviews
        wordTextView = (TextView) findViewById(R.id.wordDisplay);
        definitionTextView = (TextView) findViewById(R.id.definitionDisplay);
        wordTextView.setText("");
        definitionTextView.setText("");

        // Start display the words randomly
        prevPlayedWord = popRandomWord();

        playerTask = new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Word newWordDisplayed = getAndDisplayRandomWord();
                        wordsToMemorize.add(prevPlayedWord);
                        prevPlayedWord = newWordDisplayed;

                        playAudioWord(newWordDisplayed);
                    }
                });


            }
        };

        timer = new Timer();
        timer.scheduleAtFixedRate(playerTask, WORD_PLAY_DELAY, WORD_PLAY_INTERVAL);
    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }

    private void playAudioWord(Word newWordDisplayed) {
        int resId = getResources().getIdentifier(newWordDisplayed.getWord(), "raw", getPackageName());
        MediaPlayer mediaPlayer = MediaPlayer.create(this, resId);
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

    private Word getAndDisplayRandomWord() {
        Word randomWord = popRandomWord();
        wordTextView.setText(randomWord.getWord());
        definitionTextView.setText(randomWord.getDefinition());

        return randomWord;
    }
}
