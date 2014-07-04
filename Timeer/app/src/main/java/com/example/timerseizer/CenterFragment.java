package com.example.timerseizer;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.timerseizer.sql.InnerforUI;
import com.example.timerseizer.sql.TodayTask;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xxx on 14-6-18.
 */
public class CenterFragment extends Fragment {
    private Button StopButton, StartButton, BackButton, InterruptButton;
    private Bundle extra;
    private TextView CurrentWorkText, InterruptTimes, GoneTime;
    private CircleProgressBar breakProgressBar;
    private MyDigitalClock DateC, ClockC;

    private  int WORK = 25;
    private  int REST = 5;
    private  int LONGREST = 5;

    private String Title;
    private int AimTomato;

    private int NowTomato;
    private int ContinueWork;
    private int CurrentState = 0; //0未开始,1执行中,2休息中
    private int Interrupt;

    private TimeCount time = null;
    private int timeSpan;
    private ActionBar actionBar;

    private boolean inPractise;
    private Bundle bundle = null;
    private boolean isVisibleToUser;
    private boolean Viewed = false;

    TodayTask task;
    public void updateTime(){
        SharedPreferences preferences=getActivity().getSharedPreferences("TimeSetting",Context.MODE_PRIVATE);
        WORK=preferences.getInt("TomatoTime",25);
        REST=preferences.getInt("RestTime",5);
        LONGREST=preferences.getInt("LongRestTime",5);
        breakProgressBar.setTime(WORK*60000);

    }
    public void setInfo(Bundle bundle) {
        this.bundle = bundle;
    }

    private static CenterFragment centerFragment;

    public static CenterFragment getInstance() {
        if (centerFragment == null) {
            centerFragment = new CenterFragment();
        }
        return centerFragment;
    }

    public boolean isfree() {
        return CurrentState == 0;
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser) {
            actionBar = ((MainActivity) this.getActivity()).getMyActionBar();
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
            ((MainActivity) this.getActivity()).hideBarSetting();
            if (Viewed && CurrentState == 0) {
                tryRead();
            }

        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_center, container, false);
        actionBar = ((MainActivity) this.getActivity()).getMyActionBar();

        StartButton = (Button) view.findViewById(R.id.StartButton);
        StopButton = (Button) view.findViewById(R.id.StopButton);
        InterruptButton = (Button) view.findViewById(R.id.InterruptButton);
        BackButton = (Button) view.findViewById(R.id.BackButton);

        GoneTime = (TextView) view.findViewById(R.id.GoneTime);
        InterruptTimes = (TextView) view.findViewById(R.id.InterruptTimes);
        CurrentWorkText = (TextView) view.findViewById(R.id.CurrentWorkText);

        breakProgressBar = (CircleProgressBar) view.findViewById(R.id.breakBar);

        DateC = (MyDigitalClock) view.findViewById(R.id.Date);
        ClockC = (MyDigitalClock) view.findViewById(R.id.myClock);

        DateC.setFormat(00);
        ClockC.setFormat(24);

