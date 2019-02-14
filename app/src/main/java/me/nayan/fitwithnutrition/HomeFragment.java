package me.nayan.fitwithnutrition;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import me.nayan.fitwithnutrition.adapter.FoodListAdapter;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        final int[] foodId = {1, 2, 3, 4, 5,6, 7, 8,9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        final String[] foodName = {"Apple", "Orange", "Bananna", "Pine Apple", "Litche", "Apple",
                "Orange", "Bananna", "Pine Apple", "Litche", "Apple", "Orange", "Bananna", "Pine Apple",
                "Litche", "Apple", "Orange", "Bananna", "Pine Apple", "Litche"};
        final String[] foodCat = {"Fruits","Fruits","Fruits","Fruits","Fruits","Fruits","Fruits","Fruits","Fruits","Fruits",
                "Fruits","Fruits","Fruits","Fruits","Fruits","Fruits","Fruits","Fruits","Fruits","Fruits"};
        final String[] calories = {"200","200","200","200","200","200","200","200","200","200","200"
                ,"200","200","200","200","200","200","200","200","200"};
        final String[] carbs = {"100","100","100","100","100","100","100","100","100","100","100",
                "100","100","100","100","100","100","100","100","100"};
        final String[] Protine = {"70%","70%", "70%", "70%", "70%","70%","70%", "70%", "70%", "70%","70%","70%",
                "70%", "70%", "70%","70%","70%", "70%", "70%", "70%"};


        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                foodName
        );

        //foodList.setAdapter(listAdapter);

        // Grid View Handler
        FoodListAdapter adapter = new FoodListAdapter(getActivity(), foodName, foodCat, Protine, calories, carbs);
        ListView foodList = view.findViewById(R.id.home_food_list);
        foodList.setAdapter(adapter);

        foodList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View previousSelectedView;
                Intent intent;
                if (position >= 0) {
                    // For save state delete previous data and insert current data and show
                    view.setBackgroundColor(Color.parseColor("#EA4C89"));

                    // Start level page and send catId to level Page
                    intent = new Intent(getActivity(), SingleFoodDetails.class);
                    //intent.putExtra("CAT_ID", catIdList.get(position));
                    Bundle b = new Bundle();
                    b.putInt("FOOD_ID", foodId[position]);
                    b.putString("FOOD_NAME", foodName[position]);
                    b.putString("FOOD_CAT", foodCat[position]);
                    b.putString("FOOD_CAL", calories[position]);
                    b.putString("FOOD_CRB", carbs[position]);
                    b.putString("FOOD_PTN", Protine[position]);
                    intent.putExtras(b);
                    startActivity(intent);
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}
