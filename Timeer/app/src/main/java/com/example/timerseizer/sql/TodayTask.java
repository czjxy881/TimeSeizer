package com.example.timerseizer.sql;

import java.util.Date;

public class TodayTask extends Task {
    enum StateEnum{READY,ABANDON,FINISH,ERROR};
	private int ActualNum;
	private int InnerInturruptTimes;
	private int OuterInturruptTimes;
	private int State;

	Date date;
	public TodayTask(){
		ActualNum=0;
		InnerInturruptTimes=0;
		OuterInturruptTimes=0;
		State=0;
		RunnerID=-1;
		date=new Date();
	}

	public void innerInturrpt(){
		InnerInturruptTimes++;
	}

	public void outerInturrpt(){
		OuterInturruptTimes++;
	}

	public void oneClockPass(){
		ActualNum++;
	}

	public void finish(){
		oneClockPass();
		if(ExpectNum==ActualNum)
		    State=2;
	}
	//????????
	public void abort(){
        OuterInturruptTimes++;
	}

	public int getState(){
        return State;
	}
	public void set(int ActualNum,int InnerInturrptTimes,int OuterInturrptTimes,
		int RunnerID,String Name,int ID,int ExpectNum,String ExpectDate,int State,String Note){
		this.ActualNum=ActualNum;
		this.InnerInturruptTimes=InnerInturrptTimes;
		this.OuterInturruptTimes=OuterInturrptTimes;
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
				ExpectDate+"':"+ActualNum+","+InnerInturruptTimes+","+OuterInturruptTimes+":"+State+","+ExpectNum+":"+NoteString;
	}//int RunnerID,int ID,String FinishDateString,String FinishTimeString,int ActualNum,int InnerInturrptTimes,int OuterInturrptTimes
}
