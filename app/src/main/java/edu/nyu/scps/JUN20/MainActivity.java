package edu.nyu.scps.JUN20;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * This application displays a canvas and allows the user to select a color, a brush type, and a shape and draw on the canvas using a TouchListener
 */

public class MainActivity extends AppCompatActivity {

    SketchPadView sketchPad;

    final int shapeList[] = new int[] {
            edu.nyu.scps.JUN20.R.id.circle,
            edu.nyu.scps.JUN20.R.id.square,
            edu.nyu.scps.JUN20.R.id.triangle,
            edu.nyu.scps.JUN20.R.id.star,
    };

    final int brushList[] = new int[] {
            edu.nyu.scps.JUN20.R.id.brush,
            edu.nyu.scps.JUN20.R.id.stamp,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.nyu.scps.JUN20.R.layout.activity_main);

        Resources resources = getResources();

        // insert a SketchPadView view into the RelativeLayout for user to draw on

        final RelativeLayout sketchPadLayout = (RelativeLayout) findViewById(edu.nyu.scps.JUN20.R.id.paper);
        sketchPad = new SketchPadView(MainActivity.this);
        sketchPadLayout.addView(sketchPad);

        // create a listener for the seekbar which controls the size of the image drawn by the user

        SeekBar seekBar;
        seekBar = (SeekBar) findViewById(edu.nyu.scps.JUN20.R.id.size);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float scale = (float) (progress+1) / 100f;
                sketchPad.setSize(scale);
                //Log.d("XTag", "progress = " + progress);
            }
        });

        // create views for each of the `16 colors color and insert views into LinearLayout palette
        // (I created a list of colors used by a CGA16 monitor)

        int colorList[] = new int[16];
        for (int i = 0; i < colorList.length; ++i) {
            String colorid = "CGA16_" + (i+1);
            colorList[i] = resources.getIdentifier(colorid, "color", getPackageName());
        }

        final LinearLayout palette = (LinearLayout) findViewById(edu.nyu.scps.JUN20.R.id.palette);
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        int pixelWidth = displayMetrics.widthPixels;

        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                pixelWidth / colorList.length, // width
                ViewGroup.LayoutParams.MATCH_PARENT  //height
        );

        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, // width
                ViewGroup.LayoutParams.MATCH_PARENT  //height
        );

        // add listener for each color in color palette to control the color of the image drawn by the user

        ColorOnClickListener colorOnClickListener = new ColorOnClickListener();

        for (int i = 0; i < colorList.length; ++i) {
            View colorView = new View(MainActivity.this);
            colorView.setBackgroundColor(resources.getColor(colorList[i]));
            if (i < colorList.length-1) {
                colorView.setLayoutParams(layoutParams2);
            } else {
                colorView.setLayoutParams(layoutParams3);
            }
            palette.addView(colorView);
            // set an onClickListener for all the views in the color palette to enable us to change the paint color
            colorView.setOnClickListener(colorOnClickListener);
        }

        // add listener for each shape in the shape palette to control the shape of the image drawn by the user

        ShapeOnClickListener shapeOnClickListener = new ShapeOnClickListener();

        for (int i = 0; i < shapeList.length; ++i) {
            TextView shapeView = (TextView) findViewById(shapeList[i]);
            // set an onClickListener for all the views in the shape palette  to enable us to change the shape
            shapeView.setOnClickListener(shapeOnClickListener);
        }

        // add listener for each brush in the brush palette to control the type of image drawn by the user (brush vs stamp)

        BrushOnClickListener brushOnClickListener = new BrushOnClickListener();

        for (int i = 0; i < brushList.length; ++i) {
            TextView brushView = (TextView) findViewById(brushList[i]);
            // set an onClickListener for all the views in the shape palette  to enable us to change the shape
            brushView.setOnClickListener(brushOnClickListener);
        }

    }

    // when user clicks on a color sample, set sketchPad color property
    class ColorOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            ColorDrawable backgroundColor = (ColorDrawable) v.getBackground();
            sketchPad.setPaintColor(backgroundColor.getColor());

            // update color of Current Color View
            View currentColor = findViewById(edu.nyu.scps.JUN20.R.id.current_color);
            currentColor.setBackgroundColor(backgroundColor.getColor());
        }
    }

    // when user clicks on a shape, set sketchPad shape property
    class ShapeOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            TextView textView = (TextView) v;
            String text = textView.getText().toString();
            sketchPad.setShape(text);

            for (int i = 0; i < shapeList.length; ++i) {
                TextView shapeView = (TextView) findViewById(shapeList[i]);
                Log.d("shape", ""+shapeList[i]);
                shapeView.setBackgroundColor(Color.WHITE);
            }

            // update color of selected shape view
            v.setBackgroundColor(Color.YELLOW);
        }
    }

    // when user clicks on a shape, set sketchPad brush property
    class BrushOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            TextView textView = (TextView) v;
            String text = textView.getText().toString();
            sketchPad.setDrawType(text);

            for (int i = 0; i < brushList.length; ++i) {
                TextView brushView = (TextView) findViewById(brushList[i]);
                Log.d("brush", ""+brushList[i]);
                brushView.setBackgroundColor(Color.WHITE);
            }

            // update color of selected brush view
            v.setBackgroundColor(Color.YELLOW);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(edu.nyu.scps.JUN20.R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        ////noinspection SimplifiableIfStatement
        //if (id == edu.nyu.scps.JUN20.R.id.action_settings) {
        //    return true;
        //}

        if (id == edu.nyu.scps.JUN20.R.id.action_reset) {
            sketchPad.eraseSketchPad();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
