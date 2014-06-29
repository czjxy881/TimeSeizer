package com.example.app.sql;


import java.util.Vector;


public class TodayList {
	private TaskView taskView;
	private Vector<TodayTask> vector=null;
	private int taskIndex=0;
	private int vectorLength=-1;

	public TodayList(TaskView taskView){
        this.taskView=taskView;
        vector=new Vector<TodayTask>();
		setTodayList();
	}
	//
	public TodayTask getNextTask(){
		TodayTask t=null;
		while(vector.get(taskIndex).getState()== StateEnum.FINISH.getNum())
			taskIndex++;
		if(vectorLength>0&&taskIndex<vectorLength){
			t=vector.get(taskIndex);
		}
		return t;
	}

	public TodayTask getTaskByID(int ID){
		int i=0;
		for(;i<vectorLength;i++)
			if(vector.get(i).getID()==ID&&vector.get(i).getState()== StateEnum.READY.getNum())
				return vector.get(i);
		return null;
	}
    public Vector<TodayTask> getTaskByState(StateEnum state){
        Vector<TodayTask> vtemp=new Vector<TodayTask>();
        TodayTask task=null;
        int i=0;
        for(;i<vectorLength;i++) {
            task = vector.get(i);
            if (task.getState()==state.getNum())
                vtemp.add(task);
        }
        return vtemp;
    }
	//
	public Vector<TodayTask> getTodayList(){
		return vector;
	}
	public void setTodayList() {
		Vector<Task> vtask=taskView.getTodayOptionalTask();
		Task task;
        TodayTask todayTask;
			for(int i=0;i<vtask.size();i++){
				task=vtask.get(i);
                todayTask=new TodayTask();
                todayTask.set(0,0,0,task.getRunnerID(),task.Name,task.ID, task.ExpectNum, task.ExpectDate,0,task.NoteString);
				vector.add(todayTask);
			}
	}
}
