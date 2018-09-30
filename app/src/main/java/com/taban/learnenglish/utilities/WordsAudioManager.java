package com.taban.learnenglish.utilities;

import android.media.MediaPlayer;

import com.taban.learnenglish.activities.LearnEnglishApplication;
import com.taban.learnenglish.models.Word;

import java.util.HashMap;
import java.util.Map;

public class WordsAudioManager {

    // DM
    private Map<String, MediaPlayer> wordsAudioFiles;

    // CTRO
    public WordsAudioManager() {
        wordsAudioFiles = new HashMap<>();
    }

    // Methods
    public MediaPlayer getWordMediaPlayer(Word word) {
        if (!wordsAudioFiles.containsKey(word.getWord())) {
            int resId = LearnEnglishApplication.getAppContext().getResources().getIdentifier(
                    word.getWord(), "raw", LearnEnglishApplication.getAppContext().getPackageName());
            MediaPlayer mediaPlayer = MediaPlayer.create(LearnEnglishApplication.getAppContext(), resId);
            wordsAudioFiles.put(word.getWord(), mediaPlayer);
        }

        return wordsAudioFiles.get(word.getWord());
    }
}
