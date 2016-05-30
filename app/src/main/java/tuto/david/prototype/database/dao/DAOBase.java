package tuto.david.prototype.database.dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import tuto.david.prototype.database.DatabaseHandler;

public abstract class DAOBase {
    protected static final int DB_VERSION = 1;
    protected static final String DB_FILE_NAME = "database.db";

    protected SQLiteDatabase mDB = null;
    protected DatabaseHandler mHandler = null;

    public DAOBase(Context context){
        mHandler = new DatabaseHandler(context, DB_FILE_NAME, null, DB_VERSION);
    }

    public SQLiteDatabase open(){
        mDB = mHandler.getWritableDatabase();
        return mDB;
    }

    public void close(){
        mDB.close();
    }

    public SQLiteDatabase getDatabase(){
        return mDB;
    }
}
