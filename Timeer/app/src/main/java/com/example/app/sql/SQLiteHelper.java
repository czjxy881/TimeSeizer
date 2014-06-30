package com.example.app.sql;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper
{
	public SQLiteHelper(android.content.Context context, String name,
                        SQLiteDatabase.CursorFactory factory, int version){
		super(context, name,factory,version);
	}
	public SQLiteHelper(android.content.Context context, String name, int version){
		super(context, name,null,version);
	}
	public SQLiteHelper(android.content.Context context, String name)
	{
		super(context, name,null,1);
	}
<<<<<<< HEAD
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.i("Database", "success");
    }

    @Override
=======
	
	@Override
	public void onCreate(SQLiteDatabase p1)
	{
		Log.i("Database", "success");
	}

	@Override
>>>>>>> origin/UI
	public void onUpgrade(SQLiteDatabase p1, int p2, int p3)
	{
		// TODO: Implement this method
	}
	
}
