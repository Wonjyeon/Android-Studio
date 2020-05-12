package org.techtown.grid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/*
LinearLayout을 상속!
=> singer_item.xml 파일에서 최상위 레이아웃이 LinearLayout!
=> 상속해서 만들면 Layout Inflation
=> 메모리에 객체화 한 다음에 그대로 붙여줄 수 있음!
 */
public class SingerItemView extends LinearLayout {

    TextView textView;
    TextView textView2;
    ImageView imageView;

    public SingerItemView(Context context) {
        super(context);

        init(context);
    }

    public SingerItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }



    private void init(Context context)
    {
        // 시스템 서비스 : 화면이 없는 background 서비스 => 단말이 실행됐을 때 자동으로 실행시키는 서비스
        // 그 중에 LayoutInflater 라는 게 실행되고 있으니까 가져다 쓰겠다!
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // singer_item.xml 파일의 최상위 레이아웃이 LinearLayout
        // 이 클래스가 LinearLayout 을 상속 받았으므로 this 로 넣어줌!
        inflater.inflate(R.layout.singer_item,this,true);

        textView = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);
        imageView = (ImageView)findViewById(R.id.imageView);
    }

    public void setName(String name)
    {
        textView.setText(name);
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
