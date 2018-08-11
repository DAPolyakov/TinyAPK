package ru.yandexschool.emptyjava;

import android.app.Activity;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;

import ru.yandexschool.emptyjava.graphics.HeavenView;
import android.os.Handler;
import android.view.ViewGroup;

import ru.yandexschool.emptyjava.utils.WindowUtils;
import ru.yandexschool.emptyjava.world.Ball;
import ru.yandexschool.emptyjava.world.SpaceView;

public class MainActivity extends Activity {


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


    SpaceView space;
    ViewGroup root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout layout = findViewById(R.id.root);
        final HeavenView heavenView = new HeavenView(getApplicationContext(), new RectF(0f, 0f, 1000f, 1000f));
        layout.addView(heavenView);
        space = new SpaceView(this);
        layout.addView(space);
        for (int i = 0; i < 200; i++) {
            heavenView.update();
        }

        space.startGame();

        moveHeaven(heavenView);
    }
}
