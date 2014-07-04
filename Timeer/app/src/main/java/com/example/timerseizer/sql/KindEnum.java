package com.example.timerseizer.sql;

/**
 * Created by cdkyee on 2014/4/17.
 */
public enum KindEnum {
    Name_ASC(0),Name_DESC(1),ID_ASC(2),ID_DESC(3),ExpectNum_ASC(4),
    ExpectNum_DESC(5),ExpectDate_ASC(6),ExpectDate_DESC(7),NULL(8);
    private int num;
    private KindEnum(int num){
        this.num=num;
    }
    public int getNum(){
        return num;
    }
}
