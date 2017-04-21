package com.tpnet.testdelight;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by litp on 2017/4/12.
 */

public class DataBaseHelper extends SQLiteOpenHelper{

   
    public DataBaseHelper(Context context, int v) {

        this(context, "video.db", null , v);
        

    }

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        
 
    }

 
    @Override
    public void onCreate(SQLiteDatabase db) {
        
    
        db.execSQL(Program.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        
    }
}
