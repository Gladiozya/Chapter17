package com.example.chapter17;

public class PauseButton {
    private  volatile boolean mPlaying;
    private  volatile boolean mPaused;

    public  boolean getmPlaying() { return mPlaying; }
    public  boolean getmPaused() { return mPaused; }

    public void setmPaused(boolean mPaused) {
        this.mPaused = mPaused;
    }

    public  void setmPlaying(boolean mPlaying) {
        this.mPlaying = mPlaying;
    }

    PauseButton(){
        setmPlaying(false);
        setmPaused(true);
    }
}
