package org.techtown.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class CustomView extends View {

    // 어떤 색상을 할건지.
    Paint paint;

    public CustomView(Context context) {
        super(context);
        init(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint();
        paint.setColor(Color.RED);
    }



    /*
    만든 이 View 가 화면에 보여지기 전에 호출됨.
    그릴 내용이 있으면 canvas 에 그려주세요 하는 것.
    canvas : 안드로이드에서 그림을 그릴 수 있는 객체
    => 화면에 직접 그려지게 만들어주는 canvas 객체
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawRect(100,100,200,200,paint);


        // 테두리만 만든다
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4.0f);
        paint.setColor(Color.GREEN);
        canvas.drawRect(10,10,100,100,paint);


        paint.setStyle(Paint.Style.FILL);
        paint.setARGB(128,0,0,255);
        canvas.drawRect(120,10,210,100,paint);


        // 점선. 괄호 안에 점선의 속성을 넣음.
        DashPathEffect dashPathEffect = new DashPathEffect(new float[]{5,5},1);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5.0f);
        paint.setPathEffect(dashPathEffect);
        paint.setColor(Color.GREEN);
        canvas.drawRect(120,10,210,100,paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(40.0f);
        canvas.drawText("안녕하세요",20,320,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        if(action==MotionEvent.ACTION_DOWN) {
            Toast.makeText(getContext(),"눌렸어요.",Toast.LENGTH_LONG).show();
        }

        return true;
    }
}
