package org.techtown.lifecycle;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    //onCreate : 수명주기 메소드 중 하나
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this,"onCreate 호출됨",Toast.LENGTH_LONG).show();
        // onCreate 메소드가 호출 되었을 때 메시지가 뜸.
        // 시스템이 해당 시점에 맞춰서 띄워 줌.

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



//우클릭->generate->overide methods 해서 ctrl키 누르고 동시에 오버라이드.
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this,"onStart 호출됨",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this,"onStop 호출됨",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"onDestroy 호출됨",Toast.LENGTH_LONG).show();
    }

    // 중지되기 바로 직전에 호출됨
    // 일반적으로 onPause 상태에서 필요한 데이터를 저장함.
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,"onPause 호출됨",Toast.LENGTH_LONG).show();

        // 간단하게 설정 정보 등을 저장하는 방법.
        // 이 메소드를 이용하여 참조할 수 있음.
        // 파라미터는 name / mode => name은 저장할 때와 복구할 때 동일한 이름을 써야 함.
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name","소녀시대");        // editor 에 값을 넣기 위함.
        editor.commit();    // commit 함수를 호출해야 editor 에 저장됨.
    }

    // 복구되기 바로 직전에 호출됨
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"onResume 호출됨",Toast.LENGTH_LONG).show();

        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        if(pref!=null){
            String name = pref.getString("name","");
            // 값이 비어있다면 빈 값을 달라 => 두 번째 파라미터에 넣어줌.
            Toast.makeText(this,"복구된 이름 : "+name,Toast.LENGTH_LONG).show();
        }
    }
    /*
    onCreate -> onStart -> onResume 순서
    (새로운 activity 생성 후 실행중인 경우 onResume 까지 호출됨)
    => 화면 닫기 버튼 클릭
    => onPause -> onStop ->onDestroy 순서
    (activity 중지된 이후에는 onDestroy 까지 가면서 없어짐)
     */

    /*
    Main 화면에서 Menu 화면을 띄울 경우
    - Main 화면은 중지됨 => onPause -> onStop 까지. onDestroy 까지는 호출X.
    => Stack 에 여전히 남아 있음.
    => 다시 Main 화면이 실행됨
    => onResume 메소드가 호출됨.
     */

    /*
    - 게임 같은 상황에서도 수명주기는 사용됨
    => 중지된 경우는 onPause / 다시 시작되는 경우는 onResume
    => 게임에서 점수를 저장할 때는 onPause / 다시 복구할 때는 onResume
     */
}
