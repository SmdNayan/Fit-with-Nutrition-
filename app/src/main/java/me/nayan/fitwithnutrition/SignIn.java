package me.nayan.fitwithnutrition;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import me.nayan.fitwithnutrition.databse.DatabaseHelper;
import me.nayan.fitwithnutrition.databse.UserDB;

public class SignIn extends AppCompatActivity implements View.OnClickListener {

    EditText userEmail_et;
    EditText password_et;

    AppCompatButton logInBtn;
    TextView register;

    UserDB userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        userEmail_et = findViewById(R.id.login_email_et);
        password_et = findViewById(R.id.login_password_et);
        logInBtn = findViewById(R.id.login_btn);
        register = findViewById(R.id.register_tv);

        logInBtn.setOnClickListener(this);
        register.setOnClickListener(this);

        userDB = new UserDB(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.login_btn:
                logInUser();
                break;
            case R.id.register_tv:
                startActivity(new Intent(SignIn.this, SignUp.class));
                break;
        }
    }

    private void logInUser(){
        String email = userEmail_et.getText().toString();
        String pass = password_et.getText().toString();
        userCheck(email, pass);
    }

    private boolean userCheck(String email, String pass){
        Cursor crs = userDB.getSingleUser(email);
//        if(crs.moveToFirst()){
//            Toast.makeText(this, "No User Found", Toast.LENGTH_SHORT).show();
//        }else {
            while (crs.moveToNext()){
                String em = crs.getString(crs.getColumnIndex(DatabaseHelper.COL_EMAIL));
                String ps = crs.getString(crs.getColumnIndex(DatabaseHelper.COL_PASSWORD));
                if(em.equals(email) && ps.equals(pass)){
                    Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, Home.class));
                    finish();
                } else {
                    Toast.makeText(this, "Email or password incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        //}
        return true;
    }
}
