package com.xin.recyclerviewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by admin on 2019/3/12.
 */

public class CustomCircleView extends View {

    private Paint paint;

    private int radius = 80;

    private String text = "A";

    public CustomCircleView(Context context) {
        super(context);
    }

    public CustomCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint = new Paint();
        paint.setTextSize(64);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.RED);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, paint);

        Rect rect = new Rect();

        paint.setColor(Color.WHITE);
        paint.getTextBounds(text, 0, text.length(), rect);

        canvas.drawText(text, getWidth() / 2 - rect.width() / 2, getHeight() / 2 + rect.height() / 2, paint);

    }

    public void setText(String text) {
        this.text = text;
        //设置界面显示
        setViewVisibility(VISIBLE);
        invalidate();
    }

    /**
     * 设置界面显示或隐藏
     * @param visibility
     */
    public void setViewVisibility(int visibility) {
        setVisibility(visibility);
    }

}
