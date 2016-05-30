package tuto.david.prototype.database.dao;

import android.content.Context;

import tuto.david.prototype.database.entity.Member;


public class MemberDAO extends DAOBase {
    public static final String MEMBER_KEY = "id";
    public static final String MEMBER_NAME = "name";
    public static final String MEMBER_PWD = "pwd";

    public static final String MEMBER_TABLE_NAME = "member";
    public static final String MEMBER_TABLE_CREATE = "CREATE TABLE " + MEMBER_TABLE_NAME
            + " (" + MEMBER_KEY + " INTEGER PRIMARY_KEY AUTOINCREMENT, "
            + MEMBER_NAME + " TEXT NOT NULL, "
            + MEMBER_PWD + " TEXT NOT NULL);";
    public static final String MEMBER_TABLE_DROP = "DROP TABLE IF EXISTS " + MEMBER_TABLE_NAME + ";";

    public MemberDAO(Context context) {
        super(context);
    }

    public void create(Member m){
        //TODO
    }

    public void delete(long id){
        //TODO
    }

    public void update(Member m){
        //TODO
    }

    public Member read(long id){
        //TODO
        return null;
    }
}
