package com.github.andrdev.easyenglish;

import android.content.Context;
import android.graphics.Bitmap;

import com.github.andrdev.easyenglish.model.EnglishAudioItem;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by taiyokaze on 7/19/15.
 */
public class DiskUtils {


    public static String deleteFile(Context context, EnglishAudioItem item) {
        String path = buildPath(context, "donwloaded", item.getPath());
        File file = new File(path);
        file.delete();
        return path;
    }

//    public static String writeToDisk(Context context, EnglishAudioItem item) {
//        try {
//
//            return createFile(context, "order", item.getTitle(), bitmap);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static String createFile(Context context, String directoryName, String fileName, byte[] content) {
        String path = buildPath(context, directoryName, fileName);
        File file = new File(path);
        file.delete();
        try {
            OutputStream stream = new FileOutputStream(file);

            stream.write(content);
            stream.flush();
            stream.close();

        } catch (IOException e) {
            throw new RuntimeException("Failed to create", e);
        }
        return path;
    }

    public static String createFile(Context context, String directoryName, String fileName, Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        return createFile(context, directoryName, fileName, byteArray);
    }

    protected static String buildPath(Context context, String directoryName, String fileName) {
        String path = context.getDir(directoryName, Context.MODE_PRIVATE).getAbsolutePath();
        path = path + File.separator + fileName;
        return path;
    }
}
