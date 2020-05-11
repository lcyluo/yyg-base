package com.yyg.common.widget;

import android.widget.TextView;

import com.yyg.common.R;
import com.yyg.common.constant.CommonConstants;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * 播放器倒计时功能
 * Created by lcy on 17/4/28.
 * email:lcyzxin@gmail.com
 * version 1.0
 */

public class CodeTimer extends LcyCountDownTimer {

    private TextView mBtn;
    private int mEndStrRid;
    private static final long DEFAULT_TIME_COUNT = CommonConstants.CODE_INTERVAL;

    public CodeTimer(TextView btn) {
        this(DEFAULT_TIME_COUNT, 1000, btn, R.string.common_get_code);
    }

    public CodeTimer(long millisInFuture, TextView btn) {
        this(millisInFuture, 1000, btn, R.string.common_get_code);
    }

    public CodeTimer(TextView btn, long millisInFuture, int endStrRid) {
        this(millisInFuture, 1000, btn, endStrRid);
    }

    public CodeTimer(long millisInFuture, long countDownInterval, TextView btn, int endStrRid) {
        super(millisInFuture, countDownInterval);
        this.mBtn = btn;
        this.mEndStrRid = endStrRid;
    }

    @Override
    public void onTick(long millisUntilFinished, int percent) {
        SimpleDateFormat mm = new SimpleDateFormat("mm", Locale.CHINA);
        SimpleDateFormat ss = new SimpleDateFormat("ss", Locale.CHINA);
        int seconds = Integer.parseInt(mm.format(millisUntilFinished)) * 60 + Integer.parseInt(ss.format(millisUntilFinished));
        if (mBtn == null) return;
        mBtn.setEnabled(false);
        mBtn.setText(mBtn.getContext().getString(R.string.common_code_interval, seconds));
    }

    @Override
    public void onFinish() {
        cancel();
        if (mBtn == null) return;
        mBtn.setEnabled(true);
        mBtn.setText(mEndStrRid);
    }

}
