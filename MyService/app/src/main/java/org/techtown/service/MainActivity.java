package org.techtown.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                // 입력상자에 사용자가 입력한 데이터.

                // 서비스 실행 => 시스템 쪽에 서비스 실행해달라고 요청해야 해.
                Intent intent = new Intent(getApplicationContext(),MyService.class);
                intent.putExtra("command","show");
                intent.putExtra("name",name);
                startService(intent);    // 서비스를 실행하는 코드

            }
        });

        Intent passedIntent = getIntent();
        processCommand(passedIntent);

    }


    // 이미 MainActivity 가 만들어져 있는 상황에서는 onCreate 가 아닌 이 함수가 호출됨.
    // service 에서 onCreate 는 한 번만 호출되고 나머지는 onStartCommand 가 호출됨과 같다.
    @Override
    protected void onNewIntent(Intent intent) {
        processCommand(intent);
        super.onNewIntent(intent);
    }

    private void processCommand(Intent intent){
        if(intent!=null){
            String command = intent.getStringExtra("command");
            String name = intent.getStringExtra("name");

            Toast.makeText(this,"서비스로부터 전달받은 데이터 : "+command+" , "+name,Toast.LENGTH_LONG).show();
        }
    }
}
