package com.zanacode.colombianslang.utilities;

import android.content.Context;
import android.os.Environment;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//TODO God class, too smelly!
public class MoveDatabase {

    public static void moveDatabaseFiles(Context context) {

        String file1 = "spanish_slang";
        String file2 = "spanish_slang-shm";
        String file3 = "spanish_slang-wal";

        try {
            copyFile(file1, context);
            copyFile(file2, context);
            copyFile(file3, context);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void checkDatabaseFiles(Context context) {

        String file1 = "spanish_slang";
        String file2 = "spanish_slang-shm";
        String file3 = "spanish_slang-wal";

        if (!isFileInDatabaseDir(file1, context)) {
            try {
                copyFile(file1, context);
                copyFile(file2, context);
                copyFile(file3, context);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean isFileInDatabaseDir(String fileName, Context context) {

        File data = Environment.getDataDirectory();
        String currentFile = "//data//"+context.getPackageName()+"//databases//"+fileName;

        File file = new File(data, currentFile);
        return file.exists();
    }


    private static void copyFile(String fileName, Context context) throws IOException {

        String outputPath = "/data/data/" + "com.zanacode.colombianslang" + "/databases/";

        OutputStream out;
        try {

            InputStream in = context.getAssets().open(fileName);
            out = new FileOutputStream(outputPath + fileName);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;

            out.flush();
            out.close();
            out = null;

        }  catch (FileNotFoundException fnfe1) {
            Logger.e(fnfe1.getMessage());
        }
        catch (Exception e) {
            Logger.e(e.getMessage());
        }

    }



}
