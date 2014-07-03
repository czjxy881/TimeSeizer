package com.example.timerseizer.sql;

import java.util.Date;

public class TodayTask extends Task {
	private int ActualNum;
	private int InnerInturruptTimes;
	private int OuterInturruptTimes;
	private StateEnum State;

	Date date;
	public TodayTask(){
		ActualNum=0;
		InnerInturruptTimes=0;
		OuterInturruptTimes=0;
		State=StateEnum.READY;
		RunnerID=-1;
		date=new Date();
	}

	public void innerInturrpt(){
		InnerInturruptTimes++;save();
	}

	public void outerInturrpt(){
		OuterInturruptTimes++;save();
	}

	public void oneClockPass(){
		ActualNum++;
        save();
	}
    private void save(){
        InitDb.getInstance(null).updateDb.updatePlanList(getForPlanList(),State.getNum());
    }
	public void finish(){
		State=StateEnum.FINISH;
        save();
	}

	public void abort(){
        State=StateEnum.ABANDON;
        save();
	}
    public int getActualNum(){
        return ActualNum;
    }
    public int getInnerInturruptTimes(){
        return InnerInturruptTimes;
    }
    public int getOuterInturruptTimes(){
        return OuterInturruptTimes;
    }
	public String getState(){
        return State.toString();
	}
	public void set(int ActualNum,int InnerInturrptTimes,int OuterInturrptTimes,
		int RunnerID,String Name,int ID,int ExpectNum,String ExpectDate,int State,String Note){
		this.ActualNum=ActualNum;
		this.InnerInturruptTimes=InnerInturrptTimes;
		this.OuterInturruptTimes=OuterInturrptTimes;
		this.RunnerID=RunnerID;
		this.Name=Name;
		this.ID=ID;
		this.ExpectNum=ExpectNum;
		this.ExpectDate=ExpectDate;
		this.State=StateEnum.get(State);
        this.NoteString=Note;
	}
    public String getForPlanList(){
        return (RunnerID+","+ID+","+ExpectNum+",'"+ExpectDate+"',"+Priority+","+ActualNum+","+InnerInturruptTimes+","+OuterInturruptTimes);
    }
    @Override
	public String get(){
		return "'"+Name+"':"+RunnerID+","+ID+":'"+
				ExpectDate+"':"+ActualNum+","+InnerInturruptTimes+","+OuterInturruptTimes+":"+State+","+ExpectNum+":"+NoteString;
	}
}