        tryRead();

        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTask();
            }
        });

        StopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time.cancel();
                breakWhenWork();
            }
        });

        InterruptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onInterrupt();
            }
        });

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackButton();
            }
        });
        Viewed = true;
        updateTime();
        return view;
    }

    private void tryRead() {
        try {
            Title = bundle.getString("Name");
            AimTomato = bundle.getInt("Num");
            task = InnerforUI.getInstance().findTodayTaskByRunnerID(bundle.getInt("RunnerID"));
            NowTomato = task.getActualNum();
            Interrupt = task.getInnerInturruptTimes() + task.getOuterInturruptTimes();
            inPractise = false;
            bundle = null;
        } catch (Exception e) {
            inPractise = true;
            NowTomato = Interrupt = 0;
        }
        ContinueWork = CurrentState = 0;
        // Interrupt--;

        if (inPractise == false) {
            CurrentWorkText.setText( Title);
        } else {
            CurrentWorkText.setText("练习模式");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    /**
     * 开始任务
     */
    private void startTask() {
        if (time != null) time.cancel();
        clearNotification();
        CurrentState = 1;
        ContinueWork++;
        NowTomato++;
        //TODO:actionBar.Hide();
        actionBar.setDisplayHomeAsUpEnabled(false);
        DateC.setVisibility(View.INVISIBLE);
        ClockC.setVisibility(View.INVISIBLE);
        GoneTime.setVisibility(View.VISIBLE);
        InterruptTimes.setVisibility(View.VISIBLE);
        InterruptButton.setVisibility(View.VISIBLE);
        StopButton.setVisibility(View.VISIBLE);
        StartButton.setVisibility(View.GONE);
        BackButton.setVisibility(View.GONE);
        if (inPractise == false) {
            CurrentWorkText.setText(Title);
            GoneTime.setText("当前番茄:" + String.valueOf(NowTomato) + "/" + String.valueOf(AimTomato));
        } else {
            CurrentWorkText.setText("工作中");
            GoneTime.setText("当前番茄:" + String.valueOf(NowTomato));
        }


        InterruptTimes.setText("已中断:" + String.valueOf(Interrupt));
        timeSpan = WORK * 60 * 1000;
        time = new TimeCount(timeSpan, 50, CurrentState);
        time.start();
    }

    private void onInterrupt() {
        Interrupt++;
        if (inPractise == false) {
            task.innerInturrpt();
        }
        InterruptTimes.setText("已中断:" + String.valueOf(Interrupt));
    }

    private void ready() {
        breakProgressBar.setTimenotRefresh(WORK * 60 * 1000);
        breakProgressBar.setProgress(0);
        if(inPractise==true){
            NowTomato = ContinueWork = CurrentState = Interrupt = 0;
        }else {
            CurrentState = 0;
        }
        //TODO 同步数据库
        DateC.setVisibility(View.VISIBLE);
        ClockC.setVisibility(View.VISIBLE);
        GoneTime.setVisibility(View.INVISIBLE);
        InterruptTimes.setVisibility(View.INVISIBLE);
        InterruptButton.setVisibility(View.GONE);
        StopButton.setVisibility(View.GONE);
        StartButton.setVisibility(View.VISIBLE);
        BackButton.setVisibility(View.GONE);
    }

    private void breakWhenWork() {
        ready();
        if (inPractise == false) {
            ((MainActivity) getActivity()).setFragment(2);
            Toast.makeText(getActivity(), "此次任务失败了", Toast.LENGTH_SHORT).show();
        } else {
            CurrentWorkText.setText("练习模式");
        }
    }

    private void clearNotification() {
        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);
        notificationManager.cancel(110203);
    }

    private boolean Finished = false;

    private void onBackButton() {
        //TODO:数据库
        time.cancel();
        ready();
        clearNotification();
        if (Finished == true) {
            ((MainActivity) getActivity()).setFragment(2);
            TaskListControllor.getInstance(TaskListControllor.ListKind.TodayList).showUpdate();
        }
    }

    private void finishTask() {
        //TODO:
        if (inPractise == false) {
            task.oneClockPass();
            task.finish();
        }
        Finished = true;
        CurrentWorkText.setText("任务完成");
        InterruptButton.setVisibility(View.GONE);
        StopButton.setVisibility(View.GONE);
        BackButton.setVisibility(View.VISIBLE);
    }

    private void toRest() {
        //TODO:
        CurrentState = 2;
        DateC.setVisibility(View.INVISIBLE);
        ClockC.setVisibility(View.VISIBLE);
        GoneTime.setVisibility(View.VISIBLE);
        InterruptTimes.setVisibility(View.INVISIBLE);
        InterruptButton.setVisibility(View.GONE);
        StartButton.setVisibility(View.VISIBLE);
        BackButton.setVisibility(View.VISIBLE);
        StopButton.setVisibility(View.GONE);
        if (inPractise == false) {
            task.oneClockPass();
            GoneTime.setText(Title);
        }
        CurrentWorkText.setText("休息中");
        timeSpan = REST * 60 * 1000;
        time = new TimeCount(timeSpan, 50, CurrentState);
        time.start();
    }


    @Override
    public void onResume() {
        super.onResume();
        //TODO 中断方式不优雅
        onInterrupt();
    }


    class TimeCount extends CountDownTimer {
        private long alltime;
        private int CurrentState;
        Notification notification;
        PendingIntent pd;

        public TimeCount(long millisInFuture, long countDownInterval, int Currentstate) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
            alltime = millisInFuture;
            breakProgressBar.setTimenotRefresh(millisInFuture);
            breakProgressBar.setProgress(0);
            CurrentState = Currentstate;
            initNotification();
        }

        private void initNotification() {
            notification = new Notification();
            //TODO:改图标
            notification.icon = R.drawable.ic_stat_name;

            notification.defaults = Notification.DEFAULT_ALL;
            notification.flags |= notification.FLAG_INSISTENT; //重复
            notification.flags |= Notification.FLAG_AUTO_CANCEL;
            Intent intent = new Intent();
            intent.setClass(getActivity(), MainActivity.class);
            pd = PendingIntent.getActivity(getActivity(), 0, intent, 0);
            ((MainActivity) getActivity()).setFragment(1);
        }

        /**
         * 计时过程显示
         */
        @Override
        public void onTick(long millisUntilFinished) {
            if (!isVisibleToUser) return;
            long NowProgress;
            double percentage;
            String string = new SimpleDateFormat("mm:ss").format(new Date(
                    millisUntilFinished));
            NowProgress = (alltime - millisUntilFinished);
            percentage = (NowProgress * 100.0) / alltime;
            breakProgressBar.setTextnotRefresh(string);
            breakProgressBar.setProgressNotInUiThread(percentage);


        }


        /**
         * 计时完毕时触发
         */
        @Override
        public void onFinish() {
            String title = "", content = "";
            if (CurrentState == 1) {
                if (inPractise == false && AimTomato <= NowTomato) { //任务已完成
                    finishTask();
                    title = "任务完成";
                    content = "恭喜恭喜";
                } else {
                    toRest();
                    title = "休息啦";
                    content = "好好休息";
                }
            } else if (CurrentState == 2) {
                startTask();
                title = "学习啦";
                content = "好好学习";
            }


            NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);
            notification.tickerText = title;
            notification.when = System.currentTimeMillis();
            notification.setLatestEventInfo(getActivity(), title, content, pd);
            notificationManager.notify(110203, notification);
            //Uri.withAppendedPath(Audio.Media.INTERNAL_CONTENT_URI, "6");
            cancel();

        }

    }
}

