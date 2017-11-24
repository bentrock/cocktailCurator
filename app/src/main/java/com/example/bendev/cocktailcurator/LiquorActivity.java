package com.example.bendev.cocktailcurator;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class LiquorActivity extends AppCompatActivity {

    //taste is already case formated
    static String taste="";
    String base="";
    //context import
    Context context = this;
    //setUp database
    DatabaseOps dBos = new DatabaseOps(context);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liquor);
    }
    public void onClickliquor(View view){
        Button l = (Button) view;

         base = l.getText().toString();
        //makes base confirm to lower case
        base=base.toLowerCase();

        //send to querry method
        query();
    }
    public static void taste(String t){
        taste=t;

    }
    public void query(){


        String wc = "wildcard";
        System.out.println("querry for "+ taste + " " + base);
        //discovery search
        if((taste.equals("wildcard")==false)&&(base.equals("wildcard")==false)){
            System.out.println("discover");
            Cursor cr =dBos.discoverDrink(dBos,taste,base);
            ArrayList<String> allList = new ArrayList<String>();
            cr.moveToFirst();
            do{
                allList.add(cr.getString(0));
                //yield names of drinks and adds to arrayList
            }while(cr.moveToNext());

            System.out.println(allList.size());
            //if multiple drinks then send to list
            if(allList.size()>1) {
                LiquorList.setList(allList);
                startActivity(new Intent(LiquorActivity.this, LiquorList.class));

            }
            //if not send to result
            else{
                //change first entry in list to a string
                DrinkActivity.setDrinkName(allList.get(0));
                startActivity(new Intent(LiquorActivity.this, DrinkActivity.class));
            }




        }
        //taste search
        if((taste.equals("wildcard")==false)&&(base.equals("wildcard")==true)){
            System.out.println("taste");

            Cursor cr =dBos.findByTaste(dBos,taste);
            ArrayList<String> tasteList = new ArrayList<String>();
            cr.moveToFirst();
            do{
                tasteList.add(cr.getString(0));
            }while(cr.moveToNext());
            System.out.println("yields"+tasteList);
            LiquorList.setList(tasteList);
            startActivity(new Intent(LiquorActivity.this, LiquorList.class));


        }
        //base search
        if((taste.equals("wildcard")==true)&&(base.equals("wildcard")==false)){
            System.out.println("base");
            Cursor cr =dBos.findByBase(dBos,base);
            ArrayList<String> baseList = new ArrayList<String>();
            cr.moveToFirst();
            do{
                baseList.add(cr.getString(0));
            }while(cr.moveToNext());
            System.out.println("yields"+baseList);
            LiquorList.setList(baseList);
            startActivity(new Intent(LiquorActivity.this, LiquorList.class));

        }

        //search all
        else if(taste.equals("wildcard")==true&&base.equals("wildcard")==true){
            System.out.println("search all");
            Cursor cr =dBos.yieldAll(dBos);
            ArrayList<String> baseList = new ArrayList<String>();
            cr.moveToFirst();
            do{
                baseList.add(cr.getString(0));
            }while(cr.moveToNext());
            LiquorList.setList(baseList);
            startActivity(new Intent(LiquorActivity.this, LiquorList.class));
        }

    }




}
