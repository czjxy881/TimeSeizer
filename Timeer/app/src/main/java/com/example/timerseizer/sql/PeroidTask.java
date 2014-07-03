package com.example.timerseizer.sql;


public class PeroidTask extends Task {

	private long Kind;


	public PeroidTask(){
		Kind=1;
	}
	
	public long getKind(){
		return Kind;
	}
	public void setKind(int Kind){
		this.Kind=Kind;
	}


    //TODO:kind未写
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

    @Override
    public String get() {
        return "'"+Name+"',"+ID+",'"+NoteString+"':"+RunnerID+","+ID+","+ExpectNum+",'"+ExpectDate+"':"+ID+","+Kind+":"+Priority;
    }

    public String getForPeroidList(){
        return ID+","+Kind;
    }
}
