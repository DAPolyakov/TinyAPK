package ru.yandexschool.emptyjava;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;

import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class BottomBallView extends View {
    private  float ballRadius;
    private  Paint paint;
    private float x = 0;
    private float y = 0;
    private final float mSpeed = 1.5f;


    private static final float BALL_RADIUS = 15.0f;

    public BottomBallView(Context context) {
        super(context);
        initParams();
    }

    public BottomBallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initParams();
    }

    public void initParams(){
        final float scale = getContext().getResources().getDisplayMetrics().density;

        paint = new Paint();
        //paint.setColor(0xFFFF0000);
        paint.setShader(createShader());
        paint.setAlpha(200);
        ballRadius = scale * BALL_RADIUS;
    }

    public BottomBallView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams();
    }


    public BottomBallView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initParams();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int desiredWidth = 100;
        int desiredHeight = 100;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }

        //MUST CALL THIS
        setMeasuredDimension(width, height);
        x = width / 2;
        y = height / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(x, y, ballRadius, paint);

    }

    float lastX = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            lastX = event.getX();
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {

            moveTo((int) (event.getX() - lastX));
            lastX = event.getX();
            invalidate();
            return true;
        }
        return super.onTouchEvent(event);
    }


    public void moveTo(int touchX) {
        int dx = (int) ((touchX) * mSpeed);
        x += dx;
        if (x < ballRadius) x = ballRadius;
        if (x + ballRadius > getWidth()) {
            x = getWidth() - ballRadius;
        }
    }
    private Shader createShader() {
        LinearGradient shader3 = new LinearGradient(0, 0, 100, 20,
                new int[] { Color.RED, Color.CYAN, Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA }, null,
                Shader.TileMode.MIRROR);
        return shader3;
    }
}
