package com.example.kanewang.sqlitetest;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Student implements Serializable {
    private UUID mUUID;
    private String mName;
    private String mSex;
    private String mage;
    private String mBirthDate;

    public Student() {
        this(UUID.randomUUID());
    }

    public Student(UUID UUID) {
        mUUID = UUID;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSex() {
        return mSex;
    }

    public void setSex(String sex) {
        mSex = sex;
    }

    public String getage() {
        return mage;
    }

    public void setage(String mage) {
        this.mage = mage;
    }

    public String getBirthDate() {
        return mBirthDate;
    }

    public void setBirthDate(String birthDate) {
        mBirthDate = birthDate;
    }
}
