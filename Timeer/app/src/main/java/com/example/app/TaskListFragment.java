package com.example.app;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

<<<<<<< HEAD
import com.example.app.sql.InnerforUI;
import com.example.app.sql.Task;

import java.util.Objects;
=======
>>>>>>> origin/UI
import java.util.Vector;

/**
 * Created by jxy on 14-6-30.
 */
public class TaskListFragment extends Fragment {
    //TODO:获取数据库
<<<<<<< HEAD
    public Vector<Task> presidents = new Vector<Task>();

=======
    public Vector<String> presidents = new Vector<String>();
>>>>>>> origin/UI
    View view=null;
    TaskListAdapter adapter;
    TaskListControllor.ListKind ListKind;
    public TaskListFragment(TaskListControllor.ListKind listKind){
        //TODO 根据listKind生成
<<<<<<< HEAD


        InnerforUI.getInstance(getActivity()).clickAddTask("test",5,"2014-7-1","test1",0);

        ListKind=listKind;
        showUpdate();
=======
        ListKind=listKind;
        presidents.add("123");
        presidents.add("222223");
>>>>>>> origin/UI

    }
    //TODO: 测试用
    public void add(String s){
<<<<<<< HEAD
       // presidents.add(s);
    }
    public Vector<Task> getList(){
        return presidents;
    }
    public void showUpdate(){
        //TODO:根据listKind
        presidents= (Vector<Task>)(Vector)InnerforUI.getInstance(getActivity()).showTodayList();
=======
        presidents.add(s);
    }
    public Vector<String> getList(){
        return presidents;
    }
    public void showUpdate(){
>>>>>>> origin/UI
        if(adapter==null)return;
        adapter.notifyDataSetChanged();
    }
    public View.OnClickListener getEditClickListener(int i){
        if(ListKind== TaskListControllor.ListKind.DoneList)return null;
        View.OnClickListener now=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                //TODO:ListKind
<<<<<<< HEAD
                InnerforUI.getInstance().clickDeleteTodayTask(i);
                //presidents.remove(i);
                
=======
                presidents.remove(i);
>>>>>>> origin/UI
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
                    Toast.makeText(getActivity(),"有任务正在执行,不要三心二意",Toast.LENGTH_SHORT);
                }
                else {
<<<<<<< HEAD
                    Task now=presidents.get(i);
                    Bundle bundle = new Bundle();
                    bundle.putString("Name", now.getName());
                   // bundle.putString("Content", now.get);
                    bundle.putInt("Num", now.getExpectNum());
=======
                    Bundle bundle = new Bundle();
                    bundle.putString("Name", presidents.get(i));
                    bundle.putString("Content", "软工大作业");
                    bundle.putInt("Num", 10);
>>>>>>> origin/UI
                    CenterFragment.getInstance().setInfo(bundle);
                    ((MainActivity) getActivity()).setFragment(1);
                }
                }
        };
        return now;
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
