package com.mycompany.myapp;


import java.util.Date;
import java.util.Vector;
import android.content.Context;

public class TaskView {
	private int CurrentID;
	private FindDb db=null;
	private int RunnerID;
	
	/**���캯��
	 * 
	 * @param context �ɵ�ǰ������
	 */
	public TaskView(Context context) {
		db=new FindDb(context);
		RunnerID=db.queryRunnerID();
		CurrentID=db.queryID();
	}
	
	/**����ʱ���ȡTask��
	 * 
	 * @param Day ʱ��
	 * @return Vector<Task>
	 */
	public Vector<Task> getListByDay(String Day){
		Vector<Task> vector=db.FindTaskByDay(Task.class,Day);
		return vector;
	}
	
	
	
	/**��������������ѡ��Task�����ʽ����ȡ
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
	
	/**����������ݿ�
	 * 
	 * @param a Taskʵ��
	 */
	public void addTask(Task a){
		String[] strings=a.get().split(":");
		db.updateIDList(strings[0]);
		db.updatePlanList(strings[1], RunnerID);
		//RunnerID+","+ID+",'"+BeginTimeString+"',"+ExpectNum+",'"+ExpectTimeString+"',"+priority
		CurrentID++;
		RunnerID++;
	}
	
	/**��������������ݿ�
	 * 
	 * @param a ��������
	 */
	public void addPeriodTask(PeroidTask a){
		String[] strings=a.get().split(":");
		db.updateIDList(strings[0]);
		db.updatePlanList(strings[1], RunnerID);
		db.updatePeroidList(strings[2]);
		RunnerID++;
	}
	
	/**��ȡ������������ݿ�
	 * 
	 * @return Vector<PeroidTask>
	 */
	public Vector<PeroidTask> getPeriodTask(){
		Vector<PeroidTask> vector=db.FindPeroidTask(PeroidTask.class);
		return vector;
	}
	
	/**��ȡ��һ����ID
	 * 
	 * @return ID
	 */
	public int getCurrentID(){
		return CurrentID;
	}
	
	/**��ȡ���տ��õ������б�
	 * 
	 * @return Vector<Task>
	 */
	public Vector<Task> getTodayOptionalTask(){
		Vector<Task> vector=getListByDay(new Date().getDate()+"");
		return vector;
	}
	
	/**��ȡ���������
	 * 
	 * @return Vector<Task>
	 */
	public Vector<Task> getAllList(){
		return db.FindTask(Task.class);
	}
	
	/**��ȡָ��ID��Ӧ�������б�
	 * 
	 * @param id ָ��ID
	 * @return Vector<Task>
	 */
	public Vector<Task> getTaskById(int id){
		return db.FindTaskByID(Task.class, id);
	}
	
	/**��ȡָ��ID��Ӧ�����������б�
	 * 
	 * @param id ָ��ID
	 * @return Vector<Task>
	 */
	public Vector<PeroidTask> getPeriodTaskById(int id){
		return db.FindPeroidTaskByID(PeroidTask.class, id);
	}
	
	/**���������б�
	 * 
	 * @param task ����
	 */
	public void setTask(Task task){
		String[] strings=task.get().split(":");	
		db.updatePlanList(strings[1],RunnerID);
		RunnerID++;
	}
	
}
