package com.myapplication8.app.Back;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.renderscript.RenderScript;
import android.util.Log;



public class FindDb {
	
	/*** ???? ***/
	static SQLiteDatabase db=null;
	private SQLiteHelper sql=null;
	Date date=new Date();
	String day;

	public FindDb(Context context) {
		sql=new SQLiteHelper(context, "sqlite");
		db=sql.getWritableDatabase();
		createTable();
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        day=df.format(date);
        Log.e("day",day);
		tidePlanList();
	}
	
	private void tidePlanList() {
		Cursor cursor=db.query("PeroidTask", null, "ExpectDate<'"+day+"'",null, null, null, null);
       // cursor.moveToNext();
       // Log.e("cursor",cursor.getString(cursor.getColumnIndex("ExpectDate")));
		int ID,RunnerID,ExpectNum,Priority;
		String Date,newDate,BeginTime;
		while(cursor.moveToNext()){
			RunnerID=cursor.getInt(cursor.getColumnIndex("RunnerID"));
			ID=cursor.getInt(cursor.getColumnIndex("ID"));
			Date=cursor.getString(cursor.getColumnIndex("ExpectDate"));
			ExpectNum=cursor.getInt(cursor.getColumnIndex("ExpectNum"));
            BeginTime=null;
            Priority=cursor.getInt(cursor.getColumnIndex("Priority"));
            Log.e("beforehaha",Date);
			newDate=setNewDate(ID,Date);
            if(newDate!=null)
			updatePlanList(RunnerID+","+ID+","+ExpectNum+",'"+newDate+"','"+BeginTime+"',"+Priority ,0);
            Log.e("haha",newDate);
            //RunnerID,ID,ExpectNum,ExpectDate,Priority,State
		}
	}
	private String setNewDate(int ID,String date) {
		Vector<PeroidTask> peroidTask=FindPeroidTaskByID(PeroidTask.class, ID);
        if(peroidTask.isEmpty())return null;
		int Kind=peroidTask.get(0).getKind(),i=0;
		String SDate=date;
		Calendar c=Calendar.getInstance();
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");   
		while(SDate.compareTo(day)<0){
            String[] s=SDate.split("-");
            c.set(Integer.parseInt(s[0]),Integer.parseInt(s[1]),Integer.parseInt(s[2]));
			c.add(Calendar.DATE,Kind);
			Date d2=c.getTime(); 
			SDate=df.format(d2);
		}
        return SDate;
	}

	public void sql(String s){
		db.execSQL(s);
	}
	public int queryRunnerID(){
		int i,j=0;
		Cursor cursor=db.query("PlanList", null, null, null, null, null, null);
		if(cursor.moveToNext()!=true)return 0;
		else{
			do{
				i=cursor.getInt(cursor.getColumnIndex("RunnerID"));
                if(j<i)
                    j=i;
			}while(cursor.moveToNext());
            Log.e("queryRunnerID",j+1+"");
			return j+1;
		}
	}
	public int queryID() {
		int i,j=0;
		Cursor cursor=db.query("IDList", null, null, null, null, null, null);
		if(cursor.moveToNext()!=true)return 0;
		else{
			do{
				i=cursor.getInt(cursor.getColumnIndex("ID"));
                if(j<i)
                    j=i;
			}while(cursor.moveToNext());
            Log.e("queryID",j+1+"");
			return j+1;
		}
	}
	public boolean queryNameExist(String Name) {
		Cursor cursor=db.query("IDList", null, "Name=?", new String[]{Name} , null, null, null);
		return cursor.moveToNext();
	}

