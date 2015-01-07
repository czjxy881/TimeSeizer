package com.example.timerseizer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.timerseizer.sql.InnerforUI;
import com.example.timerseizer.sql.TodayList;
import com.example.timerseizer.sql.TodayTask;


/**
 * TODO 代码不好看 要用Preference重写
 * Created by xxx on 14-6-10.
 */
public class Settings extends Activity {
    SeekBar TomatoSeekBar,RestSeekBar,LongRestSeekBar;
    TextView TomatoText,RestText,LongRestText,SuggestText;
    ToggleButton VibrateButton,TickButton,FinishButton;

    SharedPreferences preference;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setPreferenceScreen();
        setContentView(R.layout.settings_layout);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        preference=getSharedPreferences("TimeSetting", MODE_PRIVATE);
        editor=preference.edit();

        TomatoSeekBar=(SeekBar)findViewById(R.id.TomatoSeekBar);
        RestSeekBar=(SeekBar)findViewById(R.id.RestSeekBar);
        LongRestSeekBar=(SeekBar)findViewById(R.id.LongRestSeekBar);
        TomatoText=(TextView)findViewById(R.id.SettingTomatoText);
        RestText=(TextView)findViewById(R.id.SettingRestText);
        LongRestText=(TextView)findViewById(R.id.SettingLongRestText);
        SuggestText=(TextView)findViewById(R.id.suggest_worktime);
        SuggestText.setText("建议番茄时间为: "+suggest()+"mins");
        TomatoSeekBar.setProgress(preference.getInt("TomatoTime",25)-1);
        TomatoText.setText(preference.getInt("TomatoTime", 25) + "min");
        TomatoSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                TomatoText.setText((i+1) + "min");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt("TomatoTime", seekBar.getProgress() + 1);
            }
        });

        RestSeekBar.setProgress(preference.getInt("RestTime",5)-1);
        RestText.setText(preference.getInt("RestTime",5)+"min");
        RestSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                RestText.setText((i) + "min");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt("RestTime", seekBar.getProgress());
            }
        });

        LongRestSeekBar.setProgress(preference.getInt("LongRestTime",15)-1);
        LongRestText.setText(preference.getInt("LongRestTime",15)+"min");
        LongRestSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                LongRestText.setText((i) + "min");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt("LongRestTime", seekBar.getProgress());
            }
        });


        VibrateButton=(ToggleButton)findViewById(R.id.Vibrate);
        VibrateButton.setChecked(preference.getBoolean("Vibrate", false));

        TickButton=(ToggleButton)findViewById(R.id.Tick);
        TickButton.setChecked(preference.getBoolean("Tick",false));

        FinishButton=(ToggleButton)findViewById(R.id.Finish);
        FinishButton.setChecked(preference.getBoolean("FinishSound",false));




    }
    private void save(){
        editor.putBoolean("Vibrate",VibrateButton.isChecked());
        editor.putBoolean("Tick",TickButton.isChecked());
        editor.putBoolean("FinishSound",FinishButton.isChecked());
        editor.commit();
    }

    @Override
    public void onBackPressed() {
        save();
        CenterFragment.getInstance().updateTime();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                save();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public int suggest(){
        int inter=InnerforUI.getInstance().getInterruptTimesInWeek();
        int finish=InnerforUI.getInstance().getTomatoNumber();
        int time=(int)(1.0*finish/inter*25);
        time=Math.min(time,60);
        time=Math.max(time,15);
        return (int)(1.0*finish/inter*30);
    }
}
