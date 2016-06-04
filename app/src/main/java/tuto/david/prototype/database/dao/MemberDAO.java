package tuto.david.prototype.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import tuto.david.prototype.database.entity.Member;


public class MemberDAO extends DAOBase {
    public static final String MEMBER_KEY = "id";
    public static final String MEMBER_NAME = "name";
    public static final String MEMBER_PWD = "pwd";

    public static final String MEMBER_TABLE_NAME = "member";
    public static final String MEMBER_TABLE_CREATE = "CREATE TABLE " + MEMBER_TABLE_NAME
            + " ("
            + MEMBER_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MEMBER_NAME + " TEXT NOT NULL UNIQUE, "
            + MEMBER_PWD + " TEXT NOT NULL "
            + ");";
    public static final String MEMBER_TABLE_DROP = "DROP TABLE IF EXISTS " + MEMBER_TABLE_NAME + ";";

    public MemberDAO(){
        super();
    }

    public long create(Member m) throws SQLiteException{
        long id = -1;
        open();
        try {
            mDB.beginTransaction();
            ContentValues values = new ContentValues();
            values.put(MEMBER_NAME, m.getName());
            values.put(MEMBER_PWD, m.getPwd());
            id = mDB.insert(MEMBER_TABLE_NAME, null, values);
            mDB.setTransactionSuccessful();
        } catch (SQLiteException e){
            Log.e("Member DAO", "Error during insertion of data");
        } finally {
            mDB.endTransaction();
        }
        close();
        return id;
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

    public Member findMemberByName(String name) throws SQLiteException{
        Member member = null;
        read();
        try {
            mDB.beginTransaction();
            Cursor cursor = mDB.rawQuery("SELECT * FROM " + MEMBER_TABLE_NAME
                    + " WHERE name=?", new String[]{name});
            if(cursor.moveToNext()){ // on verifie que le cursor n'est pas vide
                String username = cursor.getString(cursor.getColumnIndex(MEMBER_NAME));
                String pwd = cursor.getString(cursor.getColumnIndex(MEMBER_PWD));
                long id = cursor.getLong(cursor.getColumnIndex(MEMBER_KEY));
                member = new Member(id, username, pwd);
            }
            mDB.setTransactionSuccessful();
        } catch (SQLiteException e){
            Log.e("Member DAO", "Error while retrieving a member");
            Log.e("Message", e.toString());
        } finally {
            mDB.endTransaction();
        }
        close();
        return member;
    }
}
