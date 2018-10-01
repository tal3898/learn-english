package com.taban.learnenglish.models;

import java.io.Serializable;
import java.util.List;

public class Word implements Serializable {
    public String word;
    private String definition;
    private List<String> examples;
    private Object audioFile;
    private Difficulty difficulty;

    // Ctor
    public Word(String word, String definition, List<String> examples, Object audioFile, Difficulty difficulty) {
        this.word = word;
        this.definition = definition;
        this.examples = examples;
        this.audioFile = audioFile;
        this.difficulty = difficulty;
    }

    // Access Methods
    public String getWord() {
        return word;
    }

    public String getDefinition() {
        return definition;
    }

    public List<String> getExamples() {
        return examples;
    }

    public Object getAudioFile() {
        return audioFile;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}
