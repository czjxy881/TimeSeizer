package com.example.timerseizer;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.timerseizer.sql.StateEnum;
import com.example.timerseizer.sql.Task;
import com.example.timerseizer.sql.TodayTask;

import java.util.Vector;

/**
 * Created by xxx on 2014/6/30.
 */
public class TabListAdapter extends BaseAdapter {
    LayoutInflater inflater;
    private TextView TitleText;
    private CheckBox checkBox;
    TabListFragment ListFragment;



    /**
     * TaskList的适配器，并可以根据点击事件自动显示隐藏按钮
     * @param context 当前Activity getActivity()
     * @param tabListFragment 父Fragment,用于获得ClickListener
     */
    public TabListAdapter(Context context,TabListFragment tabListFragment){
        inflater=LayoutInflater.from(context);
        ListFragment=tabListFragment;
    }
    @Override
    public int getCount() {
        if(ListFragment.getList()==null)return 0;
        return ListFragment.getList().size();
    }

    @Override
    public Object getItem(int i) {
        if(ListFragment.getList()==null)return null;
        return ListFragment.getList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.tabstyles, viewGroup, false);
        checkBox = (CheckBox) view.findViewById(R.id.TabCheck);
        TitleText = (TextView) view.findViewById(R.id.TabTitleText);
        final LinearLayout layout = (LinearLayout) view.findViewById(R.id.Tablayout);
        checkBox.setChecked(ListFragment.isChecked(i));

        Task now = (Task) ListFragment.getList().get(i);
        String Title = now.getName();
        TitleText.setText("任务名称:" + Title);
        if(ListFragment.ListKind== TabListControllor.TabListKind.DoneList){
            String state=((TodayTask)now).getState();
            if(state.equals(StateEnum.ABANDON.toString())){
                state="<font color='red'>"+state+"</font>";
            }
            else{
                state="<font color='blue'>"+state+"</font>";
            }
            TitleText.setText(Html.fromHtml("任务名称:" + Title+" "+state));
        }


        //TODO 待测试
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.performClick();
                layout.performClick();
            }
        });

        layout.setOnClickListener(ListFragment.getClickListener(i, view));
        return view;
    }


    }
