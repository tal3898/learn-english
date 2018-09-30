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

    Handler handler;
    Runnable wordsMemorizeRunnable;

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
        handler = new Handler();

        // Start display the words randomly
        wordsMemorizer.prepare();
        wordsMemorizeRunnable = new Runnable() {
            @Override
            public void run() {
                wordsMemorizer.displayAndPlayRandomWord();
                handler.postDelayed(this, WORD_PLAY_INTERVAL);
            }

        };

        handler.postDelayed(wordsMemorizeRunnable, WORD_PLAY_DELAY);


    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(wordsMemorizeRunnable);
    }



}
