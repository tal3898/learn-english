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
        return this.getWordMediaPlayer(word.getWord());
    }

    public MediaPlayer getWordMediaPlayer(String word) {
        if (!wordsAudioFiles.containsKey(word)) {
            int resId = LearnEnglishApplication.getAppContext().getResources().getIdentifier(
                    word, "raw", LearnEnglishApplication.getAppContext().getPackageName());

            if (resId != 0) {
                MediaPlayer mediaPlayer = MediaPlayer.create(LearnEnglishApplication.getAppContext(), resId);
                wordsAudioFiles.put(word, mediaPlayer);
            } else {
                wordsAudioFiles.put(word, null);
            }
        }

        return wordsAudioFiles.get(word);
    }
}
