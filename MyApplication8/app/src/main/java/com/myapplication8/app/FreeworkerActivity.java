package com.myapplication8.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

public class FreeworkerActivity extends Activity {
    private Button btnfreeworker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list_fragment1);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        btnfreeworker = (Button) findViewById(R.id.btnfreeworker);

        btnfreeworker.setOnClickListener(
                new Button.OnClickListener() {
            public void onClick(View v) {
                builder.setTitle("添加任务");
                TableLayout freework = (TableLayout)getLayoutInflater().inflate(R.layout.freetimeworker,null);
                builder.setView(freework);
                builder.setPositiveButton("添加",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //添加
                    }
                });
                builder.create().show();
            }

        });
    }

}