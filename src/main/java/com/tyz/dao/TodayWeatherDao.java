package com.tyz.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tyz.pojo.TodayWeatherEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TodayWeatherDao extends BaseMapper<TodayWeatherEntity> {

    TodayWeatherEntity findByCity(String cityName);

    void deleteByCity(String cityName);

}
