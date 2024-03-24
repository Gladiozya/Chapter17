package com.example.chapter17;

import static java.lang.Thread.sleep;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.core.content.res.ResourcesCompat;

class SnakeGame extends SurfaceView implements Runnable{

    // Objects for the game loop/thread
    private Thread mThread = null;

    // Control pausing between updates
    private long mNextFrameTime;

    // The size in segments of the playable area
    private final int NUM_BLOCKS_WIDE = 40;
    private final int mNumBlocksHigh;

    private static int mScore;

    // Objects for drawing
    private static Canvas mCanvas;
    private final SurfaceHolder mSurfaceHolder;
    private static Paint mPaint;
    private final Point mDisplay;

    private final Snake mSnake;
    private final Apple mApple;
    private Bitmap mImage;

    private MotionEvent mMotionEvent;
    private final Button mButton;
    private Bitmap buttonImage;

    public static Paint getmPaint() { return mPaint; }

    public static Canvas getmCanvas() { return mCanvas; }

    public static int getmScore() { return mScore; }

    private Thread getmThread() { return mThread; }

    // This is the constructor method that gets called
    // from SnakeActivity
    public SnakeGame(Context context, Point size) {
        super(context);

        // Work out how many pixels each block is
        int blockSize = size.x / NUM_BLOCKS_WIDE;
        // How many blocks of the same size will fit into the height
        mNumBlocksHigh = size.y / blockSize;


        Audio.buildAudio(context);

        // Initialize the drawing objects
        mSurfaceHolder = getHolder();
        mPaint = new Paint();

        // Call the constructors of our two game objects
        mApple = new Apple(context,
                new Point(NUM_BLOCKS_WIDE,
                        mNumBlocksHigh),
                blockSize);

        mSnake = new Snake(context,
                new Point(NUM_BLOCKS_WIDE,
                        mNumBlocksHigh),
                blockSize);

        mDisplay = size;
        mButton=new Button(mDisplay,context);

        mImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
        mImage = Bitmap.createScaledBitmap(mImage, mDisplay.x, mDisplay.y, true);

        mPaint.setTypeface(ResourcesCompat.getFont(context, R.font.rans));

    }


    // Called to start a new game
    public void newGame() {

        // reset the snake
        mSnake.reset(NUM_BLOCKS_WIDE, mNumBlocksHigh);

        // Get the apple ready for dinner
        AppleLocation.spawn();

        // Reset the mScore
        mScore = 0;

        // Setup mNextFrameTime so an update can triggered
        mNextFrameTime = System.currentTimeMillis();
    }


    // Handles the game loop
    @Override
    public void run() {
        while (PauseButton.getmPlaying()) {
            if(!PauseButton.getmPaused()) {
                if (updateRequired()) {
                    update();
                }
            }
            draw();
        }
    }


    // Check to see if it is time for an update
    public boolean updateRequired() {

        // Run at 10 frames per second
        final long TARGET_FPS = 10;
        // There are 1000 milliseconds in a second
        final long MILLIS_PER_SECOND = 1000;

        // Are we due to update the frame
        if(mNextFrameTime <= System.currentTimeMillis()){
            // Tenth of a second has passed

            // Setup when the next update will be triggered
            mNextFrameTime =System.currentTimeMillis()
                    + MILLIS_PER_SECOND / TARGET_FPS;

            // Return true so that the update and draw
            // methods are executed
            return true;
        }

        return false;
    }


    // Update all the game objects
    public void update() {

        // Move the snake
        mSnake.move();

        // Did the head of the snake eat the apple?
        if(mSnake.checkDinner(Apple.getLocation())){
            // This reminds me of Edge of Tomorrow.
            // One day the apple will be ready!
            AppleLocation.spawn();

            // Add to  mScore
            mScore = mScore + 1;

            // Play a sound
            Audio.getmSP().play(Audio.getmEat_ID(), 1, 1, 0, 0, 1);
        }

        PauseButton.setmPaused(mButton.Clicked(mMotionEvent));

        // Did the snake die?
        if (mSnake.detectDeath()) {
            // Pause the game ready to start again
            Audio.getmSP().play(Audio.getmCrashID(), 1, 1, 0, 0, 1);

            PauseButton.setmPaused(true);
        }

    }


    // Do all the drawing
    public void draw() {
        // Get a lock on the mCanvas
        if (mSurfaceHolder.getSurface().isValid()) {
            mCanvas = mSurfaceHolder.lockCanvas();

            buttonImage=mButton.getButtonImage(PauseButton.getmPaused());
            mPaint.setColor(Color.argb(255, 255, 255, 255));

            //Draw background
            mCanvas.drawBitmap(mImage,0,0,mPaint);
            //Draw Apple, button, Snake, and Text
            DrawApple.draw(mCanvas, mPaint);
            DrawButton.draw(mCanvas,mPaint,buttonImage);
            DrawSnake.draw(mCanvas, mPaint);
            DrawText.draw(mCanvas, mPaint);

            // Unlock the mCanvas and reveal the graphics for this frame
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        mMotionEvent=motionEvent;

        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                if (PauseButton.getmPaused()) {
                    PauseButton.setmPaused(false);
                    newGame();

                    // Don't want to process snake direction for this tap
                    return true;
                }

                // Let the Snake class handle the input
                mSnake.switchHeading(motionEvent);
                break;

            default:
                break;

        }
        return true;
    }


    // Stop the thread
    public void pause() {
        PauseButton.setmPlaying(false);
        PauseButton.setmPaused(true);
        while(PauseButton.getmPaused()){
            mThread.suspend();
        }
        mThread.resume();
    }


    // Start the thread
    public void resume() {
        PauseButton.setmPlaying(true);
        mThread = new Thread(this);
        mThread.start();
    }
}
