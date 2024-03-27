package com.example.chapter17;

import android.graphics.*;
import android.view.MotionEvent;

import java.util.ArrayList;

public class SnakeHeadDirection{

    private enum Heading {
        UP(1), RIGHT(2), DOWN(3), LEFT(4);

        private int headingValue;

        Heading(int headingValue){
            this.headingValue=headingValue;
        }

        public int getHeadingValue() {
            return headingValue;
        }

    }

    private Heading heading;
    Heading[] headingDirection=heading.values();


    private final int HEADINGLIMIT=4;
    private final int HEADINGSTART=0;

    void reset(){
        heading=headingDirection[2];
    }

    // Move the head in the appropriate heading
    void movement(ArrayList<Point> segmentLocations){
        // Get the existing head position
        Point p = segmentLocations.get(0);
        switch (heading) {
            case UP:
                p.y--;
                break;

            case RIGHT:
                p.x++;
                break;

            case DOWN:
                p.y++;
                break;

            case LEFT:
                p.x--;
                break;
        }
    }

    // Handle changing direction
    void changeDirection(MotionEvent motionEvent, int halfWayPoint) {
        int headingIndex = heading.getHeadingValue();

        // Is the tap on the right hand side of the screen?
        if (motionEvent.getX() >= halfWayPoint) {
            // Rotate right, cycle back to 0 if index reaches 4 (array size)
            if (headingIndex >= HEADINGLIMIT) {
                headingIndex = HEADINGSTART;
            }
        }else {
            // Rotate left, cycle back to 3 (Left) if index reaches -1 (-1 isn't an array position)
            headingIndex=headingIndex-2;
            if (headingIndex < HEADINGSTART) {
                headingIndex = HEADINGLIMIT - 1;
            }
        }

        heading=headingDirection[headingIndex];
    }

    Bitmap draw(SnakeHeadBitmap HeadBitmap) {
        // Draw the head
        switch (heading) {
            case RIGHT:
                return HeadBitmap.getmBitmapHeadRight();

            case LEFT:
                return HeadBitmap.getmBitmapHeadLeft();

            case UP:
                return HeadBitmap.getmBitmapHeadUp();

            case DOWN:
                return HeadBitmap.getmBitmapHeadDown();

        }
        return HeadBitmap.getmBitmapHeadRight();
    }
}
