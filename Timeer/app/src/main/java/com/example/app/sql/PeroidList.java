package com.example.app.sql;

public class PeroidList {
	//ID int primary key references IDList(ID),Kind bigint
	int ID;
	long Kind;
	
	public void set(int ID, long Kind) {
		this.ID=ID;
		this.Kind=Kind;
	}
	public String get(){
		return ID+","+Kind;
	}	
}

