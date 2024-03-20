package com.example.chapter17;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class DrawButton {
    static void draw(Canvas canvas, Paint paint, Bitmap image){
        paint.setColor(Color.argb(100,255,255,255));
        canvas.drawRect(Button.getmButton(),paint);
        canvas.drawBitmap(image,Button.getmButton().left,
        Button.getmButton().top,paint);
    }
}
