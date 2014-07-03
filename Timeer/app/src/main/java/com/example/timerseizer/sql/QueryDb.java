package com.example.timerseizer.sql;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.Vector;

public class QueryDb {
    private static QueryDb queryDb=null;
    private static SQLiteDatabase db=null;
    private SQLiteHelper sql=null;
    private String DAY=null;
    /**single Instance
     *
     * @param db
     * @param sql
     * @return
     */
    public static QueryDb getInstance(SQLiteDatabase db,SQLiteHelper sql,String DAY){
        if(queryDb==null){
            queryDb=new QueryDb(db,sql,DAY);
            return queryDb;
        }
        else{return queryDb; }
    }

    private QueryDb(SQLiteDatabase db,SQLiteHelper sql,String DAY) {
        this.sql=sql;
        this.db=db;
        this.DAY=DAY;
    }
    /**
     * query Current ID
     * @return
     */
    public int queryID() {
        Cursor cursor=db.query("IDList", null, null, null, null, null, null);
            return CursorToNum(cursor,"ID");
    }
    private int CursorToNum(Cursor cursor,String kind){
        int i,j=0;
        if(cursor.moveToNext()!=true)return 0;
        else {
            do {
                i = cursor.getInt(cursor.getColumnIndex(kind));
                if (j < i) j = i;
            } while (cursor.moveToNext());
        }
        return j+1;
    }

    /**
     * get Today Information
     * @return
     */
    public DailyClass findDailyListNew(){
        DailyClass dailyClass =null;
        Cursor cursor=db.query("DailyList",null,null,null,null, null, "Date desc");
        if(cursor.moveToNext()){
            dailyClass =getDailyListEntity(cursor);
        }
        return dailyClass;
    }
    private DailyClass getDailyListEntity(Cursor cursor) {
        DailyClass object=new DailyClass();
        String Date=cursor.getString(cursor.getColumnIndex("Date"));
        String BeginTime=cursor.getString(cursor.getColumnIndex("BeginTime"));
        int RestTime=cursor.getInt(cursor.getColumnIndex("RestTime"));
        String Summary=cursor.getString(cursor.getColumnIndex("Summary"));
        int WorkTime=cursor.getInt(cursor.getColumnIndex("WorkTime"));
        int LongRestTime=cursor.getInt(cursor.getColumnIndex("LongRestTime"));

        object.set(Date,BeginTime, Summary, RestTime,WorkTime,LongRestTime);
        return object;
    }

    /**
     * 根据Name和Note查询ID
     * @param Name 任务名
     * @param Note 任务备注
     * @return ID,如果没有返回-1
     */
    public int findTaskID(String Name,String Note){
        Cursor cursor=db.query("IDList",null,
                "Name='"+Name+"' and Note='"+Note+"'",null,null, null, null);
        if(cursor.moveToNext()==false)return -1;
        return cursor.getInt(cursor.getColumnIndex("ID"));
    }
    /**
     * get Task View by RunnerID
     * @return
     */
    public Vector<Task> findTaskByID(int ID){
        Cursor cursor=db.query("PlanTask",null,
                "ID="+ID,null,null, null, null);
        return cursorToTask(cursor);
    }

    /**
     * get Task View by day
     * @param Day
     * @return
     */
    public Vector<Task> findTaskByDay(String Day) {
        //TODO  检测设成<的可靠性
        Cursor cursor=db.query("PlanTask",null,
                "ExpectDate<='"+Day+"'",null,null, null,"ExpectDate");
        return cursorToTask(cursor);
    }

