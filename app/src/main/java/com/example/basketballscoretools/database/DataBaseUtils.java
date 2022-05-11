package com.example.basketballscoretools.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseUtils {
    public static DataBaseUtils dataBaseUtils;
    public DatabaseHelper databaseHelper = null;
    private static SQLiteDatabase database = null;
    public static DataBaseUtils getInstance(Context context) {
        if (dataBaseUtils == null) {
            dataBaseUtils = new DataBaseUtils(context);
        }
        return dataBaseUtils;
    }

    public DataBaseUtils(Context context) {
        databaseHelper = new DatabaseHelper(context);
        database= databaseHelper.getWritableDatabase();
    }

    public static int delete(String table_name){
        long result = database.delete(table_name,"",null);
        return (int) result;
    }

    public static Cursor query(String table_name){
        Cursor cursor = database.query(table_name,null,null,null,null,null,null,null);
        return cursor;
    }

    public static int save(String table_name,ContentValues contentValues){
        long result =database.insert("score",null,contentValues);
        return (int) result;
    }
}
