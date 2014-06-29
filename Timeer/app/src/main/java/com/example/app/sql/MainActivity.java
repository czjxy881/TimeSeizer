package com.example.app.sql;


import java.util.Vector;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.view.View.*;
import android.database.sqlite.*;
import android.database.*;

public class MainActivity extends Activity
{
	Button btdrop;
	Button bt;
	TextView tv;
	Button insert;
	TextView name;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
    //This is α���� ����
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        

		TodayList todayList=new TodayList();
		TaskView taskView=new TaskView(this);
		Today today=new Today(this);
		
		today.set(WorkTime, RestTime, LongRestTime, StartTime);
		//when click add task ..
		Task task=new Task();
		task.set(Name, ID, ExpectNum, ExpectDate, NoteString);
		//when click add peroidtask ..
			//1.new task ..
			Task task=new Task();
			PeroidTask pTask=new PeroidTask();
			task.set(Name, taskView.getCurrentID(), ExpectNum, ExpectDate, NoteString);
			pTask.set(Name, taskView.getCurrentID(), ExpectNum, ExpectDate, Kind);
			taskView.addTask(task);
			taskView.addPeriodTask(pTask);
			//2.exist task ..
			int ID=<getFromChart(..)>;
			pTask.set(Name, ID, ExpectNum, ExpectDate, Kind);
			taskView.addPeriodTask(pTask);
		//when search TodayList/todayTask ..
		1-- Vector<TodayTask> vector=todayList.getTodayList();
		2-- TodayTask vector=todayList.getTaskByID(<ID>);
		<Chart>=vector;
		//when search task/peroidtask
		<Chart>=taskView.getAllList();
		<Chart>=taskView.getPeriodTask();
		
		//when start
		TodayTask todayTask=todayList.getNextTask();
		<runStruct.name>=.Name;//show on Activity
		<runStruct.runtime>=today.getWorkTime();
		<runStruct.ID>=todayTask.ID;//use to get TodayTask
		<.. runThread>
		
		//when finish
		<..runThread finish>
		int ID=<runStruct.ID>;
		todayList.getTaskByID(ID).finish();
		
    }
}
