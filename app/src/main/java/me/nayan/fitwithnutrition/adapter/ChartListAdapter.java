package me.nayan.fitwithnutrition.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import me.nayan.fitwithnutrition.R;

public class ChartListAdapter extends BaseAdapter {

    private Context context;

    private int[] foodNum;
    private String[] foodName;
    private String[] cal;
    private String[] time;

//    private ArrayList<GridItem> mGridData = new ArrayList<GridItem>();

    public ChartListAdapter(Context context, int[] foodNum, String[] foodName, String[] cal, String[] time) {
        this.context = context;
        this.foodNum = foodNum;
        this.foodName = foodName;
        this.cal = cal;
        this.time = time;
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

        View foodView;
        if(convertView == null){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            foodView = inflater.inflate(R.layout.food_list_two, null);

        } else {
            foodView = convertView;
        }

        TextView fN = foodView.findViewById(R.id.positionImage);
        TextView fC = foodView.findViewById(R.id.food_name);
        TextView p = foodView.findViewById(R.id.cal);
        TextView c = foodView.findViewById(R.id.time);
        fN.setText(String.valueOf(foodNum[position]));
        fC.setText(foodName[position]);
        p.setText("Calories: " +String.valueOf(cal[position]));
        c.setText(time[position]);
        return foodView;
    }

}
