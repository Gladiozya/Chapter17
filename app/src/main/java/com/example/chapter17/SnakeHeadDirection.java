package com.example.chapter17;

import android.graphics.*;
import android.view.MotionEvent;

import java.util.ArrayList;

public class SnakeHeadDirection{

    private  enum Heading {
        UP, RIGHT, DOWN, LEFT
    }
    private Heading heading=Heading.RIGHT;

    void reset(){
        heading=Heading.RIGHT;
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

        // Is the tap on the right hand side?
        if (motionEvent.getX() >= halfWayPoint) {
            switch (heading) {
                // Rotate right
                case UP:
                    heading = Heading.RIGHT;
                    break;
                case RIGHT:
                    heading = Heading.DOWN;
                    break;
                case DOWN:
                    heading = Heading.LEFT;
                    break;
                case LEFT:
                    heading = Heading.UP;
                    break;

            }
        } else {
            // Rotate left
            switch (heading) {
                case UP:
                    heading = Heading.LEFT;
                    break;
                case LEFT:
                    heading = Heading.DOWN;
                    break;
                case DOWN:
                    heading = Heading.RIGHT;
                    break;
                case RIGHT:
                    heading = Heading.UP;
                    break;
            }
        }
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
