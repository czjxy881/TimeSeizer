package com.mycompany.myapp;

public class IDList {
	int ID;
	String Name;
	String Note;
	
	public void set(int ID,String Name,String Note){
		this.ID=ID;
		this.Name=Name;
		this.Note=Note;
	}
	
	public String get(){
		return ID+",'"+Name+"','"+Note+"'";
	}
}
