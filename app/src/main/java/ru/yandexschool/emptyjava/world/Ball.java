package ru.yandexschool.emptyjava.world;


import android.graphics.Paint;

public class Ball {

    public int x;
    public int y;
    public int radius = 45;
    public Paint paint;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;

        paint = new Paint();
        paint.setColor(0xFFFF0000);
    }

}
