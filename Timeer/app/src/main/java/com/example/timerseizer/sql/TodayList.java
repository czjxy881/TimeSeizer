package com.example.timerseizer.sql;


import java.util.Vector;


public class TodayList {
	private TaskView taskView;
	private Vector<TodayTask> vector=null;
	private int taskIndex=0;
	private int vectorLength=-1;
    private static TodayList todayList;
    public static TodayList getInstance(TaskView taskView){
        if(todayList==null){
            todayList=new TodayList(taskView);
        }else{
            todayList.setTodayList();
        }
        return todayList;
    }

	private TodayList(TaskView taskView){
        this.taskView=taskView;
        vector=new Vector<TodayTask>();
		setTodayList();
	}
	//
	public TodayTask getNextTask(){
		TodayTask t=null;
		if(vectorLength>0&&taskIndex<vectorLength){
			t=vector.get(taskIndex);
		}
		return t;
	}

	public TodayTask getTaskByID(int ID){
		int i=0;
		for(;i<vectorLength;i++)
			if(vector.get(i).getID()==ID)
				return vector.get(i);
		return null;
	}
    public Vector<TodayTask> getTaskByState(StateEnum state){
        Vector<TodayTask> vtemp=new Vector<TodayTask>();
        TodayTask task=null;
        int i=0;
        for(;i<vectorLength;i++) {
            task = vector.get(i);
            if (task.getState().equals(state))
                vtemp.add(task);
        }
        return vtemp;
    }
	//
	public Vector<TodayTask> getTodayList(){
		return vector;
	}
	public void setTodayList() {
		vector=taskView.getTodayOptionalTask();
	}
}
