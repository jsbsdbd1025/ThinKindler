package com.jiang.common.utils.imageloader;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;


/**
 * Created by DB on 2016/11/19.
 */

public class PlaceHolder {

    public static Drawable createDrawable(Context context, String str) {

        int i;
        if (str == null || str.equals("")) {
            i = 0;
        } else {
            i = (int) str.charAt(0);
        }

        int resId = 0;
        switch (i % 5) {
//            case 0:
//                resId = R.mipmap.news_bg_class_blue;
//                break;
//            case 1:
//                resId = R.mipmap.news_bg_class_deekblue;
//                break;
//            case 2:
//                resId = R.mipmap.news_bg_class_green;
//                break;
//            case 3:
//                resId = R.mipmap.news_bg_class_red;
//                break;
//            case 4:
//                resId = R.mipmap.news_bg_class_yellow;
//                break;
        }

        Resources resources = context.getResources();
        float scale = resources.getDisplayMetrics().density;

        Bitmap bitmap = BitmapFactory.decodeResource(resources, resId);
        int size = bitmap.getHeight() > bitmap.getWidth() ? bitmap.getWidth() : bitmap.getHeight();
        Bitmap result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        canvas.drawColor(Color.TRANSPARENT);
        Rect rect = new Rect(0, 0, size, size);//画一个矩形
        Paint textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setAlpha(200);
        textPaint.setTextSize((int) (size * 0.65));
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
        int baseLineY = (int) (rect.centerY() - top / 2 - bottom / 2);//基线中间点的y轴计算公式

        canvas.drawBitmap(bitmap, 0, 0, textPaint);
        if (str != null && str.length() > 1)
            canvas.drawText(str.substring(0, 1), (int) (size * 0.35) / 2, baseLineY, textPaint);
        canvas.save(Canvas.ALL_SAVE_FLAG);

        return new BitmapDrawable(result);
    }

    public static Drawable createDrawableCir(Context context, String str) {

        int i;
        if (str == null || str.equals("")) {
            i = 0;
        } else {
            i = (int) str.charAt(0);
        }

        int resId = 0;
        switch (i % 5) {
//            case 0:
//                resId = R.mipmap.news_bg_class_blue;
//                break;
//            case 1:
//                resId = R.mipmap.news_bg_class_deekblue;
//                break;
//            case 2:
//                resId = R.mipmap.news_bg_class_green;
//                break;
//            case 3:
//                resId = R.mipmap.news_bg_class_red;
//                break;
//            case 4:
//                resId = R.mipmap.news_bg_class_yellow;
//                break;
        }

        Resources resources = context.getResources();
        float scale = resources.getDisplayMetrics().density;

        Bitmap bitmap = BitmapFactory.decodeResource(resources, resId);
        int size = bitmap.getHeight() > bitmap.getWidth() ? bitmap.getWidth() : bitmap.getHeight();
        Bitmap result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        canvas.drawColor(Color.TRANSPARENT);
        Rect rect = new Rect(0, 0, size, size);//画一个矩形
        Paint textPaint = new Paint();
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getWidth() / 2, bitmap.getWidth() / 2, textPaint);
        textPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0, 0, textPaint);
        textPaint.setAlpha(200);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize((int) (size * 0.65));
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
        int baseLineY = (int) (rect.centerY() - top / 2 - bottom / 2);//基线中间点的y轴计算公式
        if (str != null && str.length() > 1)
            canvas.drawText(str.substring(0, 1), (int) (size * 0.35) / 2, baseLineY, textPaint);
        canvas.save(Canvas.ALL_SAVE_FLAG);

        return new BitmapDrawable(result);
    }
}
