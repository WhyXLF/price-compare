package com.neuq.flight.grab.entity.qunar;

import lombok.Data;

/**
 * author: xiaoliufu
 * date:   2016/12/31.
 * description:
 */
@Data
public class QunarSegment {
    private String code;
    private String flightCode;
    private String depAirportCode;
    private String depAirportName;
    private String depCityName;
    private String depCityCode;
    private String arrAirportCode;
    private String arrAirportName;
    private String arrCityName;
    private String arrCityCode;
    private String depTerminal;
    private String arrTerminal;
    private String depDate;
    private String depTime;
    private String arrDate;
    private String arrTime;
    private String carrierCode;
    private String carrierShortName;
    private String carrierFullName;
    private String planeTypeCode;
    private String planeTypeName;
    private int codeShareStatus;
    private String mainCode;
    private String mainCarriercode;
    private String mainCarrierShortName;
    private String mainCarrierFullName;
    private String stops;
    private String stopInfos;
    private String domsticSeg;
    private int duration;
    private int crossDays;
    private String source;
}
