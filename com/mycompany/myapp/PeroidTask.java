package com.myapplication8.app.Back;


public class PeroidTask extends Task{
	
	int Kind;
	public PeroidTask(){
		Kind=1;
	}
	
	public int getKind(){
		return Kind;
	}
	public void setKind(int Kind){
		this.Kind=Kind;
	}

	
	
	public boolean judgeRun(String day){                 //Necessary??
		return true;
	}

	public void set(int RunnerID,String Name,int ID,String BeginTime,int ExpectNum,String ExpectDate,String Note,int Kind,int Priority){
        this.RunnerID=RunnerID;
		this.Name=Name;
		this.ID=ID;
        this.BeginTime=BeginTime;
		this.ExpectNum=ExpectNum;
		this.ExpectDate=ExpectDate;
        this.NoteString=Note;
		this.Kind=Kind;
        this.Priority=Priority;
	}
	public String get(){
		return "'"+Name+"',"+ID+",'"+NoteString+"':"+RunnerID+","+ID+","+ExpectNum+",'"+ExpectDate+"':"+ID+","+Kind+":'"+BeginTime+"':"+Priority;
	}
}
