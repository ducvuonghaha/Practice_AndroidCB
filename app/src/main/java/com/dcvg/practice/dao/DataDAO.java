package com.dcvg.practice.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dcvg.practice.database.SQliteHelperManager;
import com.dcvg.practice.model.Data;

import java.util.ArrayList;
import java.util.List;

import static com.dcvg.practice.database.SQliteHelperManager.DATA_CONTENT;
import static com.dcvg.practice.database.SQliteHelperManager.DATA_ID;
import static com.dcvg.practice.database.SQliteHelperManager.DATA_STATUS;
import static com.dcvg.practice.database.SQliteHelperManager.DATA_TABLE;

public class DataDAO {

    private SQliteHelperManager sqLiteHelperManageStudent;

    public DataDAO(Context context) {
        this.sqLiteHelperManageStudent = new SQliteHelperManager(context);
    }

    public long insertData(Data data) {
        long result = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATA_CONTENT, data.getContent());
        contentValues.put(DATA_STATUS, data.getStatus());
        SQLiteDatabase sqLiteDatabase = sqLiteHelperManageStudent.getWritableDatabase();
        result = sqLiteDatabase.insert(DATA_TABLE, null, contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public long updateData(Data data, int idData) {
        long result = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATA_CONTENT, data.getContent());
        contentValues.put(DATA_STATUS, data.getStatus());
        SQLiteDatabase sqLiteDatabase = sqLiteHelperManageStudent.getWritableDatabase();
        result = sqLiteDatabase.update(DATA_TABLE,  contentValues,DATA_ID + "=?",
                new String[]{String.valueOf(idData)});
        sqLiteDatabase.close();
        return result;
    }

    public long deleteData(int idData) {
        long result = -1;
        SQLiteDatabase sqLiteDatabase = sqLiteHelperManageStudent.getWritableDatabase();
        result = sqLiteDatabase.delete(DATA_TABLE, DATA_ID + "=?", new String[]{String.valueOf(idData)});
        return result;
    }

    public List<Data> getAllDatas() {
        List<Data> dataList = new ArrayList<>();
        dataList.clear();
        String query = "SELECT * FROM " + DATA_TABLE;
        SQLiteDatabase sqLiteDatabase = sqLiteHelperManageStudent.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    Data data = new Data(
                            cursor.getString(cursor.getColumnIndex(DATA_CONTENT)),
                            cursor.getString(cursor.getColumnIndex(DATA_STATUS)),
                            cursor.getInt(cursor.getColumnIndex(DATA_ID))
                    );
                    dataList.add(data);
                    cursor.moveToNext();
                }
                cursor.close();
                sqLiteDatabase.close();
            }
        }
        return dataList;
    }

}
