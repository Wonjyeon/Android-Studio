package org.techtown.orientation2;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    // 설정이 바뀔 때 호출되는 것 => 방향이 바뀌어도 호출됨
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            Toast.makeText(this,"가로방향 됨",Toast.LENGTH_LONG).show();
        }
        else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            Toast.makeText(this,"세로방향 됨",Toast.LENGTH_LONG).show();
        }
    }
}
