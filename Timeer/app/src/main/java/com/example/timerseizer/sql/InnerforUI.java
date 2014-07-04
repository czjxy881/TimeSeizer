package com.example.timerseizer.sql;

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

    private Vector<Task> TaskVector;
    private Vector<TodayTask> TodayTaskVector;
    private Vector<PeroidTask> PeroidTaskVector;
    private Vector<TodayTask> DoneTaskVector;
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
		todayList=TodayList.getInstance(taskView);
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


    public TodayTask findTodayTaskByRunnerID(int RunnerID){
        return db.queryDb.findTodayTaskByRunnerID(RunnerID);
    }

    /**
     * Delete Task from PeroidList
     * when delete,location infromed by ui
     * @param location :item's  of peroidTask List
     */
    public void clickDeletePeroid(int location){
        int ID= PeroidTaskVector.get(location).getID();
        taskView.delPeroid(ID);
    }

    /**
     * Delete Task from Today list,requiring Task's location in TodayTaskVector
     * @param location
     */
    public void clickDeleteTodayTask(int location){
        int RunnerID= TodayTaskVector.get(location).getRunnerID();
        int ID= TodayTaskVector.get(location).getID();

        taskView.delTask(RunnerID,ID);
    }
    /**
     * Delete Task from Task list,requiring Task's location in TaskVector
     * @param location
     */
    public void clickDeleteTask(int location){
        int RunnerID= TaskVector.get(location).getRunnerID();
        int ID= TaskVector.get(location).getID();
        taskView.delTask(RunnerID,ID);
    }
    /**
     *    Add new peroidtask ..
      */
	public void clickAddPeroidNewTask(String Name,int ExpectNum,String ExpectDate,String NoteString,int Kind,int Priority){
            PeroidTask peroidTask=taskView.addPeroidTask(Name, ExpectNum, ExpectDate, NoteString, Kind, Priority);
            PeroidTaskVector.add(peroidTask);
    }
    /**
     *  Change normal Task to PeroidTask,requiring Task's location in TaskVector and the cycle type
     *   location
     *   kind
     */
	public boolean clickSetPeroidTask(int location,long Kind){
            Task task= TaskVector.get(location);
			return taskView.setPeroidTask(task.getRunnerID(),task.getExpectNum(), task.getExpectDate(),Kind,1,task.getNoteString());
	}

    /**
     * 从任务列表添加任务到今日任务
     * @param location 任务列表的列
     */
    public void addToTodayFromAllList(int location){
        Task task= TaskVector.get(location);
        task.setExpectDate(DAY);
        taskView.addTask(task);
        //todayList.setTodayList();
    }

    /**
     * 从已完成列表添加任务到今日任务
     * @param location 任务列表的列
     */
    public void addToTodayFromDoneList(int location){
        Task task=DoneTaskVector.get(location);
        task.setExpectDate(DAY);
        taskView.addTask(task);
        //todayList.setTodayList();
    }
    /**
     * 从周期列表添加任务到今日任务
     * @param location 任务列表的列
     */
    public void addToTodayFromPeriodList(int location){
        Task task=PeroidTaskVector.get(location);
        task.setExpectDate(DAY);
        taskView.addTask(task);
       // todayList.setTodayList();
    }


    /**
     * Show Task ,and save in the TaskVector at the same time.
     * @return
     */
	public Vector<Task> showTask(){return (TaskVector =taskView.getAllList(KindEnum.ExpectDate_ASC));}

    /**
     * Show Task depend on type
     * KindEnum :Enum
    Name_ASC(0),Name_DESC(1),ID_ASC(2),ID_DESC(3),ExpectNum_ASC(4),
    ExpectNum_DESC(5),ExpectDate_ASC(6),ExpectDate_DESC(7);
    **/
    public Vector<Task> showTaskByOrder(KindEnum Kind){return (TaskVector =taskView.getListByOrder(Kind));}

    /**
     * Show PeroidTask,and save in the PeroidTaskVector at the same time.
     * @return
     */
	public Vector<PeroidTask> showPeroidTask(){return (PeroidTaskVector =taskView.getPeriodTask());}

    /**
     *     Show TodayList ,and save in the TodayTaskVector at the same time
     */
	public Vector<TodayTask> showTodayList(){
		return (TodayTaskVector =TodayList.getInstance(taskView).getTodayList());
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
        return DoneTaskVector=taskView.getFinishTask();
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
