package com.martinrgb.fcgradient;

/**
 * Created by MartinRGB on 2018/2/9.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

public class FourColorGradientView extends View {

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth(),height = getHeight();
        int radius = width>=height? height:width;

        // Linear Gradient
        Paint linearPaint = new Paint();
        linearPaint.setShader(new LinearGradient(0, getHeight(), getWidth(), 0,
                new int[]{Color.BLUE,Color.YELLOW}, //substitute the correct colors for these
                new float[]{
                        0, 1.0f},
                Shader.TileMode.REPEAT));
//        Matrix matrix = new Matrix();
//        matrix.setRotate(0);
//        shader.setLocalMatrix(matrix);
        canvas.drawRect(new RectF(0, 0, getWidth(), getHeight()), linearPaint);


        // Left Top Radial Gradient 1
        Paint leftTopRadialGradient = new Paint();
        leftTopRadialGradient.setShader(new RadialGradient(0, 0,
                radius, Color.RED, Color.TRANSPARENT, Shader.TileMode.MIRROR));
        canvas.drawCircle(0, 0, radius, leftTopRadialGradient);

        // Right Bottom Radial Gradient 1
        Paint rightBottomRadialGradient = new Paint();
        rightBottomRadialGradient.setShader(new RadialGradient(width, height,
                radius, Color.CYAN, Color.TRANSPARENT, Shader.TileMode.MIRROR));
        canvas.drawCircle(width, height, radius, rightBottomRadialGradient);

    }


    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld){
        super.onSizeChanged(xNew, yNew, xOld, yOld);
    }

    public FourColorGradientView(Context context) {
        super(context);
    }

    public FourColorGradientView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FourColorGradientView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
