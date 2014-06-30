package com.example.app;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Vector;

/**
 * Created by jxy on 14-6-30.
 */
public class TaskListFragment extends Fragment {
    //TODO:获取数据库
    public Vector<String> presidents = new Vector<String>();
    View view=null;
    TaskListAdapter adapter;
    TaskListControllor.ListKind ListKind;
    public TaskListFragment(TaskListControllor.ListKind listKind){
        //TODO 根据listKind生成
        ListKind=listKind;
        presidents.add("123");
        presidents.add("222223");

    }
    //TODO: 测试用
    public void add(String s){
        presidents.add(s);
    }
    public Vector<String> getList(){
        return presidents;
    }
    public void showUpdate(){
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
                presidents.remove(i);
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
                    Bundle bundle = new Bundle();
                    bundle.putString("Name", presidents.get(i));
                    bundle.putString("Content", "软工大作业");
                    bundle.putInt("Num", 10);
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
