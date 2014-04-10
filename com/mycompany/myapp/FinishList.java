package com.myapplication8.app.Back;

public class FinishList{
	//RunnerID int,ID int references IDList(ID),FinishDate date,FinishTime time,ActualNum int,
	//InnerInturrptTimes int,OuterInturrptTimes int,primary key(ID,FinishDate,FinishTime)
	int RunnerID;
	int ID;
	String FinishDateString;
	String FinishTimeString;
	int ActualNum;
	int InnerInturrptTimes;
	int OuterInturrptTimes;
	
	public void set(int RunnerID,int ID,String FinishDateString,String FinishTimeString,
			int ActualNum,int InnerInturrptTimes,int OuterInturrptTimes){
		this.RunnerID=RunnerID;
		this.ID=ID;
		this.FinishDateString=FinishDateString;
		this.FinishTimeString=FinishTimeString;
		this.ActualNum=ActualNum;
		this.InnerInturrptTimes=InnerInturrptTimes;
		this.OuterInturrptTimes=OuterInturrptTimes;
	}
	
	public String get(){
		return RunnerID+","+ID+",'"+FinishDateString+"','"+FinishTimeString+"',"+
				ActualNum+","+InnerInturrptTimes+","+OuterInturrptTimes;
	}
}
