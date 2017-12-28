package com.example.ganesha.weatherappfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Ganesha on 11/4/2017.
 */

public class CityDataBase extends SQLiteOpenHelper {
    public static final String DataBase_Name="Cities15.db";
    public static final String Table_Name="Cities";
    public static final String COL_2="CITY";


    public CityDataBase(Context context) {
        super(context,DataBase_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + Table_Name+"(CITY TEXT)" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+ Table_Name);
        onCreate(db);

    }

    boolean insertData(String city){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues ca=new ContentValues();
        ca.put(COL_2,city);
        long result=db.insert(Table_Name,null,ca);
        if(result==-1){
            return false;
        }
        else {
            return true;
        }

    }

    public String getCityData(int id){
        String countQuery = "SELECT  * FROM Cities"  ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        String city="";
        cursor.move(id);
        if(cursor.getCount()>0) {

            city = cursor.getString(cursor.getColumnIndex("CITY"));
        }
        cursor.close();
        return city;

    }

    public int getProfilesCount() {
        String countQuery = "SELECT  * FROM Cities"  ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    public boolean checkCity(String City){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM Cities WHERE CITY=?",new String[]{City});
        if(cursor.getCount()<=0){
            cursor.close();
            return false;

        }
        else {
            return true;
        }

    }



    public Integer deleteCity (String City) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Cities",
                "City = ? ",
                new String[] { City });
    }


}
