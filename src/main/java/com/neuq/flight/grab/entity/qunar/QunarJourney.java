package com.neuq.flight.grab.entity.qunar;

import lombok.Data;

import java.util.List;

/**
 * author: xiaoliufu
 * date:   2016/12/31.
 * description:
 */
@Data
public class QunarJourney {
    private String code;
    private String flightCode;
    private List<QunarTrip> trips;
    private int transferIndex;
    private boolean ticketInsufficient;
    private String cabinLevel;
    private String journeyType;
    private String journeyGenerate;
    private String duration;
    private String depCityCode;
    private String arrCityCode;
}
