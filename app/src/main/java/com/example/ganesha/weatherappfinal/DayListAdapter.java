package com.example.ganesha.weatherappfinal;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ganesha on 11/6/2017.
 */

public class DayListAdapter extends RecyclerView.Adapter<DayListAdapter.ViewHolder>{
ArrayList<WeatherModel> arrayList=new ArrayList<WeatherModel>();
    int CityPosition;

   public DayListAdapter(ArrayList<WeatherModel> arrayposition, int CityPosition ) {
       this.arrayList=arrayposition;
       this.CityPosition=CityPosition;
   }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.fivedaylistitem,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            switch (position) {
                case 0:
                    holder.Day.setText(arrayList.get(CityPosition).getPlus1Day()) ;
                    holder.weather.setText(arrayList.get(CityPosition).getPlus1Dayweather());
                    holder.MinTemp.setText(arrayList.get(CityPosition).getPlus1DayMinTemp());
                    holder.MaxTemp.setText(arrayList.get(CityPosition).getPlus1DayMaxTemp());
                    break;
                case 1:
                    holder.Day.setText(arrayList.get(CityPosition).getPlus2Day()) ;
                    holder.weather.setText(arrayList.get(CityPosition).getPlus2Dayweather());
                    holder.MinTemp.setText(arrayList.get(CityPosition).getPlus2DayMinTemp());
                    holder.MaxTemp.setText(arrayList.get(CityPosition).getPlus2DayMaxTemp());
                    break;
                case 2:
                    holder.Day.setText(arrayList.get(CityPosition).getPlus3Day()) ;
                    holder.weather.setText(arrayList.get(CityPosition).getPlus3Dayweather());
                    holder.MinTemp.setText(arrayList.get(CityPosition).getPlus3DayMinTemp());
                    holder.MaxTemp.setText(arrayList.get(CityPosition).getPlus3DayMaxTemp());
                    break;
                case 3:
                    holder.Day.setText(arrayList.get(CityPosition).getPlus4Day()) ;
                    holder.weather.setText(arrayList.get(CityPosition).getPlus4Dayweather());
                    holder.MinTemp.setText(arrayList.get(CityPosition).getPlus4DayMinTemp());
                    holder.MaxTemp.setText(arrayList.get(CityPosition).getPlus4DayMaxTemp());
                    break;
                case 4:
                    holder.Day.setText(arrayList.get(CityPosition).getPlus5Day()) ;
                    holder.weather.setText(arrayList.get(CityPosition).getPlus5Dayweather());
                    holder.MinTemp.setText(arrayList.get(CityPosition).getPlus5DayMinTemp());
                    holder.MaxTemp.setText(arrayList.get(CityPosition).getPlus5DayMaxTemp());
                    break;

            }
        }
        catch (Exception e){
            Log.e("TAG",e.toString());
        }

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Day;
        TextView weather;
        TextView MinTemp;
        TextView MaxTemp;
        public ViewHolder(View v) {
            super(v);
            Day=(TextView)v.findViewById(R.id.tDay);
            weather=(TextView)v.findViewById(R.id.Dayweather);
            MinTemp=(TextView)v.findViewById(R.id.minDayTemp);
            MaxTemp=(TextView)v.findViewById(R.id.maxDayTemp);
        }
    }
}
