package com.example.bendev.cocktailcurator;

import android.provider.BaseColumns;

/**
 * Table values stored here
 */

public class Table {

    public Table(){

    }


    public static abstract class TableInfo implements BaseColumns {

        //database name
        public static final String DB_NAME="cocktail";

        //tablename
        public static final String T_NAME="drinks";

        //column names
        public static final String COL_1 ="drink_name";
        public static final String COL_2 ="taste";
        public static final String COL_3="base";
        public static final String COL_4="ingred";
        public static final String COL_5="recipe";
        public static final String COL_6="source";

    }



}
