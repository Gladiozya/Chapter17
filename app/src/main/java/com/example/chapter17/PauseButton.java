package com.example.chapter17;

public class PauseButton {
    private static volatile boolean mPlaying = false;
    private static volatile boolean mPaused = true;

    public static boolean getmPlaying() { return mPlaying; }
    public static boolean getmPaused() { return mPaused; }

    public static void setmPaused(boolean mPaused) {
        PauseButton.mPaused = mPaused;
    }

    public static void setmPlaying(boolean mPlaying) {
        PauseButton.mPlaying = mPlaying;
    }
}
