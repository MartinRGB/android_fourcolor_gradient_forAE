package com.martinrgb.fcgradient;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by MartinRGB on 2018/2/9.
 */

public class LinearGradientView extends View {
    public void setGradientColor(int[] colorInt) {
        this.colorInt = colorInt;
    }

    public void setGradientWeight(float[] weightFloat) {
        this.weightFloat = weightFloat;
    }

    public void setGradientPos(float[] posFloat){
        this.posFloat = posFloat;
    }

    int[] colorInt = {
            0xFF1e5799,
            0xFF3f403b,
            0xFF2989d8,
            0xFF207cca};
    float[] weightFloat = {
            0, 0.40f, 0.60f, 0.80f
    };

    float[] posFloat ={
            1.0f, 0.f, 0.f, 1.f
    };




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth(),height = getHeight();

        Shader shader = new LinearGradient(width *posFloat[0] , height*posFloat[1], width*posFloat[2], height*posFloat[3],
                colorInt,
                weightFloat,
                    Shader.TileMode.REPEAT);
        Paint paint = new Paint();
        paint.setShader(shader);



        canvas.drawRect(new RectF(0, 0, width, height), paint);
    }

    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld){
        super.onSizeChanged(xNew, yNew, xOld, yOld);
    }


    public LinearGradientView(Context context) {
        super(context);
    }

    public LinearGradientView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearGradientView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

}
