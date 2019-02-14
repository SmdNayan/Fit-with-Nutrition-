package me.nayan.fitwithnutrition.databse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDB extends DatabaseHelper {
    public UserDB(Context context) {
        super(context);
    }

    /*
     * CRUD On User Table
     */
    // Insert Data on User Table
    public boolean insertUserData(String userName, String email, String password, int age, String gender, int weight, int height, double bmi){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USER_NAME, userName);
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_PASSWORD, password);
        contentValues.put(COL_AGE, age);
        contentValues.put(COL_GENDER, gender);
        contentValues.put(COL_WEIGHT, weight);
        contentValues.put(COL_HEIGHT, height);
        contentValues.put(COL_BMI, bmi);


        long res = database.insert(TABLE_NAME_ONE, null, contentValues);
        return res != -1;
    }

    // View data from user Table
    public Cursor viewData(){
        SQLiteDatabase database = this.getWritableDatabase();
        return database.rawQuery("select * from " + TABLE_NAME_ONE, null);
    }

    // Update data on user table
    public boolean updateData(String userName, String email, String password, int age, String gender, int weight, int height, double bmi){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USER_NAME, userName);
        values.put(COL_EMAIL, email);
        values.put(COL_PASSWORD, password);
        values.put(COL_AGE, age);
        values.put(COL_GENDER, gender);
        values.put(COL_WEIGHT, weight);
        values.put(COL_HEIGHT, height);
        values.put(COL_BMI, bmi);
        sqLiteDatabase.update(TABLE_NAME_ONE, values, "EMAIL = ?", new String[]{email});
        return true;

    }

    // Get user data using user name
    public Cursor getSingleUser(String email){
        SQLiteDatabase database = this.getWritableDatabase();
        return database.rawQuery("Select * from " + TABLE_NAME_ONE + " where " + COL_EMAIL + "='" + email +"'", null);

    }

    // Delete data on user table
    public Integer deleteData(){
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete(TABLE_NAME_ONE, null, null);
    }

}
