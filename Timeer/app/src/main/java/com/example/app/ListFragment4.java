package com.example.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.app.Note.NotepadAdapter2;
import com.example.app.Note.NotepadAdapter4;

import java.util.Vector;

public class ListFragment4 extends Fragment {
    private View mMainView;
    public NotepadAdapter4 adapter4;
    Vector<String> presidents = new Vector<String>();
    public ListView listView4;
    RelativeLayout layout ;

    private static ListFragment4 listFragment4 = null;
    public static ListFragment4 getInstance(){
        if (listFragment4==null){
            listFragment4=new ListFragment4();
            return listFragment4;
        }else return listFragment4;
    }
    public ListFragment4(){
        presidents.add("123");
        presidents.add("222223");
        presidents.add("nininini你");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {

        return mMainView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        mMainView = inflater.inflate(R.layout.listfragment_four, (ViewGroup)getActivity().findViewById(R.id.main_viewpager), false);

        this.listView4 = ((ListView) mMainView.findViewById(R.id.listview4));
        this.adapter4 = new NotepadAdapter4(getActivity(),listFragment4,presidents);
        this.listView4.setAdapter(this.adapter4);

        this.listView4.setDivider(null);
        this.listView4.setOnItemClickListener(new ItemClick());
    }
    class ItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }
}
