package com.dks.mybeatgrid;

/**
 * Created by Raj on 26-12-2016.
 */
public class Sound {
    private String mAssetPath;
    private String mName;
    private Integer soundID;

    public Sound(String assetPath){
        mAssetPath = assetPath;
        String[] component = assetPath.split("/");
        String fileName = component[component.length-1];
        mName = fileName.replace(".wav", " ");
    }

    public String getAssetPath(){
        return mAssetPath;
    }

    public String getName(){
        return mName;
    }

    public Integer getSoundID(){
        return soundID;
    }

    public void setSoundID(Integer soundId){
        soundID = soundId;
    }
}
