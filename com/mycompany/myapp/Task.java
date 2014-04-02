package com.mycompany.myapp;


public class Task{
	
	protected String Name=null;
	protected int ID=-1;
	protected int ExpectNum=0;
	protected String ExpectDate=null;
	protected String NoteString=null;
	
//	public String getName(){
//		return Name;
//	}
//	public void setName(String name){
//		this.Name=name;	
//	}	
//	
//	public int getId(){
//		return ID;
//	}
//	public void setID(int id){
//		this.ID=id;
//	}	
//	
//	public int getExpectNum(){
//		return ExpectNum;
//	}
//	public void setExpectNum(int num){
//		this.ExpectNum=num;
//	}	
//	
//	public Date getExpectDate(){
//		return ExpectDate;
//	}
//	public void setExpectDate(Date date){
//		this.ExpectDate=date;
//	}
	
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
