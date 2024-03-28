package com.example.chapter17;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.graphics.Point;


public class Button {
    //sizes for button
    private int buttonWidth;
    private int buttonHeight;
    private int buttonPadding;
    private  Rect mButton;

    private Bitmap buttonPause;
    private Bitmap buttonResume;

    public  Rect getmButton() { return mButton; }

    Button(Point display, Context context){
        buttonWidth=display.x/14;
        buttonHeight=display.y/12;
        buttonPadding=buttonWidth/90;

        mButton=new Rect(buttonPadding,
                display.y-(2*buttonHeight)+(4*buttonPadding),
                buttonWidth,
                display.y-buttonPadding);

        buttonPause = BitmapFactory.decodeResource(context.getResources(), R.drawable.pause);
        buttonResume = BitmapFactory.decodeResource(context.getResources(), R.drawable.play);
    }

    boolean Clicked(MotionEvent mMotionEvent) {
        if (mButton.top < mMotionEvent.getY() &&
                mButton.right > mMotionEvent.getX()) {
                return true;
            }
        return false;
    }


    Bitmap getButtonImage(boolean paused){
        if(paused){
            return Bitmap.createScaledBitmap(buttonResume, mButton.right,mButton.bottom-mButton.top, true);
        }else{
            return Bitmap.createScaledBitmap(buttonPause, mButton.right,mButton.bottom-mButton.top, true);
        }
    }
     void draw(Canvas canvas, Paint paint, Bitmap image){
        paint.setColor(Color.argb(100,255,255,255));
        canvas.drawRect(getmButton(),paint);
        canvas.drawBitmap(image,getmButton().left, getmButton().top,paint);
    }
}