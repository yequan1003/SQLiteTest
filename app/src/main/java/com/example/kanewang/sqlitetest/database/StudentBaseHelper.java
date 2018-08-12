package com.example.kanewang.sqlitetest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "StudentBase.db";


    public StudentBaseHelper(Context context) {
        this(context,DATABASE_NAME,null,VERSION);
    }

    public StudentBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + StudentDbSchema.StudentTable.NAME + "(" +
                StudentDbSchema.StudentTable.Cols.UUID + ", " +
                StudentDbSchema.StudentTable.Cols.Name + ", " +
                StudentDbSchema.StudentTable.Cols.Sex + ", " +
                StudentDbSchema.StudentTable.Cols.Age + ", " +
                StudentDbSchema.StudentTable.Cols.BirthDate + ")" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
