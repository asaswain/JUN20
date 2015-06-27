package edu.nyu.scps.JUN20;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PointF;

/**
 * This class draws a circle on the canvas
 */
public class CircleDrawable extends ShapeDrawable {
    private Paint paint;
    private PointF center;  //of square
    private float radius;	//of circumscribed circle

    public CircleDrawable(float x, float y, float radius, Paint paint) {
        super();
        this.paint = paint;
        center = new PointF(x, y);
        this.radius = radius;
        int b = (int) radius;
        setBounds(-b, b, b, -b); //left, top, right, bottom
    }

    @Override
    public void draw(Canvas canvas) {
        Path path = new Path();

        canvas.drawCircle(center.x, center.y, radius, paint);
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
