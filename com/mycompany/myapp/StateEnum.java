package com.myapplication8.app.Back;

/**
 * Created by cdkyee on 2014/4/17.
 */
public enum StateEnum {
    READY(0),ABANDON(1),FINISH(2),ERROR(3);
    private int num;
    private StateEnum(int num){
        this.num=num;
    }

    public int getNum() {
        return num;
    }
}
