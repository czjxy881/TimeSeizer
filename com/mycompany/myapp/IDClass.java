package com.myapplication8.app.Back;

public class IDClass {
	private int ID;
	private String Name;
	private String Note;
	
	public int getID() {
		return ID;
	}

	public String getName() {
		return Name;
	}

	public String getNote() {
		return Note;
	}


	public void set(int ID,String Name,String Note){
		this.ID=ID;
		this.Name=Name;
		this.Note=Note;
	}
	
	public String get(){
		return ID+",'"+Name+"','"+Note+"'";
	}
}
