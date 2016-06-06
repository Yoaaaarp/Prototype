package tuto.david.prototype.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import tuto.david.prototype.database.entity.Chat;
import tuto.david.prototype.database.entity.Subscription;


public class ChatDAO extends DAOBase {
    public static final String CHAT_KEY = "id";
    public static final String CHAT_TITLE = "title";

    public static final String CHAT_EXTRA_ID = "chat_extra_id";
    public static final String CHAT_EXTRA_TITLE = "chat_extra_title";

    public static final String CHAT_TABLE_NAME = "chat";
    public static final String CHAT_TABLE_CREATE = "CREATE TABLE " + CHAT_TABLE_NAME
            + " (" + CHAT_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CHAT_TITLE + " TEXT NOT NULL UNIQUE);";
    public static final String CHAT_TABLE_DROP = "DROP TABLE IF EXISTS " + CHAT_TABLE_NAME + ";";


    public ChatDAO() {
        super();
    }

    public long create(Chat c) throws SQLiteException {
        long id = -1;
        open();
        try {
            mDB.beginTransaction();
            ContentValues values = new ContentValues();
            values.put(CHAT_TITLE, c.getTitle());
            id = mDB.insert(CHAT_TABLE_NAME, null, values);
            mDB.setTransactionSuccessful();
        } catch (SQLiteException e){
            Log.e("Chat DAO", "Error during insertion of data");
        } finally {
            mDB.endTransaction();
        }
        close();
        return id;
    }

    public void delete(long id){
        //TODO
    }

    public void update(Chat c){
        //TODO
    }

    public Chat read(long id){
        Chat chat = null;
        if (id > -1){
            read();
            try{
                mDB.beginTransaction();
                Cursor cursor = mDB.rawQuery("SELECT * FROM "
                        + CHAT_TABLE_NAME + " WHERE "
                        + CHAT_KEY + " = ?", new String[]{""+id});
                if(cursor.moveToNext()){
                    String title = cursor.getString(cursor.getColumnIndex(CHAT_TITLE));
                    chat = new Chat(id, title);
                }
                mDB.setTransactionSuccessful();
            } catch(SQLiteException e){
                Log.e("Chat DAO", "Error while retrieving chat with id =" + id);
            } finally{
                mDB.endTransaction();
            }
            close();
        }
        return chat;
    }

    public List<Chat> getSubscribedChats(long userId) throws SQLiteException {
        List<Chat> chatList = null;

        if (userId > -1){
            try{
                SubscriptionDAO subDAO = new SubscriptionDAO();
                List<Subscription> subList = subDAO.getAllForMember(userId);
                if (subList != null){
                    chatList = new ArrayList<>();
                    int size = subList.size();
                    for (int i = 0; i < size; i++){
                        chatList.add(subList.get(i).getChat());
                    }
                }

            } catch(SQLiteException e){
                Log.e("Chat DAO", "Error while getting all subscribed chats");
            }
        }
        return chatList;
    }
}
