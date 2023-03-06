package com.tyz.service.impl;

import com.tyz.dao.WeatherDao;
import com.tyz.pojo.WeatherEntity;
import com.tyz.service.WeatherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {

    private WeatherDao weatherDao;


    @Override
    public List<WeatherEntity> getWeatherList(String cityName) {
        return weatherDao.findAllByCity(cityName);
    }

    @Override
    public void addWeatherList(List<WeatherEntity> weathers) {
        for(WeatherEntity w : weathers){
            weatherDao.insert(w);
        }
    }

    @Override
    public void deleteWeatherList(String cityName) {
        weatherDao.deleteAllByCity(cityName);
    }
}
