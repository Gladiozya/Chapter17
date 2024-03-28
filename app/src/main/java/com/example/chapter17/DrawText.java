package com.example.chapter17;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

public class DrawText{

    public void draw(Canvas mCanvas, Paint mPaint, Point display, int score){

        // Set the size and color of the mPaint for the text
        mPaint.setColor(Color.argb(255, 255, 255, 255));
        mPaint.setTextSize(120);

        // Draw the score
        mCanvas.drawText("" + score, 20, 120, mPaint);

        //draw the names
        mPaint.setTextSize(100);
        mCanvas.drawText("Corliss Yang & Josh Dye", display.x-1200, 120, mPaint);

    }

        public void drawPausedText(Canvas mCanvas,Paint mPaint){
        // Set the size and color of the mPaint for the text
        mPaint.setColor(Color.argb(255, 255, 255, 255));
        mPaint.setTextSize(250);

        // Draw the message
        // We will give this an international upgrade soon
        mCanvas.drawText("Tap To Play!", 200, 700, mPaint);
//            mCanvas.drawText(getResources().getString(R.string.tap_to_play),
//                    200, 700, mPaint);
    }

}
