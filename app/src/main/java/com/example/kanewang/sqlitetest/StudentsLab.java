package com.example.kanewang.sqlitetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.kanewang.sqlitetest.database.StudentBaseHelper;
import com.example.kanewang.sqlitetest.database.StudentCursorWrapper;
import com.example.kanewang.sqlitetest.database.StudentDbSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StudentsLab {

    private static StudentsLab sStudentsLab;
    private Context mContext;
    private SQLiteDatabase mSQLiteDatabase;

    public StudentsLab(Context context) {
        mContext = context.getApplicationContext();
        mSQLiteDatabase = new StudentBaseHelper(mContext).getWritableDatabase();
    }

    public static StudentsLab getStudentsLab(Context context) {
        if (sStudentsLab == null){
            sStudentsLab = new StudentsLab(context);
        }
        return sStudentsLab;
    }


    public List<Student> getStudents(){
        List<Student> students = new ArrayList<Student>();
        StudentCursorWrapper cursorWrapper = queryStudents(null, null);
        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                students.add(cursorWrapper.getStudent());
                cursorWrapper.moveToNext();
            }
        }finally {
            cursorWrapper.close();
        }
        return  students;
    }

    public Student getStudent(UUID uuid){
        StudentCursorWrapper cursorWrapper = queryStudents(StudentDbSchema.StudentTable.Cols.UUID +
         " = ?", new String[]{uuid.toString()});
        try {
            if (cursorWrapper.getCount() == 0){
                return null;
            }
            cursorWrapper.moveToFirst();
            return cursorWrapper.getStudent();
        }finally {
            cursorWrapper.close();
        }

    }

    public void addStudent(Student student){

        ContentValues values = getContentValues(student);
        mSQLiteDatabase.insert(StudentDbSchema.StudentTable.NAME, null, values);

    }

    public  void  delStudent(Student student){

        String uuidString = student.getUUID().toString();
        mSQLiteDatabase.delete(StudentDbSchema.StudentTable.NAME, StudentDbSchema.StudentTable.Cols.UUID +
         "=?", new String[]{uuidString});
    }

    public void  updateStudent(Student student){

        String uuidString = student.getUUID().toString();
        ContentValues values = getContentValues(student);

        mSQLiteDatabase.update(StudentDbSchema.StudentTable.NAME, values,
                StudentDbSchema.StudentTable.Cols.UUID + "?",
                new String[]{uuidString});
    }

    private static ContentValues getContentValues(Student student){
        ContentValues values = new ContentValues();
        values.put(StudentDbSchema.StudentTable.Cols.UUID, student.getUUID().toString());
        values.put(StudentDbSchema.StudentTable.Cols.Name, student.getName());
        values.put(StudentDbSchema.StudentTable.Cols.Sex, student.getSex());
        values.put(StudentDbSchema.StudentTable.Cols.Age, student.getage());
        values.put(StudentDbSchema.StudentTable.Cols.BirthDate, student.getBirthDate());
        return values;
    }

    private StudentCursorWrapper queryStudents(String whereCalues, String[] whereArgs){
        Cursor cursor = mSQLiteDatabase.query(StudentDbSchema.StudentTable.NAME,
                null,
                whereCalues,
                whereArgs,
                null,
                null,
                null);
        return new StudentCursorWrapper(cursor);
    }
}
