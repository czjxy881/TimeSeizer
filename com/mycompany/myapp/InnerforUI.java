package com.myapplication8.app.Back;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

public class InnerforUI {
    static InnerforUI in=null;
    public String DAY=null;
    public String TIME=null;
    Date date=new Date();
    TodayList todayList=null;
    TaskView taskView=null;
    Today today=null;
    Timer timer=new Timer();

    static FindDb db=null;
    int sign=0;//????todayList??????
    //??????
    public static InnerforUI getInstance(Context context){
        if(in==null){
            in=new InnerforUI(context);
            return in;
        }else{
            return in;
        }
    }
	public InnerforUI(Context context){
        taskView=new TaskView(context);
        db=taskView.db;
		todayList=new TodayList(taskView);
        Log.e("InnerforUI","initialToday");
		today=initialToday(context,db);
        DAY=db.day;
	}
	
	//??????_Today          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	private Today initialToday(Context context,FindDb db) {
		Today today=new Today(context,db);
        Log.e("initialToday","new Today()");
		today.setWeekDay(date.getDay());
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //YYYY-MM-DD HH:MM:SS
		//date.getYear()+"-"+date.getMonth()+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+
		TIME=df.format(date);
        Log.e("InnerforUI/initialToday",TIME+" today.getWorkTime--"+today.getWorkTime());
		today.setStartTime(TIME);

		//????????
		today.setLastingTimes(0);
		today.setActualNum(0);
		today.setSummary(null);
		return today;
	}
	
	
	//when set today information..          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	public void setTodayRestTime(int RestTime){
		today.setRestTime(RestTime);
	}
    //                                                ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	public void setTodayWorkTime(int WorkTime){
		today.setWorkTime(WorkTime);
	}
    //                                                        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	public void setTodayLongRestTime(int LongRestTIme){
		today.setLongRestTime(LongRestTIme);
	}
	//when click add task ..	
	//1.new task..    ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	public boolean clickAddTask(String Name,int ExpectNum,String ExpectDate,String NoteString,int Priority){
        if(notExistName(Name)) {
            int ID = taskView.getCurrentID();
            int RunnerID = taskView.getRunnerID();
            if (notExistName(Name)) {
                Task task = new Task();
                task.set(Name, ID, null, ExpectNum, ExpectDate, NoteString, RunnerID, Priority);
                taskView.addTask(task);
                return true;
            }
            return false;
        }
        else return false;
	}
	//2.exist ID..         ????????????????????????????????????????????????????????????????????????????????
	public boolean clickSetTask(int ID,int ExpectNum,String ExpectDate,int Priority){
		if(noExistTaskID(ID)==false){
			Task task=new Task();
            int RunnerID=taskView.getRunnerID();
			IDList idList=db.FindIDListByID(IDList.class, ID);
			String[] s=idList.get().split(",");
			task.set(s[1], ID,null, ExpectNum, ExpectDate, s[2],RunnerID,Priority);
			taskView.addTask(task);
			return true;
		}
		return false;
	}                       
    public boolean clickDeletePeroid(int ID){
        if(noExistPeroidID(ID)==false){
            taskView.delPeroid(ID);
            return true;
        }
        return false;
    }
    public boolean clickDeleteTask(int RunnerID){
        if(noExistTaskRunnerID(RunnerID)==false){
            taskView.delTask(RunnerID);
            return true;
        }
        return false;
    }

    private boolean noExistTaskRunnerID(int RunnerID) {
        Task task=taskView.getTaskByRunnerId(RunnerID);
        if(task==null)return true;
        return false;
    }

    //when click add peroidtask ..
		//1.new task ..
	public boolean clickAddPeroidNewTask(String Name,int ExpectNum,String ExpectDate,String NoteString,int Kind,int Priority){
		if(notExistName(Name)){
			int ID=taskView.getCurrentID();
            int RunnerID=taskView.getRunnerID();
			PeroidTask pTask=new PeroidTask();
			pTask.set(RunnerID,Name, ID,null, ExpectNum, ExpectDate,NoteString, Kind,Priority);
			taskView.addPeriodTask(pTask,0);
			return true;
		}
		return false;
	}


		//2.exist ID ..
	public boolean clickSetPeroidTask(int ID,int ExpectNum,String ExpectDate,int Kind,int Priority,String Note){

			IDList idList=taskView.getIDListByID(ID);
        if(idList!=null){
			PeroidTask pTask=new PeroidTask();
			pTask.set(taskView.getRunnerID(),idList.getName(),ID,null,ExpectNum,ExpectDate,Note,Kind,Priority);
			taskView.addPeriodTask(pTask,1);
			return true;
		}
		return false;
	}
	
	private boolean noExistPeroidID(int ID) {
		Vector<PeroidTask> vector=taskView.getPeriodTaskById(ID);
		if(vector.isEmpty())return false;
		return true;
	}
    private boolean noExistTaskID(int ID) {
        Vector<Task> vector=taskView.getTaskById(ID);
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
	public Today getToday(){
		return today;
	}
}
