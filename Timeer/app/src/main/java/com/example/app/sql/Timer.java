package com.example.app.sql;


import android.R.integer;
import android.os.CountDownTimer;
import android.os.SystemClock;

import com.myapplication8.app.CenterFragment;

public class Timer {
	
	private int startTime;//
	private TodayTask NowTask;

	//private int tick=25;	//tick ??????????????
	private int CurrentLastingTimes;//
	private boolean CurrentState;//
	public TimeCount time;//

	public Timer(){	
		startTime=(int) SystemClock.elapsedRealtime();	
	}
	
	
	public TodayTask setNextTask(TodayList list){
		TodayTask task=list.getNextTask();
		NowTask = task;
		CurrentState=false;
		return task;
		
	}

	public TodayTask  setTaskByID(int  ID,TodayList list) {
		TodayTask task=list.getTaskByID(ID);
		CurrentState=false;
		NowTask = task;
		return task;
		//from UI 
		
	}
	
	/*
	 * ??getTask=true????????????	
	 */

	public void start(){
		if(CurrentState==false){
			int ms = CurrentLastingTimes * 60 * 1000;
			time = new TimeCount(ms, 1000);
			time.start();
		}
	}
	
	public void finish(){
		if(CurrentState==false){
			CurrentState=true;
			NowTask.finish();
		}
	}
	public void stop(){
		if(CurrentState==false){
			CurrentState=true;
			NowTask.abort();
		}
	}

	//public void nowTaskclear(){
			//????????????????Timer??????????????????????????
	//}	
	/*
	 * ??????????
	 */
	public class TimeCount extends CountDownTimer {
		private int startTime;//????????????????????????????????????????????????????????Task??????????Task????????

		public TimeCount(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);// ????????????????,????????????????
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			//tell ui 
			
		}

		@Override
		public void onTick(long millisUntilFinished) {

		}	
	}
}







