package com.example.chapter17;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

class Apple {
    // The range of values to spawn an apple
    private static Point mSpawnRange;
    private static int mSize;

    // An image to represent the apple
    private static Bitmap mBitmapApple;


    // The grid location of the apple
    private static Point location = new Point();

    public static Bitmap getmBitmapApple() {
        return mBitmapApple;
    }

    public static Point getmSpawnRange() {
        return mSpawnRange;
    }

    public static int getmSize() {
        return mSize;
    }

    public static Point getLocation() { return location; }

    /// Set up the apple in the constructor
    Apple(Context context, Point sr, int s){

        // Make a note of the passed in spawn range
        mSpawnRange = sr;
        // Make a note of the size of an apple
        mSize = s;
        // Hide the apple off-screen until the game starts
        location.x = -10;

        // Load the image to the bitmap
        mBitmapApple = BitmapFactory.decodeResource(context.getResources(), R.drawable.apple);

        // Resize the bitmap
        mBitmapApple = Bitmap.createScaledBitmap(mBitmapApple, s, s, false);
    }
}