package com.taban.learnenglish.activities;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
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
import com.taban.learnenglish.utilities.WordsAudioManager;
import com.taban.learnenglish.utilities.WordsManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.logging.Logger;

public class ExamActivity extends AppCompatActivity {

    private static final int TIME_TO_SHOW_ANSWER = 2000;
    private static final int TIME_ANSWERS_ARE_HIDDEN = 1000;

    WordsManager wordsManager;
    WordsAudioManager wordsAudioManager;

    List<Word> wordsToExam;
    Queue<WordExam> wordExamQueue;
    Queue<WordExam> mistakenExams;
    WordExam currExam;
    Handler handler;

    TextView wordToExamTxt;
    Button option1Btn;
    Button option2Btn;
    Button option3Btn;
    Button option4Btn;
    List<Button> allOptionsButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        wordsAudioManager = new WordsAudioManager();

        allOptionsButtons = new ArrayList<>();
        wordToExamTxt = findViewById(R.id.TestedWordTxt);
        option1Btn = findViewById(R.id.ExamOption1Btn);
        option2Btn = findViewById(R.id.ExamOption2Btn);
        option3Btn = findViewById(R.id.ExamOption3Btn);
        option4Btn = findViewById(R.id.ExamOption4Btn);
        allOptionsButtons.add(option1Btn);
        allOptionsButtons.add(option2Btn);
        allOptionsButtons.add(option3Btn);
        allOptionsButtons.add(option4Btn);

        handler = new Handler();

        // Get the words to exam the user
        wordsToExam= (List<Word>) getIntent().getSerializableExtra("wordsToExam");
        Log.i("TAL", String.valueOf(wordsToExam.size()));

        wordExamQueue = generateAllWordsExam(wordsToExam);
        mistakenExams = new LinkedList<>();

        start();
    }

    /**
     * The method generates a queue with the whole exam
     * @param wordsToTest - The words to test on the user
     * @return a queue with all the exams (exam for each word)
     */
    public Queue<WordExam> generateAllWordsExam(List<Word> wordsToTest) {
        LinkedList<WordExam> exams = new LinkedList<>();
        for(Word curWord : wordsToTest) {
            List<String> curWordExamOptions = getWordOptions(curWord, wordsToTest, 4);
            WordExam curWordExam = new WordExam(curWord, curWordExamOptions);
            exams.add(curWordExam);
        }

        Collections.shuffle(exams);

        return exams;
    }

    /**
     * The method starts the exam
     */
    public void start() {
        currExam = wordExamQueue.poll();
        displayExam(currExam);
    }

    /**
     * The method displays the exam - shows the word, and after x seconds, shows the optional answers
     * @param exam - The exam to display
     */
    public void displayExam(WordExam exam) {

        wordToExamTxt.setText(exam.getWordToExam().getWord());

        MediaPlayer wordMediaPlayer = wordsAudioManager.getWordMediaPlayer(exam.getWordToExam());
        if (wordMediaPlayer != null) {
            wordMediaPlayer.start();
        }

        option1Btn.setVisibility(View.INVISIBLE);
        option2Btn.setVisibility(View.INVISIBLE);
        option3Btn.setVisibility(View.INVISIBLE);
        option4Btn.setVisibility(View.INVISIBLE);

        // Wait 2 seconds
        option1Btn.postDelayed(new Runnable() {
            @Override
            public void run() {
                option1Btn.setVisibility(View.VISIBLE);
            }
        }, TIME_ANSWERS_ARE_HIDDEN);

        option2Btn.postDelayed(new Runnable() {
            @Override
            public void run() {
                option2Btn.setVisibility(View.VISIBLE);
            }
        }, TIME_ANSWERS_ARE_HIDDEN);

        option3Btn.postDelayed(new Runnable() {
            @Override
            public void run() {
                option3Btn.setVisibility(View.VISIBLE);
            }
        }, TIME_ANSWERS_ARE_HIDDEN);

        option4Btn.postDelayed(new Runnable() {
            @Override
            public void run() {
                option4Btn.setVisibility(View.VISIBLE);
            }
        }, TIME_ANSWERS_ARE_HIDDEN);

        option1Btn.setText(exam.getOptions().get(0));
        option2Btn.setText(exam.getOptions().get(1));
        option3Btn.setText(exam.getOptions().get(2));
        option4Btn.setText(exam.getOptions().get(3));
    }

    /**
     * The method is called when an options is selected. The method play a sound to indicate
     * to the user if he was correct, and color the answers, and after X seconds:
     * if the user was wrong, insert the exam into a mistaken list, and display the next exam
     * (or finish the total test)
     * @param view - A button object which is the button the user clicked
     */
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

        displayRightAnswer(userWasRight, clickedBtn);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                clickedBtn.setBackgroundResource(R.color.notAnsweredYetButton);
                findRightAnswer().setBackgroundResource(R.color.notAnsweredYetButton);

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
                        onTestEnds();
                        Log.i("TAL", "FINISHHH");
                    }
                } else {
                    currExam = wordExamQueue.poll();
                    displayExam(currExam);
                }
            }
        }, TIME_TO_SHOW_ANSWER);


    }

    public void onTestEnds() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ExamActivity.this);
        alertBuilder.setTitle("Congratulations!");
        alertBuilder.setMessage("You finished successfully the test, keep practice and getting better.");
        alertBuilder.setCancelable(false);
        alertBuilder.setPositiveButton("Cool", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent mainActivityIntent = new Intent(LearnEnglishApplication.getAppContext(), MainActivity.class);
                startActivity(mainActivityIntent);
            }
        });

        AlertDialog alertDialog = alertBuilder.create();
        alertBuilder.show();
    }

    /**
     * The method display the right answer - if answered right, color the clicked button with green,
     * else color the clicked button with red, and the right button with green.
     * @param userWasRight - Boolean which indicates whether the user clicked correctly or not
     * @param clickedAnswerBtn - The button the user clicked
     */
    public void displayRightAnswer(boolean userWasRight, Button clickedAnswerBtn) {
        if (userWasRight) {
            clickedAnswerBtn.setBackgroundResource(R.color.rightAnswerButton);
        } else {
            clickedAnswerBtn.setBackgroundResource(R.color.wrongAnswerButton);
            findRightAnswer().setBackgroundResource(R.color.rightAnswerButton);
        }
    }

    /**
     * The method search for the right answer button.
     * @return The button of the right answer.
     */
    private Button findRightAnswer() {
        Button rightAnswer = null;

        for (Button optionBtn : allOptionsButtons) {
            if (currExam.guess(optionBtn.getText().toString())) {
                rightAnswer = optionBtn;

                break;
            }
        }

        return rightAnswer;
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

        Collections.shuffle(wordOptions);

        return wordOptions;
    }
}
