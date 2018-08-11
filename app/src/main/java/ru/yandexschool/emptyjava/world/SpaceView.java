package ru.yandexschool.emptyjava.world;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class SpaceView extends View {

    //    private float ballRadius;
    private Paint paint;
//    final PointF ball = new PointF();

    private ArrayList<Ball> items = new ArrayList<>();

    public void addItem(Ball ball) {
        items.add(ball);
    }

    public void startGame() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                moveBalls();
                invalidate();
                handler.postDelayed(this, 16);
            }
        }, 16);
    }

    private void moveBalls() {
        for (Ball ball : items) {
            ball.y += 8;
        }
    }

    public SpaceView(Context context) {
        super(context);
    }

    public SpaceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SpaceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (Ball ball : items) {
            canvas.drawCircle(ball.x, ball.y, ball.radius, ball.paint);
        }
    }

    private Point getUpLeft(Ball ball) {
        return new Point(ball.x - ball.radius, ball.y - ball.radius);
    }

    private Point getUpRight(Ball ball) {
        return new Point(ball.x + ball.radius, ball.y - ball.radius);
    }

    private Point getDownLeft(Ball ball) {
        return new Point(ball.x - ball.radius, ball.y + ball.radius);
    }

    private Point getDownRight(Ball ball) {
        return new Point(ball.x + ball.radius, ball.y + ball.radius);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();
            for (Ball ball : items) {
//                if (getUpLeft(ball).x) {
//
//                }
            }

            performClick();
        }
        return super.onTouchEvent(event);
    }
}