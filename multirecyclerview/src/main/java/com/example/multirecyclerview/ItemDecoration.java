package com.example.multirecyclerview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ItemDecoration extends RecyclerView.ItemDecoration {
    private int dividingLineHeight;
    private Paint mPaint;
    private boolean isHaveDividingLine = false;
    private boolean isHaveBackground = false;
    private Bitmap bitmap;
    private int alpha;
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (isHaveDividingLine){
            drawDividingLine(c,parent);
        }
        if (isHaveBackground){
            Matrix matrix = new Matrix();
            float s = Math.max(parent.getWidth()/bitmap.getWidth(),parent.getHeight()/bitmap.getWidth());
            matrix.setScale(s,s);
            if (bitmap!=null)
                mPaint.setAlpha(alpha);
            c.drawBitmap(bitmap,matrix,mPaint);
        }
    }

    public ItemDecoration() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (isHaveDividingLine){
            outRect.bottom+=dividingLineHeight;
        }
    }

    public void setDividingLine(int color,int height,boolean isSet){
        mPaint.setColor(color);
        dividingLineHeight = height;
        isHaveDividingLine = isSet;
    }

    public void drawDividingLine(Canvas canvas,RecyclerView parent){
        int itemtCount = parent.getChildCount();
        Rect rect = new Rect();
        rect.left = parent.getPaddingLeft();
        rect.right = parent.getWidth()-parent.getPaddingRight();
        for (int i = 0;i<itemtCount;i++){
            View childView = parent.getChildAt(i);
            rect.top = childView.getBottom();
            rect.bottom = rect.top+dividingLineHeight;
            canvas.drawRect(rect,mPaint);
        }
    }

    public void setBackground(Bitmap bitmap,int alpha,boolean isSet){
        this.bitmap = bitmap;
        this.isHaveBackground = isSet;
        this.alpha = alpha;
    }
}

