package me.nayan.fitwithnutrition;

import android.database.Cursor;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import me.nayan.fitwithnutrition.adapter.ChartListAdapter;
import me.nayan.fitwithnutrition.databse.BreakfastBD;
import me.nayan.fitwithnutrition.databse.DatabaseHelper;
import me.nayan.fitwithnutrition.databse.FoodDB;
import me.nayan.fitwithnutrition.fragments.DatePickerFragment;

public class BreakfastChart extends AppCompatActivity {

    ImageButton closeBtn;
    ListView breakfastList;
    BreakfastBD breakfastBD;
    FoodDB foodDB;
    String date;

    Button button;
    private DialogFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast_chart);

        closeBtn = findViewById(R.id.close_btn);
        breakfastList = findViewById(R.id.breakfast_list);

        button = (Button) findViewById(R.id.button);

        breakfastBD = new BreakfastBD(this);
        foodDB = new FoodDB(this);

        setDate();

        viewBreakfast(date);

        listRefresh();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });


        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    int[] foodId;
    String[] foodName;
    String[] foodCat;
    String[] foodCal;
    String[] foodProtine;
    String[] foodCarbs;
    String[] foodFat;
    int[] serialId;

    int i = 0;
    String[] time;
    private void viewBreakfast(String date){
        Cursor crs = breakfastBD.getDailyBreakfast(date);

        foodId = new int[crs.getCount()];
        foodName = new String[crs.getCount()];
        foodCat = new String[crs.getCount()];
        foodCal = new String[crs.getCount()];
        foodProtine = new String[crs.getCount()];
        foodCarbs = new String[crs.getCount()];
        foodFat = new String[crs.getCount()];
        time = new String[crs.getCount()];
        serialId = new int[crs.getCount()];


        int i=0;
        while(crs.moveToNext()){
            int fId = crs.getInt(crs.getColumnIndex(DatabaseHelper.COL_FOOD_ID));
            String fdN = crs.getString(crs.getColumnIndex(DatabaseHelper.COL_FOOD_NAME));
            String fC = crs.getString(crs.getColumnIndex(DatabaseHelper.COL_FOOD_CAT));
            String fcal = crs.getString(crs.getColumnIndex(DatabaseHelper.COL_CALORIES));
            String fCar = crs.getString(crs.getColumnIndex(DatabaseHelper.COL_CARBS));
            String fPro = crs.getString(crs.getColumnIndex(DatabaseHelper.COL_PROTINE));
            String fFat = crs.getString(crs.getColumnIndex(DatabaseHelper.COL_FAT));
            String dt = crs.getString(crs.getColumnIndex(DatabaseHelper.COL_DATE));
            foodId[i] = fId;
            foodName[i] = fdN;
            foodCat[i] = fC;
            foodCal[i] = fcal;
            foodCarbs[i] = fCar;
            foodProtine[i] = fPro;
            foodFat[i] = fFat;
            time[i] = "7.00 AM";
            serialId[i] = i+1;
            i+=1;
            if (i == crs.getCount()){
                listRefresh();
            }
        }
    }


    private void foodDetails(int fId){
        Cursor crs = foodDB.getSingleFood(fId);
        while(crs.moveToNext()){
            int fd = crs.getInt(crs.getColumnIndex(DatabaseHelper.COL_FOOD_ID));
            String fN = crs.getString(crs.getColumnIndex(DatabaseHelper.COL_FOOD_NAME));
            float fC = crs.getFloat(crs.getColumnIndex(DatabaseHelper.COL_CALORIES));
            float fP = crs.getFloat(crs.getColumnIndex(DatabaseHelper.COL_PROTINE));


        }
    }

    private void listRefresh(){
        // Grid View Handler
        ChartListAdapter chartListAdapter = new ChartListAdapter(this, serialId, foodName, foodCal, time);
        breakfastList.setAdapter(chartListAdapter);
        i=0;

        breakfastList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(BreakfastChart.this, "Item Deleted", Toast.LENGTH_SHORT).show();

                return false;
            }
        });
    }

    /**
     * Handle the new set date here.
     * @param year
     * @param month
     * @param day
     */
    Calendar c;
    public void onDateSet(int year, int month, int day){
        c = Calendar.getInstance();
        if(year == c.get(Calendar.YEAR) && month == c.get(Calendar.MONTH) &&
                day == c.get(Calendar.DAY_OF_MONTH)) {
            button.setText("Today");
            setDate();
        } else if (year == c.get(Calendar.YEAR) && month == c.get(Calendar.MONTH) &&
                day == (c.get(Calendar.DAY_OF_MONTH)) +1) {
            button.setText("Tomorrow");
            setDate();
        }else {
            button.setText(day+"/"+(month+1)+"/"+year);
            setDate();
        }
    }

    private void showDatePickerDialog(){
        if(fragment == null) {
            fragment = new DatePickerFragment(this);
        }
        fragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void setDate(){
        c = Calendar.getInstance();

        if(button.getText().toString().equalsIgnoreCase("Today")){

            String year = String.valueOf(c.get(Calendar.YEAR));
            String month = String.valueOf(c.get(Calendar.MONTH));
            String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
            date = day+"/"+(month+1)+"/"+year;
            viewBreakfast(date);
        } else if(button.getText().toString().equalsIgnoreCase("Tomorrow")){
            String year = String.valueOf(c.get(Calendar.YEAR));
            String month = String.valueOf(c.get(Calendar.MONTH));
            String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH)+1);
            date = day+"/"+(month+1)+"/"+year;
            viewBreakfast(date);
        } else {
            date = button.getText().toString();
            viewBreakfast(date);
        }
    }

}
