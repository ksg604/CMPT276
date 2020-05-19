package com.example.kevin.servingsizecalculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPotActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pot);

        setupCancelButton();
        setupOkButton();

    }

    public static Pot getPotFromIntent(Intent data){
        if(data == null)
        {
            return null;
        }
        String potName = data.getExtras().getString("potNameEntry");
        int potWeight = data.getExtras().getInt("potWeightEntry");
        Pot newPot = new Pot(potName,potWeight);
        return newPot;
    }

    private void setupCancelButton(){
        Button btn = findViewById(R.id.cancelBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPotActivity.this.finish();
            }
        });
    }

    private void setupOkButton(){
        Button btn = findViewById(R.id.okBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText userPotNameEntry = findViewById(R.id.editPotName);
                String potNameData = userPotNameEntry.getText().toString();

                EditText userPotWeightEntry =  findViewById(R.id.editPotWeight);
                String potWeightData = userPotWeightEntry.getText().toString();
                if(potWeightData.equals("")){
                    potWeightData = "0";
                }
                int potWeightDataInt = Integer.parseInt(potWeightData);

                Intent intent = new Intent();
                intent.putExtra("potNameEntry", potNameData);
                intent.putExtra("potWeightEntry", potWeightDataInt);
                setResult(Activity.RESULT_OK, intent);
                finish();

            }
        });
    }
}
