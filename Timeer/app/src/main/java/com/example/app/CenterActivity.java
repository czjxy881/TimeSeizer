package com.example.app;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xxx on 14-6-18.
 */
public class CenterActivity extends Activity  {
    private Button StopButton,StartButton,BackButton,InterruptButton;
    private Bundle extra;
    private TextView CurrentWorkText,InterruptTimes,GoneTime;
    private CircleProgressBar breakProgressBar;
    private MyDigitalClock DateC,ClockC;

    private int WORK=1;
    private int REST = 5;

    private String Title;
    private int AimTomato;

    private int NowTomato;
    private int ContinueWork;
    private int CurrentState; //0未开始,1执行中,2休息中
    private int Interrupt;

    private TimeCount time=null;
    private int timeSpan;
    private Vibrator vibrator;
    MediaPlayer player = null;
    private String showRing = "aa";// 铃声
    private ActionBar actionBar;
    private boolean showShake = true;// 震动

    //单实例化

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        try {
            extra = getIntent().getExtras();
            Title = extra.getString("Name");
            AimTomato=extra.getInt("Aim");
        }catch (Exception e){
            Title="自由";
            AimTomato=2;
        }
        NowTomato=ContinueWork=CurrentState=Interrupt=0;
        Interrupt--;
        setContentView(R.layout.fragment_center);
        StartButton = (Button) findViewById(R.id.StartButton);
        StopButton = (Button) findViewById(R.id.StopButton);
        InterruptButton= (Button) findViewById(R.id.InterruptButton);
        BackButton = (Button) findViewById(R.id.BackButton);

        GoneTime=(TextView)findViewById(R.id.GoneTime);
        InterruptTimes=(TextView)findViewById(R.id.InterruptTimes);
        CurrentWorkText=(TextView)findViewById(R.id.CurrentWorkText);

        breakProgressBar = (CircleProgressBar) findViewById(R.id.breakBar);

        DateC=(MyDigitalClock)findViewById(R.id.Date);
        ClockC=(MyDigitalClock)findViewById(R.id.myClock);

        DateC.setFormat(00);
        ClockC.setFormat(24);

        CurrentWorkText.setText("目标任务:" + Title);
        //设置添加临时任务的弹窗

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

    }

    /**
     * 开始任务
     */
    private void startTask(){
        CurrentState=1;
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

        CurrentWorkText.setText("当前任务:"+Title);
        CurrentWorkText.setTextSize(35);

        GoneTime.setText("当前番茄:"+String.valueOf(NowTomato)+"/"+String.valueOf(AimTomato));
        InterruptTimes.setText("已中断:"+String.valueOf(Interrupt));
        timeSpan = WORK * 60 * 1000;
        time = new TimeCount(timeSpan, 50,CurrentState);
        time.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(time!=null)time.cancel();
        finish();
    }

    private void onInterrupt(){
        Interrupt++;
        InterruptTimes.setText("已中断:"+String.valueOf(Interrupt));
    }

    private void breakWhenWork(){
        DateC.setVisibility(View.VISIBLE);
        ClockC.setVisibility(View.VISIBLE);
        GoneTime.setVisibility(View.INVISIBLE);
        InterruptTimes.setVisibility(View.INVISIBLE);
        InterruptButton.setVisibility(View.GONE);
        StopButton.setVisibility(View.GONE);
        StartButton.setVisibility(View.VISIBLE);
        BackButton.setVisibility(View.GONE);
        finish();
        Toast.makeText(CenterActivity.this, "此次任务失败了", Toast.LENGTH_SHORT).show();
    }

    private void onBackButton(){
        //TODO:数据库
        time.cancel();
        finish();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(110203);
    }
    private void finishTask(){
        //TODO:
        actionBar.setDisplayHomeAsUpEnabled(true);
        InterruptButton.setVisibility(View.GONE);
        StopButton.setVisibility(View.GONE);
        BackButton.setVisibility(View.VISIBLE);
    }
    private void toRest(){
        //TODO:
        actionBar.setDisplayHomeAsUpEnabled(true);
        CurrentState=2;
        DateC.setVisibility(View.INVISIBLE);
        ClockC.setVisibility(View.VISIBLE);
        GoneTime.setVisibility(View.VISIBLE);
        InterruptTimes.setVisibility(View.INVISIBLE);
        InterruptButton.setVisibility(View.GONE);
        StartButton.setVisibility(View.VISIBLE);
        BackButton.setVisibility(View.VISIBLE);
        StopButton.setVisibility(View.GONE);
        GoneTime.setText("当前任务:"+Title);
        CurrentWorkText.setText("休息中");
        timeSpan = WORK * 60 * 1000;
        time = new TimeCount(timeSpan, 50,CurrentState);
        time.start();
    }


    @Override
    protected void onResume() {
        super.onResume();
        onInterrupt();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent();
                intent.setClass(this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    class TimeCount extends CountDownTimer {
        private long alltime;
        private int CurrentState;
        Notification notification;
        PendingIntent pd;
        public TimeCount(long millisInFuture, long countDownInterval,int Currentstate) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
            alltime = millisInFuture;
            breakProgressBar.setText(new SimpleDateFormat("mm:ss").format(new Date(
                    millisInFuture)));
            CurrentState=Currentstate;
            initNotification();
        }
        private void initNotification(){
            notification=new Notification();
            //TODO:改图标
            notification.icon=R.drawable.ic_launcher;

            notification.defaults=Notification.DEFAULT_ALL;
            notification.flags |= notification.FLAG_INSISTENT; //重复
            notification.flags|=Notification.FLAG_AUTO_CANCEL;
            Intent intent = new Intent();
            intent.setClass(CenterActivity.this, CenterActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //加载已有Activity
            pd = PendingIntent.getActivity(CenterActivity.this, 0, intent, 0);
        }
        /**
         * 计时过程显示
         */
        @Override
        public void onTick(long millisUntilFinished) {
            long nowprogress;
            double percentage;
            String string = new SimpleDateFormat("mm:ss").format(new Date(
                    millisUntilFinished));
            nowprogress =  (alltime - millisUntilFinished);
            percentage = (nowprogress * 100.0) / alltime;
            breakProgressBar.setTextnotRefresh(string);
            breakProgressBar.setProgressNotInUiThread(percentage);


        }


        /**
         * 计时完毕时触发
         */
        @Override
        public void onFinish() {
            String title="",content="";
            if(CurrentState==1){
                if(AimTomato<=NowTomato){ //任务已完成
                    finishTask();
                    title="任务完成";content="恭喜恭喜";
                }else{
                    toRest();
                    title="休息啦";content="好好休息";
                }
            }else if(CurrentState==2){
                startTask();
                title="学习啦";content="好好学习";
            }


            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notification.tickerText=title;
            notification.when=System.currentTimeMillis();
            notification.setLatestEventInfo(CenterActivity.this, title, content, pd);
            notificationManager.notify(110203,notification);
            //Uri.withAppendedPath(Audio.Media.INTERNAL_CONTENT_URI, "6");
            cancel();

        }

    }
}

