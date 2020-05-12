package org.techtown.picker;

public interface OnDateTimeChangeListener {

    // DatePicker -> 년,월,일     TimePicker -> 시, 분    => 합쳐서 받겠다!
    public void OnDateTimeChange(DateTimePicker view,int year, int monthOfYear, int dayOfMonth, int hour, int minute);


}
