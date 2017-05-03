package com.neuq.flight.grab.entity.qunar;

import lombok.Data;

/**
 * author: xiaoliufu
 * date:   2016/12/29.
 * description:
 */
@Data
public class QunarCtrInfo {
    private int num;
    private int total;
    private String sort;
    private String searchPercent;
    private String queryId;
    private int interval;
    private QunarCityAndCountryInfo dep;
    private QunarCityAndCountryInfo arr;
    private int packShowCount;
    private int wayShowCount;
    private int qfteShowCount;
    private boolean completed;
}

