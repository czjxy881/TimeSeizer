package com.myapplication8.app;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;


public class ListFragment3 extends ListFragment {
    private Button btnadd3;
    private EditText etworker3;
    private EditText etfanqie3;
    private EditText etbeizhu3;

    private static ListFragment3 listFragment3 = null;
    public static ListFragment3 getInstance(){
        if (listFragment3==null){
            listFragment3=new ListFragment3();
            return listFragment3;
        }else return listFragment3;
    }
    String[] presidents = {
            "a","v","cc","aaa","ss","a","v","cc","aaa","ss","a","v","cc","aaa","ss"
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_fragment3, container, false);
        return v;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        btnadd3 = (Button) getActivity().findViewById(R.id.btnadd3);
        btnadd3.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        builder.setTitle("添加任务");
                        final TableLayout freework = (TableLayout) getLayoutInflater(null).inflate(R.layout.freetimeworker2, null);
                        builder.setView(freework);
                        builder.setPositiveButton("添加", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                EditText etworker3 = (EditText)freework.findViewById(R.id.etworker2);
                                EditText etfanqie3 = (EditText)freework.findViewById(R.id.etfanqie2);
                                EditText etbeizhu3 = (EditText)freework.findViewById(R.id.etbeizhu2);
                                String a = etworker3.getText().toString();
                                String b = etfanqie3.getText().toString();
                                String c = etbeizhu3.getText().toString();
                                Toast.makeText(getActivity(),a+"+"+b+"+"+c,Toast.LENGTH_SHORT).show();
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
                "You have selected " + presidents[position],
                Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }



}