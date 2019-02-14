package me.nayan.fitwithnutrition;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class DiatChartFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_diat_chart, container, false);

        LinearLayout breakFast, lunch, dinner;
        breakFast = view.findViewById(R.id.brekfast_view);
        lunch = view.findViewById(R.id.lunch_view);
        dinner = view.findViewById(R.id.dinner_view);

        breakFast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BreakfastChart.class));
            }
        });

        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LunchChart.class));
            }
        });

        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DinnerChart.class));
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}
