package com.example.bendev.cocktailcurator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class StartScreen extends AppCompatActivity {

    Context context = this;
    DatabaseOps dBos = new DatabaseOps(context);
    String drinkName;


    String taste;
    //holds string before it is case formated


    protected void onCreate(Bundle savedInstanceState) {
        Context contxt =this;
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start_screen);
        //prevents database from being created multiple times by only allowing it to run the first time tz
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if(!preferences.getBoolean("dbConfirm",false)){
            loadDrinks();
        }
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("dbConfirm",true);
        editor.commit();

        //dBos.deleteDatabase(dBos);


    }
    public void onClickgo(View view){
        EditText searchText =(EditText) findViewById(R.id.searchText);
        drinkName =searchText.getText().toString();
        if(drinkName!=null){
            Cursor cr =dBos.yieldAll(dBos);
            ArrayList<String> resultList = new ArrayList<String>();
            cr.moveToFirst();
            do{
               resultList.add(cr.getString(0));
            }while(cr.moveToNext());
            if(resultList.contains(drinkName)){
                DrinkActivity.setDrinkName(drinkName);

                startActivity(new Intent(StartScreen.this, DrinkActivity.class));

            }
            else{
                Toast toast = Toast.makeText(context, "Not Found", Toast.LENGTH_SHORT);
                toast.show();
            }
        }


    }


    public void onClickTaste(View view) {
        Button t = (Button) view;
        taste = t.getText().toString();
        //changes to lowercase to confirm to database entries
        taste=taste.toLowerCase();




        //sends taste to liqourActivity where querries aquire
        LiquorActivity.taste(taste);

        //switches activity
        startActivity(new Intent(StartScreen.this, LiquorActivity.class));


    }
    public void sendTaste(){
        taste=taste;
    }

    public void loadDrinks (){
        DatabaseOps dBops = new DatabaseOps(context);
        //bold drinks
        dBops.addDrink(dBops,"God Father","bold","scotch","Scotch \nAmaretto", "Combine equal parts scotch and Amaretto and stir. Serve on the rocks in a old fashioned glass.  Optional garnish a horses head if you want to send a message");

        dBops.addDrink(dBops,"Old Fashioned","bold","bourbon" , "4.5 cL Bourbon or Rye whiskey \n2 dashes Angostura bitters \n1 sugar cube" , "Few dashes plain water,Place sugar cube in an old fashioned glass add bitters and allow them to saturate sugar cube. Add a dash of water. Muddle in glass until disolved.  Fill glass with ice cubes and add Bourbon. Garnish with orange twist and serve");

        dBops.addDrink(dBops,"French Connection","bold","cognac","Cognac \nAmaretto","Combine equal parts cognac and amaretto. Pour over ice in an old fashioned glass");

        dBops.addDrink(dBos,"Martini","bold","gin","Gin \n Vermouth \nLemon or Olives","Using a mixing glass with ice cubes add 6 parts gin and one part vermouth. Stir well. You may have heard you should have this particular beverage  shaken by a Brit who shall remain nameless. However this actually makes the drink weaker.  Pour into a chilled martini glass. squeeze a small bit of oil from a lemon peel or garnish with an olive");

        dBops.addDrink(dBos,"Darker and Stormier","bold","rum","Rum \nAlcoholic Ginger Beer","Much like a dark and storm only its darker and stormier. Simply add one to two parts rum to 6 parts alcoholic ginger beer in a pint glass. Ice is optional");


        dBops.addDrink(dBops,"Black Russian","bold","vodka","2 Parts coffee liqueur \n5 parts vodka","Pour the coffee liqueur and vodka together in an old fashioned glass filled with ice cubes. Stir gently comrade");

        //citrus drinks

        dBops.addDrink(dBops,"Blood and Sand","citrus","scotch","1 Part Blended Scotch \n1 Part Blood Orange Juice /n1 part sweet vermouth 1 part Cherry Heering","Fill a cocktail shaker with ice cubes and then add all  ingredients. Strain into a cocktail or Collins glass.  Now comes the slightly tricky bit.  First take and orange and slice of a  off a small circular bit of orange peel leaving the fruit itself untouched. Then using a lighter apply heat to the orange so that it begins to warm up. While keeping the flame on the orange peel carefully squeze the orange peel into the martini glass. Drop the orange peel in the glass as garnish.");

        dBops.addDrink(dBops,"Whiskey Sour","citrus","bourbon","3 Parts Bourbon \n2 Parts fresh lemon juice \n1 Part Gommee syrup \n1 dash of eggwhite (optional)","Shake all ingredients together with ice and strain into a chilled cocktail glass. Add a slice of lemon as garnish and serve");

        dBops.addDrink(dBops,"Side Car","citrus","cognac","5 Parts Cognac \n2 Parts triple sec \n2 Parts lemon juice","Fill a cocktail shaker with ice cubes and then add all  ingredients.  Shake well and strain into a cocktail glass");

        dBops.addDrink(dBops,"Negroni","citrus","gin","1 Part Gin \n1 Part sweet red vermouth \n1 Part: Campari","Place ice into an old fashioned glass. Add all ingrigents and stir. Add an organge slice as garnish");

        dBops.addDrink(dBops,"Mojito","citrus","rum","4 Parts White Rum \n3 Parts fresh lime juice 6 sprigs  (a sprig is a stem of mint that has three or more leaves) /n2 Teaspoons of sugar /nSoda Water","Muddle mint leaves with sugar and lime juice add just a touch of soda water and then fill a collins glass with cracked ice. Add the rum and fill the remainder of the glass with soda water. Add mint leaves as garnish and serve with a straw");

        dBops.addDrink(dBops,"Moscow Mule","citrus","vodka","9 Parts Vodka \n1 Part lime juice \n24 Parts ginger beer \n 1 sprig (3 leaves on a stem) mint","In a highball glass filled with ice add the ginger beer vodka, and lime juice. Stir gently comrade.  Pour into a copper mug and garnish with a lime slice and mint");

        //sweet drinks

        dBops.addDrink(dBops,"Presbyterian","sweet","scotch", "2 Parts Scotch \n4 Parts Ginger Ale", "Add 2-3 ice cubes in a Tom Collin’s glass then Combine Scotch and ginger ale.");

        dBops.addDrink(dBops,"Mint Julep","sweet","bourbon","6 Parts Bourbon \n4 mint leaves \n1 Teaspoon powdered sugar \n1 Part Water","Muddle the mint, sugar and awater in a high ball glass. Fill glass with cracked ice and add Bourbon. Stir well y'all. Continue stirring until the glass is frosted and add a mint sprig (3 leaves of mint still on the stem) as garnish");

        dBops.addDrink(dBops, "Alexander","sweet","cognac","1 Part Cognac \n1 Part white crème de cacao \n1 Part light cream","Using a cocktail shaker shake all the ingredients with ice and then strain into a cocktail glass. Sprinkle nutmeg  on the top as garnish and serve");

        dBops.addDrink(dBops,"Cuba Libre","sweet","rum", "1 Part Rum \n2 Parts Coke \n Lime (optional)", "Also Known as a Rum and Cole this beverage contains rum and also some coke. To make add rum and coke to a Tom Collins Glass filed with ice and then add just a dash of lime if you so desire ");

        dBops.addDrink(dBops,"Tom Collins","sweet","gin","3 Parts Gin \n2 Parts fresh squeezed lemon juice \n1 Part sugar syrup \n4 parts soda water","in a collins glass with ice mix gin, lemon juice and sugar syrup. Add soda water to taste. Garnish with a slice of lemon");

        dBops.addDrink(dBops,"Appletini","sweet","vodka","3 Parts Vodka \nPart Apple schnapps \n1 Part Cointreau","In a cocktail shaker combine all ingredients. Pour into a chilled cocktail glass , garnish with an apple slice and serve."  );
    }


}

