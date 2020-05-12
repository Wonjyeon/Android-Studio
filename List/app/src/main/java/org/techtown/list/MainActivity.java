package org.techtown.list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.listView);
        SingerAdapter adapter = new SingerAdapter();

        adapter.addItem(new SingerItem("소녀시대","010-1000-1000",R.drawable.image_01));
        adapter.addItem(new SingerItem("블랙핑크","010-2000-2000",R.drawable.image_02));
        adapter.addItem(new SingerItem("아이오아이","010-3000-3000",R.drawable.image_03));
        adapter.addItem(new SingerItem("이달의 소녀","010-4000-4000",R.drawable.image_04));
        adapter.addItem(new SingerItem("레드벨벳","010-5000-5000",R.drawable.image_05));



        listView.setAdapter(adapter);

    }

    class SingerAdapter extends BaseAdapter{

        ArrayList<SingerItem> items = new ArrayList<SingerItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem (SingerItem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            SingerListView view = new SingerListView(getApplicationContext());
            SingerItem item = items.get(position);

            view.setName(item.getName());
            view.setMobile(item.getMobile());
            view.setImage(item.getResId());

            return view;
        }
    }
}
