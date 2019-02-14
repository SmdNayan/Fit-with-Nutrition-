package me.nayan.fitwithnutrition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import me.nayan.fitwithnutrition.adapter.FoodListAdapter;

public class DinnerChart extends AppCompatActivity {

    ImageButton closeBtn;
    Spinner dateSpinner;
    ListView dinnerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinner_chart);

        closeBtn = findViewById(R.id.close_btn);
        dateSpinner = findViewById(R.id.date_spinner);
        dinnerList = findViewById(R.id.dinner_list);


        // Grid View Handler
//        FoodListAdapter adapter = new FoodListAdapter(DinnerChart.this, foodName, foodCat, Protine, calories, carbs);
//        dinnerList.setAdapter(adapter);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
