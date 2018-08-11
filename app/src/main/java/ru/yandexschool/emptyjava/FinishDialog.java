package ru.yandexschool.emptyjava;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class FinishDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.finish_dialog, null);

        alertDialog.setView(view);

        ValueAnimator anim = new ValueAnimator();
        anim.setIntValues(Color.RED, Color.YELLOW);
        anim.setEvaluator(new ArgbEvaluator());
        anim.addUpdateListener(valueAnimator -> view.setBackgroundColor((Integer) valueAnimator.getAnimatedValue()));
        anim.setDuration(1000);
        anim.setRepeatCount(1000);
        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.start();



        return alertDialog.create();
    }
}
