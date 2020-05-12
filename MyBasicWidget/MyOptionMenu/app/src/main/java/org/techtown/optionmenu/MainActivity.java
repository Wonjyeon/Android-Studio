package org.techtown.optionmenu;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 액션바 숨겨보기
        //ActionBar abar = getSupportActionBar();
        //abar.hide();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }


    // 메뉴 아이템이 선택되었을 때 호출됨.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // 내부에서는 아이디를 int 값으로 관리함.
        int curId = item.getItemId();
        switch(curId)
        {
            case R.id.menu_refresh:
                Toast.makeText(this,"새로고침 메뉴 클릭됨.",Toast.LENGTH_LONG).show();
                break;

            case R.id.menu_search:
                Toast.makeText(this,"검색 메뉴 클릭됨.",Toast.LENGTH_LONG).show();
                break;

            case R.id.menu_settings:
                Toast.makeText(this,"설정 메뉴 클릭됨.",Toast.LENGTH_LONG).show();
                break;

            default:
                    break;

        }

        return super.onOptionsItemSelected(item);
    }
}
