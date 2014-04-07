package com.mycompany.myapp;


public class Task{

	protected String Name=null;
	protected int ID=-1;
	protected int ExpectNum=0;
	protected String ExpectDate=null;
	protected String NoteString=null;
	
	public String getName() {
		return Name;
	}

	public int getID() {
		return ID;
	}

	public int getExpectNum() {
		return ExpectNum;
	}

	public String getExpectDate() {
		return ExpectDate;
	}

	public String getNoteString() {
		return NoteString;
	}

	
	
	public void set(String Name,int ID,int ExpectNum,String ExpectDate,String NoteString){
		this.Name=Name;	
		this.ID=ID;
		this.ExpectNum=ExpectNum;
		this.ExpectDate=ExpectDate;
		this.NoteString=NoteString;
	}
	
	public String get(){
		return "'"+Name+"',"+ID+",'"+NoteString+"':"+ID+","+ExpectNum+",'"+ExpectDate+"'";
	}
}
