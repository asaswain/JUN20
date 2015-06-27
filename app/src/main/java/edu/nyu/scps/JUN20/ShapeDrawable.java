package edu.nyu.scps.JUN20;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

/**
 * This abstract class stores all the methods for setting the data for the various shapes we want to draw on the canvas
 */
public abstract class ShapeDrawable extends Drawable {

    private float xCoor;
    private float yCoor;
    private float size;
    private String shape;
    private Paint color;

    public ShapeDrawable() {
        this.xCoor = 0;
        this.yCoor = 0;
        this.size = 0;
        this.shape = "";
        this.color = null;
    }

    public ShapeDrawable(float xCoor, float yCoor, float size, String shape, Paint color) {
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

    abstract public void draw(Canvas canvas);
}
