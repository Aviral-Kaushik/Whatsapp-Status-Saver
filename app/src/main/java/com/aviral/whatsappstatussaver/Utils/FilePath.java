package com.aviral.whatsappstatussaver.Utils;

import android.os.Environment;

import java.io.File;

public class FilePath {

    public static File rootDirectoryForSavingStatus = new File(Environment.getExternalStorageDirectory()
            + "/Download/Whatsapp Status Saver");

    public static void createDirectory() {
        if (!rootDirectoryForSavingStatus.exists()) {
            rootDirectoryForSavingStatus.mkdirs();
        }
    }

}
