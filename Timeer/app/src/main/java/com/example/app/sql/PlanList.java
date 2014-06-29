package com.example.app.sql;

public class PlanList{
	//RunnerID int primary key,ID int references IDList(ID),BeginTime time,ExpectNum int,ExpectTime time,priority int
	int RunnerID;
	int ID;
	String BeginTimeString;
	int ExpectNum;
	String ExpectDate;
	int Priority;
	int State;
	
	public void set(int RunnerID,int ID,String BeginTimeString,int ExpectNum,String ExpectTimeString,int Priority,int State){
		this.RunnerID=RunnerID;
		this.ID=ID;
		this.BeginTimeString=BeginTimeString;
		this.ExpectNum=ExpectNum;
		this.ExpectDate=ExpectTimeString;
		this.Priority=Priority;
		this.State=State;
	}
	
	public String get(){
		return RunnerID+","+ID+",'"+BeginTimeString+"',"+ExpectNum+",'"+ExpectDate+"',"+Priority+","+State;
	}
}
