package me.nayan.fitwithnutrition;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import me.nayan.fitwithnutrition.databse.DatabaseHelper;
import me.nayan.fitwithnutrition.databse.UserDB;


public class WeightFragment extends Fragment {

    TextView weight;
    TextView bmi;
    EditText ageEt;
    Button updateAge;
    UserDB userDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weight, container, false);
        weight = view.findViewById(R.id.weight_tv);
        bmi = view.findViewById(R.id.bmi_tv);
        userDB = new UserDB(view.getContext());
        viewWeightBmi();
        weight.setText(weightU);
        bmi.setText(bmiU);

        ageEt = view.findViewById(R.id.age_et);
        updateAge = view.findViewById(R.id.btn_up_age);

        updateAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = ageEt.getText().toString();
                weight.setText(a);
                float ag = Float.parseFloat(a);

            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    String weightU, bmiU;

    public void viewWeightBmi(){
        Cursor crs = userDB.viewData();

        while(crs.moveToNext()){
            float w = crs.getFloat(crs.getColumnIndex(DatabaseHelper.COL_WEIGHT));
            float b = crs.getFloat(crs.getColumnIndex(DatabaseHelper.COL_BMI));

            weightU = String.valueOf(w);
            bmiU = String.valueOf(b);

        }

    }

}
