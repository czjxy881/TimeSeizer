package com.example.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.LayoutInflater;
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
    private Button btnnowworke;
    private Button btn_start;

    private TextView tvnowworke;
    private TextView breakTextView;
    private CircleProgressBar breakProgressBar;
    private Button returnbButton;
    private int rest=0;
    private TimeCount time;
    private int timeSpan;
    private long exitTime = 0;
    private Vibrator vibrator;
    MediaPlayer player=null;
    private String showRing="aa";// 铃声

    private boolean showShake = true;// 震动

    String[] presidents = {
            "a","v","cc","aaa","ss","a","v","cc","aaa","ss","a","v","cc","aaa","ss"    };
    //单实例化
    private static CenterFragment centerFragment=null;
    public static CenterFragment getInstance(){
        if (centerFragment==null){
            centerFragment=new CenterFragment();
            return centerFragment;
        }else return centerFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_center);
        btnnowworke = (Button)findViewById(R.id.btnnowworke);
        tvnowworke = (TextView)findViewById(R.id.tvnowworke);
        btn_start = (Button)findViewById(R.id.button_start);

        breakProgressBar = (CircleProgressBar)findViewById(R.id.breakBar);
        breakTextView = (TextView)findViewById(R.id.breakTxtView);
        returnbButton = (Button)findViewById(R.id.ReturnButton);
        rest=1;
        timeSpan = rest*60*1000;
        //returnbButton.setOnClickListener(this);
        time = new TimeCount(timeSpan, 100);// 构造CountDownTimer对象

        //设置添加临时任务的弹窗
        btnnowworke.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(CenterActivity.this);
                        builder.setTitle("添加当前任务");
                        final TableLayout centerdialog = (TableLayout) getLayoutInflater().inflate(R.layout.centerdialog, null);
                        builder.setView(centerdialog);
                        builder.setItems(presidents,new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //TODO::将添加的临时任务信息发送到后台
                                tvnowworke.setText(presidents[i]);
                            }
                        });
                        builder.create().show();
                    }
                }
        );
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                time.start();
                btn_start.setVisibility(View.GONE);
                returnbButton.setVisibility(View.VISIBLE);
            }
        });
        returnbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                breakProgressBar.setProgress(0);
                returnbButton.setVisibility(View.GONE);
                btn_start.setVisibility(View.VISIBLE);
                time.cancel();
                //time.onFinish();
                Toast.makeText(CenterActivity.this, "此次任务失败了", Toast.LENGTH_SHORT).show();
                tvnowworke.setText("任务有待开始");
                if(player!=null){
                    player.stop();
                    player.release();
                }

            }
        });


    }








    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }
        /**
         * 计时过程显示
         */
        @Override
        public void onTick(long millisUntilFinished) {
            // TODO 自动生成的方法存根
            int nowprogress;
            double percentage;
            String string = new SimpleDateFormat("mm:ss").format(new Date(
                    millisUntilFinished));
            tvnowworke.setText(string);
            nowprogress = (int)(rest*60-millisUntilFinished/1000);
            percentage = (nowprogress*100)/(rest*60);
            breakProgressBar.setProgress((int) percentage);
            tvnowworke.setText("请等待" + nowprogress + "秒");

        }
        /**
         * 计时完毕时触发
         */
        @Override
        public void onFinish() {
            // TODO 自动生成的方法存
            breakProgressBar.setProgress(0);
            returnbButton.setVisibility(View.GONE);
            btn_start.setVisibility(View.VISIBLE);
            if (showShake) {
                //开启震动
                vibrator =(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                long [] pattern = {200,500,200,500};   // 停止 开启 停止 开启
                vibrator.vibrate(pattern,-1);           //重复两次上面的pattern 如果只想震动一次，index设为-1
            }

            //开启铃声
            player =  MediaPlayer.create(CenterActivity.this,R.raw.aa);
            Uri uri = Uri.parse(showRing);

                /*try {
                    player.setDataSource(getActivity(), uri);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                final AudioManager audioManager = (AudioManager) getActivity()
                        .getSystemService(Context.AUDIO_SERVICE);
                if (audioManager.getStreamVolume(AudioManager.STREAM_RING) != 0) {
                    player.setAudioStreamType(AudioManager.STREAM_RING);
                    player.setLooping(false);
                    try {
                        player.prepare();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    player.start();
                }*/
            player.seekTo(0);
            player.start();
        }


    }
    public void onClick(View v) {
        finish();
    }
}

