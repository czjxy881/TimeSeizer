package com.example.timerseizer;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.timerseizer.sql.StateEnum;
import com.example.timerseizer.sql.Task;
import com.example.timerseizer.sql.TodayTask;

/**
 * 用于显示TaskList
 * Created by jxy on 14-6-30.
 */
public class TaskListAdapter extends BaseAdapter {

    LayoutInflater inflater;
    private TaskListFragment ListFragment;

    /**
     * TaskList的适配器，并可以根据点击事件自动显示隐藏按钮
     *
     * @param context          当前Activity getActivity()
     * @param taskListFragment 父Fragment,用于获得ClickListener并根据ClickLinstener自动显隐按钮
     */
    public TaskListAdapter(Context context, TaskListFragment taskListFragment) {
        inflater = LayoutInflater.from(context);
        ListFragment = taskListFragment;

    }

    @Override
    public int getCount() {

        if (ListFragment.getList() == null) return 0;
        return ListFragment.getList().size();

    }

    @Override
    public Object getItem(int i) {

        if (ListFragment.getList() == null) return null;
        return ListFragment.getList().get(i);

    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.styles, viewGroup, false);

        assert view != null;
        TextView titleText = (TextView) view.findViewById(R.id.TitleText);
        TextView contentText = (TextView) view.findViewById(R.id.ContentText);
        TextView dataText = (TextView) view.findViewById(R.id.DataText);
        ImageButton editButton = (ImageButton) view.findViewById(R.id.EditButton);
        ImageButton delButton = (ImageButton) view.findViewById(R.id.DelButton);
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.layout);

        Task now = ListFragment.getList().get(i);
        //TODO 设置任务详情
        titleText.setText("任务名称:" + now.getName() + "    番茄数:" + now.getExpectNum());
        contentText.setText("备注:" + now.getNoteString() + " Run:" + now.getRunnerID() + " ID:" + now.getID());
        dataText.setText("目标日期:"+now.getExpectDate());
        if (ListFragment.getEditClickListener(i) == null) editButton.setVisibility(View.INVISIBLE);
        if (ListFragment.getDelClickListener(i) == null){
            delButton.setVisibility(View.INVISIBLE);
            String state=((TodayTask)now).getState();
            if(state.equals(StateEnum.ABANDON.toString())){
                state="当前状态:<font color='red'>"+state+"</font>";
            }
            else{
                state="当前状态:<font color='blue'>"+state+"</font>";
            }
            dataText.setText(Html.fromHtml(state));
        }


        editButton.setOnClickListener(ListFragment.getEditClickListener(i));
        delButton.setOnClickListener(ListFragment.getDelClickListener(i));
        layout.setOnClickListener(ListFragment.getClickListener(i));
        layout.setLongClickable(true);
        layout.setOnLongClickListener(ListFragment.getLongClickListener(i));
        return view;
    }
}
