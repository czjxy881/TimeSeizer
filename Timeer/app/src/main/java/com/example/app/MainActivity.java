package com.example.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.app.TaskListControllor.ListKind;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.Vector;

public class MainActivity extends ActionBarActivity implements ActionBar.OnNavigationListener  {

    private ActionBar actionBar;
    private Dialog dialog;
    private Menu mMenu;
    SectionsPagerAdapter mSectionsPagerAdapter;

    private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";

    ViewPager mViewPager;

    public MainActivity() {
    }
    public ActionBar getMyActionBar(){
        return actionBar;
    }

    public void hideBarSetting(){
        if(mMenu==null)return;
        mMenu.setGroupVisible(0,false);
    }
    public void showBarSetting(){
        if(mMenu==null)return;
        mMenu.setGroupVisible(0,true);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar=getSupportActionBar();


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        //TODO:ViewPager
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(1);

    }


    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore the previously serialized current dropdown position.
        if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
            getSupportActionBar().setSelectedNavigationItem(
                    savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Serialize the current dropdown position.
        outState.putInt(STATE_SELECTED_NAVIGATION_ITEM,
                getSupportActionBar().getSelectedNavigationIndex());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenu=menu;
        getMenuInflater().inflate(R.menu.mymain, menu);
        MenuItemCompat.setShowAsAction(mMenu.getItem(2), MenuItemCompat.SHOW_AS_ACTION_IF_ROOM | MenuItemCompat.SHOW_AS_ACTION_WITH_TEXT);
        MenuItemCompat.setShowAsAction(mMenu.getItem(1), MenuItemCompat.SHOW_AS_ACTION_IF_ROOM|MenuItemCompat.SHOW_AS_ACTION_WITH_TEXT);
        MenuItemCompat.setShowAsAction(mMenu.getItem(0), MenuItemCompat.SHOW_AS_ACTION_ALWAYS|MenuItemCompat.SHOW_AS_ACTION_WITH_TEXT);
        return true;
    }

    /**
     * 生成添加弹窗,根据现在列表完成动态布局
     */
    private void initDialog(){
        dialog = new Dialog(MainActivity.this, R.style.mydialog);
        dialog.setContentView(R.layout.alertdialog_freetimework);

        final TextView TitleView=((TextView)dialog.findViewById(R.id.TitleAddText));
        TextView ContentView=((TextView)dialog.findViewById(R.id.ContentAddText));
        TextView TomatoView=((TextView)dialog.findViewById(R.id.TomatoAddText));
        TitleView.clearComposingText();
        ContentView.clearComposingText();
        TomatoView.clearComposingText();
        final ListKind listKind=turnIndex2ListKind(actionBar.getSelectedNavigationIndex());
        final RadioGroup radioGroup=(RadioGroup)dialog.findViewById(R.id.DialogRadioGroup);
        if(listKind== ListKind.AllList){
            radioGroup.setVisibility(View.VISIBLE);
        }else{
            radioGroup.setVisibility(View.GONE);
            radioGroup.clearCheck();
        }
        //((RadioButton)findViewById(R.id.))
        dialog.findViewById(R.id.DialogSaveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO:数据库更新
                String Name = TitleView.getText().toString();
                TaskListFragment listFragment;
                if(listKind==ListKind.TodayList||radioGroup.getCheckedRadioButtonId()==R.id.DialogRadioOnce){
                    listFragment=TaskListControllor.getInstance(ListKind.TodayList);
                    listFragment.add(Name);
                    listFragment.showUpdate();
                }else{
                    listFragment=TaskListControllor.getInstance(ListKind.PeriodList);
                    listFragment.add(Name);
                    listFragment.showUpdate();
                }
                listFragment=TaskListControllor.getInstance(ListKind.AllList);
                listFragment.add(Name);
                listFragment.showUpdate();
                dialog.cancel();
            }
        });
        dialog.findViewById(R.id.DialogCancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.menu_setting) {
            Intent intent = new Intent(MainActivity.this,Settings.class);
            startActivity(intent);
            return true;
        }
        if(id==R.id.menu_listadd){
            Intent intent = new Intent(MainActivity.this,TabActivity.class);
            startActivity(intent);
            return true;
        }
        if (id==R.id.menu_add){
            initDialog();

                   }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(int position, long id) {
        // When the given dropdown item is selected, show its contents in the
        // page view.
        Fragment s=null;

        switch (position){
            case 0:s=TaskListControllor.getInstance(ListKind.TodayList);
                mMenu.findItem(R.id.menu_listadd).setVisible(true);
                mMenu.findItem(R.id.menu_add).setVisible(true);
                break;
            case 1:s=TaskListControllor.getInstance(ListKind.PeriodList);
                mMenu.findItem(R.id.menu_listadd).setVisible(false);
                mMenu.findItem(R.id.menu_add).setVisible(true);
                break;
            case 2:s=TaskListControllor.getInstance(ListKind.AllList);
                mMenu.findItem(R.id.menu_listadd).setVisible(false);
                mMenu.findItem(R.id.menu_add).setVisible(true);
                break;
            case 3:s=TaskListControllor.getInstance(ListKind.DoneList);
                mMenu.findItem(R.id.menu_listadd).setVisible(false);
                mMenu.findItem(R.id.menu_add).setVisible(false);
                break;

        }

        if(s!=null)getSupportFragmentManager().beginTransaction()
                .replace(R.id.fgtright, s)
                .commit();

        return true;
    }
    /**
     * 获取Navigation对应position的ListKind
     * @param i List的序号
     * @return ListKind
     */
    public static ListKind turnIndex2ListKind(int i){
        ListKind listKind=null;
        switch (i){
            case 0:listKind=ListKind.TodayList;break;
            case 1:listKind=ListKind.PeriodList;break;
            case 2:listKind=ListKind.AllList;break;
            case 3:listKind=ListKind.DoneList;break;
        }
        return listKind;
    }

    /**
     * A {@link android.support.v4.app.FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {

            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Fragment f=null;
            switch (position){
                case 0:
                    f=LeftFragment.getInstance();
                    break;
                case 1:

                    f=RightFragment.getInstance();

                    break;
            }
            return f;

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }


}
