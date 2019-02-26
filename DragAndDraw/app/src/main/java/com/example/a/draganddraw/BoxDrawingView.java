package com.example.a.draganddraw;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;

public class BoxDrawingView extends View {
    private final static String TAG = "BoxDrawingView";

    private Box mCurentBox;
    private List<Box> mBoxes = new ArrayList<>();

    public BoxDrawingView(Context context) {
        super(context);
    }

    public BoxDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF current = new PointF(event.getX(), event.getY());


        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mCurentBox = new Box(current);
                mBoxes.add(mCurentBox);
                break;
            case MotionEvent.ACTION_UP:
                mCurentBox = null;
                break;
            case MotionEvent.ACTION_MOVE:
                if(mCurentBox != null){
                    mCurentBox.setCurrent(current);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_CANCEL:

                break;
        }


        return true;
    }
}
