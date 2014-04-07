package com.myapplication8.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.myapplication8.app.Back.*;

import java.util.Vector;

public class ListFragment1 extends ListFragment {
    private Button btnadd;
    private Button btnfreeworker;
    Vector<String> presidents = new Vector<String>();

    public ListFragment1() {
        presidents.add("123");
        presidents.add("2312");
      //  Vector<TodayTask> TodayLi = InnerforUI.getInstance(getActivity()).showTodayList();
        //for(TodayTask s:TodayLi){
       // //    presidents.add(s.getName());

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_fragment1, container, false);
        final Button btnadd = (Button) v.findViewById(R.id.btnadd);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getActivity(),TabActivity.class);
                startActivity(intent);

            }
        });
        return v;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        btnfreeworker = (Button) getActivity().findViewById(R.id.btnfreeworker);
        btnfreeworker.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        builder.setTitle("添加任务");
                        TableLayout freework = (TableLayout) getLayoutInflater(null).inflate(R.layout.freetimeworker, null);
                        builder.setView(freework);
                        builder.setPositiveButton("添加", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //添加
                            }
                        });
                        builder.setNegativeButton("取消",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //取消
                            }
                        });
                        builder.create().show();
                    }


                }
        );
    }





    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, presidents));
    }

    public void onListItemClick(ListView parent, View v,
                                int position, long id)
    {
        Toast.makeText(getActivity(),
                "selected " + presidents.get(position),
                Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }


}
