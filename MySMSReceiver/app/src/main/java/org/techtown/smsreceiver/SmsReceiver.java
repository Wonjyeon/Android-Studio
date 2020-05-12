package org.techtown.smsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {

    private static final String TAG = "SmsReceiver";

    // Date 형식의 data 를 문자열로 변환해주기 위함.
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    // 날짜와 시간:분 형식으로 만들어줄 수 있는 SimpleDateFormat 형성

    @Override
    // callBack 메소드
    // SMS 문자를 받겠다라고 등록을 했을 경우에 SMS 가 오면 얘가 자동으로 호출됨.
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG,"onReceive() 호출됨");

        Bundle bundle = intent.getExtras();
        // 번들이라는 객체 => 해쉬 테이블처럼 엑스트라 데이터를 담고 있는 애
        // => 이 자체로 통째로 뽑아낼 수 있음
        SmsMessage[] messages = parseSmsMessage(bundle);

        if(messages.length > 0){

            // 발신자 주소 => getOriginatingAddress()
            String sender = messages[0].getOriginatingAddress();
            Log.d(TAG,"sender : "+sender);


            // 메시지 내용 => getMessageBody()
            String contents = messages[0].getMessageBody().toString();
            Log.d(TAG,"contents : "+contents);

            // 발신 시각
            Date receivedDate = new Date(messages[0].getTimestampMillis());
            Log.d(TAG,"received date : "+receivedDate);

            sendToActivity(context,sender,contents,receivedDate);

        }
    }

    private void sendToActivity(Context context,String sender, String contents, Date receivedDate){
        Intent intent = new Intent(context,SmsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|
                        Intent.FLAG_ACTIVITY_SINGLE_TOP|
                        Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("sender",sender);
        intent.putExtra("contents",contents);
        intent.putExtra("receivedDate",format.format(receivedDate));

        context.startActivity(intent);
    }

    // Bundle 이라고 하는 intent 안의 extraData =>smsData를 뽑아내자!
    // SmsMessage[] 를 return
    // SmsMessage[] 안에 sms데이터가 들어감 => 쉽게 데이터를 뽑아낼 수 있음.
    // 위에 onReceive 에서 뽑을 수 있음.
    private SmsMessage[] parseSmsMessage(Bundle bundle){
        // pdus : sms 데이터를 처리하는 국제 표준 프로토콜 smpp => 그 안에 데이터가 pdus라는 이름으로 들어가있음.
        // 이 안에 sms 데이터와 관련된 내용들이 들어가있음.
        Object[] objs = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];

        for(int i=0; i<objs.length; i++){
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                String format = bundle.getString("format");
                SmsMessage.createFromPdu((byte[])objs[i],format);
            }
            else{
                messages[i] = SmsMessage.createFromPdu((byte[])objs[i]);
            }
        }
        return messages;
    }
}
