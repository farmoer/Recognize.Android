package com.farmoer.image.imageproc.view;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


public class ViewfinderView extends View {
    /**
     * Tag for the {@link Log}.
     */
    private static final String TAG = "ViewfinderView";


    private static final int CORNER_WIDTH = 5;

    public Rect getFrame() {
        return mFrame;
    }

    private Paint mPaint;
    private Rect mFrame;
    private int mScreenRate;

    private final int mMaskColor = Color.parseColor("#60000000");

    public ViewfinderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        float density = context.getResources().getDisplayMetrics().density;
        mFrame = new Rect(context.getResources().getDisplayMetrics().widthPixels / 4 , 600,  context.getResources().getDisplayMetrics().widthPixels * 3/4, 700);
        mScreenRate = (int)(5 * density);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        mPaint.setColor( mMaskColor);
        canvas.drawRect(0, 0, width, mFrame.top, mPaint);
        canvas.drawRect(0, mFrame.top, mFrame.left, mFrame.bottom + 1, mPaint);
        canvas.drawRect(mFrame.right + 1, mFrame.top, width, mFrame.bottom + 1,
                mPaint);
        canvas.drawRect(0, mFrame.bottom + 1, width, height, mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(mFrame.left, mFrame.top, mFrame.left + mScreenRate,
                mFrame.top + CORNER_WIDTH, mPaint);
        canvas.drawRect(mFrame.left, mFrame.top, mFrame.left + CORNER_WIDTH, mFrame.top
                + mScreenRate, mPaint);
        canvas.drawRect(mFrame.right - mScreenRate, mFrame.top, mFrame.right,
                mFrame.top + CORNER_WIDTH, mPaint);
        canvas.drawRect(mFrame.right - CORNER_WIDTH, mFrame.top, mFrame.right, mFrame.top
                + mScreenRate, mPaint);
        canvas.drawRect(mFrame.left, mFrame.bottom - CORNER_WIDTH, mFrame.left
                + mScreenRate, mFrame.bottom, mPaint);
        canvas.drawRect(mFrame.left, mFrame.bottom - mScreenRate,
                mFrame.left + CORNER_WIDTH, mFrame.bottom, mPaint);
        canvas.drawRect(mFrame.right - mScreenRate, mFrame.bottom - CORNER_WIDTH,
                mFrame.right, mFrame.bottom, mPaint);
        canvas.drawRect(mFrame.right - CORNER_WIDTH, mFrame.bottom - mScreenRate,
                mFrame.right, mFrame.bottom, mPaint);
    }
}
