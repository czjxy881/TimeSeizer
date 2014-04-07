package com.myapplication8.app.Back;

import java.util.Date;

import android.content.Context;

public class Today {//Set in InnerForUI
	private int WeekDay;//
	private int WorkTime;//
	private int RestTime;//
	private int LongRestTime;//
	private int LastingTimes;//finish-->update
	private String StartTime;
	private String Summary;
	private int ActualNum;
	
	
	public Today(Context context,FindDb db){
		ActualNum=0;
		DailyList dailyList=new DailyList();
		dailyList=db.FindDailyListNew(DailyList.class);
		setWorkTime(dailyList.getWorkTIme());
		setRestTime(dailyList.getRestTime());
		setLongRestTime(dailyList.getLongRestTime());
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
		ActualNum += actualNum;
	}

	public String getSummary() {
		return Summary;
	}

	public void setSummary(String Summary){
		this.Summary=Summary;
	}
	
	public String get(){
		return WeekDay+","+WorkTime+","+RestTime+","+LongRestTime+","+LastingTimes+","+StartTime+","+Summary+","+ActualNum;
	}
	public void saveDay(InnerforUI forUI){
		DailyList list=new DailyList();
		Date date=forUI.date;
		list.set(date.getYear()+"-"+date.getMonth()+"-"+date.getDate(), 
				Summary, ActualNum, RestTime,WorkTime,LongRestTime);
		forUI.db.updateDailyList(list);
	}
	
}
