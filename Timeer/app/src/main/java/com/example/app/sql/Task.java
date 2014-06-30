package com.example.app.sql;

public class Task{
    protected int RunnerID=-1;
	protected String Name=null;
	protected int ID=-1;
	protected int ExpectNum=0;
	protected String ExpectDate=null;
	protected String NoteString=null;
    protected  int Priority;

    boolean b=false;

	public String getName() {
		return Name;
	}

	public int getID() {
		return ID;
	}

    public int getRunnerID(){ return RunnerID; }

	public int getExpectNum() {
		return ExpectNum;
	}

	public String getExpectDate() {
		return ExpectDate;
	}
    public void setExpectDate(String ExpectDate){
        this.ExpectDate=ExpectDate;
    }

	public String getNoteString() {
		return NoteString;
	}

    public void setPriority(int Priority){
        this.Priority=Priority;
    }
	
	
	public void set(String Name,int ID,int ExpectNum,String ExpectDate,String NoteString,int RunnerID,int Priority){
		this.Name=Name;	
		this.ID=ID;
		this.ExpectNum=ExpectNum;
		this.ExpectDate=ExpectDate;
		this.NoteString=NoteString;
        this.RunnerID=RunnerID;
        this.Priority=Priority;
	}
	
	public String get(){
		return "'"+Name+"',"+ID+",'"+NoteString+"':"+ID+":"+ExpectNum+",'"+ExpectDate+"':"+RunnerID+":"+Priority;
	}

    /**
     * infrom current action(add new task or get existing task)
     * @param b
     */
    public void isNewPlan(boolean b){this.b=b;}

    //null:because ID/RunnerID has autoincrement.
    public String getForIDList(){
       return  "'"+Name+"',"+ID+",'"+NoteString+"'";
    }

    public String getForPlanListNoState(){
        return (b==true? "null,"+ID+","+ExpectNum+",'"+ExpectDate+"',"+Priority
                : RunnerID+","+ID+","+ExpectNum+",'"+ExpectDate+"',"+Priority);
    }
}
