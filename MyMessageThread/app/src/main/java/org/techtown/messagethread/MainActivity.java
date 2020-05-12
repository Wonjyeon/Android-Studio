package org.techtown.messagethread;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Handler handler = new Handler();

    CompletionThread completionThread;

    String msg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);


        // 버튼이 클릭되면 스레드를 실행 -> 하나씩 프로그레스 바에 증가시킬 것!
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 5초 후에 실행됨.
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ProgressThread thread = new ProgressThread();
                        thread.start();
                    }
                }, 5000);

            }
        });

        completionThread = new CompletionThread();
        completionThread.start();

    }

    class ProgressThread extends Thread {

        int value = 0;

        public void run() {

            while(true) {
                if(value>100){
                    break;
                }
                value+=1;

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setProgress(value);
                    }
                });

                try{
                    Thread.sleep(200);
                }catch(Exception e) {}
            }


            // 프로그레스바 꽉 참 -> 끝났다는 메시지를 띄우자!
            // 스레드 쪽으로 데이터를 전달.
            // post 방식으로 했으니까 이 코드 자체가 스레드 안에서 실행된다!
            completionThread.completionHandler.post(new Runnable() {
                @Override
                public void run() {
                    msg = "OK";

                    // Log.d("MainActivity","메시지 : "+msg);
                    Toast.makeText(getApplicationContext(),"메시지 : "+msg,Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    // 완료 정보를 처리할 스레드를 추가. -> 얘 만의 핸들러를 정의해줌 -> 루퍼도 정의!
    class CompletionThread extends Thread {

        public Handler completionHandler = new Handler();

        public void run() {
            Looper.prepare();
            Looper.loop();
        }
    }
}