package com.example.app.Note;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.app.CenterActivity;
import com.example.app.R;
import android.app.AlertDialog.Builder;
import com.example.app.TFragment;

import java.text.SimpleDateFormat;
import java.util.*;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by xxx on 14-6-23.
 */
public class NotepadAdapter extends BaseAdapter {
    public Context context;
    public Context context1;
    public Activity activity;
    public LayoutInflater inflater;
    public Vector<String> list;
    public TFragment tFragment;
    public NotepadAdapter adapter;

    private ImageButton DelButton,EditButton;
    private TextView DataText,TitleText,ContentText;

    public NotepadAdapter(Context context,TFragment tFragment,
                          Vector<String> list) {

        this.context = activity;
        this.context1=context;
        this.list = list;
        this.tFragment=tFragment;
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
    public View getView(final int arg0, View arg1, ViewGroup arg2) {

        arg1 = inflater.inflate(R.layout.styles, arg2, false);

        TitleText=(TextView) arg1.findViewById(R.id.TitleText);
        ContentText = (TextView) arg1.findViewById(R.id.ContentText);
        DataText = (TextView) arg1.findViewById(R.id.DataText);
        EditButton=(ImageButton)arg1.findViewById(R.id.EditButton);
        DelButton=(ImageButton)arg1.findViewById(R.id.DelButton);
        LinearLayout layout=(LinearLayout)arg1.findViewById(R.id.layout);
        String Title = list.get(arg0);
        String Content = list.get(arg0);
        //TODO: 获取任务时间
        SimpleDateFormat sDateFormat   =   new   SimpleDateFormat("yyyy-MM-dd   hh:mm:ss");
        String  DateInfo   =   sDateFormat.format(new   java.util.Date());

        TitleText.setText("任务名称:"+Title);
        ContentText.setText("备注:"+Content);
        DataText.setText(DateInfo);
        //TODO:编辑
        EditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        DelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(arg0);
                notifyDataSetInvalidated();
            }
        });

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(tFragment.getActivity(),CenterActivity.class);
                intent.putExtra("Name", list.get(arg0));
                tFragment.startActivity(intent);

            }
        });

        return arg1;
    }

    public static int getFontSize(Context context, int textSize) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        int screenHeight = dm.heightPixels;
        int rate = (int) (textSize * (float) screenHeight / 1280);
        return rate;
    }



}