package com.example.timerseizer.sql;

/**
 * Created by cdkyee on 2014/4/17.
 */
public enum StateEnum {
    READY(0),ABANDON(1),FINISH(2),ERROR(3);
    int num;
    private StateEnum(int num){
        this.num=num;
    }
    public static StateEnum get(int i){
        switch (i){
            case 0:return READY;
            case 1:return ABANDON;
            case 2:return FINISH;
            case 3:return ERROR;
        }
        return ERROR;
    }
    public int getNum(){
        return num;
    }
}
