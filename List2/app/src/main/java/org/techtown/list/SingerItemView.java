package org.techtown.list;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SingerItemView extends LinearLayout {

    TextView textView;

    public SingerItemView(Context context) {
        super(context);
    }

    public SingerItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void init(Context context)
    {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.singer_list,this,true);
    }

}
