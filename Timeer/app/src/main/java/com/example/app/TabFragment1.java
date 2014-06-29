package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v4.app.ListFragment;
import java.util.HashMap;

public class TabFragment1 extends ListFragment{
    private int keys[] = new int[5];
    private int psn;
    private Button btnlistadd1;
    private ListView listView;
    private HashMap<Integer,Boolean> checkedMap = new HashMap<Integer, Boolean>();
    private static final String[] GENRES = new String[]{
            "Action", "Adventure", "Animation", "Children", "Comedy", "Documentary", "Drama",
            "Foreign", "History", "Independent", "Romance", "Sci-Fi", "Television", "Thriller"
    };

    private static TabFragment1 tabFragment1 = null;

    public static TabFragment1 getInstance() {
        if (tabFragment1 == null) {
            tabFragment1 = new TabFragment1();
            return tabFragment1;
        } else return tabFragment1;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab1, container, false);

        return view;
    }
    public void onListItemClick(ListView parent, View v,
                                int position, long id)
    {
        boolean checked = listView.isItemChecked(position);
        checkedMap.put(position,checked);


        if(checked==true){
            Toast.makeText(getActivity(),
                    "You have selected " + GENRES[position],
                    Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(),
                    "你取消了",
                    Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onStart() {

        super.onStart();
        listView = getListView();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_multiple_choice, GENRES);
        setListAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        btnlistadd1 = (Button)getActivity().findViewById(R.id.btnlistadd1);
        btnlistadd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a=0;
                Intent intent = new Intent(getActivity(),TFragment.class);
                for(int psn:checkedMap.keySet())
                    if (checkedMap.get(psn) == true) {
                        keys[a] = (psn);
                        a++;

                    }
                Toast.makeText(getActivity(),String.valueOf(checkedMap.keySet().size()),Toast.LENGTH_SHORT).show();
                startActivity(intent);
                //getActivity().finish();
            }
        });


    }
}