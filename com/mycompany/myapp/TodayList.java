package com.mycompany.myapp;

import java.util.Vector;


public class TodayList {
	private Vector<TodayTask> vector=null;
	private int taskIndex=0;
	private int vectorLength=-1;
	
	public TodayTask getNextTask(){
		TodayTask t=null;
		if(vectorLength>0&&taskIndex<vectorLength){
			t=vector.get(taskIndex);
			taskIndex++;
		}
		return t;
	}
	
	public TodayTask getTaskByID(int ID){
		int i=0;
		for(;i<vectorLength;i++)
			if(vector.get(i).ID==ID)
				return vector.get(i);
		return null;
	}
	
	public void setTodayTask(TodayTask task){
		vector.add(task);
	}
	public Vector<TodayTask> getTodayList(){
		return vector;
	}
}