    /**
     * get Task View according to order type
     * @param kind :KindEnum
     * @return
     */
    public Vector<Task> findTaskByStyle(KindEnum kind) {
        String string=null;
        switch(kind) {
            case Name_ASC:string = "Name ASC"; break;
            case Name_DESC:string = "Name DESC";break;
            case ID_ASC:string = "ID ASC";break;
            case ID_DESC:string = "ID DESC";break;
            case ExpectNum_ASC: string = "ExpectNum ASC";break;
            case ExpectNum_DESC:string = "ExpectNum DESC";break;
            case ExpectDate_ASC:string = "ExpectDate ASC";break;
            case ExpectDate_DESC:string = "ExpectDate DESC";break;
            default:;
        }
        Cursor cursor=db.query("PlanTask",null,
                null,null,null, null,string);
        return cursorToTask(cursor);
    }
    /**
     * get Task by RunnerID
     * @param RunnerID
     * @return
     */
    public Task findTaskByRunnerID(int RunnerID) {
        Cursor cursor=db.query("PlanTask",null,
                "RunnerID="+RunnerID,null,null, null, null);
        Vector<Task> vector= cursorToTask(cursor);
        if(vector.isEmpty())return null;
        else return vector.get(0);
    }
    /**
     * help to get Task View by Cursor
     * @param cursor
     * @return
     */
    private Vector<Task> cursorToTask(Cursor cursor){
        Vector<Task> vector=new Vector<Task>();
        while(cursor.moveToNext()){
            vector.add(getTaskEntity(cursor));
        }
        return vector;
    }
    /**
     * help cursorToTask to get Task by Cursor
     * @param cursor
     * @return
     */
    private Task getTaskEntity(Cursor cursor) {
        Task object=new Task();
        int RunnerID=cursor.getInt(cursor.getColumnIndex("RunnerID"));
        String Name=cursor.getString(cursor.getColumnIndex("Name"));
        int ID=cursor.getInt(cursor.getColumnIndex("ID"));
        int ExpectNum=cursor.getInt(cursor.getColumnIndex("ExpectNum"));
        String ExpectDate=cursor.getString(cursor.getColumnIndex("ExpectDate"));
        String NoteString=cursor.getString(cursor.getColumnIndex("Note"));
        int Priority=cursor.getInt(cursor.getColumnIndex("Priority"));
        object.set(Name, ID,ExpectNum, ExpectDate,NoteString,RunnerID,Priority);
        return object;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Vector<PeroidTask> CursorToPeroidTask(Cursor cursor){
        Vector<PeroidTask> vector=new Vector<PeroidTask>();
        while(cursor.moveToNext()){
            vector.add(getPeroidTaskEntity(cursor));
        }
        return vector;
    }
    public Vector<PeroidTask> FindPeroidTask(){
        Cursor cursor=db.query("PeroidTask",null,
                null,null,null, null, null);
        return CursorToPeroidTask(cursor);
    }
    public Vector<PeroidTask> FindPeroidTaskByID(int ID){
        Cursor cursor=db.query("PeroidTask",null,
                "ID="+ID,null,null, null, null);
        return CursorToPeroidTask(cursor);
    }
    public Vector<PeroidTask> FindPeroidTaskByToday(){
        //TODO 设置周期性任务检测
        //Cursor cursor=db.query("PeroidTask",null,"ExpectDate<='"+DAY+"'",null,null, null, null);
        Cursor cursor=db.query("PeroidTask",null,null,null,null, null, "ExpectDate");
        return CursorToPeroidTask(cursor);
    }
    private PeroidTask getPeroidTaskEntity(Cursor cursor) {
        PeroidTask object=new PeroidTask();
        int RunnerID=cursor.getInt(cursor.getColumnIndex("RunnerID"));
        String Name=cursor.getString(cursor.getColumnIndex("Name"));
        int ID=cursor.getInt(cursor.getColumnIndex("ID"));
        int ExpectNum=cursor.getInt(cursor.getColumnIndex("ExpectNum"));
        String ExpectDate=cursor.getString(cursor.getColumnIndex("ExpectDate"));
        int Kind=cursor.getInt(cursor.getColumnIndex("Kind"));
        int Priority=cursor.getInt(cursor.getColumnIndex("Priority"));
        String Note=cursor.getString(cursor.getColumnIndex("Note"));
        object.set(RunnerID,Name, ID,ExpectNum, ExpectDate,Note,Kind,Priority);
        return object;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public TodayTask findTodayTaskByRunnerID(int RunnerID){
        Vector<TodayTask> vector=new Vector<TodayTask>();
        Cursor cursor=db.query("PlanTask",null,
                "RunnerID="+RunnerID,null,null, null, null);
        while(cursor.moveToNext()){
            vector.add(getTodayTaskEntity(cursor));
        }
        return vector.get(0);
    }
    private TodayTask getTodayTaskEntity(Cursor cursor) {
        TodayTask object=new TodayTask();

        String Name=cursor.getString(cursor.getColumnIndex("Name"));
        int ID=cursor.getInt(cursor.getColumnIndex("ID"));
        int ExpectNum=cursor.getInt(cursor.getColumnIndex("ExpectNum"));
        String ExpectDate=cursor.getString(cursor.getColumnIndex("ExpectDate"));
        int ActualNum=cursor.getInt(cursor.getColumnIndex("ActualNum"));
        int InnerInturrptTimes=cursor.getInt(cursor.getColumnIndex("InnerInturruptTimes"));
        int OuterInturrptTimes=cursor.getInt(cursor.getColumnIndex("OuterInturruptTimes"));
        int State=cursor.getInt(cursor.getColumnIndex("State"));
        int RunnerID=cursor.getInt(cursor.getColumnIndex("RunnerID"));
        String Note=cursor.getString(cursor.getColumnIndex("Note"));
        object.set(ActualNum, InnerInturrptTimes, OuterInturrptTimes, RunnerID, Name, ID, ExpectNum, ExpectDate,State,Note);
        return object;
    }

    public Vector<TodayTask> FindFinshTaskByDay(String Day){
        Vector<TodayTask> vector=new Vector<TodayTask>();
        Cursor cursor=db.query("FinishTask",null,
                "ExpectDate='"+Day+"'",null,null, null, "ExpectDate DESC");
        while(cursor.moveToNext()){
            vector.add(getFinishTaskEntity(cursor));
        }
        return vector;
    }

    public Vector<TodayTask> FindFinshTask(){
        Vector<TodayTask> vector=new Vector<TodayTask>();
        Cursor cursor=db.query("FinishTask",null,
                null,null,null, null, "RunnerID DESC");
        while(cursor.moveToNext()){
            vector.add(getFinishTaskEntity(cursor));
        }
        return vector;
    }
    private TodayTask getFinishTaskEntity(Cursor cursor) {
        TodayTask object=new TodayTask();

        String Name=cursor.getString(cursor.getColumnIndex("Name"));
        int ID=cursor.getInt(cursor.getColumnIndex("ID"));
        int ExpectNum=cursor.getInt(cursor.getColumnIndex("ExpectNum"));
        String ExpectDate=cursor.getString(cursor.getColumnIndex("ExpectDate"));
        int ActualNum=cursor.getInt(cursor.getColumnIndex("ActualNum"));
        int InnerInturruptTimes=cursor.getInt(cursor.getColumnIndex("InnerInturruptTimes"));
        int OuterInturruptTimes=cursor.getInt(cursor.getColumnIndex("OuterInturruptTimes"));
        int RunnerID=cursor.getInt(cursor.getColumnIndex("RunnerID"));
        String Note=cursor.getString(cursor.getColumnIndex("Note"));
        int State=cursor.getInt(cursor.getColumnIndex("State"));
        object.set(ActualNum,InnerInturruptTimes,OuterInturruptTimes,RunnerID,Name,ID,ExpectNum,ExpectDate,State,Note);
        return object;
    }

    public int getDayActualNum(String day){
       int sum=0;
       Cursor cursor= db.query("PlanList",new String[]{"sum(ActualNum) as ActualNum","ExpectDate"},"ExpectDate='"+day+"'",null,null,null,null);
       if(cursor.moveToNext())
          sum=cursor.getInt(cursor.getColumnIndex("ActualNum"));
        return sum;
    }

    public int getDayInnerInturruptTimes(String day){
        int sum=0;
        Cursor cursor= db.query("PlanList",new String[]{"sum(InnerInturruptTimes) as InnerInturruptTimes","ExpectDate"},"ExpectDate='"+day+"'",null,null,null,null);
        if(cursor.moveToNext())
            sum=cursor.getInt(cursor.getColumnIndex("InnerInturruptTimes"));
        return sum;
    }

    public int getDayInnerOuterInturruptTimes(String day){
        int sum=0;
        Cursor cursor= db.query("PlanList",new String[]{"sum(OuterInturruptTimes) as OuterInturruptTimes","ExpectDate"},"ExpectDate='"+day+"'",null,null,null,null);
        if(cursor.moveToNext())
            sum=cursor.getInt(cursor.getColumnIndex("OuterInturruptTimes"));
        return sum;
    }
}
