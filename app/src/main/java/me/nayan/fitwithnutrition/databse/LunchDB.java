package me.nayan.fitwithnutrition.databse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LunchDB extends DatabaseHelper {
    public LunchDB(Context context) {
        super(context);
    }

    /*
     * CRUD On User Table
     */
    // Insert Data on User Table
    public boolean insertBreakfast(int foodId, String date){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_FOOD_ID, foodId);
        contentValues.put(COL_DATE, date);

        long res = database.insert(TABLE_NAME_FOUR, null, contentValues);
        return res != -1;
    }

    // View data from user Table
    public Cursor viewBreakfast(){
        SQLiteDatabase database = this.getWritableDatabase();
        return database.rawQuery("select * from " + TABLE_NAME_FOUR, null);
    }

    // Update data on user table
    public boolean updateBreakfast(int foodId, String date){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FOOD_ID, foodId);
        values.put(COL_FOOD_NAME, date);
        sqLiteDatabase.update(TABLE_NAME_FOUR, values, "food_id="+ foodId, null);
        return true;

    }

    // Get user data using user name
    public Cursor getSingleBreakfast(String date){
        SQLiteDatabase database = this.getWritableDatabase();
        return database.rawQuery("Select * from " + TABLE_NAME_FOUR + " where " + COL_DATE + "='" + date+"'", null);

    }

    // Delete data on user table
    public Integer deleteBreakfast(){
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete(TABLE_NAME_FOUR, null, null);
    }

}