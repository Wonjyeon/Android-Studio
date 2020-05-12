
package org.techtown.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;

    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);
        editText4 = (EditText)findViewById(R.id.editText4);
        editText5 = (EditText)findViewById(R.id.editText5);


        textView = (TextView)findViewById(R.id.textView);



        // 데이터베이스 저장소를 만들거나 있으면 오픈!
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String databaseName = editText.getText().toString();
                openDatabase(databaseName);
            }
        });


        // 테이블 만들기. SQL문 사용 -> 데이터베이스를 오픈해야해.
        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tableName = editText2.getText().toString();
                createTable(tableName);
            }
        });


        // 데이터 추가하기.
        Button button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText3.getText().toString().trim();
                String ageStr = editText4.getText().toString().trim();
                String mobile = editText5.getText().toString().trim();

                int age = -1;
                try {
                    age = Integer.parseInt(ageStr);
                } catch(Exception e) {}

                insertData(name,age,mobile);
            }
        });


        // 데이터 조회하기.
        Button button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tableName = editText2.getText().toString();
                selectData(tableName);
            }
        });
    }





    // 데이터베이스 저장소를 생성
    public void openDatabase(String databaseName) {
        println("openDatabase() 호출됨.");
        database = openOrCreateDatabase(databaseName,MODE_PRIVATE,null);

        if(database!=null) {
            println("데이터베이스 오픈됨.");
        }
    }


    // 테이블 만들기
    public void createTable(String tableName) {
        println("createTable() 호출됨.");

        if(database!=null) {
            // 띄어쓰기 주의!
            // 현재 sql문은 만약 해당 이름의 테이블이 존재하는데 또 만들면 오류! -> 있으면 만들지 말라는 sql문을 작성해야 함!
            String sql = "create table if not exists " + tableName + "(_id integer PRIMARY KEY autoincrement, name text, age integer, mobile text)";
            database.execSQL(sql);

            println("테이블 생성됨.");
        }
        else {
            println("먼저 데이터베이스를 오픈하세요.");
        }
    }


    // 데이터 저장
    // 앱이 종료되더라도 해당 테이블에 데이터가 저장됨.
    public void insertData(String name, int age, String mobile) {
        println("insertData() 호출됨.");

        if(database!=null) {
            String sql = "insert into customer(name, age, mobile) values(?, ?, ?)";
            Object[] params = {name, age, mobile};

            // params 의 데이터로 sql 문의 ?를 대체해서 실행!
            database.execSQL(sql,params);

            println("데이터 추가함.");
        }
        else {
            println("먼저 데이터베이스를 오픈하세요.");
        }

    }


    // 데이터 조회
    public void selectData(String tableName) {
        println("selectData() 호출됨.");

        if(database!=null) {
            String sql = "select name, age, mobile from "+tableName;

            // 조회된 데이터를 하나씩 넘어가면서 확인할 수 있음!
            Cursor cursor = database.rawQuery(sql,null);
            // cursor.getCount() -> 레코드의 개수
            println("조회된 데이터의 개수 : " +cursor.getCount());


            for(int i=0; i<cursor.getCount(); i++) {
                // 다음 레코드로 이동.
                cursor.moveToNext();
                String name = cursor.getString(0);        // name 으로 뽑아냄.
                int age = cursor.getInt(1);
                String mobile = cursor.getString(2);

                println("#" + i + " -> " + name + ", " + age + ", " + mobile);
            }

            cursor.close();
        }
    }

    public void println(String data) {
        textView.append(data+"\n");
    }


}


