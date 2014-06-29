package com.example.app.sql;

public class DailyClass {
	//Date date primary key,Summary char(1),ActualNum int,RestTime int
	private String DateString=null;
	private String Summary=null;
	private int RestTime=10;
	private int WorkTime=25;
    private String BeginTime=null;
	private int LongRestTime=25;
	public String getDateString() {
		return DateString;
	}
	public String getSummary() {
		return Summary;
	}
	public int getRestTime() {
		return RestTime;
	}
	public int getWorkTime(){
		return WorkTime;
	}
	public int getLongRestTime() {
		return LongRestTime;
	}
    public String getBeginTime(){return BeginTime;}
	public void set(String DateString,String BeginTime,String Summary,int RestTime,int WorkTime,int LongRestTime){
		this.DateString=DateString;
		this.Summary=Summary;
		this.RestTime=RestTime;
		this.WorkTime=WorkTime;
		this.LongRestTime=LongRestTime;
        this.BeginTime=BeginTime;
	}
	public String get(){
		return "'"+DateString+"','"+BeginTime+"','"+Summary+"',"+RestTime+","+WorkTime+","+LongRestTime;
	}

}
