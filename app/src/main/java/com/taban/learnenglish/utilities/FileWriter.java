package com.taban.learnenglish.utilities;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.taban.learnenglish.models.Word;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class FileWriter {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void writeObjectIntoFile(Object writeableObject, String url) {


        String actualUrl = Globals.getFilesPath() + url;

        try(FileOutputStream fout = new FileOutputStream(actualUrl);
            ObjectOutputStream oos = new ObjectOutputStream(fout)) {
            oos.writeObject(writeableObject);

            Log.i("TAL", "File " + actualUrl + " was created and written");
        } catch (Exception ex) {
            Log.e("TAL", "Could not write the file " + actualUrl, ex);
        }
    }
}
