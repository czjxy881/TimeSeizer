package com.example.app.sql;

import android.content.Context;

import java.util.Vector;

public class InnerforUI {
    private static InnerforUI in=null;
    private static InitDb db=null;
    public String DAY=null;
    public String TIME=null;
    private TodayList todayList=null;
    private TaskView taskView=null;
    private Today today=null;

    private Vector<Task> taskVector;
    private Vector<TodayTask> todayTaskVector;
    private Vector<PeroidTask> peroidTaskVector;
    private static int UNDEFINE=-1;
    private static int NO_NEED=-2;

    //get single instance
    public static InnerforUI getInstance(){
        return in;
    }

    public static InnerforUI getInstance(Context context){
        if(in==null){
            in=new InnerforUI(context);
            return in;
        }else{
            return in;
        }
    }
	private InnerforUI(Context context){
        taskView=TaskView.getInstance(context);
        db=taskView.db;
		todayList=new TodayList(taskView);
		today=initialToday(context);
        DAY=db.DAY;
	}

	//initial Today          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	private Today initialToday(Context context) {
		Today today=new Today(db);
		TIME=db.TIME;
		today.setStartTime(TIME);
		today.setLastingTimes(0);
		today.setSummary(null);
		return today;
	}


	//when set today information..          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	public void setTodayRestTime(int RestTime){
		today.setRestTime(RestTime);
	}
    //                                                ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	public void setTodayWorkTime(int WorkTime){
		today.setWorkTime(WorkTime);
	}
    //                                                        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	public void setTodayLongRestTime(int LongRestTIme){
		today.setLongRestTime(LongRestTIme);
	}
	//when click add task ..
	//1.new task..    ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

    /**
     *task will add into Task list,which include data from ui
     * @param Name
     * @param ExpectNum
     * @param ExpectDate
     * @param NoteString
     * @param Priority
     */
	public void clickAddTask(String Name,int ExpectNum,String ExpectDate,String NoteString,int Priority){
            int ID =UNDEFINE;
            int RunnerID =NO_NEED;
            taskView.addTask(Name,ExpectNum,ExpectDate,NoteString,Priority);
            todayList.setTodayList();////////////////////
	}

    /**
     * Delete Task from PeroidList
     * when delete,location infromed by ui
     * @param location :item's  of peroidTask List
     */
    public void clickDeletePeroid(int location){
        int ID=peroidTaskVector.get(location).getID();
        taskView.delPeroid(ID);
    }

    /**
     * Delete Task from Today list,requiring Task's location in todayTaskVector
     * @param location
     */
    public void clickDeleteTodayTask(int location){
        int RunnerID=todayTaskVector.get(location).getRunnerID();
        int ID=todayTaskVector.get(location).getID();

        taskView.delTask(RunnerID,ID);
    }
    /**
     * Delete Task from Task list,requiring Task's location in taskVector
     * @param location
     */
    public void clickDeleteTask(int location){
        int RunnerID=taskVector.get(location).getRunnerID();
        int ID=taskVector.get(location).getID();
        taskView.delTask(RunnerID,ID);
    }

    /**
     *    Add new peroidtask ..
      */
	public void clickAddPeroidNewTask(String Name,int ExpectNum,String ExpectDate,String NoteString,int Kind,int Priority){
            PeroidTask peroidTask=taskView.addPeroidTask(Name, ExpectNum, ExpectDate, NoteString, Kind, Priority);
            peroidTaskVector.add(peroidTask);
    }
    /**
     *  Change normal Task to PeroidTask,requiring Task's location in taskVector and the cycle type
     *   location
     *   kind
     */
	public boolean clickSetPeroidTask(int location,long Kind){
            Task task=taskVector.get(location);
			return taskView.setPeroidTask(task.getRunnerID(),task.getExpectNum(), task.getExpectDate(),Kind,1,task.getNoteString());
	}

    /**
     * old Task add to Today
     * @param location
     */
    public void clickAddToToday(int location){
        Task task=taskVector.get(location);
        task.setExpectDate(DAY);
        taskView.setTask(task);
        todayList.setTodayList();
    }
    /**
     * Show Task ,and save in the taskVector at the same time.
     * @return
     */
	public Vector<Task> showTask(){return (taskVector=taskView.getAllList(KindEnum.NULL));}

    /**
     * Show Task depend on type
     * KindEnum :Enum
    Name_ASC(0),Name_DESC(1),ID_ASC(2),ID_DESC(3),ExpectNum_ASC(4),
    ExpectNum_DESC(5),ExpectDate_ASC(6),ExpectDate_DESC(7);
    **/
    public Vector<Task> showTaskByOrder(KindEnum Kind){return (taskVector=taskView.getListByOrder(Kind));}

    /**
     * Show PeroidTask,and save in the peroidTaskVector at the same time.
     * @return
     */
	public Vector<PeroidTask> showPeroidTask(){return (peroidTaskVector=taskView.getPeriodTask());}

    /**
     *     Show TodayList ,and save in the todayTaskVector at the same time
     */
	public Vector<TodayTask> showTodayList(){
		TodayList todayList=new TodayList(taskView);
		return (todayTaskVector=todayList.getTodayList());
	}

    /**
     *  show today finish-task list,and save in the todayFinishVector at the same time
     * @return
     */
    public Vector<TodayTask> showTodayFinish(){
        StateEnum State=StateEnum.FINISH;
        return todayList.getTaskByState(State);
    }
    /**
     * show finsh-task list depend on definite day,and ave in the
     */
    public Vector<TodayTask> showDayFinish(String Day){
        return taskView.getFinishTaskByDay(Day);
    }
    public Vector<TodayTask> showFinish(){
        return taskView.getFinishTask();
    }

    /**
     * set TodayList
     * @param taskView
     */
	public void setTodayList(TaskView taskView){todayList.setTodayList();}

    /**
     * set next Task
     */
    /*
    public TodayTask setNextTask(){
        TodayTask task=list.getNextTask();
        NowTask = task;
        CurrentState=false;
        return task;
    }
    */
    /**
     * set definite Task as next one
     * @param ID
     */
 //   public void setNextTaskByID(int ID){timer.setTaskByID(ID,todayList);}

    /**
     * start Task
     * @param ID
     */
 //   public void start(int ID){timer.start();}

    /**
     * abort current Task
     */
//	public void abort(){timer.stop();}

    /**
     *     show Today Information
     */
	public Today getToday(){return today;}

}
