package com.example.timerseizer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;


import com.example.timerseizer.sql.InnerforUI;
import com.example.timerseizer.sql.Task;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Created by xxx on 2014/6/30.
 */
public class TabListFragment extends Fragment {
    public Vector<Task> presidents = new Vector<Task>();
    Button button;
    View view=null;
    TabListAdapter adapter;
    CheckBox checkBox;
    TabListControllor.TabListKind ListKind;

    Map<Integer, Boolean> isCheckMap =  new HashMap<Integer, Boolean>();

    public boolean isChecked(int i){
        if(isCheckMap.containsKey(i)==false){
            isCheckMap.put(i,false);
        }
        return  isCheckMap.get(i);
    }
    public void save(){
        for(Integer i:isCheckMap.keySet()){
            if(isCheckMap.get(i)==true){
                switch (ListKind){
                    case PeriodList: InnerforUI.getInstance().addToTodayFromPeriodList(i);break;
                    case AllList:InnerforUI.getInstance().addToTodayFromAllList(i);break;
                    case DoneList:InnerforUI.getInstance().addToTodayFromDoneList(i);break;
                }

            }
        }
    }
    public void update(){
        switch (ListKind){
            case PeriodList:presidents= (Vector<Task>)(Vector)InnerforUI.getInstance().showPeroidTask();break;
            case AllList:presidents= (Vector<Task>)(Vector)InnerforUI.getInstance(getActivity()).showTask();break;
            case DoneList:presidents= (Vector<Task>)(Vector)InnerforUI.getInstance(getActivity()).showFinish();break;
        }
    }
    public TabListFragment(TabListControllor.TabListKind listKind){
        ListKind=listKind;
        isCheckMap.clear();
        update();
    }
    public Vector<Task> getList(){
        return presidents;
    }
    public View.OnClickListener getClickListener(final int i,final View sview) {
        View.OnClickListener now=new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                checkBox = (CheckBox) sview.findViewById(R.id.TabCheck);
                checkBox.setChecked(!checkBox.isChecked());
                isCheckMap.put(i, checkBox.isChecked());
            }
        };
        return now;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.listfragment_one,container,false);
        ListView listView = ((ListView) view.findViewById(android.R.id.list));
        button=((Button)view.findViewById(R.id.btntanadd));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((TabActivity)getActivity()).save();
                TaskListControllor.update();
                (getActivity()).finish();
            }
        });


        adapter = new TabListAdapter(getActivity(),this);
        listView.setAdapter(adapter);
        listView.setDivider(null);
        return view;
    }
}
