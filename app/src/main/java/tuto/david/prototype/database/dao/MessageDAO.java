package tuto.david.prototype.database.dao;


import android.content.Context;

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

    public void create(Message m){
        //TODO
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
}
