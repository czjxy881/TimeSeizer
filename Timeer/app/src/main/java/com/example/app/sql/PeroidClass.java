package com.example.app.sql;

public class PeroidClass {
	//ID int primary key references IDClass(ID),Kind bigint
	private int ID;
	private long Kind;
	
	public void set(int ID, long Kind) {
		this.ID=ID;
		this.Kind=Kind;
	}
	public String get(){
		return ID+","+Kind;
	}	
}

