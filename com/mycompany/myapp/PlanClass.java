package com.myapplication8.app.Back;

public class PlanClass {
	private int RunnerID;
	private int TaskID;

    private int ExpectNum;
    private String ExpectDate;
    private int Priority;
    private int State;
	
	public void set(int RunnerID,int TaskID,int ExpectNum,String ExpectTimeString,int Priority,int State){
		this.RunnerID=RunnerID;
		this.TaskID=TaskID;
		this.ExpectNum=ExpectNum;
		this.ExpectDate=ExpectTimeString;
		this.Priority=Priority;
		this.State=State;
	}
	
	public String get(){
		return RunnerID+","+TaskID+","+ExpectNum+",'"+ExpectDate+"',"+Priority+","+State;
	}
}
