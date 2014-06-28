package com.example.app.Note;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.app.ListFragment2;
import com.example.app.ListFragment4;
import com.example.app.R;

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
    public View getView(int arg0, View arg1, ViewGroup arg2) {

        SetShow setShow = new SetShow();


        arg1 = inflater.inflate(R.layout.styles4, arg2, false);
        setShow.cContentView = (TextViewLine) arg1
                .findViewById(R.id.changecontentview);
        setShow.cDateView = (TextView) arg1
                .findViewById(R.id.changedateview);
        String str = list.get(arg0);
        String dateStr = list.get(arg0);
        setShow.cContentView.setText("    任务名称"+"\n" + "    "+str);
        setShow.cDateView.setText(dateStr);



        return arg1;
    }






    class SetShow {
        public TextViewLine cContentView;
        public TextView cDateView;
        public Button styleButtonWrite;
        public Button styleButtonDelete;
        public Button styleButtonStart;

    }


}

