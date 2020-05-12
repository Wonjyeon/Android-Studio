package org.techtown.grid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SingerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView)findViewById(R.id.gridView);


        adapter = new SingerAdapter();


        adapter.addItem(new SingerItem("소녀시대","010-1000-1000",R.drawable.image_01));
        adapter.addItem(new SingerItem("여자친구","010-2000-2000",R.drawable.image_02));
        adapter.addItem(new SingerItem("AOA","010-3000-3000",R.drawable.image_03));
        adapter.addItem(new SingerItem("에이핑크","010-4000-4000",R.drawable.image_04));
        adapter.addItem(new SingerItem("블랙핑크","010-5000-5000",R.drawable.image_05));


        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SingerItem item = (SingerItem)adapter.getItem(position);
                Toast.makeText(getApplicationContext(),"선택 : "+ item.getName(),Toast.LENGTH_LONG).show();

            }
        });


    }

    class SingerAdapter extends BaseAdapter {

        // 리스트뷰는 껍데기고 어댑터가 데이터를 관리! -> ArrayList 로 관리
        ArrayList<SingerItem> items = new ArrayList<SingerItem>();


        // 먼저 몇 개의 아이템이 있는지 물어봄! => getCount 호출
        @Override
        public int getCount() {
            return items.size();
        }


        // item 을 추가해야해!
        public void addItem(SingerItem item)
        {
            items.add(item);
        }



        // 몇 번째 아이템인지 보고 리턴
        @Override
        public Object getItem(int position) {
            return items.get(position);
        }


        // 아이디 값이 있다면 넘겨줘
        @Override
        public long getItemId(int position) {
            return position;
        }


        // 데이터를 관리하는 어댑터가 화면에 보여질 각각의 아이템을 위한 뷰를 만들어줘!
        // 각각의 아이템의 데이터의 이미지, 글자를 화면에 표시하고 싶어!
        // 이 View 는 Layout 으로 구성돼! => 그 Layout 에 해당하는 것을 부분화면으로 정의
        // => 그걸 이용해서 객체를 만든 후 데이터를 설정하고 리턴!
        // => 부분화면 => xml, java 파일 추가
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            SingerItemView view = null;
            if(convertView==null)
            {
                view = new SingerItemView(getApplicationContext());
            }
            else
            {
                view = (SingerItemView) convertView;
            }
            SingerItem item = items.get(position);

            view.setName(item.getName());
            view.setMobile(item.getMobile());
            view.setImage(item.getResId());

            return view;
        }
    }

}
