package org.techtown.progress;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Handler handler = new Handler();

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
        }
    }
}