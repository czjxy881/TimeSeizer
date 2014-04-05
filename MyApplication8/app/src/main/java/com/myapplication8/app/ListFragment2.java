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
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;


public class ListFragment2 extends ListFragment {
    private Button btnadd2;

    public ListFragment2(){}

    String[] presidents = {
            "2","2","3","222","333","2","2","3","222","333"
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_fragment2, container, false);
        return v;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        btnadd2 = (Button) getActivity().findViewById(R.id.btnadd2);
        btnadd2.setOnClickListener(
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
