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

	public void set(String Name,int ID,int ExpectNum,String ExpectDate,int Kind){
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
