package tuto.david.prototype.database.dao;

import android.content.Context;

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

    public void create(Subscription s){
        //TODO
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
}
