package com.dean.library;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;

import java.util.Random;

/**
 * Created by DeanGuo on 1/7/17.
 */

public abstract class FloatObject {

    private int status;
    public static final int START = 0;
    public static final int MOVE = 1;
    public static final int END = 2;
    public static final int FINISH = 3;

    // percent
    float posX;
    float posY;

    private int width;
    private int height;
    private float x;
    private float y;
    private int alpha;

    private float distance = 500;
    private float mCurDistance = 0;

    private PointF start;
    private Point end;
    private Point c1;
    private Point c2;
    private Random random = new Random();
    private Paint paint = new Paint();

    private int ALPHA_LIMIT = 255;

    private static final float MOVE_PER_FRAME = 0.4F;
    private static final int ALPHA_PER_FRAME = 2;

    public FloatObject(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public abstract void drawFloatObject(Canvas canvas, float x, float y, Paint paint);

    public void init(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        paint.setAntiAlias(true);
        setStatus(FINISH);
    }

    public void drawFloatItem(Canvas canvas) {

        switch (status) {
            case START:
                // fade in
                if (isFade() && alpha <= ALPHA_LIMIT) {
                    paint.setAlpha(alpha);
                    alpha += ALPHA_PER_FRAME;
                } else {
                    setStatus(MOVE);
                }
                break;
            case MOVE:
                // 更新赛贝尔曲线点
                if (mCurDistance == 0) {
                    start = new PointF(x, y);
                    end = getRandomPoint((int)start.x, (int)start.y, (int) distance);// 取值范围distance
                    c1 = getRandomPoint((int)start.x, (int)start.y, random.nextInt(width / 2)); // 取值范围width/2
                    c2 = getRandomPoint(end.x, end.y, random.nextInt(width / 2));// 取值范围width/2
                }

                // 计算塞贝儿曲线的当前点
                PointF bezierPoint = CalculateBezierPoint(mCurDistance / distance, start, c1, c2, end);
                x = bezierPoint.x;
                y = bezierPoint.y;

                // 更新当前路径
                mCurDistance += MOVE_PER_FRAME;

                // 一段画完后重置
                if (mCurDistance >= distance) {
                    mCurDistance = 0;
                }
                break;
            case END:
                // fade out
                if (isFade() && alpha > 0) {
                    paint.setAlpha(alpha);
                    alpha -= ALPHA_PER_FRAME;
                } else {
                    setStatus(FINISH);
                }
                break;
        }

        if (status != FINISH) {
            Log.e("drawFloatObject", x+", "+y);
            drawFloatObject(canvas, x ,y, paint);
        }
    }

    /**
     * 计算塞贝儿曲线
     *
     * @param t  时间，范围0-1
     * @param s  起始点
     * @param c1 拐点1
     * @param c2 拐点2
     * @param e  终点
     * @return 塞贝儿曲线在当前时间下的点
     */
    private PointF CalculateBezierPoint(float t, PointF s, Point c1, Point c2, Point e) {
        float u = 1 - t;
        float tt = t * t;
        float uu = u * u;
        float uuu = uu * u;
        float ttt = tt * t;

        PointF p = new PointF((s.x * uuu), (s.y * uuu));
        p.x += 3 * uu * t * c1.x;
        p.y += 3 * uu * t * c1.y;
        p.x += 3 * u * tt * c2.x;
        p.y += 3 * u * tt * c2.y;
        p.x += ttt * e.x;
        p.y += ttt * e.y;

        return p;
    }


    /**
     * 根据基准点获取指定范围为半径的随机点
     */
    private Point getRandomPoint(int baseX, int baseY, int r) {
        if (r <= 0) {
            r = 1;
        }
        int x = random.nextInt(r);
        int y = (int) Math.sqrt(r * r - x * x);

        x = baseX + getRandomPNValue(x);
        y = baseY + getRandomPNValue(y);

//        if (x > width || x < 0 || y > height || y < 0) {
//            return getRandomPoint(baseX, baseY, r);
//        }

        if (x > width) {
            x = width - r;
        } else if (x < 0) {
            x = r;
        } else if (y > height) {
            y = height - r;
        } else if (y < 0) {
            y = r;
        }

        return new Point(x, y);
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setColor(int color) {
        this.paint.setColor(color);
    }

    /**
     * 获取随机正负数
     */
    private int getRandomPNValue(int value) {
        return random.nextBoolean() ? value : 0 - value;
    }

    public void setAlpha(int alpha) {
        ALPHA_LIMIT = alpha;
    }

    public boolean isFade() {
        return true;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
