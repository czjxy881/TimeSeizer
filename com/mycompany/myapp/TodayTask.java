package com.mycompany.myapp;

public class TodayTask extends Task{
	
	private int ActualNum;
	private int InnerInturrptTimes;
	private int OuterInturrptTimes;
	private int State;
	private int RunnerID;
	
	public TodayTask(){
		ActualNum=0;
		InnerInturrptTimes=0;
		OuterInturrptTimes=0;
		State=0;
		RunnerID=-1;
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
		State=2;
	}
	
	public void abort(){
		ActualNum=0;
		InnerInturrptTimes=0;
		OuterInturrptTimes=0;
		State=0;
		RunnerID=-1;
	}

	
	public void set(int ActualNum,int InnerInturrptTimes,int OuterInturrptTimes,
		int RunnerID,String Name,int ID,int ExpectNum,String ExpectDate,int State){
		this.ActualNum=ActualNum;
		this.InnerInturrptTimes=InnerInturrptTimes;
		this.OuterInturrptTimes=OuterInturrptTimes;
		this.RunnerID=RunnerID;
		this.Name=Name;
		this.ID=ID;
		this.ExpectNum=ExpectNum;
		this.ExpectDate=ExpectDate;
		this.State=State;
	}
	public String get(){
		return "'"+Name+"',"+ID+","+RunnerID+","+State+","+ExpectNum+","+ActualNum+",'"+
				ExpectDate+"',"+InnerInturrptTimes+","+OuterInturrptTimes;
	}
}
