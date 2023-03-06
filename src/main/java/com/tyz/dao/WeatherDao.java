package com.tyz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tyz.pojo.WeatherEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherDao extends BaseMapper<WeatherEntity> {

    List<WeatherEntity> findAllByCity(String cityName);

    void deleteAllByCity(String cityName);

}
