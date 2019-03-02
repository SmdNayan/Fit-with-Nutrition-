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
import android.widget.Filter;
import android.widget.ListView;

import me.nayan.fitwithnutrition.adapter.FoodListAdapter;
import me.nayan.fitwithnutrition.databse.FoodDB;

public class HomeFragment extends Fragment {

    public FoodDB foodDB;
    public FoodListAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        final int[] foodId = {1, 2, 3, 4, 5,6, 7, 8,9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        final String[] foodName = {"Butter Salted", "Butter whipped", "Butter oil", "Cheese Blue", "Cheese Cream",
                "Apple", "Orange", "Graps", "Mango", "Banana", "Milk", "Bananna", "Pine Apple",
                "Litche", "Apple", "Orange", "Bananna", "Pine Apple", "Litche","Litche"};
        final String[] foodCat = {"Dairy and Egg","Dairy and Egg","Dairy and Egg","Dairy and Egg","Dairy and Egg","Fruits","Fruits","Fruits","Fruits","Fruits",
                "Dairy and Egg","Fruits","Fruits","Fruits","Fruits","Fruits","Fruits","Fruits","Fruits","Fruits"};
        final String[] calories = {"717","718","876","353","350","717","556","446","335","350", "63","200","200","200","200","200"
                ,"200","200","200","200"};
        final String[] carbs = {"0.06","2.87","0","2.34","5.52", "1.07","2.07","0.56","5.34","5.60", "0.8","100","100","100","100","100",
                "100","100","100","100"};
        final String[] Protine = {"0.85","0.49", "0.28", "21.4", "6.15", "0.89","0.30", "0.55", "0.99", "6.15", "3.33","4.74", "70", "70", "70","70","70",
                "70", "70", "70"};
        final String[] fat = {"81.11","78.3", "99.48", "28.74", "34.44", "91.11","88.3", "90.48", "90.74", "55.44", "3.4","40", "40", "40", "40","40","40",
                "40", "40", "40"};


        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                foodName
        );

        //foodList.setAdapter(listAdapter);

        //insert data into fooddb
        foodDB = new FoodDB(view.getContext());
        for (int i=0; i<foodId.length; i++){
            foodDB.insertFood(foodId[i], foodName[i], "Nice food", null, Float.parseFloat(calories[i]),
                    Float.parseFloat(Protine[i]), Float.parseFloat(carbs[i]), Float.parseFloat(fat[i]));
        }
        // Grid View Handler
        adapter = new FoodListAdapter(getActivity(), foodName, foodCat, Protine, calories, carbs);
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
                    b.putString("FOOD_FAT", fat[position]);
                    intent.putExtras(b);
                    startActivity(intent);
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}
