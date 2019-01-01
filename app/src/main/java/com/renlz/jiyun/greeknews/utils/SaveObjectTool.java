package com.renlz.jiyun.greeknews.utils;

import android.annotation.SuppressLint;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Administrator on 2018/12/31.
 */

public class SaveObjectTool {

    @SuppressLint("SdCardPath")
    private static String defaultPath = "/sdcard/damily_cache/";

    public static void writeObject(Object object, String name)
            throws FileNotFoundException, IOException {
        File file = getFile(name);
        ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream(file));
        outputStream.writeObject(object);
        outputStream.flush();
        outputStream.close();
        Log.i("SaveObjectTool", file.getAbsolutePath());
    }
    public static Object readObject(String name) throws FileNotFoundException,
            IOException, ClassNotFoundException {
        File file = getFile(name);
        ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream(file));
        Object object = inputStream.readObject();
        inputStream.close();
        Log.i("SaveObjectTool", file.getAbsolutePath());
        return object;
    }

    private static File getFile(String name) {
        File path = new File(defaultPath);
        if (!path.exists()) {
            path.mkdirs();
        }
        String objPath = path.getAbsolutePath() + name + ".txt";
        File file = new File(objPath);

        return file;
    }
}