package me.nayan.fitwithnutrition.databse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class FoodDB extends DatabaseHelper {
    public FoodDB(Context context) {
        super(context);
    }

    /*
     * CRUD On User Table
     */
    // Insert Data on User Table
    public boolean insertFood(int foodId, String foodName, String foodDetails, byte[] foodImage, float calories, float protine, float carbs, float fat){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_FOOD_ID, foodId);
        contentValues.put(COL_FOOD_NAME, foodName);
        contentValues.put(COL_FOOD_DETAILS, foodDetails);
        contentValues.put(COL_FOOD_PHOTO, foodImage);
        contentValues.put(COL_CALORIES, calories);
        contentValues.put(COL_PROTINE, protine);
        contentValues.put(COL_CARBS, carbs);
        contentValues.put(COL_FAT, fat);

        long res = database.insert(TABLE_NAME_TWO, null, contentValues);
        return res != -1;
    }

    // View data from user Table
    public Cursor viewFood(){
        SQLiteDatabase database = this.getWritableDatabase();
        return database.rawQuery("select * from " + TABLE_NAME_TWO, null);
    }

    // Update data on user table
    public boolean updateFood(int foodId, String foodName, String foodDetails, byte[] foodImage, float calories, float protine, float carbs, float fat){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FOOD_ID, foodId);
        values.put(COL_FOOD_NAME, foodName);
        values.put(COL_FOOD_DETAILS, foodDetails);
        values.put(COL_FOOD_PHOTO, foodImage);
        values.put(COL_CALORIES, calories);
        values.put(COL_PROTINE, protine);
        values.put(COL_CARBS, carbs);
        sqLiteDatabase.update(TABLE_NAME_TWO, values, "food_id="+ foodId, null);
        return true;

    }

    // Get user data using user name
    public Cursor getSingleFood(int foodId){
        SQLiteDatabase database = this.getWritableDatabase();
        return database.rawQuery("Select * from " + TABLE_NAME_TWO + " where " + COL_FOOD_ID + "=" + foodId, null);

    }

    // Delete data on user table
    public Integer deleteFood(){
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete(TABLE_NAME_TWO, null, null);
    }


}
