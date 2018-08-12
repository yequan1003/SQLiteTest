package com.example.kanewang.sqlitetest.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.kanewang.sqlitetest.Student;

import java.util.Date;
import java.util.UUID;

public class StudentCursorWrapper extends CursorWrapper {

    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public StudentCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Student getStudent(){
        String uuidString = getString(getColumnIndex(StudentDbSchema.StudentTable.Cols.UUID));
        String Name = getString(getColumnIndex(StudentDbSchema.StudentTable.Cols.Name));
        String sex = getString(getColumnIndex(StudentDbSchema.StudentTable.Cols.Sex));
        String age = getString(getColumnIndex(StudentDbSchema.StudentTable.Cols.Age));
        String birthdate = getString(getColumnIndex(StudentDbSchema.StudentTable.Cols.BirthDate));

        Student student = new Student(UUID.fromString(uuidString));
        student.setName(Name);
        student.setSex(sex);
        student.setage(age);
        student.setBirthDate(birthdate);
        return student;
    }
}
