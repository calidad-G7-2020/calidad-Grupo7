package com.example.tetris;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.annotation.RawRes;

    public class AudioService {
        static final int DECREASE = 1, INCREASE = 2, START = 3, PAUSE = 4;
        Boolean shouldPause = false;
        MediaPlayer loop;

        private void inicioLoop(Context c, @RawRes int s){
            if(loop==null) loop = MediaPlayer.create(c, s);

            if(!loop.isPlaying()){
                loop.setLooping(true);
                loop.start();
            }
        }
        private void increase(){
            loop.setVolume(0.2f, 0.2f);

        }
        private void decrease(){
            loop.setVolume(1.0f, 1.0f);
        }

        public void start(Context c, @RawRes int s){
            inicioLoop(c,s);
            shouldPause = false;
        }
        public void pause(){
            shouldPause = true;
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            if(shouldPause) {
                                loop.pause();
                            }
                        }
                    }, 100);
        }
    }
