package ru.yandexschool.emptyjava;

import android.app.Activity;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;

import java.util.Random;

import ru.yandexschool.emptyjava.graphics.HeavenView;
import ru.yandexschool.emptyjava.utils.WindowUtils;
import ru.yandexschool.emptyjava.world.Ball;
import ru.yandexschool.emptyjava.world.SpaceView;

public class MainActivity extends Activity {

    SpaceView space;
    Random random = new Random(System.currentTimeMillis());

    void moveHeaven(final HeavenView ball) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ball.update();
                handler.postDelayed(this, 16);

            }
        }, 16);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout layout = findViewById(R.id.root);
        final HeavenView heavenView = new HeavenView(getApplicationContext(), new RectF(0f, 0f, 1000f, 1000f));
        layout.addView(heavenView);

        for (int i = 0; i < 200; i++) {
            heavenView.update();
        }

        moveHeaven(heavenView);

        space = new SpaceView(this);
        layout.addView(space);

        space.startGame();

        generate();
    }

    void generate() {
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int randomX = random.nextInt();

                Ball ball = new Ball();

                randomX = randomX % (WindowUtils.getWindowWidth(MainActivity.this) - ball.radius * 2);
                randomX = Math.abs(randomX) + ball.radius * 2;
                ball.x = randomX;
                ball.y = -100;
                space.addItem(ball);
                handler.postDelayed(this, 100);
            }
        }, 1000);
    }

}