	private void createTable(){
//        db.execSQL("Drop table FinishList");
//         db.execSQL("Drop table PlanList");
//          db.execSQL("Drop table IDList");
//          db.execSQL("Drop table DailyList");
//          db.execSQL("Drop table PeroidList");
//        db.needUpgrade(1);
		db.execSQL("Create table if not exists IDList(ID int primary key,Name varchar(30),Note varchar(100))");
		db.execSQL("Create table if not exists FinishList(RunnerID int,ID int references IDList(ID),FinishDate String,FinishTime String,ActualNum int,InnerInturrptTimes int,OuterInturrptTimes int,primary key(ID,FinishDate,FinishTime))");

		db.execSQL("Create table if not exists PlanList(RunnerID int primary key,ID int references IDList(ID),BeginTime String,ExpectNum int,ExpectDate String,Priority int,State int)");

        db.execSQL("Create table if not exists DailyList(Date date primary key,Summary char(3),ActualNum int,RestTime int,WorkTime int,LongRestTime int)");
		db.execSQL("Create table if not exists PeroidList(ID int primary key references IDList(ID),Kind int)");
        db.execSQL("replace into IDList(ID,Name,Note) values(3,'workhard','haha')");
		db.execSQL("replace into IDList(ID,Name,Note) values(1,'cdy','haha')");
        db.execSQL("replace into IDList(ID,Name,Note) values(2,'cd','haha')");
       db.execSQL("replace into PlanList(RunnerID,ID,BeginTime,ExpectNum,ExpectDate,Priority,State) values(1,1,null,10,'2013-02-16',5,0)");
        db.execSQL("replace into PlanList(RunnerID,ID,BeginTime,ExpectNum,ExpectDate,Priority,State) values(2,2,null,10,'2014-04-10',5,0)");
        db.execSQL("replace into PeroidList(ID,Kind) values(1,1)");

		db.execSQL("Create view if not exists FinishTask as select IDList.*,FinishList.*,PlanList.* from IDList,FinishList,PlanList where IDList.ID=FinishList.ID and IDList.ID=PlanList.ID");
		db.execSQL("Create view if not exists PeroidTask as select IDList.*,PeroidList.*,PlanList.* from IDList,PeroidList,PlanList where IDList.ID=PeroidList.ID and IDList.ID=PlanList.ID");
		db.execSQL("Create view if not exists DailySummary as select IDList.*,FinishList.*,DailyList.* from IDlist,FinishList,DailyList where IDList.ID=FinishList.ID and FinishList.FinishDate=DailyList.Date");
		db.execSQL("Create view if not exists PlanTask as select IDList.*,PlanList.* from IDLIst,PlanList where IDLIst.ID=PlanList.ID");
	}
	
