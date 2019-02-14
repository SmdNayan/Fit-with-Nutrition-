package me.nayan.fitwithnutrition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import me.nayan.fitwithnutrition.adapter.FoodListAdapter;

public class LunchChart extends AppCompatActivity {

    ImageButton closeBtn;
    Spinner dateSpinner;
    ListView lunchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch_chart);

        closeBtn = findViewById(R.id.close_btn);
        dateSpinner = findViewById(R.id.date_spinner);
        lunchList = findViewById(R.id.lunch_list);


        // Grid View Handler
//        FoodListAdapter adapter = new FoodListAdapter(LunchChart.this, foodName, foodCat, Protine, calories, carbs);
//        lunchList.setAdapter(adapter);


        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
