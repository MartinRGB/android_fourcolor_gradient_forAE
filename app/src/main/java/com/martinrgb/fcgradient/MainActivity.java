package com.martinrgb.fcgradient;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private LinearGradientView v1;
    private FourColorGradientView v2;
    private int DRAW_LINEAR_GRADIENT = 0;
    private int DRAW_FOUR_COLOR_GRADIENT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MiscUtils miscUtils = new MiscUtils(this,this.getApplicationContext());
        setContentView(R.layout.activity_main);

        v1 = findViewById(R.id.lv);
        int[] colorArray = new int[]{Color.RED,Color.BLUE, Color.GREEN};
        v1.setGradientColor(colorArray);
        float[] weightArray = new float[]{0,0.5f,1.0f};
        v1.setGradientWeight(weightArray);
        float[] posArray = new float[]{0,0.5f,1.f,0.5f};
        v1.setGradientPos(posArray);
    }




}
