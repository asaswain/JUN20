package edu.nyu.scps.JUN20;

/**
 * Created by swaina on 6/21/15.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class SketchPadView extends View {

    private ArrayList<SketchPadImage> imageList;
    private float scale;
    private String shape;
    private Paint paint;
    private SketchPadImage cursor;

    public SketchPadView(Context context) {
        super(context);

        // initialize class variables
        imageList = new ArrayList<SketchPadImage>();
        scale = 0.1f;
        shape = "Circle";
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        setPaintColor(Color.WHITE);
        cursor = null;

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                float radius;
                Paint tmpPaint = new Paint();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // create new point object

                        radius = scale * Math.min(getWidth(), getHeight());

                        // copy paint object before inserting into array (to avoid pointing to original which will change)
                        tmpPaint = new Paint(paint);

                        cursor = new SketchPadImage(event.getX(), event.getY(), radius, shape, tmpPaint);
                        invalidate();    //call onDraw method of TouchView

                        return true;    //do nothing else

                    case MotionEvent.ACTION_UP:
                        imageList.add(cursor);
                        cursor = null;
                        invalidate();    //call onDraw method of TouchView

                        return false;    //do nothing else

                    case MotionEvent.ACTION_MOVE:
                        radius = scale * Math.min(getWidth(), getHeight());

                        // copy paint object before inserting into array (to avoid pointing to original which will change)
                        tmpPaint = new Paint(paint);

                        cursor = new SketchPadImage(event.getX(), event.getY(), radius, shape, tmpPaint);
                        invalidate();    //call onDraw method of TouchView

                        return true;    //do nothing else

                    default:
                        return false;
                }
            }
        });
    }

    public void setRadius(float scale) {
        this.scale = scale;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    /**
     * Set color pf paintbrush
     * @param paintColor - new color
     */
    public void setPaintColor(int paintColor) {
        paint.setColor(paintColor);
    }

    /**
     * Clear all graphics from SketchPad
     */
    public void eraseSketchPad() {
        imageList.clear();
        invalidate();    //call onDraw method of TouchView
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int width = getWidth();
        final int height = getHeight();
        float radius = .1f * Math.min(width, height);

        canvas.drawColor(Color.WHITE);    //background

        for (int i = 0; i < imageList.size(); ++i) {
             // get a SketchPad image from the list and draw it
             imageList.get(i).draw(canvas);
        }

        if (cursor != null) {
            cursor.draw(canvas);
        }
    }
}
