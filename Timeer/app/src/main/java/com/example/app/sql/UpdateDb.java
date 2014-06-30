package com.example.app.sql;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by cdkyee on 2014/4/14.
 * Param: SQLiteDatabase---save DataBase
 *        SQLiteHelper---save SQLiteHelper
 *        date---save Date
 *        day---save date(Format yyyy-MM-dd)
 */
public class UpdateDb {
    private static UpdateDb updateDb=null;
    static SQLiteDatabase db=null;
    private SQLiteHelper sql=null;
    //Date date=new Date();
    String day;

    /**
     * get single instance
     * @param day
     * @param db
     * @param sql
     * @return
     */
    public static UpdateDb getInstance(String day,SQLiteDatabase db,SQLiteHelper sql){
        if(updateDb==null){
            updateDb=new UpdateDb(day,db,sql);
            return updateDb;
        }
        else{
            return updateDb;
        }
    }
    private UpdateDb(String day,SQLiteDatabase db,SQLiteHelper sql){
        this.sql=sql;
        this.db=db;
        this.day=day;
    }
    /**
     * run depend on SQL sentence
     * @param s SQL sentence
     */
    public void sql(String s){
        db.execSQL(s);
    }

    /**
     * update one line in PlanList
     * @param string
     * @param State
     */
    public boolean updatePlanList(String string,int State){
        if(string.equals(null))
            return false;
        else {
            db.execSQL("replace into PlanList(RunnerID,TaskID,ExpectNum,ExpectDate," +
                    "Priority,ActualNum,InnerInturruptTimes,OuterInturruptTimes,State) values("+string+","+State+")");
            return  true;
        }
    }

    /**
     * change the ExpectDate of one line in PlanList
     * @param Day
     * @param RunnerID
     */
    public void updateDay(String Day,int RunnerID){

        db.execSQL("update PlanList set ExpectDate='"+Day+"' where RunnerID="+RunnerID);
    }

    /**
     * update one line in planList when finish a clock
     * @param RunnerID
     * @param BeginTime
     * @param State
     * @param ActualNum
     * @param InnerInturruptTimes
     * @param OuterInturruptTimes
     */
    public void updateClockPass(int RunnerID,String BeginTime,int State,int ActualNum,int InnerInturruptTimes,int OuterInturruptTimes){
        db.execSQL("update PlanList set BeginTime='"+BeginTime+"',State="+State+",ActualNum="+ActualNum+
                ",InnerInturruptTimes="+InnerInturruptTimes+",OuterInturruptTimes="+OuterInturruptTimes+" where RunnerID="+RunnerID);
    }

    /**
     * update one line in IDList
     * @param string
     */
    public boolean updateIDList(String string){
        String s=string;
        if(s.equals(null))
           return false;
        else {
            db.execSQL("replace into IDList(Name,ID,Note) values("+s+")");
            return true;
        }
    }

    /**
     * update one line in PeroidList
     * @param string
     */
    public boolean updatePeroidList(String string){
        String s=string;
        if(s.equals(null))
            return false;
        else {
            db.execSQL("replace into PeroidList(ID,Kind) values("+s+")");
            return true;
        }
    }

    /**
     * help to update one line in DailyList,such as save today summary
     * @param list
     */
    private boolean updateDailyList(DailyClass list){
        String s=list.get();
        if(s.equals(null))
            return false;
        else {
            db.execSQL("replace into DailyList(Date,BeginTime,Summary,ActualNum," +
                    "RestTime,WorkTime,LongRestTime) values("+s+")");
            return true;
        }
    }

    /**
     * save today summary
     * @param Summary
     * @param RestTime
     * @param WorkTime
     * @param LongRestTime
     */
    public void saveDay(String Summary,String BeginTime,int RestTime,int WorkTime,int LongRestTime){
        DailyClass list=new DailyClass();
        list.set(day,BeginTime,Summary,RestTime,WorkTime,LongRestTime);
        updateDailyList(list);
    }
    /**
     * delete Task from PlanList
     */
    public void delTaskFromPlanList(int RunnerID){
        sql("Delete from PlanList where RunnerID=" + RunnerID);
    }
    /**
     * Delete Task, which save in PlanList ,PeroidList and IDList
     * @param RunnerID
     * @param ID
     */
    public void delTask(int RunnerID,int ID){
        sql("Delete from PlanList where RunnerID=" + RunnerID);

      //TODO  sql("Delete from PeroidList where ID=" + ID);
      //  sql("Delete from IDList where ID=" + ID);

    }

    /**
     * Delete the Peroid feature,which save in PeroidList
     * @param ID
     */
    public void delPeroid(int ID){
            sql("Delete from PeroidList where ID=" + ID);
    }

}
