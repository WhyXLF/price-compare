package com.neuq.flight.grab.utils.builders;

import com.neuq.flight.grab.constant.ctrip.UrlConstants;
import com.neuq.flight.grab.constant.enumType.TripType;
import com.neuq.flight.grab.service.BaseDataService;
import lombok.Builder;
import lombok.Data;

import java.util.Optional;

/**
 * author: xiaoliufu
 * date:   2017/5/1.
 * description:
 */

@Data
@Builder
public class CtripUrlBuilder {

    private BaseDataService baseDataService;
    private String toDate;
    private String retDate;
    private String fromCityCode;
    private String toCityCode;
    private int tripType;

    /**
     * 生成ctrip url
     *
     * @return optional url
     */
    public Optional<String> getCtripUrl() {
        Optional<String> stringOptional = Optional.empty();
        Optional<String> cityENameFromOptional = baseDataService.getCityEName(fromCityCode);
        Optional<String> cityENameToOptional = baseDataService.getCityEName(toCityCode);
        if (cityENameFromOptional.isPresent() && cityENameToOptional.isPresent()) {
            String fromCityEName = cityENameFromOptional.get();
            String toCityEName = cityENameToOptional.get();
            if (tripType == TripType.OW.getTripType()) {
                String format = String.format(UrlConstants.CTRIP_INTERNAL_OW_PAGE_URL, fromCityEName, toCityEName, fromCityCode, toCityCode, toDate);
                stringOptional = Optional.ofNullable(format);
            }
            if (tripType == TripType.RT.getTripType()) {
                String format = String.format(UrlConstants.CTRIP_INTERNAL_RT_PAGE_URL, fromCityEName, toCityEName, fromCityCode, toCityCode, toDate, retDate);
                stringOptional = Optional.ofNullable(format);
            }
        }
        return stringOptional;
    }
}
