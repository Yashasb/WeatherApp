package com.example.ganesha.weatherappfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ganesha on 11/4/2017.
 */

public class DemoFragment extends Fragment {
    RecyclerView ThreHourList;
    RecyclerView.Adapter ThreehourListAdapter;
    RecyclerView FiveDayList;
    RecyclerView.Adapter FiveDayListAdapter;
    ArrayList<WeatherModel> arrayList=new ArrayList<WeatherModel>();




    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragmentmenu,menu);
        MenuItem additem=(MenuItem)menu.findItem(R.id.addcity);
        additem.setVisible(false);
        MenuItem  farenhiet=(MenuItem)menu.findItem(R.id.farenhiet);
        MenuItem  celsius=(MenuItem)menu.findItem(R.id.celsius);
        farenhiet.setVisible(false);
        celsius.setVisible(false);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(this.getContext(), MainActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        try {
            //this.arrayList=getArguments().getParcelableArrayList("arraylist");
            // Log.d("TAG","hrwfrgfvs"+getArguments().getParcelableArrayList("arraylist"));
            this.arrayList=getArguments().getParcelableArrayList("arraylist");

        }
        catch (Exception e){
            Log.e("TAG",e.toString());
        }




        View v = inflater.inflate(R.layout.fragment_view, container, false);
        TextView city = (TextView) v.findViewById(R.id.fcity);
        TextView time=(TextView) v.findViewById(R.id.Date);
        TextView weatherstatus=(TextView) v.findViewById(R.id.Weatherstatus);
        TextView min=(TextView) v.findViewById(R.id.mintemp);
        TextView max=(TextView) v.findViewById(R.id.maxtemp);
        TextView temp=(TextView) v.findViewById(R.id.temp);
        //TextView plus1temp=(TextView) v.findViewById(R.id.);
        TextView plus1weather=(TextView) v.findViewById(R.id.temp);


        city.setText(getArguments().getString("city"));
        time.setText(getArguments().getString("time"));
        weatherstatus.setText(getArguments().getString("weather_status"));
        min.setText(getArguments().getString("min"));
        max.setText(getArguments().getString("max"));
        temp.setText(getArguments().getString("temp"));

        ThreHourList=(RecyclerView)v.findViewById(R.id.threehourrecycleview);
        ThreehourListAdapter=new HoursListAdapter(arrayList,getArguments().getInt("Arrayposition"));
        ThreHourList.setAdapter(ThreehourListAdapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(linearLayoutManager.HORIZONTAL);
        ThreHourList.setLayoutManager(linearLayoutManager);
        ThreHourList.setHasFixedSize(true);

        FiveDayList=(RecyclerView)v.findViewById(R.id.fivedayrecycleview);
        FiveDayListAdapter=new DayListAdapter(arrayList,getArguments().getInt("Arrayposition"));
       FiveDayList.setAdapter(FiveDayListAdapter);
        LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(this.getContext());
        linearLayoutManager2.setOrientation(linearLayoutManager2.VERTICAL);
        FiveDayList.setLayoutManager(linearLayoutManager2);
        FiveDayList.setHasFixedSize(true);

        return v;
    }
}
