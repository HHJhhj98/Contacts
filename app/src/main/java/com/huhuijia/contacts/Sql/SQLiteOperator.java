package com.huhuijia.contacts.Sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.huhuijia.contacts.Bean.ContactsBean;

import java.util.ArrayList;
import java.util.List;

public class SQLiteOperator {
    //数据库对象
    SQLiteDatabase mDB;

    public SQLiteOperator(Context context) {
        SQLiteOpenHelpers create = new SQLiteOpenHelpers(context);
        mDB = create.getWritableDatabase();
    }

    //新建联系人
    public boolean insert(ContactsBean contactsBean) {
        ContentValues values = new ContentValues();
        values.put(SQLiteOpenHelpers.KEY_NAME, contactsBean.getName());
        values.put(SQLiteOpenHelpers.KEY_PHONE, contactsBean.getPhone());
        long l = mDB.insert(SQLiteOpenHelpers.TABLE, "", values);
        if (l == 1) {
            return true;
        } else {
            return false;
        }
    }

    //查询联系人
//    public String select(String name) {
//        Cursor cursor = mDB.query(SQLiteOpenHelpers.TABLE, new String[]{SQLiteOpenHelpers.KEY_PHONE},
//                "name=?", new String[]{name}, "", "", "");
//        while (cursor.moveToNext()) {
//            String phone = cursor.getString(0);
//            return phone;
//        }
//        return "";
//    }

    //删除联系人
    public boolean delete(String name) {
        int i = mDB.delete(SQLiteOpenHelpers.TABLE, "name=?", new String[]{name + ""});
        if (i == 1) {
            return true;
        } else return false;
    }

    //修改联系人
    public boolean update(ContactsBean contactsBean) {
        ContentValues values = new ContentValues();
        values.put(SQLiteOpenHelpers.KEY_NAME, contactsBean.getName());
        values.put(SQLiteOpenHelpers.KEY_PHONE, contactsBean.getPhone());
        int i=mDB.update(SQLiteOpenHelpers.TABLE, values, "name=?", new String[]{contactsBean.getName() + ""});
        if(i==1){
            return true;
        }else return false;
    }

    // 查询所有的联系人信息
    public List<ContactsBean> queryMany() {
        ArrayList<ContactsBean> contactsBeans = new ArrayList<ContactsBean>();
        Cursor c = mDB.rawQuery("select * from "+SQLiteOpenHelpers.TABLE+"", null);
        while (c.moveToNext()) {
            ContactsBean contactsBean=new ContactsBean();
            contactsBean.setName(c.getString(1));
            contactsBean.setPhone(c.getString(2));
            contactsBeans.add(contactsBean);
        }
        c.close();
        return contactsBeans;
    }

    // 查询指定联系人信息
    public List<ContactsBean> queryAll(String name) {
        ArrayList<ContactsBean> contactsBeans = new ArrayList<ContactsBean>();
        String sql2 = "select * from " + SQLiteOpenHelpers.TABLE
                + " where " + SQLiteOpenHelpers.KEY_NAME + " like '%"+name+"%'";//注意：这里有单引号
        Cursor c = mDB.rawQuery(sql2,null);
        while (c.moveToNext()) {
            ContactsBean contactsBean=new ContactsBean();
            contactsBean.setName(c.getString(1));
            contactsBean.setPhone(c.getString(2));
            contactsBeans.add(contactsBean);
        }
        c.close();
        return contactsBeans;
    }

}
