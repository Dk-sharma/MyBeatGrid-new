package com.dks.mybeatgrid;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raj on 26-12-2016.
 */
public class BeatGrid {
    private static final String TAG ="BeatGrid";
    private static final String SOUNDS_FOLDER ="sample_sounds";
    private static final int MAX_SOUNDS = 5;

    private List<Sound> mSoundList = new ArrayList<>();
    private AssetManager mAssets;
    private SoundPool mSoundPool;

    public BeatGrid(Context context){
        mAssets = context.getAssets();
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC,0);
        loadSounds();
    }

    public void play(Sound sound){
        Integer soundId = sound.getSoundID();
        if(soundId == null){
            return;
        }
        mSoundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    private void loadSounds(){
        String[] soundNames;
        try {
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG,"Founds "+soundNames.length+" sounds");
        }catch (IOException ioe){
            Log.e(TAG, "Could not list",ioe);
            return;
        }

        for(String filename : soundNames){
            try {
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                Sound mSound = new Sound(assetPath);
                load(mSound);
                mSoundList.add(mSound);
            }catch (IOException ioe){
                Log.e(TAG, "Could not load sound "+filename, ioe);

            }
        }
    }
    private void load(Sound sound) throws IOException{
        AssetFileDescriptor afd = mAssets.openFd(sound.getAssetPath());
        int soundId = mSoundPool.load(afd, 1);
        sound.setSoundID(soundId);
    }

    public List<Sound> getSounds(){
        return mSoundList;
    }
    public void release(){
        mSoundPool.release();
    }
}
