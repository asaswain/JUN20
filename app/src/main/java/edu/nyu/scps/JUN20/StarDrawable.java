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
 * Created by swaina on 6/21/15.
 */

public class StarDrawable extends Drawable {
    private Paint paint;
    private Paint textPaint = new Paint();
    private PointF center;  //of  triangle
    private float radius;	//of circumscribed circle

    public StarDrawable(float x, float y, float radius, Paint paint) {
        super();
        this.paint = paint;
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(radius / 5);
        center = new PointF(x, y);
        this.radius = radius;
        int b = (int)radius;
        setBounds(-b, b, b, -b); //left, top, right, bottom
    }

    @Override
    public void draw(Canvas canvas) {
        int n = 3;	//number of vertices; try 5 for a pentagon
        Path path = new Path();

        /*
        Assume X axis points right, Y axis points up.
        Angles are measured counterclockwise from 3 o'clock.
        First vertex points up (to 90 degrees).
        Second vertex points to lower left (to 210 degrees).
        Third vertex points to lower right (to 330 degrees).
        */
        for (int i = 0; i < n; ++i) {
            float degrees = i * 360 / n - 90;
            float theta = (float)Math.toRadians(degrees); //convert degrees to radians
            float x = center.x + radius * (float)Math.cos(theta);
            float y = center.y + radius * (float)Math.sin(theta);

            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }

        path.close();
        canvas.drawPath(path, paint);

        /*
        Assume X axis points right, Y axis points up.
        Angles are measured counterclockwise from 3 o'clock.
        First vertex points up (to 90 degrees).
        Second vertex points to lower left (to 210 degrees).
        Third vertex points to lower right (to 330 degrees).
        */
        for (int i = 0; i < n; ++i) {
            float degrees = i * 360 / n - 90;
            float theta = (float)Math.toRadians(degrees); //convert degrees to radians
            float x = center.x + radius * (float)Math.cos(theta);
            float y = center.y + radius * (float)Math.sin(theta) * -1;

            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }

        path.close();
        canvas.drawPath(path, paint);
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
