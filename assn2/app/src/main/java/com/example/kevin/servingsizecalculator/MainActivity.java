package com.example.kevin.servingsizecalculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private PotCollection potCollection;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        potCollection = new PotCollection();
        startAddPotActivity();
        startCalculateActivity();

    }

    private void startAddPotActivity() {


        Button mainBtn = findViewById(R.id.addPotBtn);
        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,AddPotActivity.class);
                startActivityForResult(intent, 7);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case 7:

                //String potName = data.getExtras().getString("potNameEntry");
               // int potWeight = data.getExtras().getInt("potWeightEntry");
                //Pot newPot = new Pot(potName,potWeight);
                if(AddPotActivity.getPotFromIntent(data) == null){
                    return;
                }
                else{
                    populateListView(AddPotActivity.getPotFromIntent(data));
                }

                break;
        }
    }

    private void startCalculateActivity(){
        list = findViewById(R.id.listViewMain);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String potName = potCollection.getPot(position).getName();
                int potWeight = potCollection.getPot(position).getWeightInG();
                Pot potItem = new Pot(potName,potWeight);

                CalculateActivity.makeLaunchIntent(MainActivity.this,potItem);

                //Intent intent = new Intent(MainActivity.this, CalculateActivity.class);
                Intent intent = CalculateActivity.makeLaunchIntent(MainActivity.this,potItem);
                intent.putExtra("potItemName", potName);
                intent.putExtra("potItemWeight",potWeight);
                startActivity(intent);
            }
        });
    }

    private void populateListView(Pot pot){
        if(pot == null) {
            return;
        }
        else{
            potCollection.addPot(pot);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.pot_items, potCollection.getPotDescriptions());
            ListView list = (ListView) findViewById(R.id.listViewMain);
            list.setAdapter(adapter);
        }

    }
}
