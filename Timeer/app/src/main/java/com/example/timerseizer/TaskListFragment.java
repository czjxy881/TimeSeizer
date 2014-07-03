package com.example.timerseizer;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;


import com.example.timerseizer.sql.InnerforUI;
import com.example.timerseizer.sql.Task;

import java.util.Vector;

/**
 * Created by jxy on 14-6-30.
 */
public class TaskListFragment extends Fragment {

    public Vector<Task> presidents = new Vector<Task>();

    View view=null;
    TaskListAdapter adapter;
    TaskListControllor.ListKind ListKind;
    public TaskListFragment(TaskListControllor.ListKind listKind){
        //TODO 根据listKind生成
        ListKind=listKind;

        //InnerforUI.getInstance(getActivity()).clickAddTask("软件工程大作业",5,"2014-07-01","Time Seizer",0);
        showUpdate();




    }
    public void add(Task s){
        switch(ListKind) {
            case TodayList:InnerforUI.getInstance(getActivity()).clickAddTask(s.getName(),s.getExpectNum(),s.getExpectDate(),s.getNoteString(),0);break;
            //TODO:周期任务kind
            case PeriodList:InnerforUI.getInstance().clickAddPeroidNewTask(s.getName(),s.getExpectNum(),s.getExpectDate(),s.getNoteString(),1,0);break;
        }
    }
    public Vector<Task> getList(){
        return presidents;
    }
    public void showUpdate(){
        switch(ListKind){
            case TodayList:presidents= (Vector<Task>)(Vector)InnerforUI.getInstance(getActivity()).showTodayList();break;
            case PeriodList:presidents= (Vector<Task>)(Vector)InnerforUI.getInstance(getActivity()).showPeroidTask();break;
            case AllList:presidents= (Vector<Task>)(Vector)InnerforUI.getInstance(getActivity()).showTask();break;
            case DoneList:presidents= (Vector<Task>)(Vector)InnerforUI.getInstance(getActivity()).showFinish();break;
        }
        if(adapter==null)return;
        adapter.notifyDataSetChanged();
    }
    public View.OnClickListener getEditClickListener(int i){
        if(ListKind== TaskListControllor.ListKind.DoneList)return null;
        View.OnClickListener now=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 增加编辑事件
                adapter.notifyDataSetChanged();
            }
        };
        return now;
    }
    public View.OnClickListener getDelClickListener(final int i){
        if(ListKind== TaskListControllor.ListKind.DoneList)return null;
        View.OnClickListener now=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO:ListKind 删除
                switch(ListKind) {
                    case TodayList:InnerforUI.getInstance().clickDeleteTodayTask(i);break;
                    case AllList:InnerforUI.getInstance().clickDeleteTask(i);break;
                    case PeriodList:InnerforUI.getInstance().clickDeletePeroid(i);break;
                }
                showUpdate();
            }
        };
        return now;
    }
    public View.OnClickListener getClickListener(final int i){
        if(ListKind!= TaskListControllor.ListKind.TodayList)return null;
        View.OnClickListener now=new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(CenterFragment.getInstance().isfree()==false){
                    Toast.makeText(getActivity(),"有任务正在执行,不要三心二意",Toast.LENGTH_SHORT).show();
                }
                else {
                    Task now=presidents.get(i);
                    Bundle bundle = new Bundle();
                    bundle.putString("Name", now.getName());
                    bundle.putInt("Num", now.getExpectNum());
                    bundle.putInt("RunnerID",now.getRunnerID());
                    CenterFragment.getInstance().setInfo(bundle);
                    ((MainActivity) getActivity()).setFragment(1);
                }
                }
        };
        return now;
    }
    //TODO: Abandon
    public View.OnLongClickListener getLongClickListener(int i){
        if(ListKind!= TaskListControllor.ListKind.TodayList)return null;
        View.OnLongClickListener now=new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        };
        return null;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view=inflater.inflate(android.R.layout.list_content,container,false);
        ListView listView = ((ListView) view.findViewById(android.R.id.list));
        adapter = new TaskListAdapter(getActivity(),this);
        listView.setAdapter(adapter);
        listView.setDivider(null);
        return view;
    }

}
