package com.myapplication8.app.Back;


import java.util.Date;
import java.util.Vector;

import android.app.Activity;
import android.content.Context;

public class InnerforUI {
    static InnerforUI in=null;
    public static InnerforUI getInstance(Context context){
        if(in!=null){
            in=new InnerforUI(context);
            return in;
        }else{
            return in;
        }
    }

	Date date=new Date();
	TodayList todayList;
	TaskView taskView;
	Today today;
	FindDb db;
	Timer timer=new Timer();

	int sign=0;//????todayList??????
	//??????
	public InnerforUI(Context context){
        db=new FindDb(context);
		taskView=new TaskView(db);
		todayList=new TodayList(taskView);
		today=initialToday(context,db);
	}
	
	//??????_Today
	private Today initialToday(Context context,FindDb db) {
		Today today=new Today(context,db);
		today.setWeekDay(date.getDay());
		//YYYY-MM-DD HH:MM:SS
		//date.getYear()+"-"+date.getMonth()+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+
		String time=date.getYear()+"-"+date.getMonth()+"-"+date.getDay()+
				" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
		today.setStartTime(time);

		//????????
		today.setLastingTimes(0);
		today.setActualNum(0);
		today.setSummary(null);
		return today;
	}
	
	
	//when set today information..
	public void setTodayRestTime(int RestTime){
		today.setRestTime(RestTime);
	}
	public void setTodayWorkTime(int WorkTime){
		today.setWorkTime(WorkTime);
	}
	public void setTodayLongRestTime(int LongRestTIme){
		today.setLongRestTime(LongRestTIme);
	}
	//when click add task ..	
	//1.new task..
	public boolean clickAddTask(String Name,int ExpectNum,String ExpectDate,String NoteString){
		int ID=taskView.getCurrentID();
		if(notExistName(Name)){
			Task task=new Task();
			task.set(Name, ID, ExpectNum, ExpectDate, NoteString);
			taskView.addTask(task);
			return true;
		}
		return false;
	}
	//2.exist ID..
	public boolean clickSetTask(int ID,int ExpectNum,String ExpectDate){
		if(noExistID(ID)==false){
			Task task=new Task();
			IDList idList=db.FindIDListByID(IDList.class, ID);
			String[] s=idList.get().split(",");
			task.set(s[1], ID, ExpectNum, ExpectDate, s[2]);
			taskView.addTask(task);
			return true;
		}
		return false;
	}

	//when click add peroidtask ..
		//1.new task ..
	public boolean clickAddPeroidNewTask(String Name,int ExpectNum,String ExpectDate,String NoteString,int Kind){
		if(notExistName(Name)){
			int ID=taskView.getCurrentID();
			PeroidTask pTask=new PeroidTask();
			pTask.set(Name, ID, ExpectNum, ExpectDate, Kind);
			taskView.addPeriodTask(pTask,0);
			return true;
		}
		return false;
	}


		//2.exist ID ..
	public boolean clickSetPeroidTask(int ID,int ExpectNum,String ExpectDate,int Kind){
		if(noExistID(ID)==false){
			Vector<Task> vector=taskView.getTaskById(ID);
			PeroidTask pTask=new PeroidTask();
			pTask.set(vector.get(0).getName(),ID,ExpectNum,ExpectDate,Kind);
			taskView.addPeriodTask(pTask,1);
			return true;
		}
		return false;
	}
	
	private boolean noExistID(int ID) {
		Vector<PeroidTask> vector=taskView.getPeriodTaskById(ID);
		if(vector.isEmpty())return false;
		return true;
	}

	private boolean notExistName(String Name) {
		if(db.queryNameExist(Name))return false;
		return true;
	}
	
	//when search IDList/Task/PeroidTask
	public Vector<IDList> showIDList(){
		Vector<IDList> vector=taskView.getAllList();
		return vector;
	}
	public Vector<Task> showTaskByOrder(int Kind){
		Vector<Task> vector=taskView.getListByOrder(Kind);
		return vector;
	}

	//if you want to getPeroidTask, you can just set ID a negative number 
	public Vector<PeroidTask> showPeroidTask(int ID){
		Vector<PeroidTask> vector = null;
		if(ID<=-1){
			vector=taskView.getPeriodTask();
		}
		else {
			vector=taskView.getPeriodTaskById(ID);
		}
		return vector;
	}
	
	//when search TodayList ..
	public Vector<TodayTask> showTodayList(){
		TodayList todayList=new TodayList(taskView);
		setTodayList();
		return todayList.getTodayList();		
	}

	public void setTodayList(){
		todayList.setTodayList();
	}
	
	//when start,retrun ID,if you want to setNextTask, you can just set ID a negative number 
	public int start(int ID){
		setTodayList();
		TodayTask todayTask=null;
		if(ID<=-1){
			todayTask=timer.setNextTask(todayList);			
		}
		else if(ID>=0){
			todayTask=timer.setTaskByID(ID, todayList);
		}
			timer.start();
		if(todayTask!=null){
			ID=todayTask.getID();
			return ID;
		}
		else{
			return -1;
		}
	}
	
	//when abort
	public void abort(){
		timer.stop();
	}
	
	
	//when finish ????not operated by user
	//1.if all finish(start return -1),set Summary..
	public void setSummary(String Summary){
		today.setSummary(Summary);
		today.saveDay(this);
	}
	//2.if not ,get id from start
	public void writeTodayTask(){
		timer.finish();
        today.setActualNum(1);
		//ActualNum   (+1)
	}
	//when show Today Information
	public String getToday(){	
		return today.get();
	}
}
