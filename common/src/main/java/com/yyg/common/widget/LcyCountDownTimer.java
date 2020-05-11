package com.yyg.common.widget;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * 支持暂停的CountDownTimer
 * Created by lcy on 17/4/28.
 * email:lcyzxin@gmail.com
 * version 1.0
 */

public abstract class LcyCountDownTimer {

    private final long mCountdownInterval;
    private long mTotalTime;
    private long mRemainTime;

    private MyHandler mHandler = new MyHandler(this);

    public LcyCountDownTimer(long millisInFuture, long countDownInterval) {
        mTotalTime = millisInFuture;
        mCountdownInterval = countDownInterval;
        mRemainTime = millisInFuture;
    }

    public final void seek(int value) {
        synchronized (LcyCountDownTimer.this) {
            mRemainTime = ((100 - value) * mTotalTime) / 100;
        }
    }

    public final void cancel() {
        mHandler.removeMessages(MSG_RUN);
        mHandler.removeMessages(MSG_PAUSE);
    }

    public final void resume() {
        mHandler.removeMessages(MSG_PAUSE);
        mHandler.sendMessageAtFrontOfQueue(mHandler.obtainMessage(MSG_RUN));
    }

    public final void pause() {
        mHandler.removeMessages(MSG_RUN);
        mHandler.sendMessageAtFrontOfQueue(mHandler.obtainMessage(MSG_PAUSE));
    }


    public synchronized final LcyCountDownTimer start() {
        if (mRemainTime <= 0) {
            onFinish();
            return this;
        }
        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_RUN), mCountdownInterval);
        return this;
    }

    public abstract void onTick(long millisUntilFinished, int percent);

    public abstract void onFinish();

    private static final int MSG_RUN = 1;
    private static final int MSG_PAUSE = 2;

    private static class MyHandler extends Handler {
        WeakReference<LcyCountDownTimer> mTimer;

        MyHandler(LcyCountDownTimer timer) {
            mTimer = new WeakReference<>(timer);
        }

        @Override
        public void handleMessage(Message msg) {
            LcyCountDownTimer timer = mTimer.get();
            if (msg.what == MSG_RUN) {
                timer.mRemainTime = timer.mRemainTime - timer.mCountdownInterval;
                if (timer.mRemainTime < 2000) {
                    timer.cancel();
                    timer.onFinish();
                } else if (timer.mRemainTime < timer.mCountdownInterval) {
                    sendMessageDelayed(obtainMessage(MSG_RUN), timer.mRemainTime);
                } else {
                    timer.onTick(timer.mRemainTime, Long.valueOf(100 * (timer.mTotalTime - timer.mRemainTime) / timer.mTotalTime).intValue());
                    sendMessageDelayed(obtainMessage(MSG_RUN), timer.mCountdownInterval);
                }
            } else if (msg.what == MSG_PAUSE) {

            }
        }
    }

}
