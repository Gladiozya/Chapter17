package com.example.chapter17;

import android.content.Context;
import android.graphics.*;

public class SnakeHeadBitmap {
    // A bitmap for each direction the head can face
    private Bitmap mBitmapHeadRight;
    private Bitmap mBitmapHeadLeft;
    private Bitmap mBitmapHeadUp;
    private Bitmap mBitmapHeadDown;
    Matrix matrix = new Matrix();

    SnakeHeadBitmap(Context context, int ss){
        // Create and scale the bitmaps
        setmBitmapHeadRight(initializeBitmap(context));
        setmBitmapHeadLeft(initializeBitmap(context));
        setmBitmapHeadUp(initializeBitmap(context));
        setmBitmapHeadDown(initializeBitmap(context));

        // Modify the bitmaps to face the snake head in the correct direction
        setmBitmapHeadRight(initializeMatrix(mBitmapHeadRight,ss,false));

        // A matrix for scaling
        matrix.preScale(-1, 1);

        setmBitmapHeadLeft(initializeMatrix(mBitmapHeadLeft,ss,true));
        // A matrix for rotating
        matrix.preRotate(-90);
        setmBitmapHeadUp(initializeMatrix(mBitmapHeadUp,ss,true));

        // Matrix operations are cumulative
        // so rotate by 180 to face down
        matrix.preRotate(180);
        setmBitmapHeadDown(initializeMatrix(mBitmapHeadDown,ss,true));
    }
    public Bitmap getmBitmapHeadRight() {
        return mBitmapHeadRight;
    }

    public void setmBitmapHeadRight(Bitmap mBitmapHeadRight) {
        this.mBitmapHeadRight = mBitmapHeadRight;
    }

    public Bitmap getmBitmapHeadLeft() {
        return mBitmapHeadLeft;
    }

    public void setmBitmapHeadLeft(Bitmap mBitmapHeadLeft) {
        this.mBitmapHeadLeft = mBitmapHeadLeft;
    }

    public Bitmap getmBitmapHeadUp() {
        return mBitmapHeadUp;
    }

    public void setmBitmapHeadUp(Bitmap mBitmapHeadUp) {
        this.mBitmapHeadUp = mBitmapHeadUp;
    }

    public Bitmap getmBitmapHeadDown() {
        return mBitmapHeadDown;
    }

    void setmBitmapHeadDown(Bitmap mBitmapHeadDown) {
        this.mBitmapHeadDown = mBitmapHeadDown;
    }

    Bitmap initializeBitmap (Context context){
        return BitmapFactory.decodeResource(
                context.getResources(), R.drawable.head);
    }

    Bitmap initializeMatrix(Bitmap direction, int ss, boolean used){
        if(direction == mBitmapHeadRight){
            return Bitmap.createScaledBitmap(mBitmapHeadRight,
                            ss, ss, false);
        }
        return Bitmap.createBitmap(mBitmapHeadRight,
                0, 0, ss, ss, matrix, used);
    }

}