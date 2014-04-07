package com.mycompany.myapp;



import android.R.integer;
import android.os.CountDownTimer;
import android.os.SystemClock;

public class Timer {
	
	private int startTime;//
	private TodayTask NowTask;

	//private int tick=25;	//tick 的值是可设置的
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
	 * 当getTask=true时，开始计时	
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
			//番茄完成后，刷新Timer，并产生历史记录写入数据库
	//}	
	/*
	 * 倒计时部分
	 */
	public class TimeCount extends CountDownTimer {
		private int startTime;//计时开始时间，记录本地时间，记录在数据库中，用户可以查询Task开始时间和Task持续时间

		public TimeCount(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			//tell ui 
			
		}

		@Override
		public void onTick(long millisUntilFinished) {
			// TODO Auto-generated method stub
			try {  
	              Thread.sleep(1000);  
	           } catch (InterruptedException e) {  
	              e.printStackTrace();  
	           }  
			
		}	
	}
}







