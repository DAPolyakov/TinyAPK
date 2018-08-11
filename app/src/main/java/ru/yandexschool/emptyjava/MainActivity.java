package ru.yandexschool.emptyjava;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;

import ru.yandexschool.emptyjava.utils.WindowUtils;
import ru.yandexschool.emptyjava.world.Ball;
import ru.yandexschool.emptyjava.world.SpaceView;

public class MainActivity extends Activity {

    SpaceView space;
    ViewGroup root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        root = findViewById(R.id.root);

        space = new SpaceView(this);
        root.addView(space);

        for (int i = 0; i < 8; i++) {
            space.addItem(new Ball(100 + i * 100, 30));
        }

        for (int i = 0; i < 8; i++) {
            space.addItem(new Ball(120 + i * 100, 175));
        }

        for (int i = 0; i < 8; i++) {
            space.addItem(new Ball(100 + i * 100, 325));
        }

        space.startGame();
    }

    void throwBall(final SpaceView ball) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ball.setY(ball.getY() + 8);
                if (ball.getY() < (WindowUtils.getWindowHeight(MainActivity.this))) {
                    handler.postDelayed(this, 16);
                } else {
                    root.removeView(ball);
                }
            }
        }, 16);
    }

}