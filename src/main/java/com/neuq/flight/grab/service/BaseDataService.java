package com.neuq.flight.grab.service;

import com.alibaba.fastjson.JSON;
import com.neuq.flight.grab.constant.ctrip.UrlConstants;
import com.neuq.flight.grab.entity.basedata.CityInfoResponse;
import com.neuq.flight.grab.utils.http.HttpAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

/**
 * author: xiaoliufu
 * date:   2017/5/1.
 * description: 获取城市基础数据信息
 */
@Slf4j
@Service
public class BaseDataService {
    /**
     * 请求城市基础数据
     *
     * @param key key
     * @return CityInfoResponse
     */
    private CityInfoResponse queryBaseData(String key) {
        HttpAgent httpAgent = HttpAgent.create();
        String url = String.format(UrlConstants.CTRIP_INTERNAL_CITY_INFO_URL, key);
        try {
            String jsonResult = httpAgent.doGet(url).split("=")[1];
            return JSON.parseObject(jsonResult, CityInfoResponse.class);
        } catch (IOException e) {
            log.error("queryBaseData error ",e);
            try {
                String jsonResult = httpAgent.doGet(url).split("=")[1];
                return JSON.parseObject(jsonResult, CityInfoResponse.class);
            } catch (IOException e1) {
                log.error("queryBaseData error ",e);
            }
        }
        return null;
    }

    /**
     * 获取城市英文名称
     *
     * @param cityCode 城市三字码
     * @return 城市英文名称
     */
    public Optional<String> getCityEName(String cityCode) {
        Optional<String> stringOptional = Optional.empty();
        CityInfoResponse cityInfoResponse = queryBaseData(cityCode);
        if (cityInfoResponse != null) {
            stringOptional = Optional.of(cityInfoResponse.getData().get(0).getEName().toLowerCase().replace(" ", "").trim());
        }
        return stringOptional;
    }

}
