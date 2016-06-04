package tuto.david.prototype.database.dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import tuto.david.prototype.database.DatabaseHandler;

public abstract class DAOBase {
    protected SQLiteDatabase mDB = null;
    protected DatabaseHandler mHandler = null;

    public DAOBase(){
        mHandler = DatabaseHandler.getInstance();
    }

    public SQLiteDatabase open() throws SQLiteException {
        mDB = mHandler.getWritableDatabase();
        return mDB;
    }

    public SQLiteDatabase read() throws SQLiteException {
        mDB = mHandler.getReadableDatabase();
        return mDB;
    }

    public void close(){
        mDB.close();
    }

    public SQLiteDatabase getDatabase(){
        return mDB;
    }
}
