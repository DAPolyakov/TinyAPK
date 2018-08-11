package ru.yandexschool.emptyjava.utils;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;

public class WindowUtils {
    private static final WindowUtils ourInstance = new WindowUtils();

    public static WindowUtils getInstance() {
        return ourInstance;
    }

    private WindowUtils() {
    }

    public static int getWindowWidth(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public static int getWindowHeight(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

}
