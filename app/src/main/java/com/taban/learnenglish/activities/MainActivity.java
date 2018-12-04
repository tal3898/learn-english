package com.taban.learnenglish.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.taban.learnenglish.R;
import com.taban.learnenglish.models.Word;
import com.taban.learnenglish.utilities.WordsManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    WordsManager wordsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wordsManager = new WordsManager();
    }

    public void changeToLearningLayout(View view) {
        Intent wordsToMemorizeActivityIntent = new Intent(this, WordsToMemorizeActivity.class);
        startActivity(wordsToMemorizeActivityIntent);
    }

    public void changeToMemorizedWordsLayout(View view) {
        Intent wordsToMemorizeActivityIntent = new Intent(this, MemorizedWordsActivity.class);
        startActivity(wordsToMemorizeActivityIntent);
    }

    public void changeToExamLayout(View view) {
        Intent examActivityIntent = new Intent(this, ExamActivity.class);

        // Send to the activity the words for the exam - The words the user has memorized +
        // the words he memorizes now
        List<Word> wordsForTheExam= new ArrayList<>(wordsManager.getNewWordsToMemorize());
        //wordsForTheExam.addAll(wordsManager.getMemorizedWords());
        examActivityIntent.putExtra("wordsToExam", (Serializable) wordsForTheExam);


        startActivity(examActivityIntent);
    }
}
