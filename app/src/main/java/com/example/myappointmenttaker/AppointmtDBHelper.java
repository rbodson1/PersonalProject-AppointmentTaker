package com.example.myappointmenttaker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AppointmtDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "myappointments.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String CREATE_TABLE_CONTACT =
         "create table appointment (_id integer primary key autoincrement, "
                 + "appointeename text not null, streetaddress text , "
                 + "phonenumber text, birthday text , "
                 + "time text  , style text,"
                 + "price text , estimatetime text);";

    public AppointmtDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(AppointmtDBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS appointment");
        onCreate(db);
    }

}
