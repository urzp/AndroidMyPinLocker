package com.example.my_pinlocker;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

/**
 * Used to always maintain an LTR layout no matter what is the real device's layout direction
 * to avoid an unwanted reversed direction in RTL devices
 * Created by Idan on 7/6/2017.
 */

public class LTRGridLayoutManager extends GridLayoutManager {

    public LTRGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    @Override
    protected boolean isLayoutRTL(){
        return false;
    }
}
