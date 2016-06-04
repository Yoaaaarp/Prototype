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


public class SubscriptionDAO extends DAOBase {
    public static final String SUBSCRIPTION_KEY = "id";
    public static final String SUBSCRIPTION_MEMBER = "member_id";
    public static final String SUBSCRIPTION_CHAT = "chat_id";

    public static final String SUBSCRIPTION_TABLE_NAME = "subscription";
    public static final String SUBSCRIPTION_TABLE_CREATE = "CREATE TABLE " + SUBSCRIPTION_TABLE_NAME
            + " (" + SUBSCRIPTION_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SUBSCRIPTION_MEMBER + " INTEGER NOT NULL, "
            + SUBSCRIPTION_CHAT + " INTEGER NOT NULL);";
    public static final String SUBSCRIPTION_TABLE_DROP = "DROP TABLE IF EXISTS " + SUBSCRIPTION_TABLE_NAME + ";";

    public SubscriptionDAO() {
        super();
    }

    public long create(Subscription s) throws SQLiteException {
        long id = -1;
        open();
        try {
            mDB.beginTransaction();
            ContentValues values = new ContentValues();
            values.put(SUBSCRIPTION_CHAT, s.getChat().getId());
            values.put(SUBSCRIPTION_MEMBER, s.getMember().getId());
            id = mDB.insert(SUBSCRIPTION_TABLE_NAME, null, values);
            mDB.setTransactionSuccessful();
        } catch (SQLiteException e){
            Log.e("Subscription DAO", "Error during insertion of data");
        } finally {
            mDB.endTransaction();
        }
        close();
        return id;
    }

    public void delete(long id){
        //TODO
    }

    public void update(Subscription s){
        //TODO
    }

    public Subscription read(long id){
        //TODO
        return null;
    }

    public List<Subscription> getAllForMember (long memberId) throws SQLiteException{
        List<Subscription> subList = null;
        List<Long> idList = null;
        read();
        try{
            mDB.beginTransaction();
            Cursor cursor = mDB.rawQuery("SELECT * FROM " + SUBSCRIPTION_TABLE_NAME
                    + " WHERE " + SUBSCRIPTION_MEMBER + "=?", new String[]{"" + memberId});

            idList = new ArrayList<>();
            while(cursor.moveToNext()){
                long chatId = cursor.getLong(cursor.getColumnIndex(SUBSCRIPTION_CHAT));
                idList.add(new Long(chatId));
                Log.i("Subscription", "chat id = " + chatId);
            }
            mDB.setTransactionSuccessful();
        } catch(SQLiteException e){
            Log.e("Subscription", "Error while retrieving subscription for a member");
        } finally{
            mDB.endTransaction();
        }

        close();

        if (idList != null){
            int size = idList.size();
            ChatDAO chatDAO = new ChatDAO();
            Chat currChat = null;
            subList = new ArrayList<>();

            for (int i = 0; i < size; i++){
                currChat = chatDAO.read(idList.get(i));
                Subscription sub = new Subscription(1, currChat, null);
                subList.add(sub);
            }
        }
        return subList;
    }
}
