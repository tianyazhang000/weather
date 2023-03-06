package com.tyz.pojo;

import lombok.Data;

@Data
public class WeatherEntity {


    private String cityDate;

    private String city;
    private String date;
    private String sunrise;
    private String high;
    private String low;
    private String sunset;
    private String aqi;
    private String fx;
    private String fl;
    private String type;
    private String notice;

}
