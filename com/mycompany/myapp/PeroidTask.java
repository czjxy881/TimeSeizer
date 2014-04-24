package com.myapplication8.app.Back;


public class PeroidTask extends Task {
	
	private long Kind;

    private boolean b=false;
	public PeroidTask(){
		Kind=1;
	}
	
	public long getKind(){
		return Kind;
	}
	public void setKind(int Kind){
		this.Kind=Kind;
	}

	
	
	public boolean judgeRun(String day){                 //Necessary??
		return true;
	}

	public void set(int RunnerID,String Name,int ID,int ExpectNum,String ExpectDate,String Note,long Kind,int Priority){
        this.RunnerID=RunnerID;
		this.Name=Name;
		this.ID=ID;
		this.ExpectNum=ExpectNum;
		this.ExpectDate=ExpectDate;
        this.NoteString=Note;
		this.Kind=Kind;
        this.Priority=Priority;
	}
    public void isNewPlan(boolean b){this.b=b;}
	public String get(){
		return "'"+Name+"',"+ID+",'"+NoteString+"':"+RunnerID+","+ID+","+ExpectNum+",'"+ExpectDate+"':"+ID+","+Kind+":"+Priority;
	}
    public String getForIDList(){
        return "'"+Name+"',"+ID+",'"+NoteString+"'";
    }
    //RunnerID,ID,ExpectNum,ExpectDate,BeginTime,Priority,State......................................................unDetermined
    public String getForPlanListNoState(){
        return (b==true? "null,"+ID+","+ExpectNum+",'"+ExpectDate+"',"+Priority
                : RunnerID+","+ID+","+ExpectNum+",'"+ExpectDate+"',"+Priority);
    }
    public String getForPeroidList(){
        return ID+","+Kind;
    }
}
