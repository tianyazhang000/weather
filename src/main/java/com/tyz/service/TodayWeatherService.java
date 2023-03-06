package com.tyz.service;

import com.tyz.pojo.TodayWeatherEntity;

public interface TodayWeatherService {
    public TodayWeatherEntity getTodayWeather(String cityName);

    public void addTodayWeather(TodayWeatherEntity todayWeatherEntity);

    public void deleteTodayWeather(String cityName);

    public void updateTodayWeather(TodayWeatherEntity todayWeatherEntity);
}
