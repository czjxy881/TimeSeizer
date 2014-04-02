package com.mycompany.myapp;

import java.util.Date;

import android.content.Context;

public class Today {
	private int WeekDay;//
	
	public Today(Context context){
		ActualNum=0;
	}
	
	public int getWeekDay() {
		return WeekDay;
	}

	public void setWeekDay(int weekDay) {
		WeekDay = weekDay;
	}

	public int getWorkTime() {
		return WorkTime;
	}

	public void setWorkTime(int workTime) {
		WorkTime = workTime;
	}

	public int getRestTime() {
		return RestTime;
	}

	public void setRestTime(int restTime) {
		RestTime = restTime;
	}

	public int getLongRestTime() {
		return LongRestTime;
	}

	public void setLongRestTime(int longRestTime) {
		LongRestTime = longRestTime;
	}

	public int getLastingTimes() {
		return LastingTimes;
	}

	public void setLastingTimes(int lastingTimes) {
		LastingTimes = lastingTimes;
	}

	public String getStartTime() {
		return StartTime;
	}

	public void setStartTime(String startTime) {
		StartTime = startTime;
	}

	public int getActualNum() {
		return ActualNum;
	}

	public void setActualNum(int actualNum) {
		ActualNum = actualNum;
	}

	public String getSummary() {
		return Summary;
	}
	private int WorkTime;//
	private int RestTime;//
	private int LongRestTime;//
	private int LastingTimes;
	private String StartTime;
	private String Summary;
	private int ActualNum;

	
//	public void set(int WorkTime,int RestTime,int LongRestTime,String StartTime){
//		Date date=new Date();
//		WeekDay=date.getDay();
//		this.WorkTime=WorkTime;
//		this.RestTime=RestTime;
//		this.LongRestTime=LongRestTime;
//		this.StartTime=StartTime;
//	}
//	public String get() {
//		return 	WeekDay+","+WorkTime+","+RestTime+","+
//				LongRestTime+","+LastingTimes+","+StartTime+","+Summary;
//	}
	public void setSummary(String Summary){
		this.Summary=Summary;
	}
	public void saveDay(FindDb db){
		DailyList list=new DailyList();
		list.set(new Date().getDate()+"", Summary, ActualNum, RestTime);
		db.updateDailyList(list);
	}
}
