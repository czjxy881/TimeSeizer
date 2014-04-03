package com.mycompany.myapp;

import java.util.Vector;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;



public class FindDb {
	
	/*** 属性 ***/
	private SQLiteDatabase db=null;
	private SQLiteHelper sql=null;


	public FindDb(Context context) {
		sql=new SQLiteHelper(context, "sqlite");
		db=sql.getWritableDatabase();
		createTable();

	}
	
	public void sql(String s){
		db.execSQL(s);
	}
	public int queryRunnerID(){
		int i;
		Cursor cursor=db.query("PlanList", null, null, null, null, null, null);
		if(cursor.moveToNext()!=true)return 0;
		else{
			do{
				i=cursor.getInt(cursor.getColumnIndex("RunnerID"));
			}while(cursor.moveToNext());
			return i+1;
		}
	}
	public int queryID() {
		int i;
		Cursor cursor=db.query("IDList", null, null, null, null, null, null);
		if(cursor.moveToNext()!=true)return 0;
		else{
			do{
				i=cursor.getInt(cursor.getColumnIndex("ID"));
			}while(cursor.moveToNext());
			return i+1;
		}
	}
	public boolean queryNameExist(String Name) {
		Cursor cursor=db.query("IDList", null, "Name=?", new String[]{Name} , null, null, null);
		return cursor.moveToNext();
	}

	private void createTable(){
		db.execSQL("Create table if not exists IDList(ID int primary key,Name varchar(30),Note varchar(100))");
		db.execSQL("Create table if not exists FinishList(RunnerID int,ID int references IDList(ID),FinishDate date,FinishTime time,ActualNum int,InnerInturrptTimes int,OuterInturrptTimes int,primary key(ID,FinishDate,FinishTime))");
		db.execSQL("Create table if not exists PlanList(RunnerID int primary key,ID int references IDList(ID),BeginTime time,ExpectNum int,ExpectTime time,priority int)");
		db.execSQL("Create table if not exists DailyList(Date date primary key,Summary char(1),ActualNum int,RestTime int)");
		db.execSQL("Create table if not exists PeroidList(ID int primary key references IDList(ID),Kind bigint)");
		
		
		db.execSQL("Create view if not exists FinishTask as select IDList.*,FinishList.* from IDList,FinishList where IDList.ID=FinishList.ID");
		db.execSQL("Create view if not exists PeroidTask as select IDList.*,PeroidList.* from IDList,PeroidList where IDList.ID=PeroidList.ID");
		db.execSQL("Create view if not exists DailySummary as select IDList.*,FinishList.*,DailyList.* from IDlist,FinishList,DailyList where IDList.ID=FinishList.ID and FinishList.FinishDate=DailyList.Date");
		db.execSQL("Create view if not exists PlanTask as select IDList.*,PlanList.* from IDLIst,PlanList where IDLIst.ID=PlanList.ID");
	}
	
	/*** update方法  -->replace 主键存在时覆盖***/
	public void updatePlanList(String string,int RunnerID){
		String s=string;		
		if(s.equals(null))
			Log.e("updatePlanList", "s==null");
		else {
			db.execSQL("replace into PlanList(RunnerID,ID,ExpectNum,ExpectDate) values("+RunnerID+","+s+")");			
		}
	}	
	public void updateIDList(String string){
		String s=string;		
		if(s.equals(null))
			Log.e("updateIDList", "s==null");
		else {
			db.execSQL("replace into IDList(Name,ID,Note) values("+s+")");			
		}
	}	
	public void updatePeroidList(String string){
		String s=string;		
		if(s.equals(null))
			Log.e("updatePeroidList", "s==null");
		else {
			db.execSQL("replace into PeroidList(ID,Kind) values("+s+")");			
		}
	}	
	public void updateDailyList(DailyList list){
		String s=list.get();		
		if(s.equals(null))
			Log.e("updateDailyList", "s==null");
		else {
			db.execSQL("replace into updateDailyList(Date,Summary,ActualNum,RestTime) values("+s+")");			
		}
	}	

