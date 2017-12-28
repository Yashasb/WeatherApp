package com.example.ganesha.weatherappfinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {
    CityDataBase db = new CityDataBase(this);
    RecyclerView.Adapter ListAdapter;
    RecyclerView listview;

    ViewPager viewpager;
    FragmentPagerAdapter pageradapter;
    String forecasturl = "http://api.openweathermap.org/data/2.5/forecast?q=";
    String weatherurl = "http://api.openweathermap.org/data/2.5/weather?q=";
    String key = "e8088f71272967baf925a843dbc50896";
    String forc_url = "";
    String weat_url = "";
    ArrayList<WeatherModel> arrayList = new ArrayList<WeatherModel>();
    String Unit = "";
    boolean completed;
    //SharedPreferences DRef=getApplicationContext().getSharedPreferences("Mypref",MODE_PRIVATE);
    //SharedPreferences.Editor editor=DRef.edit();
    Handler handler = new Handler();
    Runnable r2;
    private static final int MY_PERMISSION_REQUEST_LOCATION= 1;
    String presentlocation="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadingscreen);

            for (int i = 1; i <= db.getProfilesCount(); i++) {

                SharedPreferences sharedPref = getSharedPreferences("unitinfo", Context.MODE_PRIVATE);
                String unit2 = sharedPref.getString("unit", "");
                Log.d("TAG", unit2);
                String city = db.getCityData(i);


                if (unit2.equals("Farenhiet")) {
                    Unit = "&units=imperial";
                } else {
                    Unit = "&units=metric";
                }
                forc_url = forecasturl + city + "&appid=" + key + Unit;
                weat_url = weatherurl + city + "&appid=" + key + Unit;


                WeatherModel weathermodel = new WeatherModel();
                weathermodel.setCity(city);
                arrayList.add(weathermodel);
                getWeatherData(weat_url, weathermodel);

                checklocservice();
            }







            r2 = new Runnable() {
                @Override
                public void run() {
                    setContentView(R.layout.activity_main);

                    listview = (RecyclerView) findViewById(R.id.recycleview);
                    ListAdapter = new ListAdapter(getApplicationContext(), db, arrayList, new ListAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(String item, int pos) {
                            setContentView(R.layout.citydetail_view);
                            viewpager = (ViewPager) findViewById(R.id.citydetailview);
                            pageradapter = new GridDetailAdapter(arrayList, db, getSupportFragmentManager());
                            viewpager.setAdapter(pageradapter);
                            //Log.d("TAG","Current: "+String.valueOf(db.getPosition(item)));
                            viewpager.setCurrentItem(pos);
                            if (item.equals(presentlocation)) {
                                Toast.makeText(getApplicationContext(), "You are here-in this City", Toast.LENGTH_LONG).show();
                            }


                            ImageButton CityListButton = (ImageButton) findViewById(R.id.backlistbutton);
                            CityListButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);

                                }
                            });
                        }
                    });
                    try {
                        listview.setAdapter(ListAdapter);
                    }
                    catch (Exception e){
                        Toast.makeText(getApplicationContext(),"ERROR-TRY AGAIN",Toast.LENGTH_SHORT);
                    }
                    listview.setHasFixedSize(true);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    listview.setLayoutManager(linearLayoutManager);

                }
            };



    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.mainactivitymenu, menu);

        MenuItem Fitem;
        Fitem = (MenuItem) menu.findItem(R.id.farenhiet);

        MenuItem Citem;
        Citem = (MenuItem) menu.findItem(R.id.celsius);

        SharedPreferences sharedPref = getSharedPreferences("unitinfo", Context.MODE_PRIVATE);

        String check = sharedPref.getString("unit", "");
        if (check.equals("Farenhiet")) {
            Fitem.setChecked(true);
        } else {
            Citem.setChecked(true);
        }


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences sharedPref = getSharedPreferences("unitinfo", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();

        switch (item.getItemId()) {
            case R.id.addcity:
                CallAutoCompleteFragment();
                break;
            case R.id.farenhiet:
                editor.putString("unit", "Farenhiet");
                editor.apply();
                item.setChecked(true);
                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent2);
                if (item.isChecked()) {
                    //item.setChecked(false);


                }

                return true;


            case R.id.celsius:
                editor.putString("unit", "Celsius");
                editor.apply();
                item.setChecked(true);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                if (item.isChecked()) {

                    item.setChecked(false);

                    return true;
                }
                break;


        }
        return super.onOptionsItemSelected(item);
    }


    private void CallAutoCompleteFragment() {
        setContentView(R.layout.fragment_place);
        // GoogleAutoPlaceFragment();
        PlaceAutocompleteFragment Placefragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.placefragment);

        AutocompleteFilter autocompleteFilter=new AutocompleteFilter.Builder().setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES).build();
       Placefragment.setFilter(autocompleteFilter);
        Placefragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                String Place;
                Place= String.valueOf(place.getName());


                if (!db.checkCity(Place)) {
                    db.insertData(Place);
                   // ListAdapter.notifyDataSetChanged();
                   // Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                   // startActivity(intent);
                    finish();
                    startActivity(getIntent());
                } else {
                    Toast.makeText(getApplicationContext(), "CITY ALREADY EXIST", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                Log.d("TAG", db.getCityData(1));
            }

            @Override
            public void onError(Status status) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                Log.d("TAGauto", String.valueOf(status));
                finish();
                startActivity(getIntent());
                Log.d("TAG", String.valueOf(status));

            }
        });


    }


    public void getWeatherData(String url, final WeatherModel weathermodel) {


        Log.d("TAG", "here" + url);


        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {



                    JSONObject coord = response.getJSONObject("coord");
                    String Longitude = String.valueOf(coord.getString("lon"));
                    String Latitude = String.valueOf(coord.getString("lat"));
                    Log.d("TAG", Longitude + "l  "+Latitude);

                    Set_Time_Date(Longitude,Latitude,weathermodel);

                    JSONObject object = response.getJSONObject("main");
                    JSONArray arr = response.getJSONArray("weather");
                    JSONObject obj = arr.getJSONObject(0);

                    String temperature = String.valueOf(object.getDouble("temp"));
                    weathermodel.setCurrent_Temp(temperature);

                    String Weather = obj.getString("main");
                    weathermodel.setWeatherStatus(Weather);

                    //handler.postDelayed(r2,100);


                } catch (JSONException e) {
                    Log.d("TAG", String.valueOf(e));
                    Toast.makeText(getApplicationContext(),"City Name Not Valid",Toast.LENGTH_LONG).show();

                    db.deleteCity(db.getCityData(db.getProfilesCount()));
                    finish();
                    startActivity(getIntent());


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                try{
                if (error instanceof TimeoutError) {
                    //Time out error
                    Log.e("VOLLEY", "1"+error.getMessage());
                    Toast.makeText(getApplicationContext(),"NO INTERNET CONNECTION",Toast.LENGTH_LONG).show();

                }else if(error instanceof NoConnectionError){
                    Log.e("VOLLEY", "2"+error.getMessage());
                    Toast.makeText(getApplicationContext(),"NO INTERNET CONNECTION",Toast.LENGTH_LONG).show();
                    //net work error

                } else if (error instanceof AuthFailureError) {
                    Log.e("VOLLEY", "3"+error.getMessage());
                    //error

                } else if (error instanceof ServerError) {
                    Log.e("VOLLEY", "4"+error.getMessage());
                    TimeUnit.SECONDS.sleep(3);
                    Toast.makeText(getApplicationContext(),"City Name Not Valid,404 Server Error",Toast.LENGTH_LONG).show();
                    db.deleteCity(db.getCityData(db.getProfilesCount()));
                    finish();
                    startActivity(getIntent());
                } else if (error instanceof NetworkError) {
                    Log.e("VOLLEY", "5"+error.getMessage());
                    Toast.makeText(getApplicationContext(),"NO INTERNET CONNECTION",Toast.LENGTH_LONG).show();
                    //Error

                } else if (error instanceof ParseError) {
                    Log.e("VOLLEY", "6"+error.getMessage());
                    Toast.makeText(getApplicationContext(),"NO INTERNET CONNECTION-parseError",Toast.LENGTH_LONG).show();


                }else{
                    //Error
                    Log.e("VOLLEY", "in else"+error.getMessage());
                }
                //End


            } catch (Exception e) {
                Log.e("VOLLEY2", "in catch"+e.getMessage());
                    Toast.makeText(getApplicationContext(),"NO INTERNET CONNECTION",Toast.LENGTH_LONG).show();
            }


            }

            }


        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(req);


    }

    private void Set_Time_Date(String longitude, String latitude, final WeatherModel weathermodel) {
        String url = "https://maps.googleapis.com/maps/api/timezone/json?location=";
        String key = "AIzaSyAJ8j40Y1aLSV3NDiUMHzG03UghEMgYn7U";
        Long tsLong = System.currentTimeMillis() / 1000;
        String timestamp = tsLong.toString();

        url = url + latitude + "," + longitude + "&timestamp=" + timestamp + "&key=" + key;
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    String[] result = new String[6];
                    String[] dayName = new String[6];

                    String object = response.getString("timeZoneId");
                    TimeZone timezone = TimeZone.getTimeZone(object);

                    Date date = new Date();
                    DateFormat format1 = new SimpleDateFormat("EEEE MMMM dd HH:mm:ss zzz yyyy ");
                    format1.setTimeZone(timezone);

                    try{

                        String wholedate = format1.format(format1.parse(format1.format(date)));
                        // Log.e("TAGY","whole date       ====== "+wholedate);



                        SimpleDateFormat formatDay = new SimpleDateFormat("EEEE");
                        Date d = formatDay.parse(wholedate);
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        calendar.add(Calendar.DAY_OF_YEAR, 1);
                        formatDay.format(calendar.getTime());


                        //get next day from todays day at that city=====================

                        Date tomDate = format1.parse(wholedate);
                        //Calendar calendar = Calendar.getInstance();
                        calendar.setTime(tomDate);
                        calendar.add(Calendar.DAY_OF_MONTH, 1);
                        String nextDayDate = format1.format(calendar.getTime());//nextDayDate will have exact time next day
                        //im using this to get the next day name and date


                        //get local noon time next day
                        String[] arr13 = nextDayDate.split(" ");
                        String noonNextDay = arr13[0]+" "+arr13[1]+" "+arr13[2]+" 12:00:00 "+arr13[4]+" "+arr13[5];
                        //Log.e("TAGY","next day         ===== "+noonNextDay);



                        //convert the local noon time of next day into gmt
                        TimeZone central3 = TimeZone.getTimeZone("UTC");
                        SimpleDateFormat parseFormat = new SimpleDateFormat("EEEE MMMM dd HH:mm:ss zzz yyyy");
                        parseFormat.setTimeZone(central3);
                        SimpleDateFormat format = new SimpleDateFormat("EEEE yyyy-MMMM-dd hh:mm:ss");
                        format.setTimeZone(central3);

                        DateFormat sdf = new SimpleDateFormat("EEEE MMMM dd HH:mm:ss zzz yyyy");
                        String fromDateString2day1=noonNextDay;
                        Date day1 = sdf.parse(fromDateString2day1);
                        TimeZone central2 = TimeZone.getTimeZone("UTC");

                        sdf.setTimeZone(central2);
                        format.setTimeZone(central2);

                        String wholedate2Day1 = sdf.format(day1);
                        Date d1 = sdf.parse(wholedate2Day1);
                        String result1 = sdf.format(d1);
                        String resultgmt = format.format(d1);

                        //Log.e("TAG","   "+resultgmt);

                        //wholedate2 has the gmt equalent to the day noon
                        //day2

                        Date day2 = sdf.parse(wholedate2Day1);
                        calendar.setTime(day2);
                        calendar.add(Calendar.DAY_OF_MONTH, 1);
                        String wholedate2Day2 = sdf.format(calendar.getTime());
                        Date d2 = parseFormat.parse(wholedate2Day2);
                        String result2 = sdf.format(d2);
                        //Log.e("TAG","day2       ====== "+result2);

                        Date day3 = sdf.parse(wholedate2Day2);
                        calendar.setTime(day3);
                        calendar.add(Calendar.DAY_OF_MONTH, 1);
                        String wholedate2Day3 = sdf.format(calendar.getTime());
                        Date d3 = parseFormat.parse(wholedate2Day3);
                        String result3 = sdf.format(d3);
                        // Log.e("TAG","day2       ====== "+result3);

                        Date day4 = sdf.parse(wholedate2Day3);
                        calendar.setTime(day4);
                        calendar.add(Calendar.DAY_OF_MONTH, 1);
                        String wholedate2Day4 = sdf.format(calendar.getTime());
                        Date d4 = parseFormat.parse(wholedate2Day4);
                        String result4 = sdf.format(d4);
                        // Log.e("TAG","day4       ====== "+result4);


                        Date day5 = sdf.parse(wholedate2Day4);
                        calendar.setTime(day5);
                        calendar.add(Calendar.DAY_OF_MONTH, 1);
                        String wholedate2Day5 = sdf.format(calendar.getTime());
                        Date d5 = parseFormat.parse(wholedate2Day5);
                        String result5 = sdf.format(d5);
                        // Log.e("TAG","day5       ====== "+result5);

                        result[0]= wholedate;
                        String[] today=wholedate.split(" ");
                        String today0=today[0]+","+today[1]+" "+today[2]+".";
                        String todaytime=today[3];
                        result[1]=result1;
                        String[] tomday=result1.split(" ");
                        String tommrow1=tomday[0];
                        result[2]=result2;
                        String[] tomday2=result2.split(" ");
                        String tommrow2=tomday2[0];
                        result[3]=result3;
                        String[] tomday3=result3.split(" ");
                        String tommrow3=tomday3[0];
                        result[4]=result4;
                        String[] tomday4=result4.split(" ");
                        String tommrow4=tomday4[0];
                       // result[5]=result5;

                        for (int i = 0; i<6; i++){


                            Log.e("TAG","day "+i+"====== "+result[i]);
                        }


                        weathermodel.setTime(todaytime);
                        weathermodel.setDate(today0);
                        weathermodel.setPlus1Day(tommrow1);
                        weathermodel.setPlus2Day(tommrow2);
                        weathermodel.setPlus3Day(tommrow3);
                        weathermodel.setPlus4Day(tommrow4);
                        getForeCastData(forc_url, weathermodel,result);


                    }catch (Exception e){

                        Log.e("TAG", "in catch "+String.valueOf(e));
                        e.printStackTrace();
                    }







                } catch (JSONException e) {
                    Log.d("TAG", "im here");

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try{
                    if (error instanceof TimeoutError) {
                        //Time out error
                        Log.e("VOLLEY", "1"+error.getMessage());
                        Toast.makeText(getApplicationContext(),"NO INTERNET CONNECTION",Toast.LENGTH_LONG).show();

                    }else if(error instanceof NoConnectionError){
                        Log.e("VOLLEY", "2"+error.getMessage());
                        Toast.makeText(getApplicationContext(),"NO INTERNET CONNECTION",Toast.LENGTH_LONG).show();
                        //net work error

                    } else if (error instanceof AuthFailureError) {
                        Log.e("VOLLEY", "3"+error.getMessage());
                        //error

                    } else if (error instanceof ServerError) {
                        Log.e("VOLLEY", "4"+error.getMessage());
                        Toast.makeText(getApplicationContext(),"City Name Not Valid,404 Server Error",Toast.LENGTH_LONG).show();
                        TimeUnit.SECONDS.sleep(2);

                    } else if (error instanceof NetworkError) {
                        Log.e("VOLLEY", "5"+error.getMessage());
                        Toast.makeText(getApplicationContext(),"NO INTERNET CONNECTION",Toast.LENGTH_LONG).show();
                        //Error

                    } else if (error instanceof ParseError) {
                        Log.e("VOLLEY", "6"+error.getMessage());
                        Toast.makeText(getApplicationContext(),"NO INTERNET CONNECTION-parseError",Toast.LENGTH_LONG).show();
                        //Error

                    }else{
                        //Error
                        Log.e("VOLLEY", "in else"+error.getMessage());
                    }
                    //End


                } catch (Exception e) {
                    Log.e("VOLLEY2", "in catch"+e.getMessage());
                    Toast.makeText(getApplicationContext(),"NO INTERNET CONNECTION",Toast.LENGTH_LONG).show();
                }
            }
        });RequestQueue queue = Volley.newRequestQueue(this);queue.add(req);

    }

    private void getForeCastData(String url, final WeatherModel weathermodel, final String[] result) {
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {        Map<Integer, String> myMap = new HashMap<Integer, String>();
                    myMap.put(0,"00:00:00");
                    myMap.put(10800,"03:00:00");
                    myMap.put(21600,"06:00:00");
                    myMap.put(32400,"09:00:00");
                    myMap.put(43200,"12:00:00");
                    myMap.put(54000,"15:00:00");
                    myMap.put(64800,"18:00:00");
                    myMap.put(75600,"21:00:00");

                    int[] timeInSeconds = {0,10800,21600,32400,43200,54000,64800,75600};//all times in seconds

                    String[] todaydateSplit = result[0].split(" ");
                    String[] nextDayDateSplit = result[1].split(" ");

                    int presentDate= Integer.parseInt(todaydateSplit[2]);//whatever the present date is
                    //Log.e("TAG", String.valueOf(presentDate));
                    String toBenoonTime = nextDayDateSplit[3];
                    String[] toBeNoonTimeSplit = toBenoonTime.split(":");
                    Log.e("TAGY",toBeNoonTimeSplit[0]+toBeNoonTimeSplit[1]+toBeNoonTimeSplit[2]);
                    int noonTimeSplitInSeconds = 3600*Integer.parseInt(toBeNoonTimeSplit[0])+60*Integer.parseInt(toBeNoonTimeSplit[1])+Integer.parseInt(toBeNoonTimeSplit[2]);

                    //grt the nearest =============
                    int dis = Math.abs(timeInSeconds[0] - noonTimeSplitInSeconds);
                    int id = 0;
                    for(int p = 1; p < timeInSeconds.length; p++){
                        int cd = Math.abs(timeInSeconds[p] - noonTimeSplitInSeconds);
                        if(cd < dis){
                            id = p;
                            dis = cd;
                        }
                    }
                    int noonTimeInSec = timeInSeconds[id];
                    String noonTimeInString = myMap.get(noonTimeInSec);
                    Log.e("TAG", "near "+String.valueOf(noonTimeInString));
                    // till here================================

                    JSONArray arr = response.getJSONArray("list");
                    int daycount = 0;
                    for (int i = 0;i<38; i++) {

                        String mintemperature="";
                        String maxtemperature="";
                        String desc="";
                        JSONObject obj = arr.getJSONObject(i);
                        String date = obj.getString("dt_txt");


                        String[] string1 = date.split(" ");
                        String[] dateString = string1[0].split("-");
                        int toBeComparedDate = Integer.parseInt(dateString[2]);

                        String toBeComparedTime = string1[1];

                        if (presentDate < toBeComparedDate) {
                            if (toBeComparedTime.equals(noonTimeInString)) {
                                Log.d("TAG", date);
                                JSONObject object = obj.getJSONObject("main");
                                 mintemperature = String.valueOf(object.getDouble("temp_min"));
                                 maxtemperature = String.valueOf(object.getDouble("temp_max"));
                                JSONArray arr3 = obj.getJSONArray("weather");
                                JSONObject obj2 = arr3.getJSONObject(0);
                                desc = obj2.getString("main");
                                Log.e("TAGaab", mintemperature + "jj" + maxtemperature);
                                daycount++;
                                switch (daycount) {
                                    case 1:
                                        weathermodel.setPlus1DayMinTemp(mintemperature);
                                        weathermodel.setPlus1DayMaxTemp(maxtemperature);
                                        weathermodel.setPlus1Dayweather(desc);
                                        break;
                                    case 2:
                                        weathermodel.setPlus2DayMinTemp(mintemperature);
                                        weathermodel.setPlus2DayMaxTemp(maxtemperature);
                                        weathermodel.setPlus2Dayweather(desc);
                                        break;
                                    case 3:
                                        weathermodel.setPlus3DayMinTemp(mintemperature);
                                        weathermodel.setPlus3DayMaxTemp(maxtemperature);
                                        weathermodel.setPlus3Dayweather(desc);
                                        break;
                                    case 4:
                                        weathermodel.setPlus4DayMinTemp(mintemperature);
                                        weathermodel.setPlus4DayMaxTemp(maxtemperature);
                                        weathermodel.setPlus4Dayweather(desc);
                                        break;

                                }
                            }
                        }

                    }


                    //Log.d("TAG", response.toString());
                    JSONArray arr2 = response.getJSONArray("list");
                    for (int i = 0; i < 9; i++) {

                        JSONObject obj = arr2.getJSONObject(i);
                        JSONObject object = obj.getJSONObject("main");
                        //Log.d("TAG", String.valueOf(obj));
                        String temperature = String.valueOf(object.getDouble("temp"));
                        JSONArray arr3 = obj.getJSONArray("weather");
                        JSONObject obj2 = arr3.getJSONObject(0);
                        String desc = obj2.getString("description");

                        String date = String.valueOf(obj.getString("dt_txt"));
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                        Date d = new Date();
                        try {
                            d = format.parse(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        SimpleDateFormat simpleDateformat = new SimpleDateFormat("HH");
                        String day = simpleDateformat.format(d);



                        switch (i) {
                            case 0:
                                weathermodel.setPlus3Temp(temperature);
                                weathermodel.setPlus3Weather(desc);
                               // weathermodel.setPlus3Time(day);
                                break;
                            case 1:
                                weathermodel.setPlus6Temp(temperature);
                                weathermodel.setPlus6Weather(desc);
                               // weathermodel.setPlus6Time(day);
                                break;
                            case 2:
                                weathermodel.setPlus9Temp(temperature);
                                weathermodel.setPlus9Weather(desc);
                               // weathermodel.setPlus9Time(day);
                                break;
                            case 3:
                                weathermodel.setPlus12Temp(temperature);
                                weathermodel.setPlus12Weather(desc);
                              //  weathermodel.setPlus12Time(day);
                                break;
                            case 4:
                                weathermodel.setPlus15Temp(temperature);
                                weathermodel.setPlus15Weather(desc);
                              //  weathermodel.setPlus15Time(day);
                                break;
                            case 5:
                                weathermodel.setPlus18Temp(temperature);
                                weathermodel.setPlus18Weather(desc);
                               // weathermodel.setPlus18Time(day);
                                break;
                            case 6:
                                weathermodel.setPlus21Temp(temperature);
                                weathermodel.setPlus21Weather(desc);
                                //weathermodel.setPlus21Time(day);
                                break;
                            case 7:
                                weathermodel.setPlus24Temp(temperature);
                                weathermodel.setPlus24Weather(desc);
                               // weathermodel.setPlus24Time(day);
                                break;

                        }

                    }


                        // Log.d("TAG", desc);




                    //JSONObject object = response.getJSONObject("main");
                    JSONArray arr5 = response.getJSONArray("list");
                    JSONObject obj = arr5.getJSONObject(0);
                    //  Log.d("TAG", obj.toString());
                    JSONObject object = obj.getJSONObject("main");
                    String temperature = String.valueOf(object.getDouble("temp"));
                    //weathermodel.setCurrent_Temp(temperature);
                    //Log.d("TAG",weathermodel.getCurrent_Temp());
                    // Log.d("TAG", temperature);
                    String max_temperature = String.valueOf(object.getDouble("temp_max"));
                    String min_temperature = String.valueOf(object.getDouble("temp_min"));
                    String date = String.valueOf(obj.getString("dt_txt"));
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                    Date d = new Date();
                    try {
                        d = format.parse(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    SimpleDateFormat simpleDateformat = new SimpleDateFormat("HH");
                    String day = simpleDateformat.format(d);
                      Log.d("TAG",day);
                   // weathermodel.setDate(day);


                    // date2=new Date();

                    // date2 = format.parse(inputDate);


                    // String dayOfTheWeek = (String)
                    weathermodel.setMax_Temp(max_temperature);
                    weathermodel.setMin_Temp(min_temperature);

                    // weathermodel.setDate(date);
                    // Log.d("TAG",weathermodel.getDate());
                    //   Log.d("TAG", min_temperature);
                    //  Log.d("TAG", max_temperature);


                    //arrayList.add(weathermodel);
                    //handler.postDelayed(r2,100);
                    // Log.d("TAG",arrayList.get(1).getCurrent_Temp()+arrayList.get(1).getDate());


                    // completed=true;
                   handler.postDelayed(r2, 50);

                } catch (JSONException e) {
                    Log.d("TAG", String.valueOf(e));

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try{
                    if (error instanceof TimeoutError) {
                        //Time out error
                        Log.e("VOLLEY", "1"+error.getMessage());
                        Toast.makeText(getApplicationContext(),"NO INTERNET CONNECTION",Toast.LENGTH_LONG).show();

                    }else if(error instanceof NoConnectionError){
                        Log.e("VOLLEY", "2"+error.getMessage());
                        Toast.makeText(getApplicationContext(),"NO INTERNET CONNECTION",Toast.LENGTH_LONG).show();
                        //net work error

                    } else if (error instanceof AuthFailureError) {
                        Log.e("VOLLEY", "3"+error.getMessage());
                        //error

                    } else if (error instanceof ServerError) {
                        Log.e("VOLLEY", "4"+error.getMessage());

                        Toast.makeText(getApplicationContext(),"City Name Not Valid-404 Error-Forecast",Toast.LENGTH_LONG).show();
                        //db.deleteCity(db.getCityData(db.getProfilesCount()));
                        TimeUnit.SECONDS.sleep(2);



                    } else if (error instanceof NetworkError) {
                        Log.e("VOLLEY", "5"+error.getMessage());
                        Toast.makeText(getApplicationContext(),"NO INTERNET CONNECTION",Toast.LENGTH_LONG).show();
                        //Error

                    } else if (error instanceof ParseError) {
                        Log.e("VOLLEY", "6"+error.getMessage());
                        Toast.makeText(getApplicationContext(),"NO INTERNET CONNECTION",Toast.LENGTH_LONG).show();
                        //Error

                    }else{
                        //Error
                        Log.e("VOLLEY", "in else"+error.getMessage());
                        Toast.makeText(getApplicationContext(),"NO INTERNET CONNECTION",Toast.LENGTH_LONG).show();
                    }
                    //End


                } catch (Exception e) {
                    Log.e("VOLLEY2", "in catch"+e.getMessage());
                    Toast.makeText(getApplicationContext(),"NO INTERNET CONNECTION",Toast.LENGTH_LONG).show();
                }

            }

        }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(req);


    }
    private void checklocservice() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION)){
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},MY_PERMISSION_REQUEST_LOCATION);
            }else
            {ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},MY_PERMISSION_REQUEST_LOCATION);}
        } else
        {LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            try{
                presentlocation=presentLocation(location.getLatitude(),location.getLongitude());
            }
            catch (Exception e){

                e.printStackTrace();
                Toast.makeText(MainActivity.this,"Location Services not enabled",Toast.LENGTH_SHORT).show();

            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) {

        switch (requestCode){
            case MY_PERMISSION_REQUEST_LOCATION:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(MainActivity.this,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){

                        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        try{
                            presentlocation=presentLocation(location.getLatitude(),location.getLongitude());
                        }
                        catch (Exception e){

                            e.printStackTrace();
                            Toast.makeText(MainActivity.this,"Location Services not enabled",Toast.LENGTH_SHORT).show();

                        }

                    }else {
                        Toast.makeText(MainActivity.this,"Location Services not enabled",Toast.LENGTH_SHORT).show();
                    }
                }
        }

    }

    public String presentLocation(double lat, double lon){

        String presentCity = "";
        Geocoder geo = new Geocoder(MainActivity.this, Locale.getDefault());
        List<Address> addressList;

        try{

            addressList = geo.getFromLocation(lat,lon,1);
            if(addressList.size()>0){
                presentCity = addressList.get(0).getLocality();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return presentCity;

    }
}





