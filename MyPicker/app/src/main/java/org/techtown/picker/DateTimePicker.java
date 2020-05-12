package org.techtown.picker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

public class DateTimePicker extends LinearLayout {

    DatePicker datePicker;
    TimePicker timePicker;
    CheckBox checkTimePicker;

    OnDateTimeChangeListener listener;

    public DateTimePicker(Context context) {
        super(context);
        init(context);
    }

    public DateTimePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.picker,this,true);

        datePicker = (DatePicker)findViewById(R.id.datePicker);
        timePicker = (TimePicker)findViewById(R.id.timePicker);
        checkTimePicker = (CheckBox)findViewById(R.id.checkTimePicker);

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int curYear = calendar.get(Calendar.YEAR);
        int curMonth = calendar.get(Calendar.MONTH);
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);
        int curHour = calendar.get(Calendar.HOUR_OF_DAY);
        int curMinute = calendar.get(Calendar.MINUTE);

        datePicker.init(curYear, curMonth, curDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if(listener!=null) {
                    listener.OnDateTimeChange(DateTimePicker.this,year,monthOfYear,dayOfMonth,timePicker.getCurrentHour(),timePicker.getCurrentMinute());
                }
            }

        });

        timePicker.setCurrentHour(curHour);
        timePicker.setCurrentMinute(curMinute);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(listener!=null) {
                    listener.OnDateTimeChange(DateTimePicker.this,datePicker.getYear(),datePicker.getMonth(),datePicker.getDayOfMonth(),hourOfDay,minute);
                }
            }
        });

        checkTimePicker.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {



                // 체크된 상태이면 enabled. 아니면 안됨.
                timePicker.setEnabled(isChecked);

                // 체크된 상태이면 VISIBLE. 아니면 INVISIBLE
                timePicker.setVisibility(checkTimePicker.isChecked()? View.VISIBLE:View.INVISIBLE);
            }
        });
    }

    public void setOnDateTimeChangeListener(OnDateTimeChangeListener listener) {
        this.listener = listener;
    }

    public void updateDateTime(int year, int monthOfYear, int dayOfMonth, int hour, int minute) {
        datePicker.updateDate(year, monthOfYear, dayOfMonth);
        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);
    }
}
