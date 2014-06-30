package com.example.app;

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
        }
        return list[i.ordinal()];
    }

}
