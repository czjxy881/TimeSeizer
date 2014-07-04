package com.example.timerseizer;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timerseizer.TaskListControllor.ListKind;
import com.example.timerseizer.sql.InnerforUI;
import com.example.timerseizer.sql.Task;

import java.util.Calendar;
import java.util.Formatter;

public class MainActivity extends ActionBarActivity implements ActionBar.OnNavigationListener  {

    private ActionBar actionBar;
    private Dialog dialog;
    private Menu mMenu;
    SectionsPagerAdapter mSectionsPagerAdapter;

    private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";

    private ViewPager mViewPager;

    public MainActivity() {

    }

    public ActionBar getMyActionBar(){
        return actionBar;
    }

    /**
     * 设置当前Fragment
     * @param i 当前页面编号
     */
    public void setFragment(int i){
        mViewPager.setCurrentItem(i);
    }

    public void hideBarSetting(){
        if(mMenu==null)return;
        mMenu.findItem(R.id.menu_listadd).setVisible(false);
        mMenu.findItem(R.id.menu_add).setVisible(false);
    }
    public void showBarSetting(){
        if(mMenu==null)return;
        mMenu.findItem(R.id.menu_listadd).setVisible(true);
        mMenu.findItem(R.id.menu_add).setVisible(true);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar=getSupportActionBar();

        InnerforUI.getInstance(this);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        //TODO:ViewPager
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(1);
        mViewPager.setOffscreenPageLimit(2); //缓存1+2页
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
    public void onBackPressed() {
        final Dialog Exit=new Dialog(this,R.style.mydialog);
        Exit.setContentView(R.layout.exit_dialog);
        Exit.findViewById(R.id.DialogSure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
        Exit.findViewById(R.id.DialogCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Exit.cancel();
            }
        });
        Exit.show();
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
        hideBarSetting();
        MenuItemCompat.setShowAsAction(mMenu.getItem(2), MenuItemCompat.SHOW_AS_ACTION_IF_ROOM | MenuItemCompat.SHOW_AS_ACTION_WITH_TEXT);
        MenuItemCompat.setShowAsAction(mMenu.getItem(1), MenuItemCompat.SHOW_AS_ACTION_IF_ROOM|MenuItemCompat.SHOW_AS_ACTION_WITH_TEXT);
        MenuItemCompat.setShowAsAction(mMenu.getItem(0), MenuItemCompat.SHOW_AS_ACTION_IF_ROOM|MenuItemCompat.SHOW_AS_ACTION_WITH_TEXT);
        return true;
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
        if (id==R.id.menu_add) {
            new EditDialog(this,turnIndex2ListKind(actionBar.getSelectedNavigationIndex())).show();
        }
        if (id==R.id.menu_cancel) {
            System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }

    public void changeActionBarButton(int position){
        switch (position){
            case 0:
                mMenu.findItem(R.id.menu_listadd).setVisible(true);
                mMenu.findItem(R.id.menu_add).setVisible(true);
                break;
            case 1:
                mMenu.findItem(R.id.menu_listadd).setVisible(false);
                mMenu.findItem(R.id.menu_add).setVisible(true);
                break;
            case 2:
                mMenu.findItem(R.id.menu_listadd).setVisible(false);
                mMenu.findItem(R.id.menu_add).setVisible(true);
                break;
            case 3:
                mMenu.findItem(R.id.menu_listadd).setVisible(false);
                mMenu.findItem(R.id.menu_add).setVisible(false);
                break;

        }
    }

    @Override
    public boolean onNavigationItemSelected(int position, long id) {
        Fragment s=null;

        switch (position){
            case 0:s=TaskListControllor.getInstance(ListKind.TodayList);
                break;
            case 1:s=TaskListControllor.getInstance(ListKind.PeriodList);
                break;
            case 2:s=TaskListControllor.getInstance(ListKind.AllList);
                break;
            case 3:s=TaskListControllor.getInstance(ListKind.DoneList);
                break;

        }


        if(s!=null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fgtright, s).commit();
            changeActionBarButton(position);
        }
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
            Fragment f=null;
            switch (position){
                case 0:
                    f=LeftFragment.getInstance();
                    break;
                case 1:
                    f=CenterFragment.getInstance();
                    break;
                case 2:
                    f=RightFragment.getInstance();
                    break;
            }
            return f;

        }

        @Override
        public int getCount() {
            return 3;
        }
    }


}
