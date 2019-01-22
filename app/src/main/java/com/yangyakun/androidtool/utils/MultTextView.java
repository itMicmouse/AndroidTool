package com.yangyakun.androidtool.utils;

import android.content.res.Resources;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;

import com.yangyakun.androidtool.R;
import com.yangyakun.androidtool.app.BaseApplication;

/**
 * Created by android on 2017/6/5.
 */

public class MultTextView {
    public static SpannableStringBuilder getStyleMoney(String str) {
        if (TextUtils.isEmpty(str)) {
            return new SpannableStringBuilder("");
        }
        try {
            AbsoluteSizeSpan size20ASS = new AbsoluteSizeSpan(BaseApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.textsize_50));
            str = "待就诊"+"(共"+str+"人)";
            SpannableStringBuilder ssb = new SpannableStringBuilder(str);
            ssb.setSpan(size20ASS, 3, ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return ssb;
        } catch (Resources.NotFoundException e) {
            return new SpannableStringBuilder(str);
        }
    }
}
