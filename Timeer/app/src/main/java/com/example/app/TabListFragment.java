package com.example.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

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
    public TabListFragment(TabListControllor.TabListKind listKind){
        //TODO 根据listKind生成
        ListKind=listKind;
        presidents.add("123");
        presidents.add("222223");
        presidents.add("123");
        presidents.add("222223");
        presidents.add("123");
        presidents.add("222223");
        presidents.add("123");
        presidents.add("222223");
        presidents.add("123");
        presidents.add("222223");
    }
    public Vector<String> getList(){
        return presidents;
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
