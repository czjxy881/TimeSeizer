package com.example.app.Note;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.app.CenterActivity;
import com.example.app.R;
import android.app.AlertDialog.Builder;
import com.example.app.TFragment;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by xxx on 14-6-23.
 */
public class NotepadAdapter extends BaseAdapter {
    public int textsize ;
    public Context context;
    public Context context1;
    public Activity activity;
    public LayoutInflater inflater;
    public Vector<String> list;
    public TFragment tFragment;
    public NotepadAdapter adapter;

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
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        SetShow setShow = new SetShow();

        arg1 = inflater.inflate(R.layout.styles, arg2, false);
        setShow.cContentView = (TextView) arg1
                .findViewById(R.id.changecontentview);
        setShow.cDateView = (TextView) arg1
                .findViewById(R.id.changedateview);
        String str = list.get(arg0);
        String dateStr = list.get(arg0);
        setShow.cContentView.setText("    任务名称:"+str+"\n" + "    任务备注"+str);
        setShow.cDateView.setText(dateStr);
        setShow.styleButtonWrite = (Button) arg1
                .findViewById(R.id.stylebutton1);
        setShow.styleButtonDelete = (Button) arg1
                .findViewById(R.id.stylebutton2);
        setShow.styleButtonWrite
                .setOnClickListener(new WriteButtonListener(arg0));
        setShow.styleButtonDelete
                .setOnClickListener(new DeleteButtonListener(arg0));
        setShow.cContentView.setTextSize(getFontSize(context1,20));

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
    public static int getFontSize(Context context, int textSize) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        int screenHeight = dm.heightPixels;
        int rate = (int) (textSize * (float) screenHeight / 1280);
        return rate;
    }

    class SetShow {
        public TextView cContentView;
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