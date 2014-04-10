package com.myapplication8.app.Back;

public class Task{
    protected int RunnerID=-1;
	protected String Name=null;
	protected int ID=-1;
    protected String BeginTime=null;
	protected int ExpectNum=0;
	protected String ExpectDate=null;
	protected String NoteString=null;
    protected  int Priority;
	
	public String getName() {
		return Name;
	}

	public int getID() {
		return ID;
	}

    public int getRunnerID(){ return RunnerID; }

	public int getExpectNum() {
		return ExpectNum;
	}

	public String getExpectDate() {
		return ExpectDate;
	}
    public void setExpectDate(String ExpectDate){
        this.ExpectDate=ExpectDate;
    }

	public String getNoteString() {
		return NoteString;
	}

    public void setPriority(int Priority){
        this.Priority=Priority;
    }
	
	
	public void set(String Name,int ID,String BeginTime,int ExpectNum,String ExpectDate,String NoteString,int RunnerID,int Priority){
		this.Name=Name;	
		this.ID=ID;
        this.BeginTime=BeginTime;
		this.ExpectNum=ExpectNum;
		this.ExpectDate=ExpectDate;
		this.NoteString=NoteString;
        this.RunnerID=RunnerID;
        this.Priority=Priority;
	}
	
	public String get(){
		return "'"+Name+"',"+ID+",'"+NoteString+"':"+ID+",'"+BeginTime+"',"+ExpectNum+",'"+ExpectDate+"':"+RunnerID+":"+Priority;
	}
}
