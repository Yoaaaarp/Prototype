package tuto.david.prototype.database.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import tuto.david.prototype.database.entity.Message;

public class MessageDAO extends DAOBase {
    public static final String MESSAGE_KEY = "id";
    public static final String MESSAGE_IMPORTANCE = "importance";
    public static final String MESSAGE_TEXT = "text";
    //public static final String MESSAGE_CREATION_DATE = "creation_date";
    public static final String MESSAGE_CHAT = "chat_id";
    public static final String MESSAGE_AUTHOR = "author_id";

    public static final String MESSAGE_TABLE_NAME = "message";
    public static final String MESSAGE_TABLE_CREATE = "CREATE TABLE " + MESSAGE_TABLE_NAME
            + "(" + MESSAGE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MESSAGE_TEXT + " TEXT NOT NULL, "
            + MESSAGE_IMPORTANCE + " INTEGER DEFAULT 1, "
            + MESSAGE_AUTHOR + " INTEGER, "
            + MESSAGE_CHAT + " INTEGER, "
            + "FOREIGN KEY(" + MESSAGE_AUTHOR + ") REFERENCES "
            + MemberDAO.MEMBER_TABLE_NAME + "(" + MemberDAO.MEMBER_KEY + "), "
            + "FOREIGN KEY(" + MESSAGE_CHAT + ") REFERENCES "
            + ChatDAO.CHAT_TABLE_NAME + "(" +ChatDAO.CHAT_KEY + "));";
    public static final String MESSAGE_TABLE_DROP = "DROP TABLE IF EXISTS " + MESSAGE_TABLE_NAME + ";";

    public MessageDAO() {
        super();
    }

    public long create(Message m) throws SQLiteException {
        long id = -1;
        open();
        try{
            mDB.beginTransaction();
            ContentValues values = new ContentValues();
            values.put(MESSAGE_IMPORTANCE, m.getImportance());
            values.put(MESSAGE_TEXT, m.getText());
            values.put(MESSAGE_CHAT, m.getChat());
            values.put(MESSAGE_AUTHOR, m.getAuthor());
            id = mDB.insert(MESSAGE_TABLE_NAME, null, values);
            mDB.setTransactionSuccessful();
        } catch(SQLiteException e){
            Log.e("Message DAO", "Error while inserting a new message");
        } finally {
            mDB.endTransaction();
        }
        close();

        return id;
    }

    public void delete(long id){
        //TODO
    }

    public void update(Message m){
        //TODO
    }

    public Message read(long id){
        //TODO
        return null;
    }

    public List<Message> getMessagesForChat(long id) throws SQLiteException{
        List<Message> msgList = null;
        read();
        try {
            mDB.beginTransaction();
            Cursor cursor = mDB.rawQuery("SELECT * FROM "
                    + MESSAGE_TABLE_NAME + " WHERE "
                    + MESSAGE_CHAT + " = ? ", new String[]{"" + id});

            msgList = new ArrayList<>();
            while(cursor.moveToNext()){
                Message msg = new Message();
                msg.setChat(cursor.getLong(cursor.getColumnIndex(MESSAGE_CHAT)));
                msg.setAuthor(cursor.getLong(cursor.getColumnIndex(MESSAGE_AUTHOR)));
                msg.setId(cursor.getLong(cursor.getColumnIndex(MESSAGE_KEY)));
                msg.setText(cursor.getString(cursor.getColumnIndex(MESSAGE_TEXT)));
                msg.setImportance(cursor.getInt(cursor.getColumnIndex(MESSAGE_IMPORTANCE)));
                msgList.add(msg);
            }
            mDB.setTransactionSuccessful();
        } catch(SQLiteException e){
            Log.e("Message DAO", "Error while retrieving chat's messages...");
        } finally {
            mDB.endTransaction();
        }
        close();
        return msgList;
    }
}
