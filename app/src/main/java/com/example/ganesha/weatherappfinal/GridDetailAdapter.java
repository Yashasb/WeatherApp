package com.example.ganesha.weatherappfinal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Ganesha on 11/4/2017.
 */

public class GridDetailAdapter extends FragmentPagerAdapter  {
    CityDataBase db;
    FragmentManager fragmentManager;
    ArrayList<WeatherModel> arrayList=new ArrayList<WeatherModel>();

    public GridDetailAdapter(ArrayList<WeatherModel> arrayList,CityDataBase db,FragmentManager fm) {
        super(fm);
        this.db=db;
        this.arrayList=arrayList;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fa=new DemoFragment();
        Bundle ba=new Bundle();
        ba.putString("city",db.getCityData(position+1));
        ba.putString("temp",arrayList.get(position).getCurrent_Temp());
         ba.putString("time",arrayList.get(position).getDate());
        ba.putString("weather_status",arrayList.get(position).getWeatherStatus());
        ba.putString("min",arrayList.get(position).getMin_Temp());
        ba.putString("max",arrayList.get(position).getMax_Temp());
        ba.putString("plus1maxtemp",arrayList.get(position).getPlus1DayMaxTemp());
        ba.putString("plus1mintemp",arrayList.get(position).getPlus1DayMinTemp());

        ba.putString("plus1weather",arrayList.get(position).getPlus1Dayweather());
        ba.putString("plus2maxtemp",arrayList.get(position).getPlus2DayMaxTemp());
        ba.putString("plus2mintemp",arrayList.get(position).getPlus2DayMinTemp());
        ba.putString("plus2weather",arrayList.get(position).getPlus2Dayweather());
        ba.putString("plus3maxtemp",arrayList.get(position).getPlus3DayMaxTemp());
        ba.putString("plus3mintemp",arrayList.get(position).getPlus3DayMinTemp());
        ba.putString("plus3weather",arrayList.get(position).getPlus3Dayweather());
        ba.putString("plus4maxtemp",arrayList.get(position).getPlus4DayMaxTemp());
        ba.putString("plus4mintemp",arrayList.get(position).getPlus4DayMinTemp());
        ba.putString("plus4weather",arrayList.get(position).getPlus4Dayweather());

        ba.putString("plus5weather",arrayList.get(position).getPlus5Dayweather());
        ba.putInt("Arrayposition",position);
        ba.putParcelableArrayList("arraylist",arrayList);


        fa.setArguments(ba);
        return fa;
    }

    @Override
    public int getCount() {
        return db.getProfilesCount();
    }


}
