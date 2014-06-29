package com.example.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.Note.NotepadAdapter;

import java.util.HashMap;
import java.util.Vector;

public class TabFragment1 extends Fragment {
    public CheckedTextView checkbox1;
    Vector<String> presidents = new Vector<String>();
    private View mMainView;
    private TabAdapter1 adapter;
    private int keys[] = new int[5];
    private int psn;
    private Button btnlistadd1;
    private ListView listView;
    HashMap<String, String> map = new HashMap<String, String>();
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
    public TabFragment1(){
        presidents.add("123");
        presidents.add("222223");
        }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        mMainView = inflater.inflate(R.layout.fragment_tab1, (ViewGroup)getActivity().findViewById(R.id.main_viewpager), false);
        super.onCreate(savedInstanceState);
        this.listView = ((ListView) mMainView.findViewById(R.id.tablist1));
        this.adapter = new TabAdapter1(getActivity(),tabFragment1,presidents);
        this.listView.setAdapter(this.adapter);
        this.listView.setDivider(null);
        this.listView.setOnItemClickListener(new ItemClick());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return mMainView;
    }

    class ItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            boolean checked = listView.isItemChecked(i);

            checkbox1.setChecked(checked);

            listView.setItemChecked(i,true);
            if(checked==true){

                Toast.makeText(getActivity(),
                        "You have selected " + presidents.get(i),
                        Toast.LENGTH_SHORT).show();
            }else {
                checkbox1.setChecked(checked);
                Toast.makeText(getActivity(),
                        "你取消了",
                        Toast.LENGTH_SHORT).show();
            }
            listView.deferNotifyDataSetChanged();

        }

    }
    @Override
    public void onStart() {

        super.onStart();
        //listView = getListView();
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_multiple_choice, GENRES);
        //setListAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        btnlistadd1 = (Button)getActivity().findViewById(R.id.btnlistadd1);
        btnlistadd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a=0;
                //Intent intent = new Intent();
                //intent.setClass(getActivity(),TFragment.class);
                for(int psn:checkedMap.keySet())
                    if (checkedMap.get(psn) == true) {
                        keys[a] = (psn);
                        a++;

                    }
                Toast.makeText(getActivity(),String.valueOf(checkedMap.keySet().size()),Toast.LENGTH_SHORT).show();

                getActivity().finish();


            }
        });


    }


    class TabAdapter1 extends BaseAdapter {
        public Context context;
        public Context context1;
        public Activity activity;
        public LayoutInflater inflater;
        public Vector<String> list;
        public TabFragment1 tabFragment1;
        public NotepadAdapter adapter;



        public TabAdapter1(Context context, TabFragment1 tabFragment1,
                           Vector<String> list) {


            this.context = activity;
            this.context1=context;
            this.list = list;
            this.tabFragment1=tabFragment1;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return list.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return list.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

        @Override
        public View getView(int arg0, View arg1, ViewGroup arg2) {

            SetShow setShow = new SetShow();


            arg1 = inflater.inflate(R.layout.stylestab, arg2, false);

            setShow.cDateView = (TextView) arg1
                    .findViewById(R.id.changedateview);
            setShow.checkBox=(CheckedTextView)arg1.findViewById(R.id.tabcheckbox);
            checkbox1=(CheckedTextView)arg1.findViewById(R.id.tabcheckbox);
            String dateStr = list.get(arg0);
            setShow.cDateView.setText(dateStr);
           if (null != map.get("isChecked")&&"1".equals(map.get("isChecked"))){
               setShow.checkBox.setChecked(true);
           }
            return arg1;
        }





        class SetShow {

            public TextView cDateView;
            public CheckedTextView checkBox;



        }
    }

}
