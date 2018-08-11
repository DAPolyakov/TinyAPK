package ru.yandexschool.emptyjava;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.RelativeLayout;

import ru.yandexschool.emptyjava.graphics.HeavenView;

public class StartAvtivity extends Activity {
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
        setContentView(R.layout.activity_start_avtivity);
        RelativeLayout layout = findViewById(R.id.startRoot);
        final HeavenView heavenView = new HeavenView(this, new RectF(0f, 0f, 1000f, 1000f));

        layout.addView(heavenView);
        for (int i = 0; i < 200; i++) {
            heavenView.update();
        }
        moveHeaven(heavenView);


        Button button = findViewById(R.id.btnStart);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.start_activity, R.anim.main_activity);
        });


        ValueAnimator anim = new ValueAnimator();
        anim.setIntValues(Color.RED, Color.YELLOW);
        anim.setEvaluator(new ArgbEvaluator());
        anim.addUpdateListener(valueAnimator -> button.setBackgroundColor((Integer) valueAnimator.getAnimatedValue()));
        anim.setDuration(1000);
        anim.setRepeatCount(1000);
        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.start();

    }


}
