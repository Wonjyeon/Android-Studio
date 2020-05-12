package org.techtown.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatService extends Service {
    public ChatService() {
    }


    // 초기화 되는 시점.
    @Override
    public void onCreate() {
        super.onCreate();

        ServerThread thread = new ServerThread();
        thread.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    // 서버를 실행!
    class ServerThread extends Thread {
        public void run(){
            int port = 5001;

            try {
                ServerSocket server = new ServerSocket(port);
                Log.d("ServerThread","서버가 실행됨.");

                while(true) {
                    // 대기 상태로 들어가게 됨.
                    // 클라이언트가 접속을 하면 socket 객체가 리턴됨.
                    // 클라이언트 쪽에서 요청이 들어왔을 때 대응하는 객체 -> socket!
                    // 클라이언트가 요청하게 되면 그 요청 정보를 먼저 읽어서 확인해볼 수 있음.
                    Socket socket = server.accept();

                    // 들어오는 데이터를 처리 -> 일종의 파이프
                    ObjectInputStream instream = new ObjectInputStream(socket.getInputStream());
                    Object input = instream.readObject();
                    Log.d("ServerThread","input : "+input);


                    ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream());
                    outstream.writeObject(input+" from server.");
                    outstream.flush();    // 버퍼에 안남도록.
                    Log.d("ServerThread","output 보냄");


                    // 한정적인 리소스를 낭비하지 않도록!
                    socket.close();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
