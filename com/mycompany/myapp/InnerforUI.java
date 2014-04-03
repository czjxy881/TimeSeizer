package com.mycompany.myapp;

import java.util.Date;
import java.util.Vector;

import android.content.Context;

public class InnerforUI {
	Date date=new Date();
	TodayList todayList;
	TaskView taskView;
	Today today;
	FindDb db;

	public InnerforUI(Context context){
		todayList=new TodayList();
		taskView=new TaskView(context);
		db=new FindDb(context);
		today=initialToday(context);
	}
	

	private Today initialToday(Context context) {
		Today today=new Today(context);
		today.setWeekDay(date.getDay());
		//YYYY-MM-DD HH:MM:SS
		//date.getYear()+"-"+date.getMonth()+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+
		String time=date.getYear()+"-"+date.getMonth()+"-"+date.getDay()+
				" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
		today.setStartTime(time);
		DailyList dailyList=new DailyList();
		dailyList=db.FindDailyListNew(DailyList.class);
		today.setWorkTime(dailyList.getWorkTIme());
		today.setRestTime(dailyList.getRestTime());
		today.setLongRestTime(dailyList.getLongRestTime());
		//³õÊ¼Ä¬ÈÏ
		today.setLastingTimes(0);
		today.setActualNum(0);
		today.setSummary(null);
		return today;
	}
	
	
	//when click add task ..	
	public boolean clickAddTask(String Name,int ExpectNum,String ExpectDate,String NoteString){
		int ID=taskView.getCurrentID();
		if(notExist(Name)){
			Task task=new Task();
			task.set(Name, ID, ExpectNum, ExpectDate, NoteString);
			taskView.addTask(task);
			return true;
		}
		return false;
	}


	//when click add peroidtask ..
		//1.new task ..
	public boolean clickAddPeroidNewTask(String Name,int ExpectNum,String ExpectDate,String NoteString,int Kind){
		if(notExist(Name)){
			int ID=taskView.getCurrentID();
			Task task=new Task();
			PeroidTask pTask=new PeroidTask();
			task.set(Name, ID, ExpectNum, ExpectDate, NoteString);
			pTask.set(Name, ID, ExpectNum, ExpectDate, Kind);
			taskView.addTask(task);
			taskView.addPeriodTask(pTask);
			return true;
		}
		return false;
	}


		//2.exist ID ..
	public void clickSetPeroidTask(int ID,int ExpectNum,String ExpectDate,int Kind){
		IDList iDlist=db.FindIDListByID(IDList.class, ID);
		PeroidTask pTask=new PeroidTask();
		pTask.set(iDlist.getName(),ID,ExpectNum,ExpectDate,Kind);
		taskView.addPeriodTask(pTask);
	}
	
	private boolean notExist(String Name) {
		if(db.queryNameExist(Name))return false;
		return true;
	}
	
	
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
