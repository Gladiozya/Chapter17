package com.example.chapter17;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class DrawSnake {

    public static void draw(Canvas canvas, Paint paint) {

        // Don't run this code if ArrayList has nothing in it

        if (!Snake.getSegmentLocations().isEmpty()) {

            Bitmap direction=Snake.getSnakeDirection().draw(Snake.getHeadBitmap());
            Snake.drawBit(direction, 0 , canvas, paint);

            // Draw the snake body one block at a time
            for (int i = 1; i < Snake.getSegmentLocations().size(); i++) {
                Snake.drawBit(Snake.getmBitmapBody(), i, canvas, paint);
            }
        }
    }
}
