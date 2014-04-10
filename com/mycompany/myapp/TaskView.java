package com.myapplication8.app.Back;

import android.content.Context;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;


public class TaskView {
	private int CurrentID;
	private int RunnerID;
	Date date=new Date();
    FindDb db=null;

	public TaskView(Context context) {
        db=new FindDb(context);
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
       // "'"+Name+"',"+ID+",'"+NoteString+"':"+ID+",'"+BeginTime+"',"+ExpectNum+",'"+ExpectDate+"':"+RunnerID+":"+Priority;
		db.updateIDList(strings[0]);
		db.updatePlanList(strings[2]+","+strings[1]+","+strings[3],0);
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
			db.updatePlanList(strings[1]+","+strings[3]+","+strings[4],0);
			db.updatePeroidList(strings[2]);
			CurrentID++;
			RunnerID++;
			return true;
		}
		else if(kind==1){//ID exist
			db.updatePlanList(strings[1]+","+strings[3]+","+strings[4],0);
			db.updatePeroidList(strings[2]);
			RunnerID++;
			return true;
		}
		else return false;
	}
    public void delTask(int RunnerID){
        PeroidTask peroidTask=db.FindOnePeroidTask(PeroidTask.class,RunnerID);
        if(peroidTask==null){
            db.sql("Delete from PlanList where RunnerID="+RunnerID);
        }
        else{
            DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal= Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DATE, peroidTask.getKind());
            String newDay=df.format(cal.getTime());
            peroidTask.setExpectDate(newDay);
            String s= peroidTask.get();
            String[] spilt=s.split(":");
            //"'"+Name+"',"+ID+",'"+NoteString+"':"+RunnerID+","+ID+","+ExpectNum+",'"+ExpectDate+"':"+ID+","+Kind+":'"+BeginTime+"':"+Priority;
            //RunnerID+","+ID+",'"+BeginTimeString+"',"+ExpectNum+",'"+ExpectDate+"',"+priority+","+State
            db.sql("replace into PlanList(RunnerID,ID,ExpectNum,ExpectDate,BeginTime,Priority,State) values("+spilt[1]+","+spilt[3]+","+0+")");
        }
    }

	public void delPeroid(int ID){
        db.sql("Delete from PeroidList where ID="+ID);
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
	public int getRunnerID(){return RunnerID;}
	/**??????????????????????//
	 * 
	 * @return Vector<Task>
	 */
	public Vector<Task> getTodayOptionalTask(){
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Vector<Task> vector=getListByDay(df.format(date));
        Log.e("getTodayOptionalTask", vector.size() + " "+df.format(date));
		return vector;
	}
	
	/**??????????????//
	 * 
	 * @return Vector<Task>
	 */
	public Vector<IDList> getAllList(){
		return db.FindIDList(IDList.class);
	}
	public IDList getIDListByID(int ID){return db.FindIDListByID(IDList.class,ID);}
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
    public Task getTaskByRunnerId(int RunnerID) {
        return db.FindTaskByRunnerID(Task.class, RunnerID);
    }
	/**????????????//
	 * 
	 * @param task ????
	 */
	public void setTask(Task task){
		String[] strings=task.get().split(":");	
		db.updatePlanList(strings[2]+","+strings[1]+","+strings[3],0);
		RunnerID++;
	}


}
