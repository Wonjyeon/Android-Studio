package org.techtown.asynctask;

import android.os.AsyncTask;
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

                ProgressTask task = new ProgressTask();
                task.execute("시작");


            }
        });

    }

    class ProgressTask extends AsyncTask<String, Integer, Integer> {

        int value = 0;

        // 스레드 안에 넣는 코드를 여기에 넣으면 됨.
        // 스레드 안에서 어떤게 동작하게 하겠다!
        // 첫 번째 String
        // 얘가 어떤 값을 return 하느냐에 따라서 결과값 전달 받는 건 onPostExecute
        // Integer -> onPostExecute(Integer integer)
        @Override
        protected Integer doInBackground(String... strings) {
            while(true) {
                if(value>100){
                    break;
                }
                value+=1;


                // 밑에 onProgressUpdate 가 자동으로 호출됨.
                publishProgress(value);

                try{
                    Thread.sleep(200);
                }catch(Exception e) {}
            }
            return value;
        }


        // 두 번째 Integer
        // 위에서 넘긴 value 가 이쪽으로 넘어옴.
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            // 전달받은 데이터로 UI를 업데이트! (핸들러 실행하듯이)
            progressBar.setProgress(values[0].intValue());
        }


        // 세 번째 Integer
        // 결과값을 전달 받음
        // 맨 마지막 과정으로 얘가 호출됨.
        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

            Toast.makeText(getApplicationContext(),"완료됨",Toast.LENGTH_LONG).show();
        }
    }




}