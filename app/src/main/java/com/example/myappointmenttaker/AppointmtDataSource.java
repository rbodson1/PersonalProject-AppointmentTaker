package com.example.myappointmenttaker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmtDataSource {
    private SQLiteDatabase database;
    private AppointmtDBHelper dbHelper;

    public AppointmtDataSource(Context context) {
        dbHelper = new AppointmtDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertAppointment(Appointmt c) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();

            initialValues.put("appointeename", c.getAppointeeName());
            initialValues.put("streetaddress", c.getStreetAddress());
            initialValues.put("phonenumber", c.getPhoneNumber());
            initialValues.put("time", c.getTime());
            initialValues.put("style", c.getStyle());
            initialValues.put("price", c.getPrice());
            initialValues.put("estimatetime", c.getEstimateTime());


            didSucceed = database.insert("appointment", null, initialValues) > 0;
        }
        catch (Exception e) {
            //Do nothing -will return false if there is an exception
        }
        return didSucceed;
    }

    public boolean updateAppointment(Appointmt c) {
        boolean didSucceed = false;
        try {
            Long rowId = (long) c.getAppointmtID();
            ContentValues updateValues = new ContentValues();

            updateValues.put("appointeename", c.getAppointeeName());
            updateValues.put("streetaddress", c.getStreetAddress());
            updateValues.put("phonenumber", c.getPhoneNumber());
            updateValues.put("time", c.getTime());
            updateValues.put("style", c.getStyle());
            updateValues.put("price", c.getPrice());
            updateValues.put("estimatetime", c.getEstimateTime());


            didSucceed = database.update("appointment", updateValues, "_id=" + rowId, null) > 0;
        }
        catch (Exception e) {
            //Do nothing -will return false if there is an exception
        }
        return didSucceed;
    }

    public int getLastAppointmentId() {
        int lastId;
        try {
            String query = "Select MAX(_id) from appointment";
            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();
            lastId = cursor.getInt(0);
            cursor.close();
        }
        catch (Exception e) {
            lastId = -1;
        }
        return lastId;
    }

    public ArrayList<String> getContactName() {
        ArrayList<String> contactNames = new ArrayList<>();
        try {
            String query = "Select appointeename from appointment";
            Cursor cursor = database.rawQuery(query, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                contactNames.add(cursor.getString(0));
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch (Exception e) {
            contactNames = new ArrayList<String>();
        }
        return contactNames;
    }

    public ArrayList<Appointmt> getAppointments(String sortField, String sortOrder) {
        ArrayList<Appointmt> contacts = new ArrayList<Appointmt>();
        try {
            String query = "SELECT  * FROM appointment ORDER BY " + sortField + " " + sortOrder;
            Cursor cursor = database.rawQuery(query, null);

            Appointmt newAppointmemt;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                newAppointmemt = new Appointmt();
                newAppointmemt.setAppointmtID(cursor.getInt(0));
                newAppointmemt.setAppointeeName(cursor.getString(1));
                newAppointmemt.setStreetAddress(cursor.getString(2));
                newAppointmemt.setPhoneNumber(cursor.getString(3));
                newAppointmemt.setTime(cursor.getString(5));
                newAppointmemt.setStyle(cursor.getString(6));
                newAppointmemt.setPrice(cursor.getString(7));
                newAppointmemt.setEstimateTime(cursor.getString(8));

                contacts.add(newAppointmemt);
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch (Exception e) {
            contacts = new ArrayList<Appointmt>();
        }
        return contacts;
    }

    public Appointmt getSpecificAppointment(int appointmentId) {
        Appointmt appointment = new Appointmt();
        String query = "SELECT  * FROM appointment WHERE _id =" + appointmentId;
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            appointment.setAppointmtID(cursor.getInt(0));
            appointment.setAppointeeName(cursor.getString(1));
            appointment.setStreetAddress(cursor.getString(2));
            appointment.setPhoneNumber(cursor.getString(3));
            appointment.setTime(cursor.getString(5));
            appointment.setStyle(cursor.getString(6));
            appointment.setPrice(cursor.getString(7));
            appointment.setEstimateTime(cursor.getString(8));


            cursor.close();
        }
        return appointment;
    }

    public boolean deleteAppointment(int contactId) {
        boolean didDelete = false;
        try {
            didDelete = database.delete("appointment", "_id=" + contactId, null) > 0;
        }
        catch (Exception e) {
            //Do nothing -return value already set to false
        }
        return didDelete;
    }

}
