package org.techtown.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class SimpleData implements Parcelable {

    int number;
    String message;

    public SimpleData(int number, String message) {
        this.number = number;
        this.message = message;
    }

    //생성자
    //parcel에서 simpledata안에 들어있는 변수를 read로 복원.
    public SimpleData(Parcel src){
        number = src.readInt();
        message = src.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
          public SimpleData createFromParcel(Parcel src){
              return new SimpleData(src);
          }

          public SimpleData[] newArray(int size){
              return new SimpleData[size];
          }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    //simpledata를 parcel로 바꿔준다.
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeString(message);
    }
}

/*
    parcel의 역할 : 객체 안에 들어있는 data를 다른 곳으로 전달할 때 사용되는 객체.
*/