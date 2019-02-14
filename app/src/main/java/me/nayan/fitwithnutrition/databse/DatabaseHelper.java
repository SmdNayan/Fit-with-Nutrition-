package me.nayan.fitwithnutrition.databse;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Name
    private static final String DATABASE_NAME = "nutrition.db";
    private static final int DATABASE_VERSION = 2;

    // User Info table and column
    public final String TABLE_NAME_ONE = "user_info";
    public static final String COL_USER_NAME = "USER_NAME";
    public static final String COL_EMAIL = "EMAIL";
    public static final String COL_PASSWORD = "PASSWORD";
    public static final String COL_AGE = "USER_AGE";
    public static final String COL_GENDER = "USER_GENDER";
    public static final String COL_WEIGHT = "USER_WEIGHT";
    public static final String COL_HEIGHT = "USER_HEIGHT";
    public static final String COL_BMI = "BMI";

    // FOOD Info table and column
    public final String TABLE_NAME_TWO = "food";
    public static final String COL_FOOD_ID = "food_id";
    public static final String COL_FOOD_NAME = "food_name";
    public static final String COL_FOOD_DETAILS = "food_details";
    public static final String COL_FOOD_PHOTO = "photo";
    public static final String COL_CALORIES = "calories";
    public static final String COL_PROTINE = "protine";
    public static final String COL_CARBS = "carbs";
    public static final String COL_FAT = "fat";

    // Breakfast Info table and column
    public final String TABLE_NAME_THREE = "breakfast";
    public static final String COL_DATE = "date";

    // Lunch Info table and column
    public final String TABLE_NAME_FOUR = "lunch";

    // Dinner Info table and column
    public final String TABLE_NAME_FIVE = "dinner";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create User Table
        db.execSQL("create table " + TABLE_NAME_ONE + " (" + COL_USER_NAME + " text, "
                + COL_EMAIL + " text primary key, " + COL_PASSWORD + " text, " + COL_AGE + " int, "+ COL_GENDER + " text, "
                + COL_WEIGHT + " int, " +  COL_HEIGHT + " int, " + COL_BMI + " double" + ")");
        db.execSQL("create table "+ TABLE_NAME_TWO + "("+ COL_FOOD_ID + " int primary key, "+ COL_FOOD_NAME + " text, " + COL_FOOD_DETAILS + " text, "+
                COL_FOOD_PHOTO + " blob, "+ COL_CALORIES + " float, "+ COL_PROTINE + " float, "+ COL_CARBS + " float, " + COL_FAT + " float" +")");

        db.execSQL("create table " + TABLE_NAME_THREE + "("+COL_FOOD_ID + " int, " + COL_DATE + " text" +")");
        db.execSQL("create table " + TABLE_NAME_FOUR + "("+COL_FOOD_ID + " int, " + COL_DATE + " text" +")");
        db.execSQL("create table " + TABLE_NAME_FIVE + "("+COL_FOOD_ID + " int, " + COL_DATE + " text" +")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + TABLE_NAME_ONE);
        db.execSQL("Drop table if exists " + TABLE_NAME_TWO);
        db.execSQL("Drop table if exists " + TABLE_NAME_THREE);
        db.execSQL("Drop table if exists " + TABLE_NAME_FOUR);
        db.execSQL("Drop table if exists " + TABLE_NAME_FIVE);
    }

}
