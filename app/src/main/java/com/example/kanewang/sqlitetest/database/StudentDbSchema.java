package com.example.kanewang.sqlitetest.database;

import java.util.Date;

public class StudentDbSchema {
    public static final class StudentTable{
        public static final String NAME= "Students";
        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String Name = "name";
            public static final String Sex = "sex";
            public static final String Age = "age";
            public static final String BirthDate = "birthdate";
        }
    }


}
