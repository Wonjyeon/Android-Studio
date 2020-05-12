package org.techtown.toast;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(),"위치가 바뀐 토스트",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP|Gravity.LEFT,200,200);
                toast.show();
            }
        });

        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();

                // toastborder의 최상위 레이아웃을 layout 변수에 저장.
                View layout = inflater.inflate(R.layout.toastborder,(ViewGroup)findViewById(R.id.toast_layout_root));

                // layout.findViewById 를 통해 text 를 불러옴.
                TextView text = (TextView)layout.findViewById(R.id.text);
                text.setText("모양을 바꾼 토스트");

                // getApplicationContext()는 스타일이 지정X. activity는 스타일이 지정될 수 O => 약간 다를 수 있다.
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER,0,-100);
                toast.setDuration(Toast.LENGTH_LONG);

                // layout이 toast의 모양으로 설정이 돼야 함!
                toast.setView(layout);
                toast.show();


            }
        });

        Button button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"스낵바입니다",Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
