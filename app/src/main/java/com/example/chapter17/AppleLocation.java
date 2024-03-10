package com.example.chapter17;

import android.content.Context;
import android.graphics.Point;
import java.util.Random;

public class AppleLocation extends Apple{
    AppleLocation(Context context, Point sr, int s) {
        super(context, sr, s);
    }

    // This is called every time an apple is eaten
    static void spawn(){
        // Choose two random values and place the apple
        Random random = new Random();
        getLocation().x = random.nextInt(getmSpawnRange().x) + 1;
        getLocation().y = random.nextInt(getmSpawnRange().y - 1) + 1;
    }
}
