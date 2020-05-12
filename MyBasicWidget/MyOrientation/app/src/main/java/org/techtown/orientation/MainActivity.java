package org.techtown.orientation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this,"onCreate() 호출됨",Toast.LENGTH_LONG).show();

        editText = (EditText) findViewById(R.id.editText);

        Button button = (Button)findViewById(R.id.button);

        // 가로 방향인 경우 버튼이 없기 떄문에 버튼 자체가 null이 됨. => null이면 이 아래 액션을 그냥 넘어가도록!
        if(button!=null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name = editText.getText().toString();
                    // 버튼을 클릭했을 때 name 변수에 들어갔음을 알려줌.
                    // new 라고 하는 객체는 별도의 유효범위가 있으므로 this 사용 X.
                    Toast.makeText(getApplicationContext(), "입력한 값을 name 변수에 할당함", Toast.LENGTH_LONG).show();

                }

            });
        }

        if(savedInstanceState!=null){
            String name = savedInstanceState.getString("name");
            // layout_land 에 있는 edittext2 를 edittext로 변경해줌
            if(editText!=null)
            {
                editText.setText("복원된 이름 : "+name);
                Toast.makeText(getApplicationContext(),"이름이 복원됨 : "+name,Toast.LENGTH_LONG).show();
            }
        }
    }


    // 이 메소드가 호출될 때는 bundle 객체에 저장됨.
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("name",name);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this,"onStart() 호출됨",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this,"onStop() 호출됨",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"onDestroy() 호출됨",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,"onPause() 호출됨",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"onCreate() 호출됨",Toast.LENGTH_LONG).show();
    }


}
