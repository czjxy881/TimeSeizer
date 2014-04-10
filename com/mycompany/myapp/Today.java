package com.myapplication8.app.Back;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.util.Log;

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
		DailyList dailyList=null;
		dailyList=db.FindDailyListNew(DailyList.class);
        if(dailyList!=null) {
            setWorkTime(dailyList.getWorkTime());
            setRestTime(dailyList.getRestTime());
            setLongRestTime(dailyList.getLongRestTime());
        }
        else{
            Log.e("Today","when null");
            setWorkTime(25);
            setRestTime(10);
            setLongRestTime(30);
        }

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
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		list.set(df.format(date),Summary, ActualNum, RestTime,WorkTime,LongRestTime);
		forUI.db.updateDailyList(list);
	}
	
}
