package me.nayan.fitwithnutrition;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.Calendar;

import me.nayan.fitwithnutrition.fragments.DatePickerFragment;

public class LunchChart extends AppCompatActivity {

    ImageButton closeBtn;
    ListView lunchList;

    Button button;
    private DialogFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch_chart);

        closeBtn = findViewById(R.id.close_btn);
        lunchList = findViewById(R.id.lunch_list);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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


    /**
     * Handle the new set date here.
     * @param year
     * @param month
     * @param day
     */
    public void onDateSet(int year, int month, int day){
        Calendar c = Calendar.getInstance();
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
