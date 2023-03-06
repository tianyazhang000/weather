package com.tyz.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tyz.pojo.TodayWeatherEntity;
import com.tyz.pojo.WeatherEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.Vector;

@Component
public class ResultWeather {

    private JsonNode json = null;
    private final String url = "http://api.k780.com/?app=weather.realtime&cityNm=";
    private boolean getJsonData(String city){
        System.out.println("getJsonData中的city:=" + city);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url + city + "&ag=today,futureDay,lifeIndex,futureHour&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json",String.class);
        if(response.getStatusCodeValue() == 200){
            String strBody = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                json = objectMapper.readTree(strBody);
                return true;
            } catch (JsonProcessingException e){
                System.out.println("json初始化失败");
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public TodayWeatherEntity getTodayWeather(String city){
        if(json == null){
            boolean judge = getJsonData(city);
            if(!judge){
                return null;
            }
        }
        TodayWeatherEntity weather = new TodayWeatherEntity();
        try{
            weather.setShidu(data.get("shidu").toString());
            weather.setGanmao(data.get("ganmao").toString());

            weather.setPm10(data.get("pm10").toString());
            weather.setPm25(data.get("pm25").toString());
            weather.setQuality(data.get("quality").toString());
            weather.setWendu(data.get("wendu").toString());

            String date="";
            Calendar now= Calendar.getInstance();
            date=date+now.get(Calendar.YEAR)+(now.get(Calendar.MONTH)+1)+now.get(Calendar.DATE);
            System.out.println("日期为： "+date);
            weather.setDate(date);
            System.out.println("城市为："+city);
            weather.setCity(city);

            String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
            Calendar n = Calendar.getInstance();
            int index= n.get(Calendar.DAY_OF_WEEK)-1;
            weather.setWeek(weeks[index]);
            System.out.println("星期为："+weather.getWeek());

            weather.setCity(city);
            System.out.println("city为："+weather.getCity());

            weather.setCityDate(city+date);
            System.out.println("cityAndDate为："+weather.getCityDate());
        } catch (Exception e){
            System.out.println("获取当天天气情况失败");
            e.printStackTrace();
            return null;
        }
        return weather;
    }


    private WeatherEntity returnWth(JsonNode deal, String city) throws Exception {
        WeatherEntity result = new WeatherEntity();

        String date="";
        Calendar now= Calendar.getInstance();
        date=date+now.get(Calendar.YEAR)+(now.get(Calendar.MONTH)+1)+now.get(Calendar.DATE);
        // System.out.println("日期为： "+date);
        result.setDate(date);
        result.setCity(city);

        result.setCityDate(city+date);
        // System.out.println("weather: "+ result.getCityDate());

        result.setAqi(deal.get("aqi").toString());
        result.setDate(deal.get("date").toString());
        result.setFl(deal.get("fl").toString());
        result.setFx(deal.get("fx").toString());
        result.setHigh(deal.get("high").toString());
        result.setLow(deal.get("low").toString());
        result.setNotice(deal.get("notice").toString());
        result.setSunrise(deal.get("sunrise").toString());
        result.setSunset(deal.get("sunset").toString());
        result.setType(deal.get("type").toString());
        return result;
    }

    public Vector<WeatherEntity> getWeatherList(String city){
        if(json == null) {
            boolean judge = getJsonData(city);
            if(!judge)
                return null;
        }

        Vector<WeatherEntity>  result = new Vector<>();
        try{
            JsonNode yd = json.get("data").get("yesterday");
            WeatherEntity yesterday = returnWth(yd,city);
            WeatherEntity forecast;
            result.add(yesterday);
            JsonNode fc = json.get("data").get("forecast");
            for(int i = 0;i < 5;i++) {
                //给returnWth的fc.get(i) 只是 forecast部分
                forecast= returnWth(fc.get(i),city);
                result.add(forecast);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

}
