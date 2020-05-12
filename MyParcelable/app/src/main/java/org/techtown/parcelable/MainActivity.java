package org.techtown.parcelable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MenuActivity.class);

                ArrayList<String> names = new ArrayList<String>();
                names.add("김진수");
                names.add("황수연");

                intent.putExtra("names",names);

                //parcelable로 구현한 클래스의 객체를 통해 바로 만들고 바로 사용.
                SimpleData data = new SimpleData(100,"hello");
                intent.putExtra("data",data);

                startActivityForResult(intent,101);
            }
        });
    }
}
