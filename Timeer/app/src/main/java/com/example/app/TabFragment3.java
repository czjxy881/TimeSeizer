package com.example.app;

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

public class TabFragment3 extends ListFragment {
    private ListView listView;
    private static TabFragment3 tabFragment3=null;
    private HashMap<Integer,Boolean> checkedMap1 = new HashMap<Integer, Boolean>();
    private Button btnlistadd3;

    private static final String[] presidents = new String[]{
            "Action", "Adventure", "Animation", "Children", "Comedy", "Documentary", "Drama",
            "Foreign", "History", "Independent", "Romance", "Sci-Fi", "Television", "Thriller"
    };
    public static TabFragment3 getInstance(){
        if (tabFragment3==null){
            tabFragment3=new TabFragment3();
            return tabFragment3;
        }else return tabFragment3;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_tab3, container, false);


        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_multiple_choice, presidents));

    }

    @Override
    public void onStart() {
        super.onStart();
        listView = getListView();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_multiple_choice, presidents);
        setListAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        btnlistadd3=(Button)getActivity().findViewById(R.id.btnlistadd3);
        btnlistadd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),TFragment.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    public void onListItemClick(ListView parent, View v,
                                int position, long id)
    {
        boolean checked = listView.isItemChecked(position);
        checkedMap1.put(position,checked);


        if(checked==true){
            Toast.makeText(getActivity(),
                    "You have selected " + presidents[position],
                    Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(),
                    "你取消了",
                    Toast.LENGTH_SHORT).show();
        }
    }

}
