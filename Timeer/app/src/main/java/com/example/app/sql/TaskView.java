package com.example.app.sql;

import android.content.Context;
import java.util.Date;
import java.util.Vector;


public class TaskView {
    private static TaskView taskView=null;
	private int CurrentID;
	Date date=new Date();
    InitDb db=null;
    /**
     *     get single instance,if null ,this method will create the instance ,
     *     if not ,this method will return the existed instance
     */
    public static TaskView getInstance(Context context){
        if(taskView==null){
            taskView=new TaskView(context);
            return taskView;
        }
        else{
            return taskView;
        }
    }

    /**
     * *constructed function*
     * @param context
     */
	private TaskView(Context context) {
        db= InitDb.getInstance(context);
		CurrentID=db.queryDb.queryID();
	}

	/**
	 * get Task list depend on day
	 * @param Day centain date
	 * @return Vector<Task>
	 */

	public Vector<Task> getListByDay(String Day){return db.queryDb.FindTaskByDay(Day);}

    /**
     * get today Task list
     * *
     * @return
     */
    public Vector<Task> getTodayOptionalTask(){ return getListByDay(db.DAY); }

    /**
     *get the available ID
     * @return ID
     */
    public int getCurrentID(){
        return CurrentID;
    }


	/**
	 *add Task to the sql
	 */
	public void addTask(String Name,int ExpectNum,String ExpectDate,String NoteString,int Priority){
        Task task = new Task();
        int RunnerID=-1;//no need
        task.set(Name, CurrentID,ExpectNum, ExpectDate, NoteString,RunnerID, Priority);
        String init=",0,0,0";                               //Initial ActualNum/InnerInturrptTimes/OuterInturruptTimes
        task.isNewPlan(true);
		db.updateDb.updateIDList(task.getForIDList());
		db.updateDb.updatePlanList(task.getForPlanListNoState()+init,0);
		CurrentID++;
	}

    /**
     * set a task to today list
     * @param task
     */
    public void setTask(Task task){
        String init=",0,0,0";                               //Initial ActualNum/InnerInturrptTimes/OuterInturruptTimes
        task.isNewPlan(true);
        db.updateDb.updatePlanList(task.getForPlanListNoState()+init, 0);
    }
	/**
	 * Add PeroidTask
	 * @param
	 */
    public PeroidTask addPeroidTask(String Name,int ExpectNum,String ExpectDate,String NoteString,int Kind,int Priority){
            int RunnerID=-1;//no need
            PeroidTask pTask=new PeroidTask();
            pTask.isNewPlan(true);
            pTask.set(RunnerID,Name, CurrentID,ExpectNum, ExpectDate,NoteString, Kind,Priority);
            addPeriodTaskToLists(pTask);
            return pTask;
    }

    /**
     * Set Task as PeroidTask
     * @param RunnerID
     * @param ExpectNum
     * @param ExpectDate
     * @param Kind
     * @param Priority
     * @param Note
     * @return
     */
    public boolean setPeroidTask(int RunnerID,int ExpectNum,String ExpectDate,long Kind,int Priority,String Note){
        Task task=db.queryDb.FindTaskByRunnerID(RunnerID);
        if(task!=null){
            PeroidTask pTask=new PeroidTask();
            pTask.set(RunnerID,task.getName(),task.getID(),ExpectNum,ExpectDate,Note,Kind,Priority);
            sedPeriodTaskToLists(pTask);
            return true;
        }
        return false;
    }

    /**
     * Help to add PeroidTask to sql database
     * @param a
     */
    private void addPeriodTaskToLists(PeroidTask a){
        String init=",0,0,0";                               //Initial ActualNum/InnerInturrptTimes/OuterInturruptTimes
        db.updateDb.updateIDList(a.getForIDList());
        //1:infrom that Task's ID is a new ID,will be ingored by InitDb.tidePlanTask
        db.updateDb.updatePlanList(a.getForPlanListNoState()+init,1);
        db.updateDb.updatePeroidList(a.getForPeroidList());
        CurrentID++;
    }

    /**
     * Help to set Task as PeroidTask to sql database
     * @param peroidTask
     */
    private void sedPeriodTaskToLists(PeroidTask peroidTask){
        String init=",0,0,0";                               //Initial ActualNum/InnerInturrptTimes/OuterInturruptTimes
        db.updateDb.updatePeroidList(peroidTask.getForPeroidList());
    }

    /**
     * Delete Task
     * @param RunnerID
     * @param ID
     */
    public void delTask(int RunnerID,int ID){
        db.updateDb.delTask(RunnerID,ID);
    }

    /**
     * Delete the Peroid feature from Task
     * @param ID
     */
	public void delPeroid(int ID){
        db.updateDb.delPeroid(ID);
    }

	/**
	 *  get the list of PeroidTask
	 * @return Vector<PeroidTask>
	 */
	public Vector<PeroidTask> getPeriodTask(){return db.queryDb.FindPeroidTaskByToday();}

    /**
     *  get the list of Task order by sort type
     * @param kind:KindEnum  type:enum
     *            Name_ASC(0),Name_DESC(1),ID_ASC(2),ID_DESC(3),ExpectNum_ASC(4),
    ExpectNum_DESC(5),ExpectDate_ASC(6),ExpectDate_DESC(7);
     * @return
     */
    public Vector<Task> getListByOrder(KindEnum kind){ return db.queryDb.FindTaskByStyle(kind);}

    /**
     * get the list of Task
     * @param kind
     * @return
     */
    public Vector<Task> getAllList(KindEnum kind){return db.queryDb.FindTaskByStyle(KindEnum.NULL);}
	/**
	 *  get the list of Task according to ID
	 * @param id ????ID
	 * @return Vector<Task>
	 */
	public Vector<Task> getTaskById(int id){
		return db.queryDb.FindTaskByID(id);
	}

    /**
     * get Task according to RunnerID
     * @param RunnerID
     * @return
     */
    public Task getTaskByRunnerId(int RunnerID) { return db.queryDb.FindTaskByRunnerID(RunnerID);}
	/**
	 *  get the list of PeroidTask according to ID
	 * @return Vector<Task>
	 */
	public Vector<PeroidTask> getPeriodTaskById(int id){return db.queryDb.FindPeroidTaskByID(id);}
    /**
     *  get the list of finish Task according to Day
     * @return Vector<Task>
     */
    public Vector<TodayTask> getFinishTaskByDay(String Day){return db.queryDb.FindFinshTaskByDay(Day);}
    /**
     *  get the list of finish Task
     * @return Vector<Task>
     */
    public Vector<TodayTask> getFinishTask(){return db.queryDb.FindFinshTask();}

}
