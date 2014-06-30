package com.example.app;

/**
 * Created by xxx on 2014/6/30.
 */
public class TabListControllor {
    public static enum TabListKind{
        TAbperiodList,TaballList,TabdoneList
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
            case 0:listKind=TabListKind.TAbperiodList;break;
            case 1:listKind=TabListKind.TaballList;break;
            case 2:listKind=TabListKind.TabdoneList;break;
        }
        return getInstance(listKind);
    }
}
