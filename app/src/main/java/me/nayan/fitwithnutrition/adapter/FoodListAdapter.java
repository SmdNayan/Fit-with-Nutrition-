package me.nayan.fitwithnutrition.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import me.nayan.fitwithnutrition.R;

public class FoodListAdapter extends BaseAdapter {
    private Context context;
    private List<String> gridViewString;
    private List<Bitmap> gridViewImage;

    private String[] foodName;
    private String[] foodCategory;
    private String[] Protine;
    private String[] Calories;
    private String[] Carbs;

//    private ArrayList<GridItem> mGridData = new ArrayList<GridItem>();

    public FoodListAdapter(Context context, String[] foodName, String[] foodCategory, String[] Protine, String[] Calories, String[] carbs) {
        this.context = context;
        this.foodName = foodName;
        this.foodCategory = foodCategory;
        this.Protine = Protine;
        this.Calories = Calories;
        this.Carbs = carbs;
    }

    @Override
    public int getCount() {
        return foodName.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View gridView;
        if(convertView == null){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            gridView = inflater.inflate(R.layout.food_list, null);

        } else {
            gridView = convertView;
        }

        TextView fN = gridView.findViewById(R.id.food_name);
        TextView fC = gridView.findViewById(R.id.category);
        TextView p = gridView.findViewById(R.id.protine);
        TextView c = gridView.findViewById(R.id.calories);
        TextView carbs = gridView.findViewById(R.id.carbs);
        fN.setText(foodName[position]);
        fC.setText(foodCategory[position]);
        p.setText("Protine: "+Protine[position]);
        c.setText("Cal: "+Calories[position]);
        carbs.setText("Carbs: " + Carbs[position]);
        return gridView;
    }
}
