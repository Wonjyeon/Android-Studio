package org.techtown.web;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView)findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        //버튼을 클릭했을 때 동작을 시키자!

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 내가 만든 웹페이지
                webView.loadUrl("file:///android_asset/sample.html");

                /*
                웹페이지를 새로 띄우려면

                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://m.naver.com"));
                startActivity(intent);
                */
            }
        });

    }
}
