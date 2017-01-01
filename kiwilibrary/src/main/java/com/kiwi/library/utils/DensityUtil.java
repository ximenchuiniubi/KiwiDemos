package com.kiwi.library.utils;

import android.content.Context;

/**
 * 像素密度
 * Created by Kiwi on 2017/1/1.
 */

public class DensityUtil {
    /**
     * dp转px
     * @param context
     * @param dpValue dp值
     * @return
     */
    public static int dipTopx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转dp
     * @param context
     * @param pxValue px值
     * @return
     */
    public static int pxTodip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
