package com.example.sqlitedatabasedemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import static android.content.ContentValues.TAG;

public class MyDatabase extends SQLiteOpenHelper {
    //   Context context;
    public MyDatabase(@Nullable MainActivity mainActivity) {
        super(mainActivity, "student.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qaury = "create table student(id integer primary key,name text,surname text,cource text)";
        db.execSQL(qaury);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS student");
        onCreate(db);

    }


    public void insertData(MainActivity mainActivity, String name, String surname, String course) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
       // contentValues.put("id",id);
        contentValues.put("name", name);
        contentValues.put("surname", surname);
        contentValues.put("cource", course);

        long a = db.insert("student", null, contentValues);

        Log.e(TAG, "insertData: " + a);

            if (a == -1) {
                Toast.makeText(mainActivity, "Record is not inserted..!!!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mainActivity, "Record is inserted", Toast.LENGTH_SHORT).show();
            }

    }

//    public void updateData(MainActivity mainActivity, String id, String name, String surname, String cource) {
//        SQLiteDatabase database = getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", name);
//        contentValues.put("surname", surname);
//        contentValues.put("cource", cource);
//        long a = database.update("student", contentValues, "id=" + id, null);
//        Log.e(TAG, "insertData: " + a);
//        if (a == 1) {
//            Toast.makeText(mainActivity, "Record is Update", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(mainActivity, "Record is Not Update", Toast.LENGTH_SHORT).show();
//        }
//    }

//    public void deleteData(MainActivity mainActivity, String id) {
//        SQLiteDatabase database = getWritableDatabase();
//        long a = database.delete("student", "id=" + id, null);
//        if (a == 1) {
//            Toast.makeText(mainActivity, "Record is Deleted", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(mainActivity, "Record is not Delete", Toast.LENGTH_SHORT).show();
//        }
//    }

    public Cursor getData() {
        SQLiteDatabase database = getReadableDatabase();
        String query = "select * from student";
        Cursor cursor = database.rawQuery(query, null);
        return cursor;
    }

    public int deleteData(String id) {
        SQLiteDatabase database = getWritableDatabase();

        return database.delete("student", "id=" + id, null);
    }


    public int updateData(MainActivity activity, String setname, String setsurname, String setcorce, String setid) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",setid);
        contentValues.put("name", setname);
        contentValues.put("surname", setsurname);
        contentValues.put("cource", setcorce);

        return database.update("student", contentValues, "id="+setid, null);
    }
}
