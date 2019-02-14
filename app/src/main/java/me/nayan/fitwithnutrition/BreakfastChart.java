package me.nayan.fitwithnutrition;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import me.nayan.fitwithnutrition.adapter.ChartListAdapter;
import me.nayan.fitwithnutrition.adapter.FoodListAdapter;
import me.nayan.fitwithnutrition.databse.BreakfastBD;
import me.nayan.fitwithnutrition.databse.DatabaseHelper;
import me.nayan.fitwithnutrition.databse.FoodDB;

public class BreakfastChart extends AppCompatActivity {

    ImageButton closeBtn;
    Spinner dateSpinner;
    ListView breakfastList;
    BreakfastBD breakfastBD;
    FoodDB foodDB;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast_chart);

        closeBtn = findViewById(R.id.close_btn);
        dateSpinner = findViewById(R.id.date_spinner);
        breakfastList = findViewById(R.id.breakfast_list);

        breakfastBD = new BreakfastBD(this);
        foodDB = new FoodDB(this);


        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        viewBreakfast(date);

        // Grid View Handler
        ChartListAdapter chartListAdapter = new ChartListAdapter(this, serialId, foodName, foodCal, time);
        breakfastList.setAdapter(chartListAdapter);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    int[] foodIdB;
    String[] dateB;
    private void viewBreakfast(String date){
        Cursor crs = breakfastBD.getDailyBreakfast(date);

        foodIdB = new int[crs.getCount()];
        dateB = new String[crs.getCount()];

        foodId = new int[crs.getCount()];
        foodName = new String[crs.getCount()];
        foodCal = new float[crs.getCount()];
        foodProtine = new float[crs.getCount()];
        serialId = new int[crs.getCount()];
        time = new String[crs.getCount()];

        int i=0;
        while(crs.moveToNext()){
            int fId = crs.getInt(crs.getColumnIndex(DatabaseHelper.COL_FOOD_ID));
            String dt = crs.getString(crs.getColumnIndex(DatabaseHelper.COL_DATE));

            foodIdB[i] = fId;
            dateB[i] = dt;
            foodDetails(fId);
            i +=1;
        }
    }

    int[] foodId;
    String[] foodName;
    float[] foodCal;
    float[] foodProtine;
    int i = 0;
    int[] serialId;
    String[] time;
    private void foodDetails(int fId){
        Cursor crs = foodDB.getSingleFood(fId);
        while(crs.moveToNext()){
            int fd = crs.getInt(crs.getColumnIndex(DatabaseHelper.COL_FOOD_ID));
            String fN = crs.getString(crs.getColumnIndex(DatabaseHelper.COL_FOOD_NAME));
            float fC = crs.getFloat(crs.getColumnIndex(DatabaseHelper.COL_CALORIES));
            float fP = crs.getFloat(crs.getColumnIndex(DatabaseHelper.COL_PROTINE));

            Toast.makeText(this, ""+fN, Toast.LENGTH_SHORT).show();

            foodId[i] = fd;
            foodName[i] = fN;
            foodCal[i] = fC;
            foodProtine[i] = fP;
            serialId[i] = i+1;
            time[i] = "7:00 AM";
            i +=1;

        }
    }

}
