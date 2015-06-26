package edu.nyu.scps.JUN20;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by swaina on 6/25/15.
 */
public class SketchPadImage {

    private float xCoor;
    private float yCoor;
    private float size;
    private String shape;
    private Paint color;

    public SketchPadImage() {
        this.xCoor = 0;
        this.yCoor = 0;
        this.size = 0;
        this.shape = "";
        this.color = null;
    }

    public SketchPadImage(float xCoor, float yCoor, float size, String shape, Paint color) {
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        this.size = size;
        this.shape = shape;
        this.color = color;
    }

    public float getXCoor() {
        return xCoor;
    }

    public float getYCoor() {
        return yCoor;
    }

    public float getSize() {
        return size;
    }

    public Paint getColor() {
        return color;
    }

    public String getShape() {
        return shape;
    }

    public void setXCoor(float xCoor) {
        this.xCoor = xCoor;
    }

    public void setYCoor(float yCoor) {
        this.yCoor = yCoor;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public void setColor(Paint color) {
        this.color = color;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public void draw(Canvas canvas) {
        if (shape.equals("Circle")) {
            CircleDrawable tmp = new CircleDrawable(xCoor, yCoor, size, color);
            tmp.draw(canvas);
        }

        if (shape.equals("Square")) {
            SquareDrawable tmp = new SquareDrawable(xCoor, yCoor, size, color);
            tmp.draw(canvas);
        }

        if (shape.equals("Triangle")) {
            TriangleDrawable tmp = new TriangleDrawable(xCoor, yCoor, size, color);
            tmp.draw(canvas);
        }

        if (shape.equals("Star")) {
            StarDrawable tmp = new StarDrawable(xCoor, yCoor, size, color);
            tmp.draw(canvas);
        }
    }

}
