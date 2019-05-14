package com.example.lib_generic.utils;

import android.media.AudioManager;
import android.media.SoundPool;

import com.example.lib_generic.R;
import com.example.lib_generic.base.SfyBaseApplication;

/**
 * 铃声等短小音频播放
 */
public class RingUtils {
    private static RingUtils instance = null;

    private SoundPool mSoundPool;
    private int         mStreamId;

    public static RingUtils getInstance() {
        if (instance == null) {
            synchronized (RingUtils.class) {
                if (instance == null) {
                    instance = new RingUtils();
                }

                return instance;
            }
        }

        return instance;
    }

    //播放铃声
    public void play() {
        if (null == mSoundPool) {
            mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
            mSoundPool.load(SfyBaseApplication.getContext(), R.raw.call_ring, 1);

        }
        mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                mStreamId = mSoundPool.play(sampleId, 1, 1, 0, -1, 1);
            }
        });
    }

    //关闭铃声
    public void stop() {
        if (null != mSoundPool) {
            //必须关闭循环，否则在调用stop之后，有可能会遇到声音继续播放问题。
            mSoundPool.setLoop(mStreamId, 0);
            mSoundPool.stop(mStreamId);
            mSoundPool.release();
            mSoundPool = null;
        }
    }

    /**
     * 设置音量
     * @param volume    0~1之间百分比
     */
    public void setVolume(float volume) {
        if (null != mSoundPool) {
            mSoundPool.setVolume(mStreamId, volume, volume);
        }
    }
}
