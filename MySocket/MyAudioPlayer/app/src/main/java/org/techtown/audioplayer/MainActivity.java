package org.techtown.audioplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String url = "http://sites.google.com/site/ubiaccessmobile/sample_audio.amr";

    MediaPlayer player;

    // 재생한 위치.
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 재생
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio();
            }
        });


        // 일시정지
        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseAudio();
            }
        });


        // 재시작
        Button button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resumeAudio();
            }
        });


        // 정지
        Button button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAudio();
            }
        });
    }



    // 미디어 플레이어를 이용하여 플레이!
    public void playAudio() {
        try {
            closePlayer();

            player = new MediaPlayer();
            player.setDataSource(url);
            player.prepare();
            player.start();

            Toast.makeText(this,"재생 시작됨.",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void pauseAudio() {
        if(player != null) {
            position = player.getCurrentPosition();
            player.pause();

            Toast.makeText(this,"일시정지됨.",Toast.LENGTH_LONG).show();
        }
    }


    public void resumeAudio() {
        if(player != null && !player.isPlaying()) {
            player.seekTo(position);
            player.start();

            Toast.makeText(this,"재시작됨.",Toast.LENGTH_LONG).show();
        }
    }


    public void stopAudio() {
        if(player != null && player.isPlaying()) {
            player.stop();

            Toast.makeText(this,"중지됨.",Toast.LENGTH_LONG).show();
        }
    }


    // 리소스를 해제해주는 메소드
    public void closePlayer () {
        if(player != null) {
            player.release();
            player = null;
        }
    }
}
