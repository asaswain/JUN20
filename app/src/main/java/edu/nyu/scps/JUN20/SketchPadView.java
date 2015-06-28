package edu.nyu.scps.JUN20;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * The SketchPadView draws various shapes on a canvas using an OnTouchListener
 */
public class SketchPadView extends View {

    private ArrayList<ShapeDrawable> imageList;
    private float scale;
    private String shape;
    private Paint paint;
    private ShapeDrawable cursorImage;
    private String drawType;

    public SketchPadView(Context context) {
        super(context);

        setListeners();
    }

    // used when constructing object from layout activity_main.xml file
    public SketchPadView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        setListeners();
    }

    // set up listeners for SketchPadView object
    private void setListeners() {

        // initialize class variables
        imageList = new ArrayList<ShapeDrawable>();
        scale = 0.1f;
        shape = "Circle";
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        setPaintColor(Color.WHITE);
        cursorImage = null;
        drawType = "Stamp";

        // anonymous class because we're only creating one listener
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                float radius;
                Paint tmpPaint;

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // create new point object

                        radius = scale * Math.min(getWidth(), getHeight());

                        // copy paint object before inserting into array (to avoid pointing to original which will change)
                        tmpPaint = new Paint(paint);

                        cursorImage = buildImage(event.getX(), event.getY(), radius, shape, tmpPaint);
                        invalidate();    //call onDraw method of TouchView

                        return true;    //do nothing else

                    case MotionEvent.ACTION_UP:
                        imageList.add(cursorImage);
                        cursorImage = null;
                        invalidate();    //call onDraw method of TouchView

                        return false;    //do nothing else

                    case MotionEvent.ACTION_MOVE:
                        radius = scale * Math.min(getWidth(), getHeight());

                        // copy paint object before inserting into array (to avoid pointing to original which will change)
                        tmpPaint = new Paint(paint);

                        cursorImage = buildImage(event.getX(), event.getY(), radius, shape, tmpPaint);
                        if (drawType.equals("Brush")) {
                            imageList.add(cursorImage);
                        }

                        invalidate();    //call onDraw method of TouchView

                        return true;    //do nothing else

                    default:
                        return false;
                }
            }
        });
    }

    // set size of shapr
    public void setSize(float scale) {
        this.scale = scale;
    }

    // set type of shape
    public void setShape(String shape) {
        this.shape = shape;
    }

    // set type of brush used to draw shape
    public void setDrawType(String drawType) {
        this.drawType = drawType;
    }

    // build drawable object depending on shape parameter
    private ShapeDrawable buildImage(float xCoor, float yCoor, float size, String shape, Paint color) {
        if (shape.equals("Circle")) {
            return new CircleDrawable(xCoor, yCoor, size, color);
        } else if (shape.equals("Square")) {
            return new SquareDrawable(xCoor, yCoor, size, color);
        } else if (shape.equals("Triangle")) {
            return new TriangleDrawable(xCoor, yCoor, size, color);
        } else if (shape.equals("Star")) {
            return new StarDrawable(xCoor, yCoor, size, color);
        } else {
            return null;
        }
    }

    // Set color of paintbrush
    public void setPaintColor(int paintColor) {
        paint.setColor(paintColor);
    }

    // Clear all drawable objects from SketchPad
    public void eraseSketchPad() {
        imageList.clear();
        invalidate();    //call onDraw method of TouchView
    }

    // draw all the shapes onto the canvas
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);    //background

        for (int i = 0; i < imageList.size(); ++i) {
            // get a SketchPad image from the list and draw it
            ShapeDrawable savedImage = imageList.get(i);
            savedImage.draw(canvas);
        }

        if (cursorImage != null) {
            cursorImage.draw(canvas);
        }
    }

}
