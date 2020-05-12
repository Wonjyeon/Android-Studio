package org.techtown.button;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class BitmapButton extends AppCompatButton {
    public BitmapButton(Context context) {
        super(context);

        init(context);
    }

    public BitmapButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        setBackgroundResource(R.drawable.title_bitmap_button_normal);

        // 픽셀 기준 사이즈!
        // setTextSize(100);

        //res->valuable->resource->dimens.xml추가
        float textSize = getResources().getDimension(R.dimen.text_size);
        setTextSize(textSize);      // 16dp로 설정됨
        setTextColor(Color.BLUE);


    }


    // 터치 했을 때 눌린 이미지로 바꾸고 싶다!

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                setBackgroundResource(R.drawable.title_bitmap_button_pressed);
                break;

            case MotionEvent.ACTION_UP:
                setBackgroundResource(R.drawable.title_bitmap_button_normal);
                break;
        }

        // 버튼의 그래픽을 다시 그리게 됨.
        invalidate();

        return true;
    }
}
