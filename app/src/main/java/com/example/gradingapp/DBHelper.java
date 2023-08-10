package com.example.gradingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    static final String DBNAME = "School.db"; // School DB
    static final int VERSION = 1; //Initial Version
    // Table names
    static final String TABLE1_NAME = "Grades";
    static final String TABLE2_NAME = "Improvement";

    // Grades Table Column
    static final String COL1_1 = "studentid";
    static final String COL1_2 = "studentname";
    static final String COL1_3 = "program";
    static final String COL1_4 = "course1";
    static final String COL1_5 = "course2";
    static final String COL1_6 = "course3";
    static final String COL1_7 = "course4";

    // Improvement Table Column
    static final String COL2_1 = "improvementid";
    static final String COL2_2 = "studentid";
    static final String COL2_3 = "course";
    static final String COL2_4 = "marks";

    // Create Grades Table
    static final String CREATE_GRADES_TABLE = "create table " + TABLE1_NAME +
            " (" + COL1_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL1_2 + " TEXT NOT NULL, " +
            COL1_3 + " TEXT, " +
            COL1_4 + " INTEGER, " +
            COL1_5 + " INTEGER, " +
            COL1_6 + " INTEGER, " +
            COL1_7 + " INTEGER); ";

    // Create Improvement Table
    static final String CREATE_IMPROVEMENT_TABLE = "create table " + TABLE2_NAME +
            " (" + COL2_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL2_2 + " INTEGER NOT NULL, " +
            COL2_3 + " TEXT, " +
            COL2_4 + " INTEGER); ";

    //Remove Grades Table
    static final String DROP_GRADES_TABLE = "DROP TABLE IF EXISTS " + TABLE1_NAME;
    //Remove Improvement Table
    static final String DROP_IMPROVEMENT_TABLE = "DROP TABLE IF EXISTS " + TABLE2_NAME;

    public DBHelper(Context context)
    {
        //Create DB with Version
        super(context, DBNAME, null, VERSION);
    }

    //Create Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Grades Table
        db.execSQL(CREATE_GRADES_TABLE);
        // Create Improvement Table
        db.execSQL(CREATE_IMPROVEMENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_GRADES_TABLE);
        db.execSQL(DROP_IMPROVEMENT_TABLE);
        onCreate(db);
    }

    // Insert Row into table using ContentsValues
    public boolean InsertGrade(Grade objGrd)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL1_2, objGrd.getStudentname());
        cv.put(COL1_3, objGrd.getProgram());
        cv.put(COL1_4, objGrd.getCourse1());
        cv.put(COL1_5, objGrd.getCourse2());
        cv.put(COL1_6, objGrd.getCourse3());
        cv.put(COL1_7, objGrd.getCourse4());

        long result = db.insert(TABLE1_NAME, null, cv);
        return ((result==-1) ? false: true);
    }

    public boolean UpdateGrade(Improvement objImp)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(objImp.getCourse(),objImp.getMarks());

        long result = db.update(TABLE1_NAME,cv, String.format("%s = ?", COL1_1),
                new String[]{String.valueOf(objImp.getStudentid())});
        return  ((result==-1) ? false: true);
    }

    public boolean InsertImprovement(Improvement objImp)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2_2, objImp.getStudentid());
        cv.put(COL2_3, objImp.getCourse());
        cv.put(COL2_4, objImp.getMarks());

        long result = db.insert(TABLE2_NAME,null,cv);
        return ((result==-1) ? false: true);
    }

    public Cursor FetchStudentById(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorObj;
        cursorObj = db.rawQuery("select * from " + TABLE1_NAME +
                " Where studentid =" + id,null);

        if(cursorObj != null)
        {
            cursorObj.moveToFirst();
        }
        return cursorObj;
    }

    // Using cursor, select all rows from table, and return cursor
    public Cursor ListStudent()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorObj;
        // User SQL to get all results.
        cursorObj = db.rawQuery("select * from " + TABLE1_NAME, null);

        if(cursorObj != null)
        {
            cursorObj.moveToFirst();
        }
        return cursorObj;
    }




}
