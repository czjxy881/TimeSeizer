package com.example.app.Note;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.app.CenterActivity;
import com.example.app.ListFragment2;
import com.example.app.ListFragment3;
import com.example.app.ListFragment4;
import com.example.app.R;

import java.text.SimpleDateFormat;
import java.util.Vector;

/**
 * Created by xxx on 14-6-28.
 */
public class NotepadAdapter4 extends BaseAdapter{
    public Context context;
    public Context context1;
    public Activity activity;
    public LayoutInflater inflater;
    public Vector<String> list;
    public ListFragment4 listFragment4;
    public NotepadAdapter adapter;

    private ImageButton DelButton,EditButton;
    private TextView DataText,TitleText,ContentText;

    public NotepadAdapter4(Context context,ListFragment4 listFragment4,
                           Vector<String> list) {

        this.context = activity;
        this.context1=context;
        this.list = list;
        this.listFragment4=listFragment4;
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

        arg1 = inflater.inflate(R.layout.styles4, arg2, false);

        TitleText=(TextView) arg1.findViewById(R.id.TitleText4);
        ContentText = (TextView) arg1.findViewById(R.id.ContentText4);
        DataText = (TextView) arg1.findViewById(R.id.DataText4);

        LinearLayout layout=(LinearLayout)arg1.findViewById(R.id.layout4);
        String Title = list.get(arg0);
        String Content = list.get(arg0);
        //TODO: 获取任务时间
        SimpleDateFormat sDateFormat   =   new   SimpleDateFormat("yyyy-MM-dd   hh:mm:ss");
        String  DateInfo   =   sDateFormat.format(new   java.util.Date());

        TitleText.setText("任务名称:"+Title);
        ContentText.setText("备注:"+Content);
        DataText.setText(DateInfo);




        return arg1;
    }



}

