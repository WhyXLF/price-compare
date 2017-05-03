package com.neuq.flight.grab.entity.qunar;

import lombok.Data;

/**
 * author: xiaoliufu
 * date:   2016/12/31.
 * description:
 */
@Data
public class QunarFlightInfo {
    private QunarJourney journey;
    private QunarPrice price;
    private int rankLine;
    private double rank;
    private String tag;
}
