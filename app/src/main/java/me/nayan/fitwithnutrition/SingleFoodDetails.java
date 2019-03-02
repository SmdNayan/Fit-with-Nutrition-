package me.nayan.fitwithnutrition;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import me.nayan.fitwithnutrition.databse.BreakfastBD;
import me.nayan.fitwithnutrition.databse.DinnerDB;
import me.nayan.fitwithnutrition.databse.LunchDB;
import me.nayan.fitwithnutrition.fragments.DatePickerFragment;

public class SingleFoodDetails extends AppCompatActivity {

    Toolbar toolbar;
    ImageButton closeBtn;
    ImageButton saveBtn;
    TextView foodNameTv;
    ImageView mainFoodImage;
    TextView calories;
    TextView protine;
    TextView carbs;
    TextView fat;
    Spinner spinner;
    Button addBtn;

    Bundle bundle;

    String foodName, foodCat, prot, cal, crb, ft;
    int foodId;

    BreakfastBD breakfastBD;
    LunchDB lunchDB;
    DinnerDB dinnerDB;

    // Date Initialization
    String date;

    Button button;
    private DialogFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_food_details);

        toolbar = findViewById(R.id.toolbar);
        closeBtn = findViewById(R.id.close_btn);
        saveBtn = findViewById(R.id.save_btn);
        foodNameTv = findViewById(R.id.food_name);
        mainFoodImage = findViewById(R.id.food_main_image);
        calories = findViewById(R.id.calories);
        protine = findViewById(R.id.protine);
        carbs = findViewById(R.id.carbs);
        fat = findViewById(R.id.fat);
        spinner = findViewById(R.id.spinner);
        addBtn = findViewById(R.id.add_btn);

        breakfastBD = new BreakfastBD(this);
        lunchDB = new LunchDB(this);
        dinnerDB = new DinnerDB(this);

        // Get Category ID, that send from intent
        bundle = getIntent().getExtras();
        assert bundle != null;
        foodId= bundle.getInt("FOOD_ID");
        foodName= bundle.getString("FOOD_NAME");
        foodCat= bundle.getString("FOOD_CAT");
        cal= bundle.getString("FOOD_CAL");
        crb= bundle.getString("FOOD_CRB");
        prot= bundle.getString("FOOD_PTN");
        ft = bundle.getString("FOOD_FAT");


        calories.setText("Calories: "+cal);
        protine.setText("Protin: "+ prot);
        carbs.setText("Carb: "+ crb);
        fat.setText("Fat: "+ft);
        foodNameTv.setText(foodName);

        ArrayAdapter<CharSequence> categorySpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.mealTime, android.R.layout.simple_spinner_item);
        spinner.setAdapter(categorySpinnerAdapter);
        categorySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMeal();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMeal();
            }
        });

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });


    }

    private void saveMeal(){
        String mealTime = spinner.getSelectedItem().toString();
        c = Calendar.getInstance();

        if(button.getText().toString().equalsIgnoreCase("Today")){
            String year = String.valueOf(c.get(Calendar.YEAR));
            String month = String.valueOf(c.get(Calendar.MONTH));
            String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
            date = day+"/"+(month+1)+"/"+year;
        } else if(button.getText().toString().equalsIgnoreCase("Tomorrow")){
            String year = String.valueOf(c.get(Calendar.YEAR));
            String month = String.valueOf(c.get(Calendar.MONTH));
            String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH)+1);
            date = day+"/"+(month+1)+"/"+year;
        } else {
            date = button.getText().toString();
        }

        if(mealTime.equalsIgnoreCase("Breakfast")){
            breakfastBD.insertBreakfast(foodId, foodName, foodCat, cal, crb, prot, ft, date);
            Toast.makeText(this, "Breakfast added " + foodId + " " + date , Toast.LENGTH_SHORT).show();
        } else if(mealTime.equalsIgnoreCase("Lunch")){
            lunchDB.insertBreakfast(foodId, foodName, foodCat, cal, crb, prot, ft, date);
            Toast.makeText(this, "Lunch Added ", Toast.LENGTH_SHORT).show();
        } else if(mealTime.equalsIgnoreCase("Dinner")) {
            dinnerDB.insertBreakfast(foodId, foodName, foodCat, cal, crb, prot, ft, date);
            Toast.makeText(this, "Dinner Added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please Select prefer slot", Toast.LENGTH_SHORT).show();
        }

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
        } else if (year == c.get(Calendar.YEAR) && month == c.get(Calendar.MONTH) &&
                day == (c.get(Calendar.DAY_OF_MONTH)) +1) {
            button.setText("Tomorrow");
        }else {
            button.setText(day+"/"+(month+1)+"/"+year);
        }
    }

    private void showDatePickerDialog(){
        if(fragment == null) {
            fragment = new DatePickerFragment(this);
        }
        fragment.show(getSupportFragmentManager(), "datePicker");
    }

}
