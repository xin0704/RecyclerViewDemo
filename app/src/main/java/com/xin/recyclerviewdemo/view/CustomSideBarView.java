package com.xin.recyclerviewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by admin on 2019/3/12.
 */

public class CustomSideBarView extends View {

    private Paint paint;

    private int letterHeight;

    private int touchItem = -1;

    private int bgColor = 0x1a000000;

    private SideBarListener listener;

    private String letters[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};

    public CustomSideBarView(Context context) {
        super(context);
    }

    public CustomSideBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSideBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint = new Paint();

        paint.setTextSize(32);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        //计算每个字母在屏幕中占的高度
        letterHeight = height / letters.length;

        for (int i = 0; i < letters.length; i++) {

            int letterWidth = (int) paint.measureText(letters[i]);

            canvas.drawText(letters[i], width / 2 - letterWidth / 2, letterHeight * (i + 1), paint);

        }

        canvas.drawColor(bgColor);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:

                float point = event.getY();
                int index = (int) (point / getHeight() * letters.length);// 通过获取触摸的y轴的位置，计算出是第几个字母
                //判断数组越界异常
                if (index < 0) {
                    index = 0;
                }
                if (index >= letters.length) {
                    index = letters.length - 1;
                }
                if (listener != null) {
                    touchItem = index;
                    listener.itemSelected(letters[index]);
                }
                break;
            case MotionEvent.ACTION_UP:

                if (listener != null) {
                    listener.motionEventActionUp();
                }

                break;

        }
        invalidate();
        return true;
    }


    public void setSideBarListener(SideBarListener listener) {
        this.listener = listener;
    }

    public interface SideBarListener {
        //触摸事件的监听接口
        void itemSelected(String str);
        //触摸事件的监听接口
        void motionEventActionUp();
    }

}
