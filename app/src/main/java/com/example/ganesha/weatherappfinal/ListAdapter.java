package com.example.ganesha.weatherappfinal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ganesha on 11/4/2017.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {


    public interface OnItemClickListener{
        void onItemClick(String item,int position);

    }
    private Context context;
    private OnItemClickListener listener;
    CityDataBase db;
    ArrayList<WeatherModel> arrayList=new ArrayList<WeatherModel>();

    public ListAdapter(Context context, CityDataBase db, ArrayList<WeatherModel> arrayList, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.db = db;
        this.arrayList=arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.citylistitem,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int pos=position+1;
     String City=db.getCityData(pos);
        holder.bind(City,pos,listener);


    }

    @Override
    public int getItemCount() {
        return db.getProfilesCount();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView city;
        public TextView time;
         public TextView temp;
        public Button delete;
        public ViewHolder(View itemView){
            super(itemView);
            city=(TextView) itemView.findViewById(R.id.city);
             time=(TextView) itemView.findViewById(R.id.time);
            temp=(TextView) itemView.findViewById(R.id.temp);
            delete=(Button)itemView.findViewById(R.id.deletebutton);

        }

        public void bind(final String CityName, final int pos,final OnItemClickListener listener) {
            city.setText(CityName);
            try {
                temp.setText(arrayList.get(pos-1).getCurrent_Temp());
                Log.d("TAG", arrayList.get(0).getCurrent_Temp() + arrayList.get(0).getDate());
                time.setText(arrayList.get(pos-1).getTime());
            }
            catch (Exception e){
                Log.e("TAG",e.toString());
            }
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.deleteCity(CityName);
                    arrayList.remove(pos-1);
                    notifyDataSetChanged();


                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(CityName,pos-1);
                }
            });

        }
    }
}
