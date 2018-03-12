package org.molaei.progressbarwithindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;

/**
 * Created by ali on 3/12/18.
 */

public class ProgressBarWithIndicator extends LinearLayout {
    private ProgressBar progressBar;
    private SeekBar topSeekBar;
    private SeekBar bottomSeekBar;
    private int max;

    public ProgressBarWithIndicator(Context context) {
        super(context);
    }

    public ProgressBarWithIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressBarWithIndicator, 0, 0);        //Use a

        final int progress = typedArray.getInt(R.styleable.ProgressBarWithIndicator_android_progress, 0);

        max = typedArray.getInt(R.styleable.ProgressBarWithIndicator_android_max, 0);

        int borderColor = typedArray.getColor(R.styleable.ProgressBarWithIndicator_borderColor, Color.parseColor("#000000"));
        int indicatorColor = typedArray.getColor(R.styleable.ProgressBarWithIndicator_indicatorColor, Color.parseColor("#000000"));
        int progressColor = typedArray.getColor(R.styleable.ProgressBarWithIndicator_progressColor, Color.parseColor("#000000"));
        int progressBackgroundColor = typedArray.getColor(R.styleable.ProgressBarWithIndicator_progressBackground, Color.parseColor("#FFFFFF"));
        int backgroundColor = typedArray.getColor(R.styleable.ProgressBarWithIndicator_android_background, Color.parseColor("#FFFFFF"));

        int borderWidth = typedArray.getInt(R.styleable.ProgressBarWithIndicator_progressBorderWidth, 2);
        int progressBarHeight = typedArray.getInt(R.styleable.ProgressBarWithIndicator_progressBarHeight, -1);

        int seekbarSelection = typedArray.getInt(R.styleable.ProgressBarWithIndicator_indicatorBar, 1);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //noinspection ConstantConditions
        inflater.inflate(R.layout.progress_bar_with_indicator, this, true);

        LinearLayout linearLayout = (LinearLayout) getChildAt(0);

        topSeekBar = (SeekBar) linearLayout.getChildAt(0);
        topSeekBar.setMax(max);
        topSeekBar.setProgress(progress);
        topSeekBar.setEnabled(false);

        topSeekBar.getThumb().setColorFilter(indicatorColor, PorterDuff.Mode.SRC_IN);

        topSeekBar.setBackgroundColor(backgroundColor);
        LayerDrawable topSeekbarDrawable = (LayerDrawable) topSeekBar.getProgressDrawable();
        Drawable topSeekBarBackgroundDrawable = topSeekbarDrawable.getDrawable(0);
        Drawable topSeekBarProgressDrawable = topSeekbarDrawable.getDrawable(1);

        topSeekBarBackgroundDrawable.setColorFilter(backgroundColor, PorterDuff.Mode.SRC_IN);
        topSeekBarProgressDrawable.setColorFilter(backgroundColor, PorterDuff.Mode.SRC_IN);


        FrameLayout border = (FrameLayout) linearLayout.getChildAt(1);
        border.setBackgroundColor(borderColor);

        progressBar = (ProgressBar) border.getChildAt(0);
        progressBar.setMax(max);
        progressBar.setProgress(progress);
        progressBar.getLayoutParams().height = progressBarHeight;

        LayerDrawable progressBarDrawable = (LayerDrawable) progressBar.getProgressDrawable();
        Drawable progressBarBackgroundDrawable = progressBarDrawable.getDrawable(0);
        Drawable progressBarProgressDrawable = progressBarDrawable.getDrawable(1);

        progressBarBackgroundDrawable.setColorFilter(progressBackgroundColor, PorterDuff.Mode.SRC_IN);
        progressBarProgressDrawable.setColorFilter(progressColor, PorterDuff.Mode.SRC_IN);
        int px = dpToPx(borderWidth);
        progressBar.setPadding(px, px, px, px);

        bottomSeekBar = (SeekBar) linearLayout.getChildAt(2);
        bottomSeekBar.setMax(max);
        bottomSeekBar.setProgress(progress);
        bottomSeekBar.setEnabled(false);

        bottomSeekBar.getThumb().setColorFilter(indicatorColor, PorterDuff.Mode.SRC_IN);

        bottomSeekBar.setBackgroundColor(backgroundColor);
        LayerDrawable bottomSeekbarDrawable = (LayerDrawable) bottomSeekBar.getProgressDrawable();
        Drawable bottomSeekBarBackgroundDrawable = bottomSeekbarDrawable.getDrawable(0);
        Drawable bottomSeekBarProgressDrawable = bottomSeekbarDrawable.getDrawable(1);

        bottomSeekBarBackgroundDrawable.setColorFilter(backgroundColor, PorterDuff.Mode.SRC_IN);
        bottomSeekBarProgressDrawable.setColorFilter(backgroundColor, PorterDuff.Mode.SRC_IN);

        switch (seekbarSelection) {
            case 0:
                topSeekBar.setVisibility(VISIBLE);
                bottomSeekBar.setVisibility(GONE);
                break;
            case 1:
                topSeekBar.setVisibility(GONE);
                bottomSeekBar.setVisibility(VISIBLE);
                break;
            case 2:
                topSeekBar.setVisibility(GONE);
                bottomSeekBar.setVisibility(GONE);
                break;
            default:
                topSeekBar.setVisibility(VISIBLE);
                bottomSeekBar.setVisibility(VISIBLE);
                break;
        }
        typedArray.recycle();
    }

    private int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public void setProgress(int value) {
        progressBar.setProgress(value);
        topSeekBar.setProgress(value);
        bottomSeekBar.setProgress(value);
    }

    public void setMax(int value) {
        progressBar.setMax(value);
        topSeekBar.setMax(value);
        bottomSeekBar.setMax(value);
    }
}
