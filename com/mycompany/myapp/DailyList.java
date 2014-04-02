package com.mycompany.myapp;

public class DailyList{
	//Date date primary key,Summary char(1),ActualNum int,RestTime int
	String DateString=null;
	String Summary=null;
	int ActualNum=0;
	int RestTime=10;
	
	public void set(String DateString,String Summary,int ActualNum,int RestTime){
		this.DateString=DateString;
		this.Summary=Summary;
		this.ActualNum=ActualNum;
		this.RestTime=RestTime;
	}
	public String get(){
		return "'"+DateString+"','"+Summary+"',"+ActualNum+","+RestTime;
	}
}
