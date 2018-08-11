package ru.yandexschool.emptyjava;

import android.app.Activity;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
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
        final HeavenView heavenView = new HeavenView(this, new RectF(0f, 0f, 1000f, 1000f));


        layout.addView(heavenView);
        for (int i = 0; i < 200; i++) {
            heavenView.update();
        }
        moveHeaven(heavenView);
        final BottomBallView bottomBall = new BottomBallView(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.addView(bottomBall, params);

    }
}

        root = findViewById(R.id.root);

        space = new SpaceView(this);
        root.addView(space);

        for (int i = 0; i < 8; i++) {
            space.addItem(new Ball(100 + i * 100, 30));
        }

        for (int i = 0; i < 8; i++) {
            space.addItem(new Ball(120 + i * 100, 175));
        }

//        for (int i = 0; i < 8; i++) {
//            space.addItem(new Ball(100 + i * 100, 325));
//        }
//
//        space.startGame();
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