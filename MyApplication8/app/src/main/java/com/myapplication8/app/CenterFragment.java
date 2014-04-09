package com.myapplication8.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;


public class CenterFragment extends Fragment {
    private Button btnnowworke;
    private TextView tvnowworke;

    String[] presidents = {
            "a","v","cc","aaa","ss","a","v","cc","aaa","ss","a","v","cc","aaa","ss"
    };
    private static CenterFragment centerFragment=null;
    public static CenterFragment getInstance(){
        if (centerFragment==null){
            centerFragment=new CenterFragment();
            return centerFragment;
        }else return centerFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnnowworke = (Button)getActivity().findViewById(R.id.btnnowworke);
        tvnowworke = (TextView)getActivity().findViewById(R.id.tvnowworke);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        btnnowworke.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        builder.setTitle("添加当前任务");
                        final TableLayout centerdialog = (TableLayout) getLayoutInflater(null).inflate(R.layout.centerdialog, null);
                        builder.setView(centerdialog);
                        builder.setItems(presidents,new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                tvnowworke.setText(presidents[i]);

                            }
                        });

                        builder.create().show();
                    }


                }
        );
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }



}
