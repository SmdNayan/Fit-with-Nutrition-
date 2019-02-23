package me.nayan.fitwithnutrition;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import me.nayan.fitwithnutrition.databse.DatabaseHelper;
import me.nayan.fitwithnutrition.databse.UserDB;

public class NavigationHeader {

    public Activity activity;
    private TextView name;
    private AppCompatButton viewProfile;

    public NavigationHeader(Activity activity){
        this.activity = activity;
    }

    public void saveHeaderData(){



    }

}
