package ru.yandexschool.emptyjava.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;

import java.util.Date;
import java.util.Random;

public class HeavenView extends View {

    public class Star {
        public float x;
        public float y;
        public int layer;

        public Star(float x, float y, int layer) {
            this.x = x;
            this.y = y;
            this.layer = layer;
        }

    }

    private RectF rectangle;
    private final float[] speedFactor = {0.8f, 0.5f, 0.3f};
    private final float[] radius = {5f, 3f, 2f};
    private final float starProbability = 0.2f;
    private final float starCeilingY = -200f;
    private final float starSpeed = 20.f;
    private Paint paint = new Paint();
    private final int MAX_STAR_COUNT = 200;

    private Random random = new Random(new Date().getTime());

    public  Star[] stars = new Star[MAX_STAR_COUNT];
    int starCount = 0;

    public HeavenView(Context context, RectF rect) {
        super(context);
        rectangle = rect;

        for(int i = 0; i < MAX_STAR_COUNT; i++){
            stars[i] = new Star(0, rectangle.bottom + 1, 0);
        }
    }

    public void generate() {

    }

    public void update() {

        float f = random.nextFloat();
        if (starCount < MAX_STAR_COUNT)
            if (f < starProbability) {
                changeStar();
                starCount++;
            }

        for (Star pt : stars) {
            assert (pt.layer >= 0 && pt.layer < 3);
            //Log.d("XXX", String.format("%d", pt.layer));
            pt.y += starSpeed * speedFactor[pt.layer];

        }

        changeStar();

        invalidate();
    }

    private void changeStar() {
        for(int i = 0; i < starCount; i++){
            if (stars[i].y > rectangle.bottom){
                generateStar(stars[i]);
                break;
            }
        }
    }

    private void generateStar(Star star) {
        starCount++;
        star.layer = Math.abs(random.nextInt() % 3);
        star.x = random.nextFloat() * (rectangle.right - rectangle.left) + rectangle.left;
        star.y = starCeilingY;
    }

    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.WHITE);
        for(Star star : stars)
            canvas.drawCircle(star.x, star.y, radius[star.layer], paint);
    }
}


