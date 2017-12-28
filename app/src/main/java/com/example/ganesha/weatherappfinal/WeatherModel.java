package com.example.ganesha.weatherappfinal;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ganesha on 11/5/2017.
 */

public class WeatherModel implements Parcelable{

    String City;
    String Day;
    String Date;
    String Time;
    String WeatherStatus;
    String Current_Temp;
    String Min_Temp;
    String Max_Temp;

    String plus3Time;
    String plus3Weather;
    String plus3Temp;
    String plus6Time;
    String plus6Weather;
    String plus6Temp;
    String plus9Time;
    String plus9Weather;
    String plus9Temp;
    String plus12Time;
    String plus12Weather;
    String plus12Temp;
    String plus15Time;
    String plus15Weather;
    String plus15Temp;
    String plus18Time;
    String plus18Weather;
    String plus18Temp;
    String plus21Time;
    String plus21Weather;
    String plus21Temp;
    String plus24Time;
    String plus24Weather;
    String plus24Temp;

    String plus1Day;
    String plus1Dayweather;
    String plus1DayMinTemp;
    String plus1DayMaxTemp;

    String plus2Day;
    String plus2Dayweather;
    String plus2DayMinTemp;
    String plus2DayMaxTemp;

    String plus3Day;
    String plus3Dayweather;
    String plus3DayMinTemp;
    String plus3DayMaxTemp;

    String plus4Day;
    String plus4Dayweather;
    String plus4DayMinTemp;
    String plus4DayMaxTemp;

    String plus5Day;
    String plus5Dayweather;

    public String getPlus1DayMinTemp() {
        return plus1DayMinTemp;
    }

    public void setPlus1DayMinTemp(String plus1DayMinTemp) {
        this.plus1DayMinTemp = plus1DayMinTemp;
    }

    public String getPlus1DayMaxTemp() {
        return plus1DayMaxTemp;
    }

    public void setPlus1DayMaxTemp(String plus1DayMaxTemp) {
        this.plus1DayMaxTemp = plus1DayMaxTemp;
    }

    public String getPlus2DayMinTemp() {
        return plus2DayMinTemp;
    }

    public void setPlus2DayMinTemp(String plus2DayMinTemp) {
        this.plus2DayMinTemp = plus2DayMinTemp;
    }

    public String getPlus2DayMaxTemp() {
        return plus2DayMaxTemp;
    }

    public void setPlus2DayMaxTemp(String plus2DayMaxTemp) {
        this.plus2DayMaxTemp = plus2DayMaxTemp;
    }

    public String getPlus3DayMinTemp() {
        return plus3DayMinTemp;
    }

    public void setPlus3DayMinTemp(String plus3DayMinTemp) {
        this.plus3DayMinTemp = plus3DayMinTemp;
    }

    public String getPlus3DayMaxTemp() {
        return plus3DayMaxTemp;
    }

    public void setPlus3DayMaxTemp(String plus3DayMaxTemp) {
        this.plus3DayMaxTemp = plus3DayMaxTemp;
    }

    public String getPlus4DayMinTemp() {
        return plus4DayMinTemp;
    }

    public void setPlus4DayMinTemp(String plus4DayMinTemp) {
        this.plus4DayMinTemp = plus4DayMinTemp;
    }

    public String getPlus4DayMaxTemp() {
        return plus4DayMaxTemp;
    }

    public void setPlus4DayMaxTemp(String plus4DayMaxTemp) {
        this.plus4DayMaxTemp = plus4DayMaxTemp;
    }

    public String getPlus5DayMinTemp() {
        return plus5DayMinTemp;
    }

    public void setPlus5DayMinTemp(String plus5DayMinTemp) {
        this.plus5DayMinTemp = plus5DayMinTemp;
    }

    public String getPlus5DayMaxTemp() {
        return plus5DayMaxTemp;
    }

    public void setPlus5DayMaxTemp(String plus5DayMaxTemp) {
        this.plus5DayMaxTemp = plus5DayMaxTemp;
    }

    String plus5DayMinTemp;
    String plus5DayMaxTemp;
    String timeoffset;



    public void setTimeoffset(String timeoffset) {
        this.timeoffset = timeoffset;
    }
    public String getTimeoffset() {
        return timeoffset;
    }



    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getWeatherStatus() {
        return WeatherStatus;
    }

    public void setWeatherStatus(String weatherStatus) {
        WeatherStatus = weatherStatus;
    }

    public String getCurrent_Temp() {
        return Current_Temp;
    }

    public void setCurrent_Temp(String current_Temp) {
        Current_Temp = current_Temp;
    }

    public String getMin_Temp() {
        return Min_Temp;
    }

    public void setMin_Temp(String min_Temp) {
        Min_Temp = min_Temp;
    }

    public String getMax_Temp() {
        return Max_Temp;
    }

    public void setMax_Temp(String max_Temp) {
        Max_Temp = max_Temp;
    }

    public String getPlus3Time() {
        return plus3Time;
    }

    public void setPlus3Time(String plus3Time) {
        this.plus3Time = plus3Time;
    }

    public String getPlus3Weather() {
        return plus3Weather;
    }

    public void setPlus3Weather(String plus3Weather) {
        this.plus3Weather = plus3Weather;
    }

    public String getPlus3Temp() {
        return plus3Temp;
    }

    public void setPlus3Temp(String plus3Temp) {
        this.plus3Temp = plus3Temp;
    }

