package com.myapplication8.app.Back;

import java.util.Vector;


public class TodayList {
	private TaskView taskView;
	private TodayTask todayTask;
	private Vector<TodayTask> vector=null;
	private int taskIndex=0;
	private int vectorLength=-1;
	
	public TodayList(TaskView taskView){
		this.taskView = taskView;
		setTodayList();
	}
	//
	public TodayTask getNextTask(){
		TodayTask t=null;
		if(vector.get(taskIndex).getState()==2)
			taskIndex++;
		if(vectorLength>0&&taskIndex<vectorLength){
			t=vector.get(taskIndex);
		}
		return t;
	}
	//finish?
	public TodayTask getTaskByID(int ID){
		int i=0;
		for(;i<vectorLength;i++)
			if(vector.get(i).getID()==ID&&vector.get(i).getState()==0)
				return vector.get(i);
		return null;
	}
	
	//
	public Vector<TodayTask> getTodayList(){
		return vector;
	}
	public void setTodayList() {
		Vector<Task> vtask=taskView.getTodayOptionalTask();
		Task task;
			for(int i=0;i<vtask.size();i++){
				task=vtask.get(i);
				todayTask.set(task.Name,task.ID, task.ExpectNum, task.ExpectDate, task.NoteString);
				vector.add(todayTask);	
			}
	}
}
