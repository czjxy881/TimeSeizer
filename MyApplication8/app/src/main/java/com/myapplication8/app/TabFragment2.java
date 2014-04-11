package com.myapplication8.app;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;
public class TabFragment2 extends ListFragment {
    private int keys[] = new int[5];
    private int psn;
    private Button btnlistadd2;
    private ListView listView;
    private HashMap<Integer,Boolean> checkedMap2 = new HashMap<Integer, Boolean>();
    private static final String[] GENRES2 = new String[]{
            "a", "b", "c", "e", "d", "Documentary", "Drama",
            "Foreign", "History", "Independent", "Romance", "Sci-Fi", "Television", "Thriller"
    };

    private static TabFragment2 tabFragment2=null;
    public static TabFragment2 getInstance(){
        if (tabFragment2==null){
            tabFragment2=new TabFragment2();
            return tabFragment2;
        }else return tabFragment2;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab_fragment2, container, false);

        return view;
    }

    @Override
    public void onStart() {

        super.onStart();
        listView = getListView();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_multiple_choice, GENRES2);
        setListAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        btnlistadd2=(Button)getActivity().findViewById(R.id.btnlistadd2);
        btnlistadd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ListActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });


    }
    public void onListItemClick(ListView parent, View v,
                                int position, long id)
    {
        boolean checked = listView.isItemChecked(position);
        checkedMap2.put(position,checked);


        if(checked==true){
            Toast.makeText(getActivity(),
                    "You have selected " + GENRES2[position],
                    Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(),
                    "你取消了",
                    Toast.LENGTH_SHORT).show();
        }

    }

}