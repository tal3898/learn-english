package com.taban.learnenglish.activities;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.taban.learnenglish.R;
import com.taban.learnenglish.models.Word;
import com.taban.learnenglish.utilities.WordsAudioManager;
import com.taban.learnenglish.utilities.WordsMemorizer;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class WordPlayerActivity extends AppCompatActivity {

    private static final int WORD_PLAY_INTERVAL = 4*1000;
    private static final int WORD_PLAY_DELAY = (int) (0.75*1000);


    private List<Word> wordsToMemorize;
    private WordsMemorizer wordsMemorizer;

    private TextView wordTextView;
    private TextView definitionTextView;

    TimerTask playerTask;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_player);

        // Init the textviews
        wordTextView = (TextView) findViewById(R.id.wordDisplay);
        definitionTextView = (TextView) findViewById(R.id.definitionDisplay);
        wordTextView.setText("");
        definitionTextView.setText("");

        // Init variables
        wordsToMemorize = (List<Word>) getIntent().getSerializableExtra("wordsToMemorize");
        wordsMemorizer = new WordsMemorizer(wordTextView, definitionTextView, wordsToMemorize);

        // Start display the words randomly

        playerTask = new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        wordsMemorizer.displayAndPlayRandomWord();
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



}
