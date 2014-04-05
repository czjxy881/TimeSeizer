package com.myapplication8.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class FreewokrerFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//可以用下面的方法得到参数
//      mNum = getArguments().getInt("num");
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View v = mInflater.inflate(R.layout.freetimeworker,null);
        return new AlertDialog.Builder(getActivity())
                .setTitle("111")
                .setView(v)
                .setPositiveButton("222",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
//确定按钮do something
                            }
                        }
                )
                .setNegativeButton("333",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
//取消按钮do something
                            }
                        }
                )
                .create();
    }
}
