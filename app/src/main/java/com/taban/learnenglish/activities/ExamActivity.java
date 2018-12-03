package com.taban.learnenglish.activities;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.taban.learnenglish.R;
import com.taban.learnenglish.models.Word;
import com.taban.learnenglish.models.WordExam;
import com.taban.learnenglish.utilities.Globals;
import com.taban.learnenglish.utilities.WordsManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.logging.Logger;

public class ExamActivity extends AppCompatActivity {

    private static final int TIME_TO_SHOW_ANSWER = 2000;

    WordsManager wordsManager;
    List<Word> wordsToExam;
    Queue<WordExam> wordExamQueue;
    Queue<WordExam> mistakenExams;
    WordExam currExam;

    TextView wordToExamTxt;
    Button option1Btn;
    Button option2Btn;
    Button option3Btn;
    Button option4Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        wordToExamTxt = findViewById(R.id.TestedWordTxt);
        option1Btn = findViewById(R.id.ExamOption1Btn);
        option2Btn = findViewById(R.id.ExamOption2Btn);
        option3Btn = findViewById(R.id.ExamOption3Btn);
        option4Btn = findViewById(R.id.ExamOption4Btn);

        // Get the words to exam the user
        wordsToExam= (List<Word>) getIntent().getSerializableExtra("wordsToExam");
        Log.i("TAL", String.valueOf(wordsToExam.size()));

        wordExamQueue = generateAllWordsExam(wordsToExam);
        mistakenExams = new LinkedList<>();

        start();
    }

    public void onOptionSelected(View view) {
        Button clickedBtn = (Button) view;
        String chosenDefinition = clickedBtn.getText().toString();
        boolean userWasRight = currExam.guess(chosenDefinition);

        if (userWasRight) {
            Log.i("TAL", "correct");
            Globals.wordsAudioManager.getWordMediaPlayer("great").start();
        } else {
            Log.i("TAL", "loser");
            mistakenExams.add(currExam);
            Globals.wordsAudioManager.getWordMediaPlayer("off").start();
        }

        displayRightAnswer(userWasRight, clickedBtn).postDelayed(new Runnable() {
            @Override
            public void run() {
                clickedBtn.setBackgroundResource(R.color.notAnsweredYetButton);

                if (wordExamQueue.size() == 0) {

                    if (mistakenExams.size() != 0) {
                        // now running the mistaken ones
                        wordExamQueue = new LinkedList<>(mistakenExams);
                        mistakenExams = new LinkedList<>();
                        currExam = wordExamQueue.poll();
                        displayExam(currExam);
                    } else {
                        // Finished the test
                        // TODO: add handle
                        Log.i("TAL", "FINISHHH");
                    }
                } else {
                    currExam = wordExamQueue.poll();
                    displayExam(currExam);
                }
            }
        }, TIME_TO_SHOW_ANSWER);


    }


    public Button displayRightAnswer(boolean userWasRight, Button clickedAnswerBtn) {
        if (userWasRight) {
            clickedAnswerBtn.setBackgroundResource(R.color.rightAnswerButton);
        } else {
            clickedAnswerBtn.setBackgroundResource(R.color.wrongAnswerButton);
        }

        return clickedAnswerBtn;
    }

    public void start() {
        currExam = wordExamQueue.poll();
        displayExam(currExam);
    }

    public void displayExam(WordExam exam) {

        wordToExamTxt.setText(exam.getWordToExam().getWord());

        option1Btn.setVisibility(View.INVISIBLE);
        option2Btn.setVisibility(View.INVISIBLE);
        option3Btn.setVisibility(View.INVISIBLE);
        option4Btn.setVisibility(View.INVISIBLE);

        int timeLongHidden = 1000;

        // Wait 2 seconds
        option1Btn.postDelayed(new Runnable() {
            @Override
            public void run() {
                option1Btn.setVisibility(View.VISIBLE);
            }
        }, timeLongHidden);

        option2Btn.postDelayed(new Runnable() {
            @Override
            public void run() {
                option2Btn.setVisibility(View.VISIBLE);
            }
        }, timeLongHidden);

        option3Btn.postDelayed(new Runnable() {
            @Override
            public void run() {
                option3Btn.setVisibility(View.VISIBLE);
            }
        }, timeLongHidden);

        option4Btn.postDelayed(new Runnable() {
            @Override
            public void run() {
                option4Btn.setVisibility(View.VISIBLE);
            }
        }, timeLongHidden);

        option1Btn.setText(exam.getOptions().get(0));
        option2Btn.setText(exam.getOptions().get(1));
        option3Btn.setText(exam.getOptions().get(2));
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
