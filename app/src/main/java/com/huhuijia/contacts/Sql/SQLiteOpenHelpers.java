package com.huhuijia.contacts.Sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteOpenHelpers extends SQLiteOpenHelper {
    private final static String FILE_NAME = "database_db";
    private final static int VERSION = 1;
    public final static String TABLE = "person";
    public final static String KEY_ID = "_id";
    public final static String KEY_NAME = "name";
    public final static String KEY_PHONE = "phone";

    //建库
    public SQLiteOpenHelpers(Context context) {
        super(context, FILE_NAME, null, VERSION);
    }

    //建表
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql ="create table person ("
                +"_id integer primary key autoincrement, "
                +"name text,"
                +"phone text)";
        // String sql = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT,% TEXT)", TABLE, KEY_ID, KEY_NAME, KEY_PHONE); //建表SQL语句
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
