package com.example.timerseizer;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timerseizer.sql.InnerforUI;


public class LeftFragment extends Fragment {

    private TextView Finish;
    private TextView Interrupt;
    private static LeftFragment leftFragment=null;



    //单实例化
    public static LeftFragment getInstance(){
        if (leftFragment==null){
            leftFragment=new LeftFragment();
            return leftFragment;
        }else return leftFragment;
    }
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        return inflater.inflate(R.layout.fragment_left, container, false);
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser ) {

        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            ActionBar actionBar=((MainActivity)this.getActivity()).getMyActionBar();
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
            ((MainActivity)this.getActivity()).hideBarSetting();
            Finish=(TextView)getActivity().findViewById(R.id.tvfqnumber);
            Interrupt=(TextView)getActivity().findViewById(R.id.tvddnumber);
            WebView webView=(WebView)getActivity().findViewById(R.id.webView);

            Finish.setText(String.valueOf(InnerforUI.getInstance().getToday().getFinish()));
            Interrupt.setText(String.valueOf(InnerforUI.getInstance().getToday().getInterrupt()));


            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl("file:///android_asset/web/index.htm");
            webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
            webView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()){
                        case MotionEvent.ACTION_UP:
                            view.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                        case MotionEvent.ACTION_DOWN:
                            view.getParent().requestDisallowInterceptTouchEvent(true);
                            break;
                    }
                    return false;
                }
            });

        }
    }



    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }



}
