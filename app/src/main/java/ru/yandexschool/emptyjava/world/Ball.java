package ru.yandexschool.emptyjava.world;


import android.graphics.Paint;

public class Ball {

    public int radius = 45;

    public int x;
    public int y;
    public Paint paint;

    public Ball() {
        paint = new Paint();
        paint.setColor(0xFFFF0000);
    }

//    public Ball(int x, int y) {
//        this.x = x + radius;
//        this.y = y + radius;
//    }

}
