package com.neuq.flight.grab.constant.ctrip;

/**
 * @author: cuibingqi.
 * @date: 17/2/11 下午2:38.
 * @descripe: 抓包请求的url
 */
public class UrlConstants {

    public static final String CTRIP_INTERNAL_OW_PAGE_URL = "http://flights.ctrip.com/international/%s-%s-%s-%s?%s&y_s";
    public static final String CTRIP_INTERNAL_RT_PAGE_URL = "http://flights.ctrip.com/international/%s-%s-%s-%s?%s&%s&y_s";

    public static final String CTRIP_INTERNAL_CITY_INFO_URL = "http://flights.ctrip.com/international/tools/poi.ashx?charset=utf-8&key=%s&channel=1&mode=1&f=1&v=2";
}
