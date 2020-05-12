package org.techtown.grid;


// 걸그룹에 대한 데이터를 담을 클래스!
public class SingerItem {

    String name;
    String mobile;
    int resId;

    // Constructor 선택해서 생성자 만들기
    public SingerItem(String name, String mobile, int resId) {
        this.name = name;
        this.mobile = mobile;
        this.resId=resId;
    }


    // getter and setter 선택
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    // toString 선택 => 객체 안에 들어가있는 거를 문자열로 출력
    @Override
    public String toString() {
        return "SingerItem{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
