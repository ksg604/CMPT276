package com.example.kevin.servingsizecalculator;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Math;

import org.w3c.dom.Text;

public class CalculateActivity extends AppCompatActivity {
    private int weightOfFood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        setupEmptyPotSpecs();
        setupBackBtn();
        calculateWeightWithFood();
        calculateServingWeight();
    }
    public static Intent makeLaunchIntent(Context context, Pot pot) {
        if (context == null) {
            return null;
        }
        if (pot == null) {
            return null;
        }
        Intent intent = new Intent(context,CalculateActivity.class);
        intent.putExtra("potName5", pot.getName());
        intent.putExtra("potWeight5", pot.getWeightInG());

        return intent;

    }

    private void setupEmptyPotSpecs(){
        Intent intent = getIntent();
        String potName = intent.getExtras().getString("potItemName");
        int potWeight = intent.getExtras().getInt("potItemWeight");

        TextView potNameTxt = findViewById(R.id.potNameCalc);
        potNameTxt.setText(potName);
        TextView potWeightTxt = findViewById(R.id.potWeightCalc);
        potWeightTxt.setText(""+potWeight);
        TextView weightOfFoodTxt = findViewById(R.id.weightOfFoodCalc);
        weightOfFoodTxt.setText("");
        TextView servingsWeight = findViewById(R.id.servingsWeightDispCalc);
        servingsWeight.setText("");
    }

    private void calculateWeightWithFood(){
        Intent intent = getIntent();
        final int potWeight = intent.getExtras().getInt("potItemWeight");
        final EditText potWithFoodEntry = findViewById(R.id.weightWithFoodInputCalc);
        potWithFoodEntry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String potWithFoodData = potWithFoodEntry.getText().toString();
                if(potWithFoodData.equals("")){
                    potWithFoodData = "0";
                }
                int potWithFoodWeightData = Integer.parseInt(potWithFoodData);

                //Pot with food in it must weigh more than empty pot.
                if(potWithFoodWeightData > potWeight){
                    weightOfFood = potWithFoodWeightData - potWeight;
                    String foodWeightString = Integer.toString(weightOfFood);
                    TextView foodWeightTxt = findViewById(R.id.weightOfFoodCalc);
                    foodWeightTxt.setText("" + foodWeightString);


                }else{
                    TextView weightOfFoodTxt =findViewById(R.id.weightOfFoodCalc);
                    Log.i("Servings", "Error: Invalid Input.  Weight of pot with food in it must be greater than empty pot weight.");
                    Toast.makeText(getApplicationContext(), "Please input a number greater than or equal to empty pot weight.", Toast.LENGTH_LONG)
                            .show();
                    weightOfFoodTxt.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }


    private void calculateServingWeight(){


        final EditText numServingsEntry = findViewById(R.id.numServingsInputCalc);
        numServingsEntry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String numServingsData = numServingsEntry.getText().toString();
                if(numServingsData.equals(""))
                {
                    numServingsData = "1";
                }
                int numServingsDataInt = Integer.parseInt(numServingsData);

                if(numServingsDataInt >= 1){
                    float numServings = (float)(weightOfFood / numServingsDataInt);

                    int eachServing = Math.round(numServings);

                    String eachServingString = Integer.toString(eachServing);

                    TextView servingsWeight =findViewById(R.id.servingsWeightDispCalc);

                    servingsWeight.setText(""+eachServingString);
                }else{
                    TextView servingsWeight =findViewById(R.id.servingsWeightDispCalc);
                    Log.i("Servings", "Error: # Servings must be greater than or equal to 1.");
                    Toast.makeText(getApplicationContext(), "Please input a number greater than or equal to 1.", Toast.LENGTH_LONG)
                            .show();
                    servingsWeight.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void setupBackBtn(){
        Button btn = findViewById(R.id.backBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalculateActivity.this.finish();
            }
        });
    }

}