    public String getPlus6Time() {
        return plus6Time;
    }

    public void setPlus6Time(String plus6Time) {
        this.plus6Time = plus6Time;
    }

    public String getPlus6Weather() {
        return plus6Weather;
    }

    public void setPlus6Weather(String plus6Weather) {
        this.plus6Weather = plus6Weather;
    }

    public String getPlus6Temp() {
        return plus6Temp;
    }

    public void setPlus6Temp(String plus6Temp) {
        this.plus6Temp = plus6Temp;
    }

    public String getPlus9Time() {
        return plus9Time;
    }

    public void setPlus9Time(String plus9Time) {
        this.plus9Time = plus9Time;
    }

    public String getPlus9Weather() {
        return plus9Weather;
    }

    public void setPlus9Weather(String plus9Weather) {
        this.plus9Weather = plus9Weather;
    }

    public String getPlus9Temp() {
        return plus9Temp;
    }

    public void setPlus9Temp(String plus9Temp) {
        this.plus9Temp = plus9Temp;
    }

    public String getPlus12Time() {
        return plus12Time;
    }

    public void setPlus12Time(String plus12Time) {
        this.plus12Time = plus12Time;
    }

    public String getPlus12Weather() {
        return plus12Weather;
    }

    public void setPlus12Weather(String plus12Weather) {
        this.plus12Weather = plus12Weather;
    }

    public String getPlus12Temp() {
        return plus12Temp;
    }

    public void setPlus12Temp(String plus12Temp) {
        this.plus12Temp = plus12Temp;
    }

    public String getPlus15Time() {
        return plus15Time;
    }

    public void setPlus15Time(String plus15Time) {
        this.plus15Time = plus15Time;
    }

    public String getPlus15Weather() {
        return plus15Weather;
    }

    public void setPlus15Weather(String plus15Weather) {
        this.plus15Weather = plus15Weather;
    }

    public String getPlus15Temp() {
        return plus15Temp;
    }

    public void setPlus15Temp(String plus15Temp) {
        this.plus15Temp = plus15Temp;
    }

    public String getPlus18Time() {
        return plus18Time;
    }

    public void setPlus18Time(String plus18Time) {
        this.plus18Time = plus18Time;
    }

    public String getPlus18Weather() {
        return plus18Weather;
    }

    public void setPlus18Weather(String plus18Weather) {
        this.plus18Weather = plus18Weather;
    }

    public String getPlus18Temp() {
        return plus18Temp;
    }

    public void setPlus18Temp(String plus18Temp) {
        this.plus18Temp = plus18Temp;
    }

    public String getPlus21Time() {
        return plus21Time;
    }

    public void setPlus21Time(String plus21Time) {
        this.plus21Time = plus21Time;
    }

    public String getPlus21Weather() {
        return plus21Weather;
    }

    public void setPlus21Weather(String plus21Weather) {
        this.plus21Weather = plus21Weather;
    }

    public String getPlus21Temp() {
        return plus21Temp;
    }

    public void setPlus21Temp(String plus21Temp) {
        this.plus21Temp = plus21Temp;
    }

    public String getPlus24Time() {
        return plus24Time;
    }

    public void setPlus24Time(String plus24Time) {
        this.plus24Time = plus24Time;
    }

    public String getPlus24Weather() {
        return plus24Weather;
    }

    public void setPlus24Weather(String plus24Weather) {
        this.plus24Weather = plus24Weather;
    }

    public String getPlus24Temp() {
        return plus24Temp;
    }

    public void setPlus24Temp(String plus24Temp) {
        this.plus24Temp = plus24Temp;
    }

    public String getPlus1Day() {
        return plus1Day;
    }

    public void setPlus1Day(String plus1Day) {
        this.plus1Day = plus1Day;
    }

    public String getPlus1Dayweather() {
        return plus1Dayweather;
    }

    public void setPlus1Dayweather(String plus1Dayweather) {
        this.plus1Dayweather = plus1Dayweather;
    }



    public String getPlus2Day() {
        return plus2Day;
    }

    public void setPlus2Day(String plus2Day) {
        this.plus2Day = plus2Day;
    }

    public String getPlus2Dayweather() {
        return plus2Dayweather;
    }

    public void setPlus2Dayweather(String plus2Dayweather) {
        this.plus2Dayweather = plus2Dayweather;
    }



    public String getPlus3Day() {
        return plus3Day;
    }

    public void setPlus3Day(String plus3Day) {
        this.plus3Day = plus3Day;
    }

    public String getPlus3Dayweather() {
        return plus3Dayweather;
    }

    public void setPlus3Dayweather(String plus3Dayweather) {
        this.plus3Dayweather = plus3Dayweather;
    }



    public String getPlus4Day() {
        return plus4Day;
    }

    public void setPlus4Day(String plus4Day) {
        this.plus4Day = plus4Day;
    }

    public String getPlus4Dayweather() {
        return plus4Dayweather;
    }

    public void setPlus4Dayweather(String plus4Dayweather) {
        this.plus4Dayweather = plus4Dayweather;
    }



    public String getPlus5Day() {
        return plus5Day;
    }

    public void setPlus5Day(String plus5Day) {
        this.plus5Day = plus5Day;
    }

    public String getPlus5Dayweather() {
        return plus5Dayweather;
    }

    public void setPlus5Dayweather(String plus5Dayweather) {
        this.plus5Dayweather = plus5Dayweather;
    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
