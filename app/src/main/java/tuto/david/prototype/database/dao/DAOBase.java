package tuto.david.prototype.database.dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import tuto.david.prototype.database.DatabaseHandler;

public abstract class DAOBase {
    protected SQLiteDatabase mDB = null;
    protected DatabaseHandler mHandler = null;

    public DAOBase(Context context){
        mHandler = DatabaseHandler.getInstance();
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
