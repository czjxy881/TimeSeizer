package com.example.timerseizer;

/**
 * Created by jxy on 14-6-30.
 */
public class TaskListControllor {
    public static enum ListKind{
        TodayList,PeriodList,AllList,DoneList
    }
    private static TaskListFragment []list=new TaskListFragment[4];

    /**
     * 获取相应实例
     * @param i ListKind形式的List
     * @return TaskListFragment单例
     */
    public static TaskListFragment getInstance(ListKind i){
        if(list[i.ordinal()]==null){
            list[i.ordinal()]=new TaskListFragment(i);
        }else{
            list[i.ordinal()].showUpdate();
        }
        return list[i.ordinal()];
    }
    public static TaskListFragment getInstance(int i){
        ListKind listKind=null;
        switch (i){
            case 0:listKind=ListKind.PeriodList;break;
            case 1:listKind=ListKind.AllList;break;
            case 2:listKind=ListKind.DoneList;break;
        }
        return getInstance(listKind);
    }
    public static void update(){
        for(ListKind s:ListKind.values()) {
            getInstance(s).showUpdate();
        }
    }
}
