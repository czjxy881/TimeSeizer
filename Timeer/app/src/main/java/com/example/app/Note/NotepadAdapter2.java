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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.app.CenterActivity;
import com.example.app.ListFragment2;
import com.example.app.R;
import com.example.app.TFragment;

import java.util.Vector;

/**
 * Created by xxx on 14-6-28.
 */
public class NotepadAdapter2 extends BaseAdapter{
    public Context context;
    public Context context1;
    public Activity activity;
    public LayoutInflater inflater;
    public Vector<String> list;
    public ListFragment2 listFragment2;
    public NotepadAdapter adapter;

    public NotepadAdapter2(Context context,ListFragment2 listFragment2,
                          Vector<String> list) {

        this.context = activity;
        this.context1=context;
        this.list = list;
        this.listFragment2=listFragment2;
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


        arg1 = inflater.inflate(R.layout.styles, arg2, false);
        setShow.cContentView = (TextViewLine) arg1
                .findViewById(R.id.changecontentview);
        setShow.cDateView = (TextView) arg1
                .findViewById(R.id.changedateview);
        String str = list.get(arg0);
        String dateStr = list.get(arg0);
        setShow.cContentView.setText("    任务名称"+"\n" + "    "+str);
        setShow.cDateView.setText(dateStr);
        setShow.styleButtonWrite = (Button) arg1
                .findViewById(R.id.stylebutton1);
        setShow.styleButtonDelete = (Button) arg1
                .findViewById(R.id.stylebutton2);
        setShow.styleButtonWrite
                .setOnClickListener(new WriteButtonListener(arg0));
        setShow.styleButtonDelete
                .setOnClickListener(new DeleteButtonListener(arg0));


        return arg1;
    }



    class WriteButtonListener implements View.OnClickListener {
        private int position;

        public WriteButtonListener(int position) {
            this.position = position;

        }



        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub


        }


    }

    class DeleteButtonListener implements View.OnClickListener {
        private int position;

        public DeleteButtonListener(int position) {
            this.position = position;

        }

        @Override
        public void onClick(View v) {

            String str = list.get(position);
            list.remove(str);

            notifyDataSetInvalidated();
        }

    }

    class SetShow {
        public TextViewLine cContentView;
        public TextView cDateView;
        public Button styleButtonWrite;
        public Button styleButtonDelete;

    }

    class SetEdit {
        public RelativeLayout layout;
        public Button cancelButton;
        public String content;
        public String date;
        public Date dateNow;
        public EditText editText;
        public String id;
        public Button sureButton;
        public TextView textView;
    }
}
