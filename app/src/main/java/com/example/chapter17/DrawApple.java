package com.example.chapter17;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class DrawApple extends Apple{
    DrawApple(Context context, Point sr, int s) {
        super(context, sr, s);
    }

    // Draw the apple
    static void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(getmBitmapApple(),
                getLocation().x * getmSize(), getLocation().y * getmSize(), paint);

    }
}
