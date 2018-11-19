package com.taban.learnenglish.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.taban.learnenglish.R;
import com.taban.learnenglish.models.Word;
import com.taban.learnenglish.models.WordExam;
import com.taban.learnenglish.utilities.WordsManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.logging.Logger;

public class ExamActivity extends AppCompatActivity {

    WordsManager wordsManager;
    List<Word> wordsToExam;
    Queue<WordExam> wordExamQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        // Get the words to exam the user
        wordsToExam= (List<Word>) getIntent().getSerializableExtra("wordsToExam");
        Log.i("TAL", String.valueOf(wordsToExam.size()));

        wordExamQueue = generateAllWordsExam(wordsToExam);

        start();
    }

    public void start() {
        WordExam curExam = wordExamQueue.poll();
        displayExam(curExam);

    }

    public void displayExam(WordExam exam) {
        TextView word = findViewById(R.id.TestedWordTxt);
        word.setText(exam.getWordToExam().getWord());

        Button option1Btn = findViewById(R.id.ExamOption1Btn);
        option1Btn.setText(exam.getOptions().get(0));

        Button option2Btn = findViewById(R.id.ExamOption2Btn);
        option2Btn.setText(exam.getOptions().get(1));

        Button option3Btn = findViewById(R.id.ExamOption3Btn);
        option3Btn.setText(exam.getOptions().get(2));

        Button option4Btn = findViewById(R.id.ExamOption4Btn);
        option4Btn.setText(exam.getOptions().get(3));
    }

    /**
     * The method generates a queue with the whole exam
     * @param wordsToTest - The words to test on the user
     * @return a queue with all the exams (exam for each word)
     */
    public Queue<WordExam> generateAllWordsExam(List<Word> wordsToTest) {
        Queue<WordExam> exams = new LinkedList<>();
        for(Word curWord : wordsToTest) {
            List<String> curWordExamOptions = getWordOptions(curWord, wordsToTest, 4);
            WordExam curWordExam = new WordExam(curWord, curWordExamOptions);
            exams.add(curWordExam);
        }

        return exams;
    }

    /**
     * The method generate a list of answer options (with the right one) for a word
     * @param word - The word to create for the answer options
     * @param wordsToTest - The list of words to test on the user
     * @param numOfOptions - The number of options for the exam
     * @return A list of options for a definition for the given word.
     */
    private List<String> getWordOptions(Word word, List<Word> wordsToTest, int numOfOptions) {
        List<String> wordOptions = new ArrayList<>();
        wordOptions.add(word.getDefinition());

        List<Word> copyWordsToTest = new ArrayList<>(wordsToTest);

        Random rnd = new Random();
        for (int i = 0; i < numOfOptions; i++) {
            int randomWordIndex = rnd.nextInt(copyWordsToTest.size());
            Word randomWord = copyWordsToTest.get(randomWordIndex);
            String randomWordDefinition = randomWord.getDefinition();
            wordOptions.add(randomWordDefinition);
            copyWordsToTest.remove(randomWordIndex);
        }

        return wordOptions;
    }
}
