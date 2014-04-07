package com.myapplication8.app.Back;

public class DailyList{
	//Date date primary key,Summary char(1),ActualNum int,RestTime int
	private String DateString=null;
	private String Summary=null;
	private int ActualNum=0;
	private int RestTime=10;
	private int WorkTime=25;
	private int LongRestTime=25;
	public String getDateString() {
		return DateString;
	}
	public String getSummary() {
		return Summary;
	}
	public int getActualNum() {
		return ActualNum;
	}
	public int getRestTime() {
		return RestTime;
	}
	public int getWorkTIme(){
		return WorkTime;
	}
	public int getLongRestTime() {
		return LongRestTime;
	}
	public void set(String DateString,String Summary,int ActualNum,int RestTime,int WorkTime,int LongRestTime){
		this.DateString=DateString;
		this.Summary=Summary;
		this.ActualNum=ActualNum;
		this.RestTime=RestTime;
		this.WorkTime=WorkTime;
		this.LongRestTime=LongRestTime;
	}
	public String get(){
		return "'"+DateString+"','"+Summary+"',"+ActualNum+","+RestTime+","+WorkTime+","+LongRestTime;
	}

}
