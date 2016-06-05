package tuto.david.prototype.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import tuto.david.prototype.database.dao.*;
import tuto.david.prototype.database.entity.Member;

public class DatabaseHandler extends SQLiteOpenHelper {
    protected static final int DB_VERSION = 3;
    protected static final String DB_FILE_NAME = "database.db";

    private static DatabaseHandler instance = null;
    private static Context context = null;

    private static Object sync = new Object();

    private DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    public static DatabaseHandler getInstance(){
        if (context == null){
            throw new IllegalStateException("DatabaseHandler: You have to set the context before using this instance");
        }
        if (instance == null){
            synchronized (sync){
                if (instance == null) {
                    instance = new DatabaseHandler(context, DB_FILE_NAME, null ,DB_VERSION);
                }
            }
        }
        return instance;
    }

    public static void setContext(Context c){
        context = c;
    }

    @Override
    public void onCreate(SQLiteDatabase db) throws SQLiteException{
        Log.i("DB : ","Before database creation");
        db.execSQL(MemberDAO.MEMBER_TABLE_CREATE);
        db.execSQL(ChatDAO.CHAT_TABLE_CREATE);
        db.execSQL(MessageDAO.MESSAGE_TABLE_CREATE);
        db.execSQL(SubscriptionDAO.SUBSCRIPTION_TABLE_CREATE);
        Log.i("DB : ","After database creation");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) throws SQLiteException{
        // en cas de changement de version, on drop la table et on la recrée
        db.execSQL(MemberDAO.MEMBER_TABLE_DROP);
        db.execSQL(ChatDAO.CHAT_TABLE_DROP);
        db.execSQL(MessageDAO.MESSAGE_TABLE_DROP);
        db.execSQL(SubscriptionDAO.SUBSCRIPTION_TABLE_DROP);
        onCreate(db);
    }


}
