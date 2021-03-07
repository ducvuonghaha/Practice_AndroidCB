package com.dcvg.practice.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQliteHelperManager extends SQLiteOpenHelper {

    public static final String DB_NAME = "QUANLYDULIEU";
    public static final int DB_VERSION = 1;

    public static final String DATA_TABLE = "DuLieu";
    public static final String DATA_ID = "IDDuLieu";
    public static final String DATA_CONTENT = "NoiDungDuLieu";
    public static final String DATA_STATUS = "TrangThaiDuLieu";

    public SQliteHelperManager(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CLASS_TABLE = "CREATE TABLE " + DATA_TABLE + "(" +
                "" + DATA_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "" + DATA_CONTENT + " NVARCHAR(100) NOT NULL," +
                "" + DATA_STATUS + " NVARCHAR(100) NOT NULL" +
                ")";
        db.execSQL(CREATE_CLASS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATA_TABLE);
    }
}
