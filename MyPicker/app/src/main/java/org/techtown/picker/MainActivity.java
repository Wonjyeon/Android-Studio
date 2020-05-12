package org.techtown.picker;

import android.nfc.TagLostException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    DateTimePicker picker;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        picker = (DateTimePicker)findViewById(R.id.picker);

        picker.setOnDateTimeChangeListener(new OnDateTimeChangeListener() {
            @Override
            public void OnDateTimeChange(DateTimePicker view, int year, int monthOfYear, int dayOfMonth, int hour, int minute) {

                // 넘어오는 정보들을 문자열로 바꿔서 textView에 표시하려면?
                // dateformat이 필요!
                Calendar calendar = Calendar.getInstance();
                calendar.set(year,monthOfYear,dayOfMonth,hour,minute);
                String curTime = format.format(calendar.getTime());

                textView.setText(curTime);
            }
        });
    }
}
