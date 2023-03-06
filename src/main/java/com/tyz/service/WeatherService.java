package com.tyz.service;

import com.tyz.pojo.WeatherEntity;

import java.util.List;

public interface WeatherService {

    public List<WeatherEntity> getWeatherList(String cityName);

    public void addWeatherList(List<WeatherEntity> weathers);

    public void deleteWeatherList(String cityName);

}
