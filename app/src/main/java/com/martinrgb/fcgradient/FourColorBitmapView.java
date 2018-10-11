package com.martinrgb.fcgradient;

/**
 * Created by MartinRGB on 2018/2/9.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
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

public class FourColorBitmapView extends View {

    private Paint linearPaint;
    private Paint radialPaint1;
    private Paint radialPaint2;


    public FourColorBitmapView(Context context) {
        super(context);
        init();
    }

    public FourColorBitmapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FourColorBitmapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    long start_time;
    private void init(){
        start_time = SystemClock.elapsedRealtime();
        linearPaint = new Paint();
        radialPaint1 = new Paint();
        radialPaint2 = new Paint();
//        PorterDuffXfermode mode = new PorterDuffXfermode(PorterDuff.Mode.DARKEN);
//        radialPaint1.setXfermode(mode);
//        radialPaint2.setXfermode(mode);
    }
    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld){
        super.onSizeChanged(xNew, yNew, xOld, yOld);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth(),height = getHeight();
        int radius = width>=height? height:width;

        float costTime = (float)(SystemClock.elapsedRealtime() - start_time)/1000.0f;
        // Way 1
        Bitmap linearBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ALPHA_8);

//        linearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DARKEN));
        Canvas linearCanvas = new Canvas(linearBitmap);
        linearPaint.setShader(new LinearGradient(0, height, width, 0,
                new int[]{Color.BLUE,Color.YELLOW}, //substitute the correct colors for these
                new float[]{
                        0, 1.0f},
                Shader.TileMode.REPEAT));

        linearCanvas.drawRect(0, 0, width, height, linearPaint);
        canvas.drawBitmap(linearBitmap, 0,0, linearPaint);
        linearBitmap.recycle();


        // Way 2:
//        Bitmap radialBitmap3 = Bitmap.createBitmap(radius, radius, Bitmap.Config.ALPHA_8);
//        Paint radialPaint3 = new Paint();
//        Canvas radialCanvas3 = new Canvas(radialBitmap3);
//        radialPaint3.setShader(new RadialGradient(0, height,
//                radius, Color.BLUE, Color.TRANSPARENT, Shader.TileMode.MIRROR));
//
//        radialCanvas3.drawCircle(0, height, radius, radialPaint3);
//        canvas.drawBitmap(radialBitmap3, 0,0, radialPaint3);
//        radialBitmap3.recycle();
//
//        Bitmap radialBitmap4 = Bitmap.createBitmap(radius, radius, Bitmap.Config.ALPHA_8);
//        Paint radialPaint4 = new Paint();
//        Canvas radialCanvas4 = new Canvas(radialBitmap4);
//        radialPaint4.setShader(new RadialGradient(width, 0,
//                radius, Color.YELLOW, Color.TRANSPARENT, Shader.TileMode.MIRROR));
//
//        radialCanvas4.drawCircle(width, 0, radius, radialPaint4);
//        canvas.drawBitmap(radialBitmap4, 0,0, radialPaint4);
//        radialBitmap4.recycle();



        Bitmap radialBitmap1 = Bitmap.createBitmap(radius, radius, Bitmap.Config.ALPHA_8);

        Canvas radialCanvas1 = new Canvas(radialBitmap1);
//        radialPaint1.setShader(new RadialGradient(0+(float)Math.cos(costTime)*500.f, 0+(float)Math.cos(costTime)*500.f,
//                radius, Color.RED, Color.TRANSPARENT, Shader.TileMode.MIRROR));

        radialPaint1.setShader(new RadialGradient(0, 0,
                radius, Color.RED, Color.TRANSPARENT, Shader.TileMode.MIRROR));


        radialCanvas1.drawCircle(0, 0, radius, radialPaint1);
        canvas.drawBitmap(radialBitmap1, 0,0, radialPaint1);
        radialBitmap1.recycle();

        Bitmap radialBitmap2 = Bitmap.createBitmap(radius, radius, Bitmap.Config.ALPHA_8);

//        radialPaint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN));
        Canvas radialCanvas2 = new Canvas(radialBitmap2);
//        radialPaint2.setShader(new RadialGradient(width+(float)Math.sin(costTime)*500.f, height+(float)Math.sin(costTime)*500.f,
//                radius, Color.CYAN, Color.TRANSPARENT, Shader.TileMode.MIRROR));

        radialPaint2.setShader(new RadialGradient(width, height,
                radius, Color.CYAN, Color.TRANSPARENT, Shader.TileMode.MIRROR));

        radialCanvas2.drawCircle(width, height, radius, radialPaint2);
        canvas.drawBitmap(radialBitmap2, 0,0, radialPaint2);
        radialBitmap2.recycle();

        // Repeat

        //postInvalidate();

    }


    // referenced from http://blog.johnnovak.net/2016/09/21/what-every-coder-should-know-about-gamma/
    // referenced from https://stackoverflow.com/questions/21423670/4-color-gradient-using-java-awt/27632287#27632287
    public static Bitmap doGamma(Bitmap src, double red, double green, double blue) {
        // create output image
        Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
        // get image size
        int width = src.getWidth();
        int height = src.getHeight();
        // color information
        int A, R, G, B;
        int pixel;
        // constant value curve
        final int    MAX_SIZE = 256;
        final double MAX_VALUE_DBL = 255.0;
        final int    MAX_VALUE_INT = 255;
        final double REVERSE = 1.0;

        // gamma arrays
        int[] gammaR = new int[MAX_SIZE];
        int[] gammaG = new int[MAX_SIZE];
        int[] gammaB = new int[MAX_SIZE];

        // setting values for every gamma channels
        for(int i = 0; i < MAX_SIZE; ++i) {
            gammaR[i] = (int)Math.min(MAX_VALUE_INT,
                    (int)((MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / red)) + 0.5));
            gammaG[i] = (int)Math.min(MAX_VALUE_INT,
                    (int)((MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / green)) + 0.5));
            gammaB[i] = (int)Math.min(MAX_VALUE_INT,
                    (int)((MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / blue)) + 0.5));
        }

        // apply gamma table
        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                // get pixel color
                pixel = src.getPixel(x, y);
                A = Color.alpha(pixel);
                // look up gamma
                R = gammaR[Color.red(pixel)];
                G = gammaG[Color.green(pixel)];
                B = gammaB[Color.blue(pixel)];
                // set new color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }

        // return final image
        return bmOut;
    }




}
