package me.nayan.fitwithnutrition;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import me.nayan.fitwithnutrition.databse.UserDB;

public class SignUp extends AppCompatActivity {

    ImageButton backBtn;
    AutoCompleteTextView fullNameET;
    AutoCompleteTextView emilET;
    EditText passwordET;
    EditText rePassET;
    RadioGroup gender;
    AppCompatRadioButton femaleRB;
    AppCompatRadioButton maleRB;
    EditText ageUserET;
    EditText weightUserET;
    EditText heightUserET;
    Button signUpBtn;
    TextView clickHere;

    String fullName, emil, password, rePass, userGender;
    int ageUser, weightUser, heightUser;

    UserDB userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        backBtn = findViewById(R.id.back_btn);
        fullNameET = findViewById(R.id.full_name_et);
        emilET = findViewById(R.id.email_et);
        passwordET = findViewById(R.id.password_et);
        rePassET = findViewById(R.id.retype_ps_et);

        gender = findViewById(R.id.gender);
        femaleRB = findViewById(R.id.radio_female);
        maleRB = findViewById(R.id.radio_male);

        ageUserET = findViewById(R.id.age_et);
        weightUserET = findViewById(R.id.weight_et);
        heightUserET = findViewById(R.id.height_et);
        signUpBtn = findViewById(R.id.sign_in_button);
        clickHere = findViewById(R.id.click_here_tv);


        userDB = new UserDB(this);


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userReg();
            }
        });

        clickHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, SignUp.class));
                finish();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void userReg(){
        fullName = fullNameET.getText().toString();
        emil= emilET.getText().toString();
        password= passwordET.getText().toString();
        rePass=rePassET.getText().toString();
        String au=ageUserET.getText().toString();
        String wu=weightUserET.getText().toString();
        String hu=heightUserET.getText().toString();

        ageUser = Integer.parseInt(au);
        weightUser = Integer.parseInt(wu);
        heightUser = Integer.parseInt(hu);
        // Collect gender data
        getDataFromRadioBtn();
        bodyMass();
        userDB.insertUserData(fullName, emil, password, ageUser, userGender, weightUser, heightUser,BMI);
    }

    double BMI = 0;
    private void bodyMass(){

        double h = (double)heightUser/100.00;
        BMI = (double)weightUser/(h*h);
        if (BMI>=18.5 && BMI<=24.9){
            BMImessage("You are a healthy person");
        }else if (BMI<18.5){
            BMImessage("You are not a healthy person. You need to increase wait.");
        } else if (BMI>24.9){
            BMImessage("You are an over weighted person.You need to decrease wait.");
        }

    }

    public void BMImessage(String txt){

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_for_level_complition);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        TextView textView = dialog.findViewById(R.id.content);
        textView.setText(" Your BMI is "+ BMI + " " + txt);

        dialog.findViewById(R.id.bt_back).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, Home.class));
                dialog.dismiss();
                finish();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);


    }

    public void getDataFromRadioBtn() {
        // get selected radio button from radioGroup
        int selectedId = gender.getCheckedRadioButtonId();
        // find the radiobutton by returned id
        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        userGender = radioButton.getText().toString();
    }
}
