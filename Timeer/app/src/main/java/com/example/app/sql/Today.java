package com.example.app.sql;

public class Today {//Set in InnerForUI
	private int WeekDay;//
	private int WorkTime;//
	private int RestTime;//
	private int LongRestTime;//
	private int LastingTimes;//finish-->update
	private String StartTime;
	private String Summary;

    InitDb db=null;

	public Today(InitDb db){
        this.db=db;
        DailyClass dailyClass =db.queryDb.FindDailyListNew();
        if(dailyClass !=null) {
            setWorkTime(dailyClass.getWorkTime());
            setRestTime(dailyClass.getRestTime());
            setLongRestTime(dailyClass.getLongRestTime());
        }
        else{
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


	public String getSummary() {
		return Summary;
	}

	public void setSummary(String Summary){
		this.Summary=Summary;
	}

	public String get(){
		return WeekDay+","+WorkTime+","+RestTime+","+LongRestTime+","+LastingTimes+","+StartTime+","+Summary;
	}
	public void saveDay(){
        db.updateDb.saveDay(Summary,StartTime,RestTime,WorkTime,LongRestTime);
	}
	
}
