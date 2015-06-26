package edu.nyu.scps.JUN20;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;

/**
 * Created by swaina on 6/25/15.
 */
public class SquareDrawable extends Drawable {
    private Paint paint;
    private Paint textPaint = new Paint();
    private PointF center;  //of square
    private float radius;	//of circumscribed circle

    public SquareDrawable(float x, float y, float radius, Paint paint) {
        super();
        this.paint = paint;
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(radius / 5);
        center = new PointF(x, y);
        this.radius = radius;
        int b = (int) radius;
        setBounds(-b, b, b, -b); //left, top, right, bottom
    }

    @Override
    public void draw(Canvas canvas) {
        Path path = new Path();

        /*
        Assume X axis points right, Y axis points up.
        */

        float minX = center.x - radius;
        float minY = center.y - radius;

        float maxX = center.x + radius;
        float maxY = center.y + radius;

        canvas.drawRect(minX, minY, maxX, maxY, paint);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        paint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

}

