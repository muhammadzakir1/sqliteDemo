package com.example.zakiryousuf.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Zakir Yousuf on 02/04/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String Database_Name="student.db";
    private static final String Table_Name="student_table";
    private static final String col_1="ID";
    private static final String col_2="NAME";
    private static final String col_3="SURNAME";
    private static final String col_4="MARKS";


    public DatabaseHelper(Context context) {
        super(context, Database_Name, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Table_Name +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS "+Table_Name);
    onCreate(db);
    }

    public boolean insertData(String name, String surname, String marks){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues values =new ContentValues();
        values.put(col_2,name);
        values.put(col_3,surname);
        values.put(col_4,marks);
        long result= db.insert(Table_Name,null,values);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from "+Table_Name,null);
        return res;

    }

    public boolean updatedata(String id, String name, String surname, String marks){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues values =new ContentValues();
        values.put(col_1,id);
        values.put(col_2,name);
        values.put(col_3,surname);
        values.put(col_4,marks);
        db.update(Table_Name,values,"id = ?",new String[]{ id });
        return true;
    }

    public boolean deleteData(String id, String name, String surname, String marks){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(col_1,id);
        values.put(col_2,name);
        values.put(col_3,surname);
        values.put(col_4,marks);
        db.delete(Table_Name,"id = ?",new String[]{id});
        return true;
    }

}
