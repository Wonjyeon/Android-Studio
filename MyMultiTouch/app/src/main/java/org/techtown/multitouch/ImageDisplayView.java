package org.techtown.multitouch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

public class ImageDisplayView extends View {

    Paint paint;
    Matrix matrix;

    public ImageDisplayView(Context context) {
        super(context);
        init(context);
    }

    public ImageDisplayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint();
        matrix = new Matrix();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        /*
        메모리에 똑같은 이미지를 만들어줌.
        -> 그래픽을 화면에 View가 보여지는 시점에 그리게 되면 성능이 떨어짐
        -> 미리 메모리에 만들어놓고 메모리에서 그려줌
        -> 메모리에 그린 정보를 화면에 보여지는 시점에 뿌려주기만 함!
        -> View 의 크기가 정해지면 동일한 크기로 메모리에 bitmap 이미지를 만들어줌.
         */
    }

    /*
    터치했을 때 처리하는 함수.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    /*
    화면에 그려지는 시점에 필요하다면 더 그려라! -> 메모리에 만들어진 bitmap 이미지를 가져다가 그려줌.
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }



}
