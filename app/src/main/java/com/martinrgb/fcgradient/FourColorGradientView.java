package com.martinrgb.fcgradient;

/**
 * Created by MartinRGB on 2018/2/9.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class FourColorGradientView extends View {


    long start_time;
    private Paint linearPaint,leftTopRadialGradient,rightBottomRadialGradient;

    public FourColorGradientView(Context context) {
        super(context);
        init();
    }

    public FourColorGradientView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FourColorGradientView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        start_time = SystemClock.elapsedRealtime();
        linearPaint = new Paint();
        leftTopRadialGradient = new Paint();
        rightBottomRadialGradient = new Paint();
        PorterDuffXfermode mode = new PorterDuffXfermode(PorterDuff.Mode.DARKEN);
        PorterDuffXfermode mode2 = new PorterDuffXfermode(PorterDuff.Mode.SCREEN);
        leftTopRadialGradient.setXfermode(mode);
        rightBottomRadialGradient.setXfermode(mode2);
    }

    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld){
        super.onSizeChanged(xNew, yNew, xOld, yOld);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int radius = width>=height? height:width;

        float costTime = (float)(SystemClock.elapsedRealtime() - start_time)/1000.0f;
        // Linear Gradient

        linearPaint.setShader(new LinearGradient(0, height, width, 0 ,
                new int[]{Color.BLUE,Color.YELLOW}, //substitute the correct colors for these
                new float[]{
                        0, 1.0f},
                Shader.TileMode.REPEAT));
//        Matrix matrix = new Matrix();
//        matrix.setRotate(0);
//        shader.setLocalMatrix(matrix);
        canvas.drawRect(new RectF(0, 0, width, height), linearPaint);


        // Left Top Radial Gradient 1
        leftTopRadialGradient.setShader(new RadialGradient(0, (float)Math.sin(costTime)*width/2, //0
                radius*1.5f, Color.RED, Color.TRANSPARENT, Shader.TileMode.MIRROR));
        canvas.drawCircle(0 , 0, radius*3.f, leftTopRadialGradient);


        // Right Bottom Radial Gradient 1
        rightBottomRadialGradient.setShader(new RadialGradient(width+(float)Math.sin(costTime)*width/2,height,
                radius*1.5f, Color.CYAN, Color.TRANSPARENT, Shader.TileMode.MIRROR));
        canvas.drawCircle(width, height, radius*3.f, rightBottomRadialGradient);


        postInvalidate();
    }

}
