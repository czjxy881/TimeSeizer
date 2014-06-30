package com.example.app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

/**
 * Created by jxy on 14-6-30.
 */
public class TaskListAdapter extends BaseAdapter {
    //TODO:改成Task类
    Vector<String> list=null;
    LayoutInflater inflater;
    private ImageButton DelButton,EditButton;
    private TextView DataText,TitleText,ContentText;
    TaskListFragment ListFragment;
    /**
     * TaskList的适配器，并可以根据点击事件自动显示隐藏按钮
     * @param context 当前Activity getActivity()
     * @param taskListFragment 父Fragment,用于获得ClickListener并根据ClickLinstener自动显隐按钮
     */
    public TaskListAdapter(Context context,TaskListFragment taskListFragment){
        inflater=LayoutInflater.from(context);
        ListFragment=taskListFragment;
        list=taskListFragment.getList();
    }

    @Override
    public int getCount() {
        if(list==null)return 0;
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        if(list==null)return null;
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view=inflater.inflate(R.layout.styles,viewGroup,false);

        TitleText=(TextView) view.findViewById(R.id.TitleText);
        ContentText = (TextView) view.findViewById(R.id.ContentText);
        DataText = (TextView) view.findViewById(R.id.DataText);
        EditButton=(ImageButton)view.findViewById(R.id.EditButton);
        DelButton=(ImageButton)view.findViewById(R.id.DelButton);
        LinearLayout layout=(LinearLayout)view.findViewById(R.id.layout);

        if(ListFragment.getEditClickListener(i)==null)EditButton.setVisibility(View.INVISIBLE);
        if(ListFragment.getDelClickListener(i)==null)DelButton.setVisibility(View.INVISIBLE);

        String Title = list.get(i);
        String Content = list.get(i);
        //TODO: 获取任务时间
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd   hh:mm:ss");
        String  DateInfo = sDateFormat.format(new java.util.Date());

        TitleText.setText("任务名称:"+Title);
        ContentText.setText("备注:"+Content);
        DataText.setText(DateInfo);


        EditButton.setOnClickListener(ListFragment.getEditClickListener(i));
        DelButton.setOnClickListener(ListFragment.getDelClickListener(i));
        layout.setOnClickListener(ListFragment.getClickListener(i));
        return view;
    }
}