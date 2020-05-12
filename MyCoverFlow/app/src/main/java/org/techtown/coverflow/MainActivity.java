package org.techtown.coverflow;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // coverflow 는 리스트나 그리드처럼 선택 위젯 -> adapter 를 선언해야해.
        CoverFlow coverFlow = (CoverFlow)findViewById(R.id.coverFlow);

        ImageAdapter adapter = new ImageAdapter();
        coverFlow.setAdapter(adapter);
    }

    class ImageAdapter extends BaseAdapter {

        int[] items = {R.drawable.item01,
                R.drawable.item02,
                R.drawable.item03,
                R.drawable.item04,
                R.drawable.item05,};


        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int position) {
            return items[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView view = new ImageView(getApplicationContext());
            view.setImageResource(items[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

            // 이미지가 들어갔으니까
            BitmapDrawable drawable = (BitmapDrawable) view.getDrawable();
            // 부드럽게 그려주는 기능
            drawable.setAntiAlias(true);

            return view;
        }
    }
}
