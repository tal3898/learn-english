package com.taban.learnenglish.utilities;

import android.content.Context;

import com.taban.learnenglish.activities.LearnEnglishApplication;
import com.taban.learnenglish.models.Difficulty;

import java.io.File;

public class Globals {

    // DM
    public static WordsAudioManager wordsAudioManager;
    private static Difficulty userChosenDifficulty;
    private static String filesPath;

    // Ctor
    static {
        wordsAudioManager = new WordsAudioManager();
        filesPath =  String.valueOf(LearnEnglishApplication.getAppContext()) + File.separator;
        userChosenDifficulty = Difficulty.BEGINNER;
    }

    // Access Methods

    public static String getFilesPath() {
        return filesPath;
    }

    public static Difficulty getUserChosenDifficulty() {
        return userChosenDifficulty;
    }
}
