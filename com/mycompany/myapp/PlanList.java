package com.mycompany.myapp;

public class PlanList{
	//RunnerID int primary key,ID int references IDList(ID),BeginTime time,ExpectNum int,ExpectTime time,priority int
	int RunnerID;
	int ID;
	String BeginTimeString;
	int ExpectNum;
	String ExpectTimeString;
	int priority;
	int State;
	
	public void set(int RunnerID,int ID,String BeginTimeString,int ExpectNum,String ExpectTimeString,int priority,int State){
		this.RunnerID=RunnerID;
		this.ID=ID;
		this.BeginTimeString=BeginTimeString;
		this.ExpectNum=ExpectNum;
		this.ExpectTimeString=ExpectTimeString;
		this.priority=priority;
		this.State=State;
	}
	
	public String get(){
		return RunnerID+","+ID+",'"+BeginTimeString+"',"+ExpectNum+",'"+ExpectTimeString+"',"+priority+","+State;
	}
}
