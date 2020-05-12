package org.techtown.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    // 상수로 TAG를 정의함 => 계속해서 사용할 수 있음.
    private static final String TAG = "MyService";
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // 출력하는 Log 의 종류를 TAG 로 구분할 수 있도록.
        Log.d(TAG,"onCreate() 호출됨.");
    }

    @Override

    /*
    서비스는 한 번 실행이 되면 계속 실행됨.
    말은 startService 이지만 startService 를 아무리 여러번 실행한다고 하더라도
    onCreate 는 이미 만들어져 있으니까 새로 만들어지지 않음.
    => 문제는 intent 안에 넣어서 전달하는 것이 onCreate 에서 확인할 수 없는 문제가 생김
    => service 는 intent 안에 넣어서 전달한 ExtraData 를 확인할 때는 onStartCommand 로 확인함
    => 명령어를 처리해주세요! 라는 의미가 됨.
    => Main 에서 보낸 intent 객체는 onStartCommand 에서 받아서 확인할 수 있음.
     */
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG,"onStartCommand() 호출됨.");


        // 처리를 안 하도록.
        if(intent==null){
            return Service.START_STICKY;
            // 서비스가 종료되었을 때에도 다시 자동으로 실행해달라! 라는 옵션.
        }
        else{
            // processCommand 메소드를 통해 intent 를 처리하도록!
            processCommand(intent);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent){
        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");

        Log.d(TAG,"전달받은 데이터 : "+command+" , "+name);

        try{
            //5초동안 중지
            Thread.sleep(5000);
        }catch(Exception e){};

        // Activity 로 보낼 거야!
        Intent showIntent = new Intent(getApplicationContext(),MainActivity.class);

        /*
        화면이 없는 service 에서 화면이 있는 activity 쪽으로 화면을 띄워달라고 하면 문제가 생김!
        화면은 task 로 묶여 있음. => 각각의 화면이 연속적으로 뜰 수 있도록 만든 것이 task
        화면이 없는 곳에서 화면을 띄울라고 하면 task 라는 정보가 없어서 문제가 생길 수 있음.
        => 옵션으로 flag 를 줘야 해.
        1. 화면이 없는 것에서 화면이 뜰 수 있게 함.
        2. MainActivity 가 이미 화면에 떠 있어 => 만들어져 있는 activity 면 그걸 재활용 해줘.
        3. 혹시 그 위에 다른 화면이 있으면 그걸 제거해줘.
         */
        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|
                            Intent.FLAG_ACTIVITY_SINGLE_TOP|
                            Intent.FLAG_ACTIVITY_CLEAR_TOP);
        showIntent.putExtra("command","show");
        showIntent.putExtra("name",name+" from Service.");
        startActivity(showIntent);

    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        Log.d(TAG,"onDestroy() 호출됨.");
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
