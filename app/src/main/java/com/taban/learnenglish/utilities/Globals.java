package com.taban.learnenglish.utilities;

import android.content.Context;

import java.io.File;

public class Globals {

    // DM
    private static String filesPath;

    // Access Methods

    public static String getFilesPath() {
        return filesPath;
    }

    // Methods
    public static void initVariables(Context context) {
        filesPath =  String.valueOf(context.getFilesDir()) + File.separator;
    }
}
