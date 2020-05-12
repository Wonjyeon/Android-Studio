package org.techtown.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

    Bitmap bitmap;

    Bitmap mBitmap;
    Canvas mCanvas;


    public MyView(Context context) {
        super(context);
        init(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.face);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // 메모리에 가로,세로 크기가 동일한 비트맵 이미지 생성
        mBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);

        // 메모리에 그리기 하고 싶다 => Canvas 객체 생성해서 설정
        // canvas 가 bitmap 쪽으로 그려질 수 있게 도와줌.
        mCanvas = new Canvas();
        mCanvas.setBitmap(mBitmap);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        mCanvas.drawLine(100,100,400,200,paint);
        mCanvas.drawBitmap(bitmap,0,0,null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmap,0,0,null);
    }
}
