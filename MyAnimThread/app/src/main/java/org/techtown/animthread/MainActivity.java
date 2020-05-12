package org.techtown.animthread;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Drawable> imageList = new ArrayList<Drawable>();
    ImageView imageView;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageView);

        Resources res = getResources();
        imageList.add(res.getDrawable(R.drawable.item01));
        imageList.add(res.getDrawable(R.drawable.item02));
        imageList.add(res.getDrawable(R.drawable.item03));
        imageList.add(res.getDrawable(R.drawable.item04));
        imageList.add(res.getDrawable(R.drawable.item05));

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimThread thread = new AnimThread();
                thread.start();
            }
        });
    }

    class AnimThread extends Thread {
        public void run() {
            int index=0;

            for(int i=0; i<100; i++) {
                index = i%5;
                final Drawable drawable = imageList.get(index);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageDrawable(drawable);
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch(Exception e) {}
            }
        }
    }
}
