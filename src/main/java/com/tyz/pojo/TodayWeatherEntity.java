package com.tyz.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("today_weather")
public class TodayWeatherEntity {

    private String wtBlueSkyId;
    private String wtId1;
    private String wtId2;
    private String wtNm1;
    private String wtNm2;
    private String wtIcon1;
    private String wtIcon2;
    private String wtTemp1;
    private String wtTemp2;
    private String wtWindId1;
    private String wtWindId2;
    private String wtWindNm1;
    private String wtWindNm2;
    private String wtWinpId1;
    private String wtWinpId2;
    private String wtWinpNm1;
    private String wtWinpNm2;
    private String wtSunr;
    private String wtSuns;

}
