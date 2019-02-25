package com.example.a.beatbox_mvvm.model;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;

public class BeatBox {
    private static final String TAG = "BeatBox";
    private static final String SOUNDS_FOLDER = "sample_sounds";
    private AssetManager mAssets;

    public BeatBox(Context context){
        mAssets = context.getAssets();
        loadSound();
    }
    private void loadSound(){
        String[] soundNames;
        try {
            soundNames = mAssets.list(SOUNDS_FOLDER);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
