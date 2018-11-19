package com.taban.learnenglish.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.taban.learnenglish.R;
import com.taban.learnenglish.models.Word;
import com.taban.learnenglish.utilities.WordsManager;

import java.util.List;

public class ExamActivity extends AppCompatActivity {

    WordsManager wordsManager;
    List<Word> wordsToExam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        // Get the words to exam the user
        wordsToExam= (List<Word>) getIntent().getSerializableExtra("wordsToExam");

        TextView word = findViewById(R.id.TestedWordTxt);
        word.setText(wordsToExam.get(0).getWord());
    }
}
