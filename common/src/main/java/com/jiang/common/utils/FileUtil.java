package com.jiang.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by DB on 2016/8/4.
 */
public class FileUtil {
    private final static String TAG = "FileUtil";

    public static Uri convertUri(Uri uri, Context context, boolean isAvatar) {
        InputStream is = null;
        try {
            is = context.getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            is.close();
            return saveBitmapReturnUri(bitmap, context, isAvatar);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Uri saveBitmapReturnUri(Bitmap bm, Context context) {
        return saveBitmapReturnUri(bm, context, true);
    }

    public static Uri saveBitmapReturnUri(Bitmap bm, Context context, boolean isAvatar) {
        String file = context.getCacheDir().getPath();
        Log.e(TAG, "saveBitmapReturnUri: " + file);
        File img = null;
        if (isAvatar) {
            img = new File(file, "/avatar.png");
        } else {
            Date date = new Date();
            String str = date.getTime() + "";
            img = new File(file, "/" + str + ".jpg");
        }
        if (img.exists()) {
            img.delete();
        }
        try {
            FileOutputStream fos = new FileOutputStream(img);
            bm.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            return Uri.fromFile(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String saveBitmapReturnStr(Bitmap bitmap) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String name = formatter.format(System.currentTimeMillis()) + ".jpg";
        FileOutputStream b = null;

        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(path + "/myImage/");
        /** 检测文件夹是否存在，不存在则创建文件夹 **/
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
        String fileName = file.getPath() + "/" + name;
        try {
            b = new FileOutputStream(fileName);
      /* 把数据写入文件 */
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);
            return fileName;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (b == null) {
                    return "";
                }
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
