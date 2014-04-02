package com.mycompany.myapp;


import java.util.Date;
import java.util.Vector;
import android.content.Context;

public class TaskView {
	private int CurrentID;
	private FindDb db=null;
	private int RunnerID;
	
	/**构造函数
	 * 
	 * @param context 由当前上下文
	 */
	public TaskView(Context context) {
		db=new FindDb(context);
		RunnerID=db.queryPlanList();
		CurrentID=db.queryIDList();
	}
	
	/**根据时间获取Task表
	 * 
	 * @param Day 时间
	 * @return Vector<Task>
	 */
	public Vector<Task> getListByDay(String Day){
		Vector<Task> vector=db.FindTaskByDay(Task.class,Day);
		return vector;
	}
	
	
	
	/**根据升序降序类型选择Task表的形式并获取
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
	
	/**添加任务到数据库
	 * 
	 * @param a Task实例
	 */
	public void addTask(Task a){
		String[] strings=a.get().split(":");
		db.updateIDList(strings[0]);
		//RunnerID+","+ID+",'"+BeginTimeString+"',"+ExpectNum+",'"+ExpectTimeString+"',"+priority
		CurrentID++;
	}
	
	/**添加周期任务到数据库
	 * 
	 * @param a 周期任务
	 */
	public void addPeriodTask(PeroidTask a){
		String[] strings=a.get().split(":");
		db.updatePeroidList(strings[2]);
	}
	
	/**获取周期任务的数据库
	 * 
	 * @return Vector<PeroidTask>
	 */
	public Vector<PeroidTask> getPeriodTask(){
		Vector<PeroidTask> vector=db.FindPeroidTask(PeroidTask.class);
		return vector;
	}
	
	/**获取下一个新ID
	 * 
	 * @return ID
	 */
	public int getCurrentID(){
		return CurrentID;
	}
	
	/**获取今日可用的任务列表
	 * 
	 * @return Vector<Task>
	 */
	public Vector<Task> getTodayOptionalTask(){
		Vector<Task> vector=getListByDay(new Date().getDate()+"");
		return vector;
	}
	
	/**获取整张任务表
	 * 
	 * @return Vector<Task>
	 */
	public Vector<Task> getAllList(){
		return db.FindTask(Task.class);
	}
	
	/**获取指定ID对应的任务列表
	 * 
	 * @param id 指定ID
	 * @return Vector<Task>
	 */
	public Vector<Task> getTaskById(int id){
		return db.FindTaskByID(Task.class, id);
	}
	
	/**获取指定ID对应的周期任务列表
	 * 
	 * @param id 指定ID
	 * @return Vector<Task>
	 */
	public Vector<PeroidTask> getPeriodTaskById(int id){
		return db.FindPeroidTaskByID(PeroidTask.class, id);
	}
	
	/**设置任务列表
	 * 
	 * @param task 任务
	 */
	public void setTask(Task task){
		String[] strings=task.get().split(":");	
		db.updatePlanList(strings[1],RunnerID);
		RunnerID++;
	}
	
}
