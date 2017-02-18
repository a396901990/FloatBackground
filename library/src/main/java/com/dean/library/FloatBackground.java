package com.dean.library;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DeanGuo on 1/7/17.
 */

public class FloatBackground extends FrameLayout {

    private static final long DELAY = 26;

    List<FloatObject> floats = new ArrayList<>();

    public FloatBackground(Context context) {
        super(context);
    }

    public FloatBackground(Context context, AttributeSet attrs) {
        super(context, attrs);
        // call onDraw in ViewGroup
        setWillNotDraw(false);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw || h != oldh) {
            initFloatObject(w, h);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (FloatObject floatObject : floats) {
            floatObject.drawFloatItem(canvas);
        }
        // 隔一段时间重绘一次, 动画效果
        getHandler().postDelayed(runnable, DELAY);

    }

    // 重绘线程
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            invalidate();
            // 控制帧数
        }
    };

    public void startFloat() {
        for (FloatObject floatObject : floats) {
            floatObject.setStatus(FloatObject.START);
        }
    }

    public void endFloat() {
        for (FloatObject floatObject : floats) {
            floatObject.setStatus(FloatObject.END);
        }
    }

    public void addFloatView(FloatObject floatObject) {
        if (floatObject == null) {
            return;
        }
        floats.add(floatObject);
    }

    private void initFloatObject(int width, int height) {
        for (FloatObject floatObject : floats) {
            int x = (int) (floatObject.posX * width);
            int y = (int) (floatObject.posY * height);
            floatObject.init(x, y, width, height);
        }
    }
}
