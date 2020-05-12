package org.techtown.socket;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClientThread thread = new ClientThread();
                thread.start();
            }
        });
    }

    class ClientThread extends Thread {
        public void run() {
            // IP를 지정
            String host = "localhost";
            int port = 5001;

            try {
                Socket socket = new Socket(host, port);

                // 보내기 위한 통로를 만듦.
                ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream());
                outstream.writeObject("안녕");
                outstream.flush();

                Log.d("ClientThread","서버로 보냄");

                ObjectInputStream instream = new ObjectInputStream(socket.getInputStream());
                final Object input = instream.readObject();
                Log.d("ServerThread","받은 데이터 : "+input);

                // 이렇게 되면 핸들러가 안쓰였기 때문에 에러!
                // textView.setText("받은 데이터 : "+input);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("받은 데이터 : "+input);
                    }
                });
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
