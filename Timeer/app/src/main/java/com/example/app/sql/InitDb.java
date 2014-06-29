package com.example.app.sql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by cdkyee on 2014/4/17.
 */
public class InitDb {
    public UpdateDb updateDb=null;
    public QueryDb queryDb=null;
    private static InitDb initDb=null;
    String DAY=null;
    String TIME=null;
    int DAY_OF_WEEK=-1;
    int DAY_OF_MONTH=-1;

    /**
     * single instance
     * @param context
     * @return
     */
    public static InitDb getInstance(Context context){
        if(initDb==null){
            initDb=new InitDb(context);
            return initDb;
        }
        else{
            return initDb;
        }
    }
    static SQLiteDatabase db=null;
    private SQLiteHelper sql=null;

    /**
     * inital Database,updateDb,QuerDb,data about time and call method to update the ExpectDate in PlanList
     * @param context
     */
    private InitDb(Context context){
        sql=new SQLiteHelper(context, "sqlite");
        db=sql.getWritableDatabase();
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dfTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal=Calendar.getInstance();
        DAY=df.format(cal.getTime());
        TIME=dfTime.format(cal.getTime());
        DAY_OF_WEEK=cal.get(cal.DAY_OF_WEEK);
        DAY_OF_MONTH=cal.get(cal.DAY_OF_MONTH);
        queryDb= QueryDb.getInstance(db, sql,DAY);
        updateDb=UpdateDb.getInstance(DAY,db,sql);
        createTable();
        tidePlanList();
    }

    /**
     * update the ExpectDate in PlanList
     */
    private void tidePlanList() {
        //Task which is State=1(new ID) will be ingored
        Cursor cursor=db.query("PeroidTask", null, "ExpectDate='"+DAY+"' and State=1",null, null, null, null);
        if(cursor.moveToNext()==false){
            cursor=db.query("PeroidTask", null, "ExpectDate<'"+DAY+"'",null, null, null, "ID ASC");
            int ID=-1,RunnerID;
            long PeroidKind;
            String Date;
            while(cursor.moveToNext()) {
                if (cursor.getInt(cursor.getColumnIndex("ID")) != ID) {
                    ID = cursor.getInt(cursor.getColumnIndex("ID"));
                    RunnerID = cursor.getInt(cursor.getColumnIndex("RunnerID"));
                    Date = cursor.getString(cursor.getColumnIndex("ExpectDate"));
                    PeroidKind = cursor.getLong(cursor.getColumnIndex("Kind"));
                    if (setNewDate(Date, PeroidKind)) {
                        Task task = queryDb.FindTaskByRunnerID(RunnerID);
                        task.setExpectDate(DAY);
                        String s = task.getForPlanListNoState();
                        s = "null" + s.substring(s.indexOf(","));
                        updateDb.updatePlanList(s+",0,0,0", 0);
                    }
                }
            }
        }
    }

    /**
     * Help to inform whether change the ExceptDate or not
     * @param date
     * @param Kind
     * @return
     */
    private boolean setNewDate(String date,long Kind) {
        long temp=Long.MAX_VALUE&Kind;
        int i=0;
        if((temp=temp>>1)==0){
            return true;
        }
        else if((temp>>7)==0) {
            i=DAY_OF_WEEK;
            if((temp>>(i-1))!=0&&(temp>>i)==0)
                return true;
            else
                return false;
        }
        else if((temp=temp>>7)!=0){
            i=DAY_OF_MONTH;
            if((temp>>(i-1))!=0&&(temp>>i)==0)
                return true;
            else
                return false;
        }
        return false;
    }


    /**
     * inital the database table and data
     */
    private void createTable(){//TODO
        db.execSQL("Create table if not exists IDList(ID int primary key,Name varchar(30),Note varchar(100))");
        db.execSQL("Create table if not exists PlanList(RunnerID integer primary key autoincrement,TaskID int references IDClass(ID),ExpectNum int,ExpectDate String,Priority int,State int,ActualNum int,InnerInturruptTimes int,OuterInturruptTimes int)");
        db.execSQL("Create table if not exists DailyList(Date String primary key,Summary char(3),BeginTime String,RestTime int,WorkTime int,LongRestTime int)");
        db.execSQL("Create table if not exists PeroidList(ID int primary key references IDClass(ID),Kind int)");

        db.execSQL("replace into IDList(ID,Name,Note) values(3,'workhard','haha')");
        db.execSQL("replace into IDList(ID,Name,Note) values(1,'cdy','haha')");
        db.execSQL("replace into IDList(ID,Name,Note) values(2,'cd','haha')");
        db.execSQL("replace into PlanList(RunnerID,TaskID,ExpectNum,ExpectDate,Priority,State,ActualNum,InnerInturruptTimes,OuterInturruptTimes) values(1,1,10,'2013-02-16',5,0,0,0,0)");
        db.execSQL("replace into PlanList(RunnerID,TaskID,ExpectNum,ExpectDate,Priority,State,ActualNum,InnerInturruptTimes,OuterInturruptTimes) values(2,2,10,'2014-04-10',5,2,10,10,10)");
        db.execSQL("replace into PeroidList(ID,Kind) values(1,1)");
        db.execSQL("update IDList set Name='hardwork' where ID=3");

        db.execSQL("Create view if not exists FinishTask as select IDList.*,RunnerID,ExpectNum,ExpectDate,State,ActualNum,InnerInturruptTimes,OuterInturruptTimes from IDList,PlanList where IDList.ID=PlanList.TaskID and State=2");
        db.execSQL("Create view if not exists PeroidTask as select IDList.*,Kind,RunnerID,ExpectNum,ExpectDate,Priority,State,ActualNum,InnerInturruptTimes,OuterInturruptTimes from IDList,PeroidList,PlanList where IDList.ID=PeroidList.ID and IDList.ID=PlanList.TaskID");
        db.execSQL("Create view if not exists PlanTask as select IDList.*,RunnerID,ExpectNum,ExpectDate,Priority,State,ActualNum,InnerInturruptTimes,OuterInturruptTimes from IDLIst,PlanList where IDLIst.ID=PlanList.TaskID");
    }

    /**
     * Database close
     */
    public void close(){
        db.close();
    }
}
