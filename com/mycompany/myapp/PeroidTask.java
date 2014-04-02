package com.mycompany.myapp;


public class PeroidTask extends Task{
	
	long Kind;
	public PeroidTask(){
		Kind=1;
	}
	
	public long getKind(){
		return Kind;
	}
	public void setKind(int Kind){
		this.Kind=Kind;
	}	
	
	
	public boolean judgeRun(int WeekDay){                 //Random??
		return true;
	}

	public void set(String Name,int ID,int ExpectNum,String ExpectDate,long Kind){
		this.Name=Name;
		this.ID=ID;
		this.ExpectNum=ExpectNum;
		this.ExpectDate=ExpectDate;
		this.Kind=Kind;
	}
	public String get(){
		return "'"+Name+"',"+ID+",'"+NoteString+"':"+ID+","+ExpectNum+",'"+ExpectDate+"':"+ID+","+Kind;
	}
}
