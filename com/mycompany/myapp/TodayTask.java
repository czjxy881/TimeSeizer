package com.myapplication8.app.Back;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class TodayTask extends Task{
	
	private int ActualNum;
	private int InnerInturrptTimes;
	private int OuterInturrptTimes;
	private int State;
	private int RunnerID;

	Date date;
	public TodayTask(){
		ActualNum=0;
		InnerInturrptTimes=0;
		OuterInturrptTimes=0;
		State=0;
		RunnerID=-1;
		date=new Date();
	}
	
	public void innerInturrpt(){
		InnerInturrptTimes++;
	}
	
	public void outerInturrpt(){
		OuterInturrptTimes++;
	}
	
	public void oneClockPass(){
		ActualNum++;
	}
	
	public void finish(){
		oneClockPass();
		if(ExpectNum==ActualNum)
		State=2;
		InnerforUI.db.updateFinishList(date,this);
			//set new date by kind
			String SDate=setNewDate();
			if(SDate!=null)
                InnerforUI.db.updatePlanList(RunnerID+","+ID+","+ExpectNum+",'"+SDate+"',"+Priority,2);
			//RunnerID,ID,ExpectNum,ExpectDate
	}
	//????????
	public void abort(){
		State=1;
		String SDate=setNewDate();
        InnerforUI.db.updatePlanList(RunnerID+","+ID+","+ExpectNum+",'"+SDate+"','"+BeginTime+"',"+Priority,1);
        InnerforUI.db.updateFinishList(date, this);
	}
	private String setNewDate() {
		Vector<PeroidTask> peroidTask=InnerforUI.db.FindPeroidTaskByID(PeroidTask.class, ID);
		if(peroidTask.isEmpty()==false){
			int Kind=peroidTask.get(0).getKind();
			Calendar c=Calendar.getInstance();   
			DateFormat df=new SimpleDateFormat("yyyy-MM-dd");   
			c.setTime(new Date());   
			c.add(Calendar.DATE,Kind);   
			Date d2=c.getTime();   
			String SDate=df.format(d2);
            Log.e("setNewDate", SDate);
			return SDate;
		}
		else return null;
	}
	public int getState(){
		return State;
	}
	public void set(int ActualNum,int InnerInturrptTimes,int OuterInturrptTimes,
		int RunnerID,String Name,int ID,int ExpectNum,String ExpectDate,int State,String Note){
		this.ActualNum=ActualNum;
		this.InnerInturrptTimes=InnerInturrptTimes;
		this.OuterInturrptTimes=OuterInturrptTimes;
		this.RunnerID=RunnerID;
		this.Name=Name;
		this.ID=ID;
		this.ExpectNum=ExpectNum;
		this.ExpectDate=ExpectDate;
		this.State=State;
        this.NoteString=Note;
	}
	public String get(){
		return "'"+Name+"':"+RunnerID+","+ID+":'"+
				ExpectDate+"':"+ActualNum+","+InnerInturrptTimes+","+OuterInturrptTimes+":"+State+","+ExpectNum+":"+NoteString;
	}//int RunnerID,int ID,String FinishDateString,String FinishTimeString,int ActualNum,int InnerInturrptTimes,int OuterInturrptTimes
}
