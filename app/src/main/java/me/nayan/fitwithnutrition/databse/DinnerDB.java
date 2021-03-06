package me.nayan.fitwithnutrition.databse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DinnerDB extends DatabaseHelper {
    public DinnerDB(Context context) {
        super(context);
    }

    /*
     * CRUD On User Table
     */
    // Insert Data on User Table
    public boolean insertBreakfast(int foodId, String foodName, String foodCat, String cal,
                                   String carb, String prot, String fat,  String date){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_FOOD_ID, foodId);
        contentValues.put(COL_FOOD_NAME, foodName);
        contentValues.put(COL_FOOD_CAT, foodCat);
        contentValues.put(COL_CALORIES, cal);
        contentValues.put(COL_CARBS, carb);
        contentValues.put(COL_PROTINE, prot);
        contentValues.put(COL_FAT, fat);
        contentValues.put(COL_DATE, date);

        long res = database.insert(TABLE_NAME_FIVE, null, contentValues);
        return res != -1;
    }

    // View data from user Table
    public Cursor viewBreakfast(){
        SQLiteDatabase database = this.getWritableDatabase();
        return database.rawQuery("select * from " + TABLE_NAME_FIVE, null);
    }

    // Update data on user table
    public boolean updateBreakfast(int foodId, String date){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FOOD_ID, foodId);
        values.put(COL_FOOD_NAME, date);
        sqLiteDatabase.update(TABLE_NAME_FIVE, values, "food_id="+ foodId, null);
        return true;

    }

    // Get user data using user name
    public Cursor getSingleBreakfast(String date){
        SQLiteDatabase database = this.getWritableDatabase();
        return database.rawQuery("Select * from " + TABLE_NAME_FIVE + " where " + COL_DATE + "='" + date+"'", null);

    }

    // Delete data on user table
    public Integer deleteBreakfast(){
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete(TABLE_NAME_FIVE, null, null);
    }

}
