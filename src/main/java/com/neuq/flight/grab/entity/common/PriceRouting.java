package com.neuq.flight.grab.entity.common;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * author: xiaoliufu
 * date:   2017/1/24.
 * description: 报价航线
 */
@Data
public class PriceRouting {
    private List<Integer> prices;
    private int tax;
    private int priceType;
    private PriceRule rule;
    private List<PriceSegment> fromSegments = Lists.newArrayList();
    private List<PriceSegment> retSegments = Lists.newArrayList();
    private String currency;

}
