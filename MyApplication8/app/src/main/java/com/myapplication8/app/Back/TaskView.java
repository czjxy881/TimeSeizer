package com.myapplication8.app.Back;

import java.util.Date;
import java.util.Vector;


public class TaskView {
	private int CurrentID;
	private FindDb db=null;
	private int RunnerID;
	Date date=new Date();

	public TaskView(FindDb db) {
		this.db=db;
		RunnerID=db.queryRunnerID();
		CurrentID=db.queryID();
	}
	
	/**????????????Task??//
	 * 
	 * @param Day ????
	 * @return Vector<Task>
	 */
	public Vector<Task> getListByDay(String Day){
		Vector<Task> vector=db.FindTaskByDay(Task.class,Day);
		return vector;
	}
	
	
	
	/**????????????????????Task??????????????//
	 * 
	 * @param kind 0-7
	 * 			0	Name ASC
				1	Name DESC
		 		2	ID ASC
				3	ID DESC
				4	ExpectNum ASC
				5	ExpectNum DESC
				6	ExpectDate ASC
				7	ExpectDate DESC
	 * @return  Vector<Task>
	 */
	public Vector<Task> getListByOrder(int kind){
		Vector<Task> vector=db.FindTaskByStyle(Task.class,kind);
		return vector;
	}
	
	/**??????????????????//
	 * 
	 * @param a Task????
	 */
	public void addTask(Task a){
		String[] strings=a.get().split(":");
		db.updateIDList(strings[0]);
		db.updatePlanList(strings[1], RunnerID,0);
		//RunnerID+","+ID+",'"+BeginTimeString+"',"+ExpectNum+",'"+ExpectTimeString+"',"+priority
		CurrentID++;
		RunnerID++;
	}
	
	/**??????????????????????//
	 * 
	 * @param a ????????
	 */
	public boolean addPeriodTask(PeroidTask a,int kind){
		String[] strings=a.get().split(":");
		if(kind==0){//ID new
			db.updateIDList(strings[0]);
			db.updatePlanList(strings[1], RunnerID,0);
			db.updatePeroidList(strings[2]);
			CurrentID++;
			RunnerID++;
			return true;
		}
		else if(kind==1){//ID exist
			db.updatePlanList(strings[1], RunnerID,0);
			db.updatePeroidList(strings[2]);
			RunnerID++;
			return true;
		}
		else return false;
	}
	
	/**????????????????????//
	 * 
	 * @return Vector<PeroidTask>
	 */
	public Vector<PeroidTask> getPeriodTask(){
		Vector<PeroidTask> vector=db.FindPeroidTask(PeroidTask.class);
		return vector;
	}
	
	/**????????????ID//
	 * 
	 * @return ID
	 */
	public int getCurrentID(){
		return CurrentID;
	}
	
	/**??????????????????????//
	 * 
	 * @return Vector<Task>
	 */
	public Vector<Task> getTodayOptionalTask(){
		Vector<Task> vector=getListByDay(date.getYear()+"-"+date.getMonth()+"-"+date.getDate());
		return vector;
	}
	
	/**??????????????//
	 * 
	 * @return Vector<Task>
	 */
	public Vector<IDList> getAllList(){
		return db.FindIDList(IDList.class);
	}
	
	/**????????ID??????????????//
	 * 
	 * @param id ????ID
	 * @return Vector<Task>
	 */
	public Vector<Task> getTaskById(int id){
		return db.FindTaskByID(Task.class, id);
	}
	
	/**????????ID??????????????????//
	 * 
	 * @param id ????ID
	 * @return Vector<Task>
	 */
	public Vector<PeroidTask> getPeriodTaskById(int id){
		return db.FindPeroidTaskByID(PeroidTask.class, id);
	}
	
	/**????????????//
	 * 
	 * @param task ????
	 */
	public void setTask(Task task){
		String[] strings=task.get().split(":");	
		db.updatePlanList(strings[1],RunnerID,0);
		RunnerID++;
	}
	
}
