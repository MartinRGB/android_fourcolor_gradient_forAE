package com.martinrgb.fcgradient;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by MartinRGB on 2018/2/8.
 */

public class MiscUtils {

    public static float widthDP,heightDP;
    public static int widthPX,heightPX;

    public MiscUtils(AppCompatActivity mActivity,Context ctx) {
        hideBars(mActivity);
        MeasureInDP(mActivity,ctx);
        MeasureInPX(mActivity);
    }

    public static void hideBars(AppCompatActivity mActivity){
        mActivity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mActivity.getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        mActivity.getSupportActionBar().hide();
    }

    public static void MeasureInDP(AppCompatActivity mActivity, Context context) {
        Display display = mActivity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics ();
        display.getMetrics(outMetrics);
        float density  = context.getResources().getDisplayMetrics().density;
        widthDP = outMetrics.heightPixels / density;
        heightDP = outMetrics.widthPixels / density;

        Log.e("inDP","DPHeight " + heightDP + "DPWidth " + widthDP);
    }

    public static void MeasureInPX(AppCompatActivity mActivity){
        Display display = mActivity.getWindowManager().getDefaultDisplay();
        widthPX = display.getWidth();
        heightPX = display.getHeight();

        Log.e("inPx","Height " + heightPX + "Width " + widthPX);
    }


    public static float getWidthDP() {
        return widthDP;
    }

    public static float getHeightDP() {
        return heightDP;
    }

    public static int getWidthPX() {
        return widthPX;
    }

    public static int getHeightPX() {
        return heightPX;
    }


}
