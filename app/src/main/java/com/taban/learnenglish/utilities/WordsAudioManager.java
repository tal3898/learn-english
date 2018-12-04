package com.taban.learnenglish.utilities;

import android.media.MediaPlayer;

import com.taban.learnenglish.activities.LearnEnglishApplication;
import com.taban.learnenglish.models.Word;

import java.util.HashMap;
import java.util.Map;

public class WordsAudioManager {

    private final String WORD_DOES_NOT_EXIST_AUDIO_FILE_NAME = "word_does_not_exist_audio";

    // DM
    private Map<String, MediaPlayer> wordsAudioFiles;

    // CTOR
    public WordsAudioManager() {
        wordsAudioFiles = new HashMap<>();
        putTheEmptyAudioWord();
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
                wordsAudioFiles.put(word, wordsAudioFiles.get(WORD_DOES_NOT_EXIST_AUDIO_FILE_NAME));
            }
        }

        return wordsAudioFiles.get(word);
    }

    private void putTheEmptyAudioWord() {
        int resId = LearnEnglishApplication.getAppContext().getResources().getIdentifier(
                WORD_DOES_NOT_EXIST_AUDIO_FILE_NAME, "raw", LearnEnglishApplication.getAppContext().getPackageName());
        MediaPlayer mediaPlayer = MediaPlayer.create(LearnEnglishApplication.getAppContext(), resId);
        wordsAudioFiles.put(WORD_DOES_NOT_EXIST_AUDIO_FILE_NAME, mediaPlayer);
    }
}
