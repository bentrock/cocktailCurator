package com.example.bendev.cocktailcurator;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DrinkActivity extends AppCompatActivity {

    static String drinkName;
    String ingredients;
    String recipe;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
        System.out.println("drinkName is" + drinkName);
        getData();
        //set text
        TextView ingredText = (TextView) findViewById(R.id.ingred);
        TextView recText = (TextView) findViewById(R.id.recipe);
        TextView nameText = (TextView) findViewById(R.id.drinkNameField);
        ingredText.setText(ingredients);
        recText.setText(recipe);
        nameText.setText(drinkName);
        //set photo
        ImageView drinkPhoto = (ImageView) findViewById(R.id.drinkPhoto);
        //creates a no space version of drinkName in order to comply with image format
        String noSpace = drinkName.toLowerCase().replaceAll("\\s", "");
        System.out.println(noSpace + "=noSpace");
        //finds image resource
        int imageGen = getResources().getIdentifier(noSpace, "drawable", "com.example.bendev.cocktailcurator");

        drinkPhoto.setImageResource(imageGen);


    }

    public static void setDrinkName(String dN) {
        drinkName = dN;
    }

    public void getData() {
        Context context = this;
        //setUp database
        DatabaseOps dBos = new DatabaseOps(context);

        Cursor cr = dBos.findbyName(dBos, drinkName);
        cr.moveToFirst();
        ingredients = cr.getString(3);
        recipe = cr.getString(4);
    }


    public void launchMaps(View view) {



        System.out.println("get location");


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    //request permision
                    this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    0);
            return;
        }
        LocationManager locateMan = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locateMan.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        Double lat=0.0;
        Double lon=0.0;
        try {
            lat = location.getLatitude();
            lon = location.getLongitude();
        }
        catch(Exception e){
            Toast toast = Toast.makeText(context, "Last location not found.\n Open google maps to your present location and try again", Toast.LENGTH_SHORT);
            toast.show();

        }

        Uri gmmIntentUri = Uri.parse("geo:"+lat+","+lon+"?q=" + Uri.encode("liquor store"));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }

    }

}
