package com.tyz.controller;

import com.tyz.common.ResultWeather;
import com.tyz.pojo.TodayWeatherEntity;
import com.tyz.pojo.WeatherEntity;
import com.tyz.service.TodayWeatherService;
import com.tyz.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private ResultWeather deal;
    @Autowired
    private TodayWeatherService todayWeatherService;
    @Autowired
    private WeatherService weatherService;

    @GetMapping(value = "/{city}")
    public String getWeatherInfo(@PathVariable("city") String city, Model model){

        String date="";
        Calendar now= Calendar.getInstance();
        date= date + now.get(Calendar.YEAR) + (now.get(Calendar.MONTH) + 1) + now.get(Calendar.DATE);

        //若是没有建立表格，这个操作有问题！！   手动建立一个表格，让其存在
        System.out.println("getWeatherInfo中的cityAndDate1: = " + city + date);
//        TodayWeatherEntity weather = todayWeatherService.getTodayWeather(city + date);
        TodayWeatherEntity weather = null;
        System.out.println("getWeatherInfo中的cityAndDate2: = "+city + date);
//        List<WeatherEntity> weatherList = weatherService.getWeatherList(city + date);
        List<WeatherEntity> weatherList = null;

        //也就是 在数据库中 没有 该数据
        if(weather == null) {
            ResultWeather deal = new ResultWeather();
            TodayWeatherEntity weatherToday = deal.getTodayWeather(city);
            List<WeatherEntity> weatherSet= deal.getWeatherList(city);
            weatherService.addWeatherList(weatherSet);
            if(weatherToday != null)
            {
                model.addAttribute("TodayWth",weatherToday);
                model.addAttribute("WthSet",weatherSet);
                todayWeatherService.addTodayWeather(weatherToday);
            }
            else {
                System.out.print("设置error");
                model.addAttribute("error","查询次数过快或城市名称不存在");
            }
        }
        //如果在数据库中存在，就直接读取数据库中的数据
        else{
            System.out.println("从数据库中直接读取的数据: "+city+date+" 的天气");
            model.addAttribute("TodayWth",weather);
            model.addAttribute("WthSet",weatherList);
        }
        //返回weather页面
        return "weather";
    }

}
