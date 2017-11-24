package com.example.bendev.cocktailcurator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Database opperations carried out here
 */

public class DatabaseOps extends SQLiteOpenHelper {
    public static final int db_ver=1;
    public String CREATE_QUERY = "CREATE TABLE  "+ Table.TableInfo.T_NAME +
            "("+ Table.TableInfo.COL_1+" TEXT,"+ Table.TableInfo.COL_2+" TEXT,"
            + Table.TableInfo.COL_3 +" TEXT," + Table.TableInfo.COL_4 +" TEXT,"
            + Table.TableInfo.COL_5 +" TEXT," + Table.TableInfo.COL_6+" TEXT);";

    public DatabaseOps(Context context) {
        super(context, Table.TableInfo.DB_NAME, null, db_ver);
        Log.d("Database opps","Database is created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);

        Log.d("Database opps","Table is created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void addDrink(DatabaseOps dOps, String drinkName, String taste, String base, String ingred, String recipe  ){

        SQLiteDatabase sq = dOps.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Table.TableInfo.COL_1,drinkName);
        cv.put(Table.TableInfo.COL_2,taste);
        cv.put(Table.TableInfo.COL_3,base);
        cv.put(Table.TableInfo.COL_4,ingred);
        cv.put(Table.TableInfo.COL_5,recipe);
        //source is broken fix later
        //cv.put(Table.TableInfo.COL_6,source);
        long a=sq.insert(Table.TableInfo.T_NAME,null,cv);
        Log.d("Database opps","Row Added");

    }

    public void deleteDatabase(DatabaseOps dOps){
        SQLiteDatabase sq = dOps.getWritableDatabase();
        String [] col = {Table.TableInfo.COL_1,Table.TableInfo.COL_2,Table.TableInfo.COL_3,Table.TableInfo.COL_4,Table.TableInfo.COL_5};
        sq.execSQL("delete from "+ Table.TableInfo.T_NAME);
    }


    public Cursor discoverDrink(DatabaseOps dOps, String taste , String base){
        SQLiteDatabase sq = dOps.getReadableDatabase();
        String [] col = {Table.TableInfo.COL_1,Table.TableInfo.COL_2,Table.TableInfo.COL_3,Table.TableInfo.COL_4,Table.TableInfo.COL_5};
        Cursor cr = sq.query(Table.TableInfo.T_NAME,col,"taste='"+taste+"' AND " +"base='"+base+"'",null,null,null,null);
        return cr;


    }

    public Cursor findByTaste(DatabaseOps dOps, String taste){
        SQLiteDatabase sq = dOps.getReadableDatabase();
        //contain columns as array
        String [] col = {Table.TableInfo.COL_1,Table.TableInfo.COL_2,Table.TableInfo.COL_3,Table.TableInfo.COL_4,Table.TableInfo.COL_5};
        Cursor cr = sq.query(Table.TableInfo.T_NAME,col, "taste='"+taste+"'",null,null,null,null);
        return cr;


    }
    public Cursor findByBase(DatabaseOps dOps, String base){
        SQLiteDatabase sq = dOps.getReadableDatabase();
        String [] col = {Table.TableInfo.COL_1,Table.TableInfo.COL_2,Table.TableInfo.COL_3,Table.TableInfo.COL_4,Table.TableInfo.COL_5};
        Cursor cr = sq.query(Table.TableInfo.T_NAME,col, "base='"+base+"'",null,null,null,null);
        return cr;

    }
    public Cursor wildCard(DatabaseOps dOps){
        SQLiteDatabase sq = dOps.getReadableDatabase();
        String [] col = {Table.TableInfo.COL_1,Table.TableInfo.COL_2,Table.TableInfo.COL_3,Table.TableInfo.COL_4,Table.TableInfo.COL_5};
        Cursor cr =sq.query(Table.TableInfo.T_NAME,col,null,null,null,null,null);
        return cr;

    }
    public Cursor findbyName(DatabaseOps dOps, String drinkName){
        SQLiteDatabase sq = dOps.getReadableDatabase();
        String [] col = {Table.TableInfo.COL_1,Table.TableInfo.COL_2,Table.TableInfo.COL_3,Table.TableInfo.COL_4,Table.TableInfo.COL_5};
        Cursor cr = sq.query(Table.TableInfo.T_NAME,col, "drink_name='"+drinkName+"'",null,null,null,null);
        return cr;

    }
    public Cursor yieldAll (DatabaseOps dOps){
        SQLiteDatabase sq = dOps.getReadableDatabase();
        String [] col = {Table.TableInfo.COL_1,Table.TableInfo.COL_2,Table.TableInfo.COL_3,Table.TableInfo.COL_4,Table.TableInfo.COL_5};
        Cursor cr =sq.query(Table.TableInfo.T_NAME,col,null,null,null,null,null);
        return cr;
    }
}
