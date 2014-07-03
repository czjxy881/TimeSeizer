package com.example.timerseizer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;


import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Created by xxx on 2014/6/30.
 */
public class TabListFragment extends Fragment {
    //TODO:获取数据库
    public Vector<String> presidents = new Vector<String>();
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


    public TabListFragment(TabListControllor.TabListKind listKind){
        //TODO 根据listKind生成
        ListKind=listKind;
        presidents.add("软件工程");
        presidents.add("软件体系结构");
        presidents.add("编译原理");
        presidents.add("人工智能");
        presidents.add("人机交互");
        presidents.add("云计算");
        presidents.add("并行程序设计");
        presidents.add("计算机图像");
        presidents.add("多媒体技术");
        presidents.add("嵌入式");
        isCheckMap.clear();
    }
    public Vector<String> getList(){
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
        //view=inflater.inflate(android.R.layout.list_content,container,false);
        view=inflater.inflate(R.layout.listfragment_one,container,false);
        ListView listView = ((ListView) view.findViewById(android.R.id.list));
        button=((Button)view.findViewById(R.id.btntanadd));
        adapter = new TabListAdapter(getActivity(),this);
        listView.setAdapter(adapter);
        listView.setDivider(null);
        return view;
    }
}
