package org.techtown.smsreceiver;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 나중엔 복사해서 사용해도 됨.
        int permissionCheck = ContextCompat.checkSelfPermission(this,Manifest.permission.RECEIVE_SMS);
        // 권한이 있는지 없는지 체크
        if(permissionCheck==PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this,"SMS 수신 권한 주어져 있음.",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"SMS 수신 권한 없음",Toast.LENGTH_LONG).show();

            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.RECEIVE_SMS)){
                Toast.makeText(this,"SMS 권한 설명 필요함",Toast.LENGTH_LONG).show();
            }
            else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECEIVE_SMS},101);
            }
        }
    }


    // 우리가 권한이 부여가 됐는지 안됐는지 알 수 있음.

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case 1:
                if(grantResults.length>0) {
                    if(grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this,"SMS 수신 권한을 사용자가 승인함",Toast.LENGTH_LONG).show();
                    }
                    else if(grantResults[0]==PackageManager.PERMISSION_DENIED) {
                        Toast.makeText(this,"SMS 수신 권한을 사용자가 거부함",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(this,"SMS 수신 권한을 부여받지 못함",Toast.LENGTH_LONG).show();
                    }
                }

        }
    }
}
