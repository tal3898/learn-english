package com.taban.learnenglish.models;

import java.util.List;

public class WordExam {

    // DM
    private Word wordToExam;
    private List<String> options;

    //Ctor
    public WordExam(Word wrdToExm, List<String> optns) {
        this.setWordToExam(wrdToExm);
        this.setOptions(optns);
    }

    // Access Methods

    private void setWordToExam(Word wordToExam) {
        this.wordToExam = wordToExam;
    }

    private void setOptions(List<String> options) {
        this.options = options;
    }

    public Word getWordToExam() {
        return wordToExam;
    }

    public List<String> getOptions() {
        return options;
    }

    // Method

    /**
     * The method gets a user guess, and returns true if the user guessed correctly, else
     * returns false.
     * @param userGuess - The user guess
     * @return True if the user guessed correctly, else returns false.
     */
    public boolean guess(String userGuess) {
        return wordToExam.getDefinition().equals(userGuess);
    }
}
