package ru.yandexschool.emptyjava.graphics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Ball {

    private Random random = new Random(new Date().getTime());
    private Paint paint = new Paint();
    public int x;
    public int y;
    public int radius = 50;
    public int phase;

    private int innerRadius;

    public int destructionSpeed = 100;

    private boolean terminating = false;
    public boolean terminated = false;


    public List<PointF> outerVerticesL = new LinkedList<>();
    public List<PointF> outerVerticesR = new LinkedList<>();
    public List<PointF> innerVertices = new LinkedList<>();
    public List<PointF[]> faces = new LinkedList<>();
    private int[] colors = new int[4];

    public Ball() {
        this.phase = random.nextInt(360);
        innerRadius = (int) (radius * 0.3);
        colors[0] = 0xffff0000;
        colors[1] = 0xffff2a2a;
        colors[2] = 0xffff5555;
        colors[3] = 0xffff8080;
        generate();
    }

    private void generate() {
        int outerMaxArc = random.nextInt(27) + 45;
        int totalArc = 0;
        while (totalArc < 360) {
            float x0 = (float) (radius * Math.cos(0.0175 * totalArc));
            float y0 = (float) (radius * Math.sin(0.0175 * totalArc));
            if (totalArc > 180)
                outerVerticesL.add(new PointF(x0, y0));
            else
                outerVerticesR.add(new PointF(x0, y0));

            totalArc += outerMaxArc;
        }

        int innerVerticesCount = random.nextInt(2);
        if (innerVerticesCount == 1) {
            float x1 = (float) (innerRadius * Math.cos(0.0175 * 90));
            float y1 = (float) (innerRadius * Math.sin(0.0175 * 90));
            innerVertices.add(new PointF(x1, y1));


            for (int i = 0; i < outerVerticesR.size() - 1; i++) {
                PointF[] pf = new PointF[3];
                pf[0] = new PointF(x1, y1);
                pf[1] = new PointF(outerVerticesR.get(i).x, outerVerticesR.get(i).y);
                pf[2] = new PointF(outerVerticesR.get(i + 1).x, outerVerticesR.get(i + 1).y);
                faces.add(pf);
            }


            float x2 = (float) (innerRadius * Math.cos(0.0175 * 270));
            float y2 = (float) (innerRadius * Math.sin(0.0175 * 270));
            innerVertices.add(new PointF(x2, y2));

            for (int i = 0; i < outerVerticesL.size() - 1; i++) {
                PointF[] pf = new PointF[3];
                pf[0] = new PointF(x2, y2);
                pf[1] = new PointF(outerVerticesL.get(i).x, outerVerticesR.get(i).y);
                pf[2] = new PointF(outerVerticesL.get(i + 1).x, outerVerticesR.get(i + 1).y);
                faces.add(pf);
            }


        } else {

            int shift = random.nextInt(360);
            float x1 = (float) (innerRadius * Math.cos(0.0175 * shift) + x);
            float y1 = (float) (innerRadius * Math.sin(0.0175 * shift) + y);
            innerVertices.add(new PointF(x1, y1));


            for (int i = 0; i < outerVerticesR.size() - 1; i++) {
                PointF[] pf = new PointF[3];
                pf[0] = new PointF(x1, y1);
                pf[1] = new PointF(outerVerticesR.get(i).x, outerVerticesR.get(i).y);
                pf[2] = new PointF(outerVerticesR.get(i + 1).x, outerVerticesR.get(i + 1).y);
                faces.add(pf);
            }

            for (int i = 0; i < outerVerticesR.size() - 1; i++) {
                PointF[] pf = new PointF[3];
                pf[0] = new PointF(x1, y1);
                pf[1] = new PointF(outerVerticesL.get(i).x, outerVerticesR.get(i).y);
                pf[2] = new PointF(outerVerticesL.get(i + 1).x, outerVerticesR.get(i + 1).y);
                faces.add(pf);
            }

        }


    }

    int k = 1;

    public void onDraw(Canvas canvas) {


        paint.setColor(Color.MAGENTA);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);


        for (PointF[] pf : faces) {
            paint.setColor(colors[random.nextInt(4)]);
            //paint.setColor(Color.MAGENTA);
            for (int i = 0; i < 3; i++) {

                Path path = new Path();
                path.moveTo(pf[0].x + x, pf[0].y + y);
                path.lineTo(pf[1].x + x, pf[1].y + y);
                path.lineTo(pf[2].x + x, pf[2].y + y);
                path.lineTo(pf[0].x + x, pf[0].y + y);
                path.close();
                canvas.drawPath(path, paint);

            }
        }

    }

}
