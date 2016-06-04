package tuto.david.prototype.database.dao;

import android.content.Context;

import tuto.david.prototype.database.entity.Chat;


public class ChatDAO extends DAOBase {
    public static final String CHAT_KEY = "id";
    public static final String CHAT_TITLE = "title";

    public static final String CHAT_TABLE_NAME = "chat";
    public static final String CHAT_TABLE_CREATE = "CREATE TABLE " + CHAT_TABLE_NAME
            + " (" + CHAT_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CHAT_TITLE + " TEXT NOT NULL);";
    public static final String CHAT_TABLE_DROP = "DROP TABLE IF EXISTS " + CHAT_TABLE_NAME + ";";


    public ChatDAO() {
        super();
    }

    public void create(Chat c){
        //TODO
    }

    public void delete(long id){
        //TODO
    }

    public void update(Chat c){
        //TODO
    }

    public Chat read(long id){
        //TODO
        return null;
    }
}
