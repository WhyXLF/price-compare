package com.neuq.flight.grab.entity.qunar;

import lombok.Data;

import java.util.List;

/**
 * author: xiaoliufu
 * date:   2016/12/31.
 * description:
 */
@Data
public class QunarTrip {
    private String code;
    private String flightCode;
    private String depCityCode;
    private String C;
    private int crossDays;
    private int duration;
    private List<QunarSegment> flightSegments;
    private List<QunarTransInfo> transInfos;
}
