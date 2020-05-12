package org.techtown.paintboard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ColorItemView extends LinearLayout {

    TextView textView;


    public ColorItemView(Context context) {
        super(context);
        init(context);
    }

    public ColorItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.color_item,this,true);

        textView = (TextView)findViewById(R.id.textView);

    }

    public void setColor(int color) {
        textView.setBackgroundColor(color);
    }
}
