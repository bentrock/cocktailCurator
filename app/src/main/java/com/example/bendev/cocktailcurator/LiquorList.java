package com.example.bendev.cocktailcurator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class LiquorList extends AppCompatActivity {


    static ArrayList<String> list;
    //context import
    Context context = this;
    //setUp database
    DatabaseOps dBos = new DatabaseOps(context);
    //setup list view
    ListView lv;
    String drinkName;

    ArrayAdapter<String> adapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liquor_list);

        //transformers arraylist to listview
        lv = (ListView) findViewById(R.id.resultList);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                list );

        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("clicked!");
                Object result=lv.getItemAtPosition(i);
                drinkName=result.toString();
                DrinkActivity.setDrinkName(drinkName);
                startActivity(new Intent(LiquorList.this, DrinkActivity.class));
            }
        });


    }







    public static void setList(ArrayList <String> l){
        //gets arrayList to populate list
        list= new ArrayList<String>(l);


    }



}







