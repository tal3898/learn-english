package com.taban.learnenglish.utilities;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.taban.learnenglish.models.Word;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Optional;

public class FileReader {

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public Object readObjectFromFile(String url) {
        String actualUrl = Globals.getFilesPath() + url;
        try (FileInputStream fin = new FileInputStream(actualUrl);
             ObjectInputStream ois = new ObjectInputStream(fin);) {

            Object readObject = ois.readObject();

            Log.i("TAL", "The object was read successfully from file " + actualUrl);

            return readObject;
        } catch (Exception e) {
            Log.e("TAL", "Could not read the file " + actualUrl, e);
            return null;
        }
    }
}
