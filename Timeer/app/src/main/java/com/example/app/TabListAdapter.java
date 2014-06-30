package com.example.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Created by xxx on 2014/6/30.
 */
public class TabListAdapter extends BaseAdapter {
    Vector<String> list=null;
    LayoutInflater inflater;
    private TextView TitleText;
    private CheckBox checkBox;
    TabListFragment ListFragment;
    HashMap<Integer, Boolean> state = null;
    Map<Integer, Boolean> isCheckMap =  new HashMap<Integer, Boolean>();


    /**
     * TaskList的适配器，并可以根据点击事件自动显示隐藏按钮
     * @param context 当前Activity getActivity()
     * @param tabListFragment 父Fragment,用于获得ClickListener
     */
    public TabListAdapter(Context context,TabListFragment tabListFragment){
        inflater=LayoutInflater.from(context);
        ListFragment=tabListFragment;
        list=tabListFragment.getList();
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
        view=inflater.inflate(R.layout.tabstyles,viewGroup,false);
        checkBox=(CheckBox)view.findViewById(R.id.TabCheck);
        TitleText=(TextView) view.findViewById(R.id.TabTitleText);
        LinearLayout layout=(LinearLayout)view.findViewById(R.id.Tablayout);

        String Title = list.get(i);
        TitleText.setText("任务名称:"+Title);
        layout.setOnClickListener(getClickListener(i, view));

        return view;
    }
    public View.OnClickListener getClickListener(final int i,final View sview) {


        View.OnClickListener now=new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                checkBox=(CheckBox)sview.findViewById(R.id.TabCheck);
                boolean ischecked=checkBox.isChecked();
                isCheckMap.put(i,checkBox.isChecked());

                checkBox.setChecked(!checkBox.isChecked());
                if(ischecked)
                {
                    //将选中的放入hashmap中
                    isCheckMap.put(i, ischecked);
                }
                else
                {
                    //取消选中的则剔除
                    isCheckMap.remove(i);
                }
            }
        };
        return now;
    }


}
