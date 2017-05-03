package com.neuq.flight.grab.entity.qunar;

import lombok.Data;

import java.util.Map;

/**
 * author: xiaoliufu
 * date:   2016/12/29.
 * description:
 */
@Data
public class QunarResult {
    private QunarCtrInfo ctrlInfo;

    private String fuzzyInfo;
    private Map<String,QunarFlightInfo> flightPrices;

}
