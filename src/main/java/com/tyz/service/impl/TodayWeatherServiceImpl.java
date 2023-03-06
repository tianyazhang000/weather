package com.tyz.service.impl;

import com.tyz.dao.TodayWeatherDao;
import com.tyz.pojo.TodayWeatherEntity;
import com.tyz.service.TodayWeatherService;
import org.springframework.stereotype.Service;

@Service
public class TodayWeatherServiceImpl implements TodayWeatherService {

    private TodayWeatherDao todayWeatherDao;

    @Override
    public TodayWeatherEntity getTodayWeather(String cityName) {
        TodayWeatherEntity weather;
        try {
            weather = todayWeatherDao.selectById(cityName);
            if(weather == null){
                System.out.println("未找到该城市");
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return weather;
    }

    @Override
    public void addTodayWeather(TodayWeatherEntity todayWeatherEntity) {
        try {
            if(todayWeatherEntity != null){
                todayWeatherDao.insert(todayWeatherEntity);
            } else {
                System.out.println("error");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteTodayWeather(String cityName) {
        try {
            todayWeatherDao.deleteByCity(cityName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateTodayWeather(TodayWeatherEntity todayWeatherEntity) {
        try {
            todayWeatherDao.updateById(todayWeatherEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
