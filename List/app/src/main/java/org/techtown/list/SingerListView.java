package org.techtown.list;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.zip.Inflater;

public class SingerListView extends LinearLayout {

    TextView textView1;
    TextView textView2;
    ImageView imageView;

    public SingerListView(Context context) {
        super(context);

        init(context);
    }

    public SingerListView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.singer_list,this,true);

        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    public void setName(String name)
    {
        textView1.setText(name);
    }
    public void setMobile(String mobile)
    {
        textView2.setText(mobile);
    }
    public void setImage(int resId)
    {
        imageView.setImageResource(resId);
    }



}