	public DailyList FindDailyListNew(Class<DailyList> list){
		DailyList dailyList=new DailyList();
		Cursor cursor=db.query("DailyList",null,null,null,null, null, "Date desc");
		if(cursor.moveToNext()){
			dailyList=getDailyListEntity(cursor, list);
		}
		return dailyList;
	}
 	private DailyList getDailyListEntity(Cursor cursor, Class<DailyList> list) {
 		DailyList object=new DailyList();		
		String Date=cursor.getString(cursor.getColumnIndex("Date"));
		int ActualNum=cursor.getInt(cursor.getColumnIndex("ActualNum"));
		int RestTime=cursor.getInt(cursor.getColumnIndex("RestTime"));
		String Summary=cursor.getString(cursor.getColumnIndex("Summary"));
		int WorkTime=cursor.getInt(cursor.getColumnIndex("WorkTime"));
		int LongRestTime=cursor.getInt(cursor.getColumnIndex("LongRestTime"));

		object.set(Date, Summary, ActualNum, RestTime,WorkTime,LongRestTime);
		return object;
	}
	public IDList FindIDListByID(Class<IDList> list, int iD) {
		IDList iDList = null;
		Cursor cursor=db.query("IDList",null,
				null,null,null, null, null);
		if(cursor.moveToNext()){
			iDList=getIDListEntity(cursor);
		}
		return iDList;	
	}
	private IDList getIDListEntity(Cursor cursor) {
		IDList iDList=new IDList();
		String Name=cursor.getString(cursor.getColumnIndex("Name"));
		int ID=cursor.getInt(cursor.getColumnIndex("ID"));
		String Note=cursor.getString(cursor.getColumnIndex("Note"));
		iDList.set(ID, Name, Note);
		return iDList;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Vector<Task> FindTask(Class<Task> list) {
		Vector<Task> vector=new Vector<Task>();
		Cursor cursor=db.query("PlanTask",new String[]{"Name","ID","ExpectNum","ExpectDate"},
				null,null,null, null, null);
		while(cursor.moveToNext()){
			vector.add(getTaskEntity(cursor, list));
		}
		return vector;	
	}
	public Vector<Task> FindTaskByID(Class<Task> list,int ID){
		Vector<Task> vector=new Vector<Task>();
		Cursor cursor=db.query("PlanTask",new String[]{"Name","ID","ExpectNum","ExpectDate"},
				"ID=?",new String[]{""+ID},null, null, null);
		while(cursor.moveToNext()){
			vector.add(getTaskEntity(cursor, list));
		}
		return vector;		
	}
	public Vector<Task> FindTaskByDay(Class<Task> list, String Day) {
		Vector<Task> vector=new Vector<Task>();
		Cursor cursor=db.query("PlanTask",new String[]{"Name","ID","ExpectNum","ExpectDate"},
				"ExpectDate=?",new String[]{Day},null, null, null);
		while(cursor.moveToNext()){
			vector.add(getTaskEntity(cursor, list));
		}
		return vector;	
	}
	public Vector<Task> FindTaskByStyle(Class<Task> list,int kind) {
		Vector<Task> vector;
		String string=null;
		if(kind==0){//Name ASC
			string="Name ASC";	
		}
		else if(kind==1){//Name DESC
			string="Name DESC";
		}
		if(kind==2){//ID ASC
			string="ID ASC";	
		}
		else if(kind==3){//ID DESC
			string="ID DESC";
		}
		else if(kind==4){//ExpectNum ASC
			string="ExpectNum ASC";
		}
		else if(kind==5){//ExpectNum DESC
			string="ExpectNum DESC";
		}
		else if(kind==6){//ExpectDate ASC
			string="ExpectDate ASC";
		}
		else if(kind==7){//ExpectDate DESC
			string="ExpectDate DESC";
		}
		vector=new Vector<Task>();
		Cursor cursor=db.query("PlanTask",new String[]{"Name","ID","ExpectNum","ExpectDate"},
				null,null,null, null,string);
		while(cursor.moveToNext()){
			vector.add(getTaskEntity(cursor, list));
		}
		return vector;
	}

	private Task getTaskEntity(Cursor cursor, Class<Task> list) {
		Task object=new Task();		
		String Name=cursor.getString(cursor.getColumnIndex("Name"));
		int ID=cursor.getInt(cursor.getColumnIndex("ID"));
		int ExpectNum=cursor.getInt(cursor.getColumnIndex("ExpectNum"));
		String ExpectDate=cursor.getString(cursor.getColumnIndex("ExpectDate"));
		String NoteString=cursor.getString(cursor.getColumnIndex("NoteString"));
		object.set(Name, ID, ExpectNum, ExpectDate,NoteString);
		return object;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Vector<PeroidTask> FindPeroidTaskByID(Class<PeroidTask> list,int ID){
		Vector<PeroidTask> vector=new Vector<PeroidTask>();
		Cursor cursor=db.query("PeroidTask",new String[]{"Name","ID","ExpectNum","ExpectDate","Kind"},
				"ID=?",new String[]{""+ID},null, null, null);
		while(cursor.moveToNext()){
			vector.add(getPeroidTaskEntity(cursor, list));
		}
		return vector;		
	}
	public Vector<PeroidTask> FindPeroidTask(Class<PeroidTask> list){
		Vector<PeroidTask> vector=new Vector<PeroidTask>();
		Cursor cursor=db.query("PeroidTask",new String[]{"Name","ID","ExpectNum","ExpectDate","Kind"},
				null,null,null, null, null);
		while(cursor.moveToNext()){
			vector.add(getPeroidTaskEntity(cursor, list));
		}
		return vector;		
	}
	
	private PeroidTask getPeroidTaskEntity(Cursor cursor, Class<PeroidTask> list) {
		PeroidTask object=new PeroidTask();
		
		String Name=cursor.getString(cursor.getColumnIndex("Name"));
		int ID=cursor.getInt(cursor.getColumnIndex("ID"));;
		int ExpectNum=cursor.getInt(cursor.getColumnIndex("ExpectNum"));;
		String ExpectDate=cursor.getString(cursor.getColumnIndex("ExpectDate"));
		long Kind=cursor.getLong(cursor.getColumnIndex("Kind"));
		object.set(Name, ID, ExpectNum, ExpectDate,Kind);
		return object;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Vector<TodayTask> FindTodayTaskByID(Class<TodayTask> list,int ID){
		Vector<TodayTask> vector=new Vector<TodayTask>();
		Cursor cursor=db.query("TodayTask",new String[]{"Name","ID","ExpectNum","ExpectDate"},
				"ID=?",new String[]{""+ID},null, null, null);
		while(cursor.moveToNext()){
			vector.add(getTodayTaskEntity(cursor, list));
		}
		return vector;		
	}
	private TodayTask getTodayTaskEntity(Cursor cursor, Class<TodayTask> list) {
		TodayTask object=new TodayTask();
		
		String Name=cursor.getString(cursor.getColumnIndex("Name"));
		int ID=cursor.getInt(cursor.getColumnIndex("ID"));
		int ExpectNum=cursor.getInt(cursor.getColumnIndex("ExpectNum"));
		String ExpectDate=cursor.getString(cursor.getColumnIndex("ExpectDate"));
		int ActualNum=cursor.getInt(cursor.getColumnIndex("ActualNum"));
		int InnerInturrptTimes=cursor.getInt(cursor.getColumnIndex("InnerInturrptTimes"));
		int OuterInturrptTimes=cursor.getInt(cursor.getColumnIndex("OuterInturrptTimes"));
		int State=cursor.getInt(cursor.getColumnIndex("State"));
		int RunnerID=cursor.getInt(cursor.getColumnIndex("RunnerID"));
		
		object.set(ActualNum, InnerInturrptTimes, OuterInturrptTimes, RunnerID, Name, ID, ExpectNum, ExpectDate,State);
		return object;
	}








	


}

