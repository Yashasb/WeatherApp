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

public class HoursListAdapter extends RecyclerView.Adapter<HoursListAdapter.ViewHolder> {
    ArrayList<WeatherModel> arrayList=new ArrayList<WeatherModel>();
    int CityPosition;
    public HoursListAdapter(ArrayList<WeatherModel> arrayList, int arrayposition) {
        this.arrayList=arrayList;
        this.CityPosition=arrayposition;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.threehourlayout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            String hour="";
            int h;
            switch (position) {
                case 0:

                     h=3+Integer.parseInt(arrayList.get(CityPosition).getTime().substring(0,2));
                    if(h>24){
                        h=h-24;
                    }
                    if(h>12){
                        h=h-12;
                        hour=String.valueOf(h)+"pm";
                    }
                    else{
                        hour=String.valueOf(h)+"am";
                    }

                    holder.time.setText(hour) ;
                    holder.weather.setText(arrayList.get(CityPosition).getPlus3Weather());
                    holder.Temp.setText(arrayList.get(CityPosition).getPlus3Temp());
                    break;
                case 1:

                    h=6+Integer.parseInt(arrayList.get(CityPosition).getTime().substring(0,2));
                    if(h>24){
                        h=h-24;
                    }
                    if(h>12){
                        h=h-12;
                        hour=String.valueOf(h)+"pm";
                    }
                    else{
                        hour=String.valueOf(h)+"am";
                    }

                    holder.time.setText(hour) ;
                    holder.weather.setText(arrayList.get(CityPosition).getPlus6Weather());
                    holder.Temp.setText(arrayList.get(CityPosition).getPlus6Temp());
                    break;
                case 2:
                    h=9+Integer.parseInt(arrayList.get(CityPosition).getTime().substring(0,2));
                    if(h>24){
                        h=h-24;
                    }
                    if(h>12){
                        h=h-12;
                        hour=String.valueOf(h)+"pm";
                    }
                    else{
                        hour=String.valueOf(h)+"am";
                    }

                    holder.time.setText(hour) ;

                    holder.weather.setText(arrayList.get(CityPosition).getPlus9Weather());
                    holder.Temp.setText(arrayList.get(CityPosition).getPlus9Temp());
                    break;
                case 3:
                    h=0;
                    h=12+Integer.parseInt(arrayList.get(CityPosition).getTime().substring(0,2));
                    if(h>24){
                        h=h-24;
                    }
                    if(h>12){
                        h=h-12;
                        hour=String.valueOf(h)+"pm";
                    }
                    else{
                        hour=String.valueOf(h)+"am";
                    }

                    holder.time.setText(hour) ;

                    holder.weather.setText(arrayList.get(CityPosition).getPlus12Weather());
                    holder.Temp.setText(arrayList.get(CityPosition).getPlus12Temp());
                    break;

                case 4:
                    h=0;

                    h=15+Integer.parseInt(arrayList.get(CityPosition).getTime().substring(0,2));
                    if(h>24){
                        h=h-24;
                    }
                    if(h>12){
                        h=h-12;
                        hour=String.valueOf(h)+"pm";
                    }
                    else{
                        hour=String.valueOf(h)+"am";
                    }

                    holder.time.setText(hour) ;

                    holder.weather.setText(arrayList.get(CityPosition).getPlus15Weather());
                    holder.Temp.setText(arrayList.get(CityPosition).getPlus15Temp());
                    break;
                case 5:
                    h=0;
                    h=18+Integer.parseInt(arrayList.get(CityPosition).getTime().substring(0,2));
                    if(h>24){
                        h=h-24;
                    }
                    if(h>12){
                        h=h-12;
                        hour=String.valueOf(h)+"pm";
                    }
                    else{
                        hour=String.valueOf(h)+"am";
                    }

                    holder.time.setText(hour) ;

                    holder.weather.setText(arrayList.get(CityPosition).getPlus18Weather());
                    holder.Temp.setText(arrayList.get(CityPosition).getPlus18Temp());
                    break;
                case 6:
                    h=0;
                    h=21+Integer.parseInt(arrayList.get(CityPosition).getTime().substring(0,2));
                    if(h>24){
                        h=h-24;
                    }
                    if(h>12){
                        h=h-12;
                        hour=String.valueOf(h)+"pm";
                    }
                    else{
                        hour=String.valueOf(h)+"am";
                    }

                    holder.time.setText(hour) ;

                    holder.weather.setText(arrayList.get(CityPosition).getPlus21Weather());
                    holder.Temp.setText(arrayList.get(CityPosition).getPlus21Temp());
                    break;
                case 7:
                    h=24+Integer.parseInt(arrayList.get(CityPosition).getTime().substring(0,2));
                    if(h>24){
                        h=h-24;
                    }
                    if(h>12){
                        h=h-12;
                        hour=String.valueOf(h)+"pm";
                    }
                    else{
                        hour=String.valueOf(h)+"am";
                    }

                    holder.time.setText(hour) ;

                    holder.weather.setText(arrayList.get(CityPosition).getPlus24Weather());
                    holder.Temp.setText(arrayList.get(CityPosition).getPlus24Temp());
                    break;



            }
        }
        catch (Exception e){
            Log.e("TAG",e.toString());
        }


    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView time;
        TextView weather;
        TextView Temp;
        public ViewHolder(View v) {
            super(v);

            time=(TextView)v.findViewById(R.id.htime);
            weather=(TextView)v.findViewById(R.id.hweather);
            Temp=(TextView)v.findViewById(R.id.htemperature);
        }
    }
}
