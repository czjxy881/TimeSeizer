package com.example.app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.example.app.Note.NotepadAdapter3;

import java.util.Vector;

public class ListFragment3 extends Fragment {
    private Button numberButton3;
    private View mMainView;
    public NotepadAdapter3 adapter3;
    Vector<String> presidents = new Vector<String>();
    public ListView listView3;
    private static ListFragment3 listFragment3 = null;
    public static ListFragment3 getInstance(){
        if (listFragment3==null){
            listFragment3=new ListFragment3();
            return listFragment3;
        }else return listFragment3;
    }
    public ListFragment3(){
        presidents.add("123");
        presidents.add("222223");
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
        mMainView = inflater.inflate(R.layout.listfragment_three, (ViewGroup)getActivity().findViewById(R.id.main_viewpager), false);

        this.numberButton3 = ((Button) mMainView.findViewById(R.id.numberbtn3));
        this.listView3 = ((ListView) mMainView.findViewById(R.id.listview3));
        this.adapter3 = new NotepadAdapter3(getActivity(),listFragment3,presidents);
        this.listView3.setAdapter(this.adapter3);
        numberButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"1",Toast.LENGTH_SHORT).show();
            }
        });
        this.listView3.setDivider(null);
        this.listView3.setOnItemClickListener(new ItemClick());
    }

    class ItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        }
    }


}
