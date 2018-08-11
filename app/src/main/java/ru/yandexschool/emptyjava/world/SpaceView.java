package ru.yandexschool.emptyjava.world;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import ru.yandexschool.emptyjava.InvalidateListener;
import ru.yandexschool.emptyjava.graphics.Ball;

public class SpaceView extends View {

    int windowHeight = 0;

    private ArrayList<Ball> items = new ArrayList<>();
    public InvalidateListener invalidateListener = null;

    public void addItem(Ball ball) {
        items.add(ball);
        invalidate();
    }

    public boolean isCollision(int top, int bottom, int start, int right) {

        for (Ball ball : items) {
            if (isBallCollision(ball, top, bottom, start, right)) {
                return true;
            }
        }
        return false;
    }

    private boolean isBallCollision(Ball ball, int top, int bottom, int start, int right) {

        if (getDownLeft(ball).y < top) return false;
        if (getUpLeft(ball).y > bottom) return false;

        if ((getDownLeft(ball).x >= start) && (getDownLeft(ball).x <= right)) {
            return true;
        }

        if ((getDownRight(ball).x >= start) && (getDownRight(ball).x <= right)) {
            return true;
        }

        return false;
    }

    public void startGame(int windowHeight) {
        this.windowHeight = windowHeight;
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
        for (int i = items.size() - 1; i >= 0; i--) {
            Ball ball = items.get(i);
            ball.y += 8;

            if (ball.y > windowHeight) {
                items.remove(ball);
            }
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
//            canvas.drawCircle(ball.x, ball.y, ball.radius, ball.paint);
            ball.onDraw(canvas);
        }
        if (invalidateListener != null) {
            invalidateListener.invalidate();
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
            boolean key = false;
            for (int i = items.size() - 1; i >= 0; i--) {
                Ball ball = items.get(i);
                if (ball.terminated) {
                    items.remove(ball);
                    continue;
                }
                if (((x > getUpLeft(ball).x) && (x < getUpRight(ball).x))
                        && ((y > getUpLeft(ball).y) && (y < getDownLeft(ball).y))) {
                    key = true;
                    ball.terminate();
                }
            }
            if (key) {
                invalidate();
            }
            performClick();
        }
        return super.onTouchEvent(event);
    }
}