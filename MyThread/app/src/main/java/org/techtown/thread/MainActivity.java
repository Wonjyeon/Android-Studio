package org.techtown.thread;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    // int value = 0;
    ValueHandler handler = new ValueHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);


        // 스레드를 시작시키는 버튼
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundThread thread = new BackgroundThread();

                // run 함수가 실행됨. => value가 1초마다 증가됨.
                thread.start();
            }
        });


        // 진행 값을 텍스트뷰에 보여주는 버튼.
        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 메인 스레드 안에서 UI 에 접근하는 것. -> 오류가 X.
               // textView.setText("현재 값 : "+value);
            }
        });
    }

    class BackgroundThread extends Thread {

        int value = 0;
        boolean running = false;

        public void run() {
            running = true;
            while(running) {
                value+=1;

                /*
                이 코드를 여기에서 실행시키면 오류가 남!
                이 View를 만든 메인 스레드만 접근할 수 있으므로!
                 */
                // textView.setText("현재 값 : "+value);

                Message message = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("value",value);
                message.setData(bundle);
                handler.sendMessage(message);
                // handler 가 실행됨

                try{
                    Thread.sleep(1000);
                } catch (Exception e){}
            }
        }
    }

    class ValueHandler extends Handler {

        /*
        UI에 직접 접근할 수 없으니까 handler에 메시지를 보낼거야!
        핸들러가 받아 -> 핸들 메시지
        -> 이 핸들러는 메인 스레드에서 동작하므로 오류가 안뜸
         */
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            int value = bundle.getInt("value");
            textView.setText("현재 값 : "+value);
            // textView를 여기서 접근! -> 오류가 안뜸.
        }
    }
}
