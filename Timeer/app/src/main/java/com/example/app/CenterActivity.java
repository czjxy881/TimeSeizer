package com.example.app;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xxx on 14-6-18.
 */
public class CenterActivity extends Activity implements View.OnClickListener {
    private Button ReturnButton;
    private Button StartButton;
    private Bundle extra;
    private String msg;
    private TextView CurrentWorkText;
    private CircleProgressBar breakProgressBar;
    private int rest = 0;
    private TimeCount time;
    private int timeSpan;
    private long exitTime = 0;
    private Vibrator vibrator;
    MediaPlayer player = null;
    private String showRing = "aa";// 铃声
    private ActionBar actionBar;
    private boolean showShake = true;// 震动

    //单实例化

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        try {
            Bundle extra = getIntent().getExtras();
            msg = extra.getString("Name");
        }catch (Exception e){
            msg="自由";
        }
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE); // 画框
        drawable.setStroke(1, Color.rgb(185, 185, 185)); // 边框粗细及颜色

        setContentView(R.layout.fragment_center);
        StartButton = (Button) findViewById(R.id.StartButton);
        ReturnButton = (Button) findViewById(R.id.ReturnButton);
        CurrentWorkText=(TextView)findViewById(R.id.CurrentWorkText);
        breakProgressBar = (CircleProgressBar) findViewById(R.id.breakBar);
       // StartButton.setBackground(drawable);
       // ReturnButton.setBackground(drawable);



        rest = 1;
        timeSpan = rest * 60 * 1000;
        //returnbButton.setOnClickListener(this);
        time = new TimeCount(timeSpan, 50);// 构造CountDownTimer对象
        CurrentWorkText.setText("目标任务:" + msg);
        //设置添加临时任务的弹窗

        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                time.start();
                StartButton.setVisibility(View.GONE);
                ReturnButton.setVisibility(View.VISIBLE);
                getActionBar().hide();
            }
        });
        ReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                breakProgressBar.setProgress(0);
                ReturnButton.setVisibility(View.GONE);
                StartButton.setVisibility(View.VISIBLE);
                time.cancel();
                if (player != null) {
                    player.stop();
                    player.release();
                }
                finish();
                //time.onFinish();
                if(ReturnButton.getText()!="返回") {
                    Toast.makeText(CenterActivity.this, "此次任务失败了", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    public void onClick(View v) {
        finish();
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
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
            alltime = millisInFuture;
            breakProgressBar.setText(new SimpleDateFormat("mm:ss").format(new Date(
                    millisInFuture)));
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
            CurrentWorkText.setText("已开始" + new SimpleDateFormat("mm:ss").format(new Date(
                    nowprogress+1000)) + "秒");

        }


        /**
         * 计时完毕时触发
         */
        @Override
        public void onFinish() {
            // TODO 自动删除,返回停止音乐
           // breakProgressBar.setProgress(0);
            ReturnButton.setText("返回");
          //  StartButton.setVisibility(View.VISIBLE);
            if (showShake) {
                //开启震动
                vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                long[] pattern = {200, 500, 200, 500};   // 停止 开启 停止 开启
                vibrator.vibrate(pattern, -1);           //重复两次上面的pattern 如果只想震动一次，index设为-1
            }

            //开启铃声
            player = MediaPlayer.create(CenterActivity.this, R.raw.aa);

            player.seekTo(0);
            player.start();
        }

    }
}

