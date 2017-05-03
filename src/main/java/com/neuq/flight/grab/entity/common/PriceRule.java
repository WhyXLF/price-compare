package com.neuq.flight.grab.entity.common;

import lombok.Data;

/**
 * author: xiaoliufu
 * date:   2017/1/24.
 * description:
 */
@Data
public class PriceRule {
    // 退票
    private String refund;

    // 改签
    private String endorse;

    // 行李
    private String baggage;

    // 其它
    private String other;

}
