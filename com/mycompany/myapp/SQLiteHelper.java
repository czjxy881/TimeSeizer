package com.mycompany.myapp;
import android.database.sqlite.*;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper
{
	public SQLiteHelper(android.content.Context context, java.lang.String name,
	android.database.sqlite.SQLiteDatabase.CursorFactory factory, int version){
		super(context, name,factory,version);
	}
	public SQLiteHelper(android.content.Context context, java.lang.String name,int version){
		super(context, name,null,version);
	}
	public SQLiteHelper(android.content.Context context, java.lang.String name)
	{
		super(context, name,null,1);
	}
	
	@Override
	public void onCreate(SQLiteDatabase p1)
	{
		Log.i("Database", "success");
	}

	@Override
	public void onUpgrade(SQLiteDatabase p1, int p2, int p3)
	{
		// TODO: Implement this method
	}
	
}