	/*** update????  -->replace ??????????????***/
	public void updatePlanList(String string,int State){
		String s=string;		
		if(s.equals(null))
			Log.e("updatePlanList", "s==null");
		else {
			db.execSQL("replace into PlanList(RunnerID,ID,ExpectNum,ExpectDate,BeginTime,Priority,State) values("+s+","+State+")");
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
			db.execSQL("replace into updateDailyList(Date,Summary,ActualNum,RestTime,WorkTime,LongRestTime) values("+s+")");
		}
	}	
	public void updateFinishList(Date date,TodayTask task){
		String[] s=task.get().split(":");		
		if(s.equals(null))
			Log.e("updateFinishList", "s==null");
		else {
            DateFormat df=new SimpleDateFormat("HH:mm:ss");
            String time=df.format(date);
			String dateString=day;
			String timeString=time;
			db.execSQL("replace into updateFinishList(RunnerID,ID,FinishDateString,FinishTimeString,ActualNum,InnerInturrptTimes,OuterInturrptTimes) " +
					"values("+s[1]+",'"+dateString+"','"+timeString+"',"+s[3]+")");			
		}
	}
	public DailyList FindDailyListNew(Class<DailyList> list){
		DailyList dailyList=null;
		Cursor cursor=db.query("DailyList",null,null,null,null, null, "Date desc");
		if(cursor.moveToNext()){
			dailyList=getDailyListEntity(cursor, list);
            Log.e("FindDailyListNew","moveToNext");
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
	public Vector<IDList> FindIDList(Class<IDList> list) {
		Vector<IDList> vector= new Vector<IDList>();
		Cursor cursor=db.query("IDList",null,
				null,null,null, null, null);
		while(cursor.moveToNext()){
			vector.add(getIDListEntity(cursor));
		}
		return vector;	
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
		Cursor cursor=db.query("PlanTask",null,
				null,null,null, null, null);
		while(cursor.moveToNext()){
			vector.add(getTaskEntity(cursor, list));
		}
		return vector;	
	}
	public Vector<Task> FindTaskByID(Class<Task> list,int ID){
		Vector<Task> vector=new Vector<Task>();
		Cursor cursor=db.query("PlanTask",null,
				"ID="+ID,null,null, null, null);
		while(cursor.moveToNext()){
			vector.add(getTaskEntity(cursor, list));
		}
		return vector;		
	}
	public Vector<Task> FindTaskByDay(Class<Task> list, String Day) {
		Vector<Task> vector=new Vector<Task>();
		Cursor cursor=db.query("PlanTask",null,
				"ExpectDate='"+Day+"'",null,null, null, null);
		while(cursor.moveToNext()){
			vector.add(getTaskEntity(cursor, list));
            Log.e("FindTaskByDay","moveToNext");
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
		Cursor cursor=db.query("PlanTask",null,
				null,null,null, null,string);
		while(cursor.moveToNext()){
			vector.add(getTaskEntity(cursor, list));
		}
		return vector;
	}
    public Task FindTaskByRunnerID(Class<Task> list, int RunnerID) {
        Vector<Task> vector=new Vector<Task>();
        Cursor cursor=db.query("PlanTask",null,
                "RunnerID="+RunnerID,null,null, null, null);
        if(cursor.moveToNext()){
            vector.add(getTaskEntity(cursor, list));
            Log.e("FindTaskByRunnerID","moveToNext");
            return vector.get(0);
        }
        else
            return null;

    }

	private Task getTaskEntity(Cursor cursor, Class<Task> list) {
		Task object=new Task();
        int RunnerID=cursor.getInt(cursor.getColumnIndex("RunnerID"));
		String Name=cursor.getString(cursor.getColumnIndex("Name"));
		int ID=cursor.getInt(cursor.getColumnIndex("ID"));
        String BeginTime=cursor.getString(cursor.getColumnIndex("BeginTime"));
		int ExpectNum=cursor.getInt(cursor.getColumnIndex("ExpectNum"));
		String ExpectDate=cursor.getString(cursor.getColumnIndex("ExpectDate"));
		String NoteString=cursor.getString(cursor.getColumnIndex("Note"));
        int Priority=cursor.getInt(cursor.getColumnIndex("Priority"));
		object.set(Name, ID,BeginTime, ExpectNum, ExpectDate,NoteString,RunnerID,Priority);
		return object;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Vector<PeroidTask> FindPeroidTaskByID(Class<PeroidTask> list,int ID){
		Vector<PeroidTask> vector=new Vector<PeroidTask>();
		Cursor cursor=db.query("PeroidTask",null,
				"ID=?",new String[]{""+ID},null, null, null);

		while(cursor.moveToNext()){
			vector.add(getPeroidTaskEntity(cursor, list));
		}
		return vector;		
	}
	public Vector<PeroidTask> FindPeroidTask(Class<PeroidTask> list){
		Vector<PeroidTask> vector=new Vector<PeroidTask>();
		Cursor cursor=db.query("PeroidTask",null,
				null,null,null, null, null);
		while(cursor.moveToNext()){
			vector.add(getPeroidTaskEntity(cursor, list));
		}
		return vector;		
	}
    public PeroidTask FindOnePeroidTask(Class<PeroidTask> list,int RunnerID){
        Vector<PeroidTask> vector=new Vector<PeroidTask>();
        Cursor cursor=db.query("PeroidTask",null,
                "RunnerID="+RunnerID,null,null, null, null);
        if(cursor.moveToNext()){
            vector.add(getPeroidTaskEntity(cursor, list));
            return vector.get(0);
        }
        else
            return null;
    }
	private PeroidTask getPeroidTaskEntity(Cursor cursor, Class<PeroidTask> list) {
		PeroidTask object=new PeroidTask();
		int RunnerID=cursor.getInt(cursor.getColumnIndex("RunnerID"));
		String Name=cursor.getString(cursor.getColumnIndex("Name"));
		int ID=cursor.getInt(cursor.getColumnIndex("ID"));
        String BeginTime=cursor.getString(cursor.getColumnIndex("BeginTime"));
		int ExpectNum=cursor.getInt(cursor.getColumnIndex("ExpectNum"));
		String ExpectDate=cursor.getString(cursor.getColumnIndex("ExpectDate"));
		int Kind=cursor.getInt(cursor.getColumnIndex("Kind"));
        int Priority=cursor.getInt(cursor.getColumnIndex("Priority"));
        String Note=cursor.getString(cursor.getColumnIndex("Note"));
		object.set(RunnerID,Name, ID,BeginTime,ExpectNum, ExpectDate,Note,Kind,Priority);
		return object;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Vector<TodayTask> FindTodayTaskByID(Class<TodayTask> list,int ID){
		Vector<TodayTask> vector=new Vector<TodayTask>();
		Cursor cursor=db.query("TodayTask",null,
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
		String Note=cursor.getString(cursor.getColumnIndex("Note"));
		object.set(ActualNum, InnerInturrptTimes, OuterInturrptTimes, RunnerID, Name, ID, ExpectNum, ExpectDate,State,Note);
		return object;
	}



}

