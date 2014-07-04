package com.example.timerseizer;

/**
 * Created by xxx on 2014/6/30.
 */
public class TabListControllor {
    public static enum TabListKind{
        PeriodList,AllList,DoneList
    }
    public static TabListFragment []list=new TabListFragment[3];
    /**
     * 获取TablistFragment的实例
     * @param i TabListKind形式的List
     * @return 返回TabListFragment单例
     */
    public static TabListFragment getInstance(TabListKind i){
        if(list[i.ordinal()]==null){
            list[i.ordinal()]=new TabListFragment(i);
        }
        list[i.ordinal()].update();
        return list[i.ordinal()];
    }
    /**
     * 获取相应实例
     * @param i List的序号
     * @return TabListFragment单例
     */
    public static TabListFragment getInstance(int i){
        TabListKind listKind=null;
        switch (i){
            case 0:listKind=TabListKind.PeriodList;break;
            case 1:listKind=TabListKind.AllList;break;
            case 2:listKind=TabListKind.DoneList;break;
        }
        return getInstance(listKind);
    }
    public static void save(){
        for(TabListKind s:TabListKind.values()) {
            getInstance(s).save();
        }
    }
}
